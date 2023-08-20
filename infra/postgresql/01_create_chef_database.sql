CREATE SCHEMA chefs;

CREATE TABLE chefs."tb_chef" (
    "id" SERIAL NOT NULL,
    "name" VARCHAR(250) NOT NULL,
    "description" VARCHAR(250) NOT NULL,
    "created_at" TIMESTAMP,
    CONSTRAINT "tb_chef_pk" PRIMARY KEY ("id")
);

CREATE TABLE chefs."tb_order" (
    "id" INT NOT NULL,
    "created_at" TIMESTAMP,
    CONSTRAINT "tb_order_pk" PRIMARY KEY ("id")
);

CREATE TABLE chefs."tb_order_item" (
    "id" INT NOT NULL,
    "product_id" varchar NOT NULL,
    "name" VARCHAR(250) NOT NULL,
    "description" VARCHAR(250) NOT NULL,
    "created_at" TIMESTAMP,
    "order_id" INT NOT NULL,
    CONSTRAINT "tb_order_item_pk" PRIMARY KEY ("id"),
    CONSTRAINT "tb_order_fk" FOREIGN KEY ("order_id") REFERENCES chefs."tb_order" ("id")
);

CREATE TABLE chefs."tb_chef_order" (
    "id" SERIAL NOT NULL,
    "prepared_by" INT NOT NULL,
    "order_prepared" INT NOT NULL,
    "status" VARCHAR(250) NOT NULL,
    "created_at" TIMESTAMP,
    "updated_at" TIMESTAMP,
    CONSTRAINT "tb_chef_order_pk" PRIMARY KEY ("id"),
    CONSTRAINT "tb_chef_fk" FOREIGN KEY ("prepared_by") REFERENCES chefs."tb_chef" ("id"),
    CONSTRAINT "tb_order_fk" FOREIGN KEY ("order_prepared") REFERENCES chefs."tb_order" ("id")
);