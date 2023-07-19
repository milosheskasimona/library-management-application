CREATE SCHEMA IF NOT EXISTS libraryappsh;

CREATE TABLE libraryappsh.author (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    dateOfBirth DATE
);

CREATE TABLE libraryappsh.client (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    dateOfBirth DATE
);
CREATE TABLE libraryappsh.book (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    authorId INT REFERENCES libraryappsh.author(id),
    dateOfPublishing DATE,
    numberOfCopies INT
);

CREATE TABLE libraryappsh.user (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE libraryappsh.role (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE libraryappsh.user_role (
  user_id INT,
  role_id INT,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES libraryappsh.user(id),
  FOREIGN KEY (role_id) REFERENCES libraryappsh.role (id)
);

CREATE TABLE libraryappsh.order (
  id SERIAL PRIMARY KEY,
  client_id INT NOT NULL,
  issue_date DATE,
  due_date DATE,
  FOREIGN KEY (client_id) REFERENCES libraryappsh.client (id)
);

CREATE TABLE libraryappsh.order_book (
  order_id INT,
  book_id INT,
  PRIMARY KEY (order_id, book_id),
  FOREIGN KEY (order_id) REFERENCES libraryappsh.order(id),
  FOREIGN KEY (book_id) REFERENCES libraryappsh.book (id)
);
