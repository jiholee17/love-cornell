import json
from flask import Flask, request
from db import db, Letter

import datetime
import users_dao

app = Flask(__name__)
db_filename = "data.db"

app.config["SQLALCHEMY_DATABASE_URI"] = "sqlite:///%s" % db_filename
app.config["SQLALCHEMY_TRACK_MODIFICATIONS"] = False
app.config["SQLALCHEMY_ECHO"] = True

db.init_app(app)
with app.app_context():
    db.create_all()

# generalized response formats -------------------------------------------------
def success_response(data, code=200):
    return json.dumps(data), code

def failure_response(message, code=404):
    return json.dumps({"error": message}), code

def extract_token(request):
    """
    Helper function that extracts the token from the header of a request
    """
    auth_header = request.headers.get("Authorization")
    if auth_header is None:
        return False, failure_response("Missing authorization")

    bearer_token = auth_header.replace("Bearer ", "").strip()
    if bearer_token is None or not bearer_token:
        return False, failure_response("Invalid authorization")
    
    return True, bearer_token

# USER ROUTES ------------------------------------------------------------------

@app.route("/register/", methods=["POST"])
def register_account():
    """
    Endpoint for registering a user's account
    """
    body = json.loads(request.data)
    email = body.get("email")
    password = body.get("password")

    if email is None or password is None:
        return failure_response("Missing email or password.", 400)
    
    success, user = users_dao.create_user(email, password)

    if not success:
        return failure_response("User already exists.", 400)

    return success_response(
        {
            "session_token": user.session_token,
            "session_expiration": str(user.session_expiration),
            "update_token": user.update_token
        }
    )

@app.route("/login/", methods=["POST"])
def login():
    """
    Endpoint for logging a user in
    """
    body = json.loads(request.data)
    email = body.get("email")
    password = body.get("password")

    if email is None or password is None:
        return failure_response("Missing email or password.", 400)

    success, user = users_dao.verify_credentials(email, password)

    if not success:
        return failure_response("Incorrect email or password.", 401)

    return success_response(
        {
            "session_token": user.session_token,
            "session_expiration": str(user.session_expiration),
            "update_token": user.update_token
        }
    )

@app.route("/session/", methods=["POST"])
def update_session():
    """
    Endpoint for updating a user's session
    """
    success, update_token = extract_token(request)

    if not success:
        return failure_response("Could not extract session token.", 400)

    success_user, user = users_dao.renew_session(update_token)

    if not success_user:
        return failure_response("Invalid update token.", 400)

    return success_response(
        {
            "session_token": user.session_token,
            "session_expiration": str(user.session_expiration),
            "update_token": user.update_token
        }
    )

@app.route("/logout/", methods=["POST"])
def logout():
    """
    Endpoint for logging out a user
    """
    success, session_token = extract_token(request)

    if not success:
        return failure_response("Could not extract session token.", 400)

    user = users_dao.get_user_by_session_token(session_token)
    
    if user is None or not user.verify_session_token(session_token):
        return failure_response("Invalid session token.", 400)

    user.session_token = ""
    user.session_expiration = datetime.datetime.now()
    user.update_token = ""

    return success_response({"message": "You have successfully logged out."})


# LETTER ROUTES ----------------------------------------------------------------

@app.route("/letters/")
def get_all_letters():
  """
  Endpoint for getting all letters
  """
  letters = {"letters": [l.serialize() for l in Letter.query.all()]}
  return success_response(letters)

@app.route("/letters/", methods=["POST"])
def post_letter():
    """
    Endpoint for posting a letter
    """
    body = json.loads(request.data)
    receiver = body.get('receiver')
    content = body.get('content')
    color = body.get('color')

    if receiver is None or content is None or color is None:
        return failure_response("Required fields missing.", 400)

    new_letter = Letter(
        receiver = receiver,
        content = content,
        color = color
    )

    db.session.add(new_letter)
    db.session.commit()
    return success_response(new_letter.serialize(), 201)

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8000, debug=True)