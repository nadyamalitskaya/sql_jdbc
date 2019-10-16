INSERT INTO user_login(login_name, password, email)
VALUES ('yourDestroyer', '1003624gr7n', 'dimaflower@gmail.com');
INSERT INTO user_login(login_name, password, email)
VALUES ('littleKitten', '738agags223', 'nastya@nure.ua');
INSERT INTO user_login(login_name, password, email)
VALUES ('phenyabutum', 'kjn22mmm34124', 'dmitrovasuk@gmail.com');
INSERT INTO user_login(login_name, password, email)
VALUES ('greymilk', '1927455lol', 'nadyakornienko@gmail.com');
INSERT INTO user_login(login_name, password, email)
VALUES ('rikki', 'azachem666azaza', 'olegkarpenko@nure.ua');

INSERT INTO user_role(role_name)
VALUES ('user');
INSERT INTO user_role(role_name)
VALUES ('admin');

INSERT INTO system_user(first_name, last_name, login_id, passport_number, phone_number, address, birthday, role_id)
VALUES ('Nadya', 'Malitskaya', 1, 'BT818343', '380960887931', 'Lenin str., 58', '09.10.1999', 1);
INSERT INTO system_user(first_name, last_name, login_id, passport_number, phone_number, address, birthday, role_id)
VALUES ('Nastya', 'Glazko', 2, 'BC562311', '380961123096', 'Risus St., 20', '08.20.1997', 1);
INSERT INTO system_user(first_name, last_name, login_id, passport_number, phone_number, address, birthday, role_id)
VALUES ('Dima', 'Vasuk', 3, 'BC578456', '380667655903', '1561 Duis Rd.', '05.14.1995', 2);
INSERT INTO system_user(first_name, last_name, login_id, passport_number, phone_number, address, birthday, role_id)
VALUES ('Nadya', 'Kornienko', 4, 'AT463359', '38066771653', '5470 Posuere Ave', '10.21.1999', 1);
INSERT INTO system_user(first_name, last_name, login_id, passport_number, phone_number, address, birthday, role_id)
VALUES ('Oleg', 'Karpenko', 5, 'BT954423', '380961418703', '7326 Elementum Rd.', '01.03.2003', 1);

INSERT INTO book(address_in_storage, name, language)
VALUES ('13472', 'The Great Gatsby', 'english');
INSERT INTO book(address_in_storage, name, language)
VALUES ('45334', 'Moby Dic', 'english');
INSERT INTO book(address_in_storage, name, language, volume)
VALUES ('13244', 'War and Peace', 'russian', 3);
INSERT INTO book(address_in_storage, name, language)
VALUES ('12397', 'Anna Karenina', 'russian');
INSERT INTO book(address_in_storage, name, language)
VALUES ('14524', 'The Iliad', 'english');
INSERT INTO book(address_in_storage, name, language)
VALUES ('15647', 'The Odyssey', 'english');
INSERT INTO book(address_in_storage, name, language)
VALUES ('33429', 'To the Lighthouse', 'english');
INSERT INTO book(address_in_storage, name, language)
VALUES ('45623', 'Wuthering Heights', 'deutsch');
INSERT INTO book(address_in_storage, name, language, volume)
VALUES ('40233', 'Atlas Shrugged', 'english', 3);
INSERT INTO book(address_in_storage, name, language)
VALUES ('40234', 'Atlas Shrugged', 'english');
INSERT INTO book(address_in_storage, name, language, volume)
VALUES ('40232', 'Atlas Shrugged', 'english', 2);
INSERT INTO book(address_in_storage, name, language)
VALUES ('48104', 'Gone With the Wind', 'english');

INSERT INTO genre (name, description)
VALUES ('Philosophical novel',
        'Discoursing the function and role of society, the purpose of life, ethics or morals, and etc.');
INSERT INTO genre (name, description)
VALUES ('Historical fiction', 'The plot takes place in a setting located in the past.');
INSERT INTO genre (name, description)
VALUES ('Epic poetry',
        'Involving a time beyond living memory in which occurred the extraordinary doings of the extraordinary men and women who, in dealings with the gods or other superhuman forces.');
INSERT INTO genre (name, description)
VALUES ('Novel', 'A long, fictional narrative which describes intimate human experiences.');
INSERT INTO genre (name, description)
VALUES ('Nautical fiction',
        'A setting on or near the sea, that focuses on the human relationship to the sea and sea voyages and highlights nautical culture in these environments.');
INSERT INTO genre (name, description)
VALUES ('Tragedy',
        'A form of drama based on human suffering that invokes an accompanying catharsis or pleasure in audiences.');
INSERT INTO genre (name, description)
VALUES ('Realist novel',
        'Attempts to represent familiar things as they are. Realist authors chose to depict everyday and banal activities and experiences.');
INSERT INTO genre (name, description)
VALUES ('Poetry', 'It is verbal art, mainly poetic');
INSERT INTO genre (name, description)
VALUES ('Gothic fiction',
        'A genre or mode of literature that combines fiction and horror, death, and at times romance');
INSERT INTO genre (name, description)
VALUES ('Fantasy', 'A fiction set in a fictional universe, often inspired by real world myth and folklore.');
INSERT INTO genre (name, description)
VALUES ('Utopian fiction',
        'Portrays the setting that agrees with the author''s ethos, having various attributes of another reality intended to appeal to readers.');
INSERT INTO genre (name, description)
VALUES ('Modernism',
        'Characterized by a very self-conscious break with traditional ways of writing, in both poetry and prose fiction.');

INSERT INTO book_genre(book_id, genre_id)
VALUES (1, 6);
INSERT INTO book_genre(book_id, genre_id)
VALUES (2, 4);
INSERT INTO book_genre(book_id, genre_id)
VALUES (2, 5);
INSERT INTO book_genre(book_id, genre_id)
VALUES (3, 2);
INSERT INTO book_genre(book_id, genre_id)
VALUES (4, 7);
INSERT INTO book_genre(book_id, genre_id)
VALUES (5, 3);
INSERT INTO book_genre(book_id, genre_id)
VALUES (5, 8);
INSERT INTO book_genre(book_id, genre_id)
VALUES (6, 3);
INSERT INTO book_genre(book_id, genre_id)
VALUES (7, 12);
INSERT INTO book_genre(book_id, genre_id)
VALUES (8, 6);
INSERT INTO book_genre(book_id, genre_id)
VALUES (8, 9);
INSERT INTO book_genre(book_id, genre_id)
VALUES (9, 1);
INSERT INTO book_genre(book_id, genre_id)
VALUES (10, 1);
INSERT INTO book_genre(book_id, genre_id)
VALUES (11, 1);
INSERT INTO book_genre(book_id, genre_id)
VALUES (12, 2);

INSERT INTO author(first_name, last_name, sex, birthday)
VALUES ('Margaret', 'Mitchell', 'female', '11.08.1990');
INSERT INTO author(first_name, sex)
VALUES ('Homer', 'male');
INSERT INTO author(first_name, middle_name, last_name, sex, birthday)
VALUES ('Francis', 'Scott', 'Fitzgerald', 'male', '10.24.1896');
INSERT INTO author(first_name, last_name, sex, birthday)
VALUES ('Leo', 'Tolstoy', 'male', '10.09.1828');
INSERT INTO author(first_name, middle_name, last_name, sex, birthday)
VALUES ('Emily', 'Jane', 'Bronte', 'female', '07.30.1818');
INSERT INTO author(first_name, middle_name, last_name, sex, birthday)
VALUES ('Virginia', 'Adeline', 'Woolf', 'female', '01.25.1882');
INSERT INTO author(first_name, last_name, sex, birthday)
VALUES ('Herman', 'Melville', 'male', '10.28.1891');
INSERT INTO author(first_name, last_name, sex, birthday)
VALUES ('Ayn', 'Rand', 'female', '02.02.1905');

INSERT INTO book_author(book_id, author_id)
VALUES (1, 3);
INSERT INTO book_author(book_id, author_id)
VALUES (2, 7);
INSERT INTO book_author(book_id, author_id)
VALUES (3, 4);
INSERT INTO book_author(book_id, author_id)
VALUES (4, 4);
INSERT INTO book_author(book_id, author_id)
VALUES (5, 2);
INSERT INTO book_author(book_id, author_id)
VALUES (6, 2);
INSERT INTO book_author(book_id, author_id)
VALUES (7, 6);
INSERT INTO book_author(book_id, author_id)
VALUES (8, 5);
INSERT INTO book_author(book_id, author_id)
VALUES (9, 8);
INSERT INTO book_author(book_id, author_id)
VALUES (10, 8);
INSERT INTO book_author(book_id, author_id)
VALUES (11, 8);
INSERT INTO book_author(book_id, author_id)
VALUES (12, 1);

INSERT INTO composition_comment(user_id, book_id, comment, comment_date)
VALUES (1, 11, 'A very great ending!!! I am very impressed!', '03.24.2017');
INSERT INTO composition_comment(user_id, book_id, comment, comment_date)
VALUES (3, 3, 'I think, it is a very boring book with a lot of water inside imho...', '05.13.2018');
INSERT INTO composition_comment(user_id, book_id, comment, comment_date)
VALUES (2, 1, 'I think i will read this book again!! Very nice!!!', '12.08.2017');
INSERT INTO composition_comment(user_id, book_id, comment, comment_date)
VALUES (4, 3, 'I do not agree with Dima Vasuk, a very deep work that leaves the reader impressed for a long time',
        '07.12.2018');

INSERT INTO item_status(name)
VALUES ('In a storage');
INSERT INTO item_status(name)
VALUES ('Booked');

INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (1997, 'Yellow yard', 1, 1);
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (1995, 'Yellow yard', 7, 1);
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (1999, 'Virago', 7, 2);
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (2001, 'Virago', 2, 1);
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (2001, 'Virago', 12, 2);
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (2001, 'The Bodley Head', 9, 1);
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (2005, 'The Bodley Head', 10, 2);
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (2006, 'The Bodley Head', 11, 1);
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (1996, 'Picador', 10, 1);
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (2007, 'Picador', 8, 1);
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (2012, 'Picador', 5, 2);
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (1993, 'Picador', 6, 2);
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (1995, 'Random House', 6, 1);
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (1995, 'Random House', 5, 1);
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (1991, 'Random House', 3, 2);
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (1991, 'Random House', 4, 1);
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (2000, 'Random House', 12, 1);
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (2002, 'Pelican', 9, 2);
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (2003, 'Pelican', 10, 1);
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (2004, 'Pelican', 11, 1);
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (2001, 'Pelican', 3, 1);
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (2001, 'Peli', 4, 1);
UPDATE item
SET publish_office = 'Pelican'
WHERE id = 22;
INSERT INTO item(publish_year, publish_office, book_id, item_status_id)
VALUES (1999, 'Random House', 5, 1);
DELETE
FROM item
WHERE id = 23;

INSERT INTO status_comment(user_id, item_id, comment, comment_date)
VALUES (1, 20, 'Not great, not terrible!', '03.25.2017');
INSERT INTO status_comment(user_id, item_id, comment, comment_date)
VALUES (3, 15, 'A very old and bad condition of a book!', '05.11.2018');
INSERT INTO status_comment(user_id, item_id, comment, comment_date)
VALUES (2, 1, 'Surprisingly the book is in a good condition!', '12.09.2017');
INSERT INTO status_comment(user_id, item_id, comment, comment_date)
VALUES (4, 21, 'I did not find any disadvantages!', '07.13.2018');
INSERT INTO status_comment(user_id, item_id, comment, comment_date)
VALUES (3, 15, 'I do not like your website!!!!!', '07.13.2018');
DELETE
FROM status_comment
WHERE id = 5;

INSERT INTO order_status(name)
VALUES ('Accepted');
INSERT INTO order_status(name)
VALUES ('On the way');
INSERT INTO order_status(name)
VALUES ('Delivered');
INSERT INTO order_status(name)
VALUES ('Returned');

INSERT INTO user_order(user_id, item_id, address, order_date, return_date, order_status_id)
VALUES (1, 20, 'Lenin str., 58', '02.14.2017', '03.20.2017', 4);
INSERT INTO user_order(user_id, item_id, address, order_date, return_date, order_status_id)
VALUES (3, 15, 'Adway St., 24', '05.03.2018', '06.03.2018', 3);
INSERT INTO user_order(user_id, item_id, address, order_date, return_date, order_status_id)
VALUES (1, 7, 'Lenin str., 58', '09.15.2019', '10.15.2019', 2);
INSERT INTO user_order(user_id, item_id, address, order_date, return_date, order_status_id)
VALUES (4, 4, 'Pushkin st. 34', '06.13.2017', '07.10.2017', 4);
INSERT INTO user_order(user_id, item_id, address, order_date, return_date, order_status_id)
VALUES (2, 1, 'Risus St. 4', '08.11.2017', '09.10.2017', 4);
INSERT INTO user_order(user_id, item_id, address, order_date, return_date, order_status_id)
VALUES (2, 17, 'Risus St. 4', '09.10.2019', '10.11.2019', 1);
INSERT INTO user_order(user_id, item_id, address, order_date, return_date, order_status_id)
VALUES (2, 11, 'Risus St. 4', '08.30.2019', '09.30.2019', 2);
UPDATE user_order
SET item_id = 12
WHERE id = 7;