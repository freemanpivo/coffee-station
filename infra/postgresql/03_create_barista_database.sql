CREATE SCHEMA baristas;

CREATE TABLE baristas."tb_barista" (
    "id" SERIAL NOT NULL,
    "name" VARCHAR(250) NOT NULL,
    "description" VARCHAR(250) NOT NULL,
    "created_at" TIMESTAMP,
    CONSTRAINT "tb_barista_pk" PRIMARY KEY ("id")
);

CREATE TABLE baristas."tb_order" (
    "id" INT NOT NULL,
    "created_at" TIMESTAMP,
    CONSTRAINT "tb_order_pk" PRIMARY KEY ("id")
);

CREATE TABLE baristas."tb_order_item" (
    "id" INT NOT NULL,
    "product_id" varchar NOT NULL,
    "name" VARCHAR(250) NOT NULL,
    "description" VARCHAR(250) NOT NULL,
    "created_at" TIMESTAMP,
    "order_id" INT NOT NULL,
    CONSTRAINT "tb_order_item_pk" PRIMARY KEY ("id"),
    CONSTRAINT "tb_order_fk" FOREIGN KEY ("order_id") REFERENCES baristas."tb_order" ("id")
);

CREATE TABLE baristas."tb_barista_order" (
    "id" SERIAL NOT NULL,
    "prepared_by" INT NOT NULL,
    "order_prepared" INT NOT NULL,
    "status" VARCHAR(250) NOT NULL,
    "created_at" TIMESTAMP,
    "updated_at" TIMESTAMP,
    CONSTRAINT "tb_barista_order_pk" PRIMARY KEY ("id"),
    CONSTRAINT "tb_barista_fk" FOREIGN KEY ("prepared_by") REFERENCES baristas."tb_barista" ("id"),
    CONSTRAINT "tb_order_fk" FOREIGN KEY ("order_prepared") REFERENCES baristas."tb_order" ("id")
);