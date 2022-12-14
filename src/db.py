import datetime
import hashlib
import os

import timeago
import bcrypt
from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

associate_users_with_favorites = db.Table(
  "associate_users_with_favorites",
  db.Column("user_id", db.Integer, db.ForeignKey("user.id")),
  db.Column("letter_id", db.Integer, db.ForeignKey("letter.id"))
)

class User(db.Model):
  """
  User model
  """
  __tablename__ = "user"
  id = db.Column(db.Integer, primary_key=True)
  email = db.Column(db.String, nullable=False, unique=True)
  password_digest = db.Column(db.String, nullable=False)
  favorites = db.relationship("Letter", secondary=associate_users_with_favorites, back_populates="users")
  drafts = db.relationship("Draft", cascade="delete")

  # Session information
  session_token = db.Column(db.String, nullable=False, unique=True)
  session_expiration = db.Column(db.DateTime, nullable=False)
  update_token = db.Column(db.String, nullable=False, unique=True)

  def __init__(self, **kwargs):
    """
    Initializes a User object
    """
    self.email = kwargs.get("email")
    self.password_digest = bcrypt.hashpw(kwargs.get("password").encode("utf8"), bcrypt.gensalt(rounds=13))
    self.renew_session()

  def _urlsafe_base_64(self):
    """
    Randomly generates hashed tokens (used for session/update tokens)
    """
    return hashlib.sha1(os.urandom(64)).hexdigest()

  def renew_session(self):
    """
    Renews the sessions, i.e.
    1. Creates a new session token
    2. Sets the expiration time of the session to be a day from now
    3. Creates a new update token
    """
    self.session_token = self._urlsafe_base_64()
    self.session_expiration = datetime.datetime.now() + datetime.timedelta(days=1)
    self.update_token = self._urlsafe_base_64()

  def verify_password(self, password):
    """
    Verifies the password of a user
    """
    return bcrypt.checkpw(password.encode("utf8"), self.password_digest)

  def verify_session_token(self, session_token):
    """
    Verifies the session token of a user
    """
    return session_token == self.session_token and datetime.datetime.now() < self.session_expiration

  def verify_update_token(self, update_token):
    """
    Verifies the update token of a user
    """
    return update_token == self.update_token

  def serialize(self):
    """
    Serializes a User object
    """
    return {
      "id": self.id,
      "email": self.email,
      "favorites": [l.serialize() for l in self.favorites],
      "drafts": [d.serialize() for d in self.drafts]
    }


class Letter(db.Model):
  """
  Letter model
  """
  __tablename__ = "letter"
  id = db.Column(db.Integer, primary_key=True, autoincrement=True)
  receiver = db.Column(db.String, nullable=False)
  sender = db.Column(db.String, nullable=False)
  content = db.Column(db.String, nullable=False)
  color = db.Column(db.String, nullable=False)
  timestamp = db.Column(db.DateTime, nullable=False)
  users = db.relationship("User", secondary=associate_users_with_favorites, back_populates="favorites")

  def serialize(self):
    """
    Serializes a Letter object
    """
    ago = timeago.format(self.timestamp)
    return {
      "id": self.id,
      "receiver": self.receiver,
      "sender": self.sender,
      "content": self.content,
      "color": self.color,
      "timestamp": ago
    }

class Draft(db.Model):
  """
  Draft model
  """
  __tablename__ = "draft"
  id = db.Column(db.Integer, primary_key=True, autoincrement=True)
  receiver = db.Column(db.String, nullable=False)
  sender = db.Column(db.String, nullable=False)
  content = db.Column(db.String, nullable=False)
  color = db.Column(db.String, nullable=False)
  user_id = db.Column(db.Integer, db.ForeignKey("user.id"), nullable=False)

  def serialize(self):
    """
    Serializes a Draft object
    """
    return {
      "id": self.id,
      "receiver": self.receiver,
      "sender": self.sender,
      "content": self.content,
      "color": self.color
    }