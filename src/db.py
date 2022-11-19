from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

class Letter(db.Model):
  """
  Letter model
  """
  __tablename__ = "letter"
  id = db.Column(db.Integer, primary_key=True, autoincrement=True)
  receiver = db.Column(db.String, nullable=False)
  content = db.Column(db.String, nullable=False)
  color = db.Column(db.String, nullable=False)

  def serialize(self):
    """
    Serializes a Letter object
    """
    return {
      "id": self.id,
      "receiver": self.receiver,
      "content": self.content,
      "color": self.color
    }