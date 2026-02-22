DROP TABLE task;

CREATE TABLE task(
    id SERIAL PRIMARY KEY,
    title VARCHAR(64) NOT NULL,
    description VARCHAR(128),
    is_finished boolean NOT NULL DEFAULT FALSE,
    date TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO task(title, description)
VALUES
    ('first', 'first description'),
    ('second', 'second description'),
    ('third', 'third description'),
    ('fourth', 'fourth description'),
    ('fifth', 'fifth description'),
    ('sixth', 'sixth description'),
    ('seventh', 'seventh description'),
    ('eight', 'eight description'),
    ('night', 'night description');