import json
from flask import Flask, request
from db import db, Letter

app = Flask(__name__)
db_filename = "data.db"

app.config["SQLALCHEMY_DATABASE_URI"] = "sqlite:///%s" % db_filename
app.config["SQLALCHEMY_TRACK_MODIFICATIONS"] = False
app.config["SQLALCHEMY_ECHO"] = True

db.init_app(app)
with app.app_context():
    db.create_all()

# generalized response formats
def success_response(data, code=200):
    return json.dumps(data), code

def failure_response(message, code=404):
    return json.dumps({"error": message}), code

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