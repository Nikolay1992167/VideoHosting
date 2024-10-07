--liquibase formatted sql
--changeset Minich_Nikolay:1
CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY,
    nickname VARCHAR(50)  NOT NULL UNIQUE,
    name     VARCHAR(50)  NOT NULL,
    email    VARCHAR(100) NOT NULL UNIQUE
);