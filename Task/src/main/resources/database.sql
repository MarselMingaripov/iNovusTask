--Table user
CREATE TABLE users(
  id SERIAL NOT NULL  PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
)