CREATE TABLE user_login
(
    id         SERIAL PRIMARY KEY,
    login_name VARCHAR(256) NOT NULL UNIQUE,
    password   VARCHAR(256) NOT NULL CHECK (length(password) > 8),
    email      VARCHAR(256) NOT NULL UNIQUE
);
CREATE TABLE user_role
(
    id        SERIAL PRIMARY KEY,
    role_name VARCHAR(40) NOT NULL UNIQUE
);
CREATE TABLE system_user
(
    id              SERIAL PRIMARY KEY,
    first_name      VARCHAR(256) NOT NULL,
    last_name       VARCHAR(256) NOT NULL,
    login_id        INT          NOT NULL,
    passport_number VARCHAR(9) UNIQUE,
    phone_number    VARCHAR(12)  NOT NULL,
    address         VARCHAR(256),
    birthday        DATE,
    role_id         INT          NOT NULL,
    FOREIGN KEY (login_id) REFERENCES user_login (id) ON DELETE RESTRICT,
    FOREIGN KEY (role_id) REFERENCES user_role (id)
);

CREATE TABLE book
(
    id                 SERIAL PRIMARY KEY,
    address_in_storage VARCHAR(256) NOT NULL,
    name               VARCHAR(256) NOT NULL,
    language           VARCHAR(150) NOT NULL,
    volume             INT DEFAULT 1

);

CREATE TABLE genre
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(256) NOT NULL UNIQUE,
    description VARCHAR(256)
);

CREATE TABLE book_genre
(
    id       SERIAL PRIMARY KEY,
    book_id  INT NOT NULL,
    genre_id INT NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genre (id) ON DELETE CASCADE
);
CREATE TABLE author
(
    id          SERIAL PRIMARY KEY,
    first_name  VARCHAR(256) NOT NULL,
    middle_name VARCHAR(256),
    last_name   VARCHAR(256),
    sex         VARCHAR(6)   NOT NULL,
    birthday    DATE
);

CREATE TABLE book_author
(
    id        SERIAL PRIMARY KEY,
    book_id   INT NOT NULL,
    author_id INT NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES author (id) ON DELETE CASCADE
);
CREATE TABLE composition_comment
(
    id           SERIAL PRIMARY KEY,
    user_id      INT          NOT NULL,
    book_id      INT          NOT NULL,
    comment      VARCHAR(300) NOT NULL,
    comment_date DATE         NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book (id),
    FOREIGN KEY (user_id) REFERENCES system_user (id)
);
CREATE TABLE item_status
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL UNIQUE
);
CREATE TABLE item
(
    id             SERIAL PRIMARY KEY,
    publish_year   INT          NOT NULL,
    publish_office VARCHAR(256) NOT NULL,
    book_id        INT          NOT NULL,
    item_status_id INT          NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE,
    FOREIGN KEY (item_status_id) REFERENCES item_status (id) ON DELETE RESTRICT
);
CREATE TABLE status_comment
(
    id           SERIAL PRIMARY KEY,
    user_id      INT          NOT NULL,
    item_id      INT          NOT NULL,
    comment      VARCHAR(300) NOT NULL,
    comment_date DATE         NOT NULL,
    FOREIGN KEY (item_id) REFERENCES item (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES system_user (id)
);
CREATE TABLE order_status
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL UNIQUE
);
CREATE TABLE user_order
(
    id              SERIAL PRIMARY KEY,
    user_id         INT          NOT NULL,
    item_id         INT          NOT NULL,
    address         VARCHAR(256) NOT NULL,
    order_date      DATE         NOT NULL,
    return_date     DATE         NOT NULL,
    order_status_id INT          NOT NULL,
    FOREIGN KEY (item_id) REFERENCES item (id),
    FOREIGN KEY (user_id) REFERENCES system_user (id),
    FOREIGN KEY (order_status_id) REFERENCES order_status (id)
);