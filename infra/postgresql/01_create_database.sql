CREATE DATABASE products_db;

CREATE SCHEMA products;

CREATE TABLE products."tb_product" (
    "id" INT NOT NULL,
    "name" VARCHAR(250) NOT NULL,
    "description" VARCHAR(250) NOT NULL,
    CONSTRAINT "tb_products_pk" PRIMARY KEY ("id")
);