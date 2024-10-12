--liquibase formatted sql
--changeset Minich_Nikolay:5
INSERT INTO channels (title, description, author_id, created_at, main_language, avatar, category)
VALUES ('Cooking with Alice', 'Learn how to make delicious dishes with Alice', 1, '2023-01-01', 'EN', null, 'Food'),
       ('Охота', 'Канал о разной охоте', 2, '2023-01-02', 'RU', null, 'Adventure'),
       ('ТНТ', 'Развлечения и отдых', 3, '2023-01-03', 'RU', null, 'Comedy'),
       ('БТ>', 'Навiны', 4, '2023-01-04', 'BY', null, 'News'),
       ('Стиль', 'Канал о красоте и моде', 5, '2023-01-05', 'RU', null, 'Fashion'),
       ('Бабайка', 'Вершы и калыханки для малых', 6, '2023-01-06', 'BY', null, 'Childhood'),
       ('Еда для всех', 'Канал о еде разных времен и народов', 7, '2023-01-07', 'RU', null, 'Food'),
       ('24Техно', 'Современное кино', 8, '2023-01-08', 'RU', null, 'Films'),
       ('Культура', 'Канал об искусстве', 9, '2023-01-09', 'RU', null, 'Art'),
       ('Jack''s Adventure', 'Join Jack''s thrilling adventure stories', 10, '2023-01-10', 'EN', null, 'Adventure');