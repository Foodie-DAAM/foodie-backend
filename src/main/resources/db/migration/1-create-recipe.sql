-- DROP TABLE IF EXISTS recipe;
--
-- CREATE TABLE recipe
-- (
--     id              SERIAL
--         CONSTRAINT recipe_pk PRIMARY KEY,
--     url             VARCHAR NOT NULL,
--     title           VARCHAR NOT NULL,
--     ingredients     VARCHAR NOT NULL,
--     steps           VARCHAR NOT NULL,
--     nutrition_facts VARCHAR,
--     description     VARCHAR,
--     duration        INT,
--     servings        INT,
--     picture         VARCHAR
-- );
--
-- CREATE UNIQUE INDEX recipe_url_uindex ON recipe (url);