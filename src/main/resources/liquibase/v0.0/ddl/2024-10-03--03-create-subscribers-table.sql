--liquibase formatted sql
--changeset Minich_Nikolay:3
CREATE TABLE IF NOT EXISTS subscribers
(
    user_id    BIGINT REFERENCES users (id) ON DELETE CASCADE,
    channel_id BIGINT REFERENCES channels (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, channel_id)
);