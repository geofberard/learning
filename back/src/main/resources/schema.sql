CREATE TABLE boxes
(
    id            VARCHAR(255) PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    position      INT          NOT NULL,
    interval_days INT          NOT NULL
);

CREATE TABLE categories
(
    id   VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE cards
(
    id                 VARCHAR(255) PRIMARY KEY,
    question           TEXT NOT NULL,
    answer             TEXT NOT NULL,
    box_id             VARCHAR(255),
    last_reviewed_date DATE,
    next_review_date   DATE,
    category_id        VARCHAR(255),
    FOREIGN KEY (box_id) REFERENCES boxes (id),
    FOREIGN KEY (category_id) REFERENCES categories (id)
);
