CREATE TABLE task(
    id SERIAL PRIMARY KEY,
    title VARCHAR(64),
    description VARCHAR(128)
);

INSERT INTO task(title, description)
VALUES
    ('first', 'first description'),
    ('second', 'second description');