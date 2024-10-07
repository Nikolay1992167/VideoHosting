--liquibase formatted sql
--changeset Minich_Nikolay:2
CREATE TABLE IF NOT EXISTS channels
(
    id            BIGSERIAL PRIMARY KEY,
    title         VARCHAR(40)                  NOT NULL UNIQUE,
    description   VARCHAR(500)                 NOT NULL,
    author_id     BIGINT REFERENCES users (id) NOT NULL,
    created_at    DATE                         NOT NULL,
    main_language VARCHAR(20)                  NOT NULL,
    avatar        oid,
    category      VARCHAR(20)                  NOT NULL
);