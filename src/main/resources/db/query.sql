DROP TABLE task;

CREATE TABLE task(
    id SERIAL PRIMARY KEY,
    title VARCHAR(64) NOT NULL,
    description VARCHAR(128),
    is_finished boolean DEFAULT FALSE
);

INSERT INTO task(title, description)
VALUES
    ('first', 'first description'),
    ('second', 'second description');