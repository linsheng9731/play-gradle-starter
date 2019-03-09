# --- !Ups

CREATE TABLE "products" (
  "id" SERIAL NOT NULL PRIMARY KEY,
  "name" Text
);

INSERT INTO products (name) VALUES('Test Product');

# --- !Downs

DROP TABLE IF EXISTS products;
