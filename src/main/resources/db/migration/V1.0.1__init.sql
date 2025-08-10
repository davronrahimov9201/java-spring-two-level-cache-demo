CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE "public"."category" (
    "id" uuid DEFAULT uuid_generate_v4() NOT NULL,
    "name" varchar(128) NOT NULL,
    "description" text,
    "created_at" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY ("id")
);

INSERT INTO category (name, description)
SELECT
    'Category ' || i,
    'Description for category ' || i
    FROM generate_series(1, 50) s(i);

CREATE TABLE "public"."product" (
    "id" uuid DEFAULT uuid_generate_v4() NOT NULL,
    "category_id" uuid NOT NULL,
    "name" varchar(255) NOT NULL,
    "description" text,
    "price" decimal(10,2) NOT NULL,
    "stock_quantity" int4 NOT NULL DEFAULT 0,
    "created_at" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY ("id"),
    CONSTRAINT "product_category_id_fk1" FOREIGN KEY ("category_id") REFERENCES "public"."category" ("id") ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE INDEX "product_category_id_idx1" ON "public"."product" USING hash ("category_id");

INSERT INTO product (category_id, name, description, price, stock_quantity)
SELECT
    (SELECT id FROM category WHERE i = i ORDER BY RANDOM() LIMIT 1),
    'Product ' || i,
    'Description for product ' || i,
    (random() * 100)::NUMERIC(10, 2),
    (random() * 1000)::INT
FROM generate_series(1, 10000) s(i);