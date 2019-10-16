-- Простые запросы на SELECT
SELECT *
FROM user_order;
SELECT *
FROM book;
SELECT description
FROM genre;
SELECT login_name
FROM user_login;
SELECT DISTINCT user_id
FROM user_order;

-- 10 запросов с различными условиями
SELECT first_name, last_name
FROM system_user
WHERE first_name LIKE 'Na%';
SELECT publish_office
FROM item
WHERE publish_year IN (2000, 1999, 1995);
SELECT first_name
FROM author
WHERE middle_name IS NULL;
SELECT first_name
FROM system_user
WHERE birthday < '01.01.1999';
SELECT comment
FROM composition_comment
WHERE comment_date > '12.01.2017';
SELECT first_name, middle_name, last_name
FROM author
WHERE sex = 'female';
SELECT first_name, birthday
FROM author
where middle_name IS NOT NULL;
SELECT address
FROM user_order
WHERE order_status_id IN (2, 3);
SELECT first_name
FROM system_user
WHERE last_name LIKE 'K%'
  AND role_id = 1;
SELECT publish_year
FROM item
WHERE publish_office LIKE 'Addy'
   OR item_status_id = 2;

--Запросы со вложенными запросами
SELECT first_name, last_name
FROM system_user
WHERE login_id IN (SELECT id FROM user_login WHERE email LIKE '%nure%');
SELECT comment
FROM composition_comment
WHERE user_id IN (SELECT id FROM system_user WHERE birthday > '01.01.1999');

-- Запрос с использованием ORDER BY
SELECT first_name, last_name
FROM system_user
WHERE role_id IN (SELECT id FROM user_role WHERE role_name = 'user')
ORDER BY last_name DESC;

-- Запрос с помощью UNION возврашает адреса из двух разных таблиц
SELECT address
FROM system_user
WHERE role_id IN (SELECT id FROM user_role WHERE role_name = 'admin')
UNION
SELECT address
FROM user_order
WHERE user_id IN (SELECT id FROM system_user WHERE role_id IN (SELECT id FROM user_role WHERE role_name = 'user'));

-- Запрос с INNER JOIN вернет название книги, и имя фамилию её автора
SELECT b.name, a.first_name, a.last_name
FROM book AS b
         JOIN book_author AS ba ON b.id = ba.book_id
         JOIN author AS a ON ba.author_id = a.id;

-- Запрос с INNER JOIN вернет название книги, год её издания, имя и фамилию комментатора,
-- комментарий и дату этого комментария
SELECT b.name, i.publish_year, u.first_name, u.last_name, sc.comment, sc.comment_date
FROM system_user AS u
         JOIN status_comment AS sc ON u.id = sc.user_id
         JOIN item AS i ON sc.item_id = i.id
         JOIN book AS b ON i.book_id = b.id;

--Запрос с RIGHT JOIN вернет названия всех имен и описаний жанров и соответствующие им названия книг
SELECT b.name, g.name, g.description
FROM book AS b
         JOIN book_genre AS bg ON b.id = bg.book_id
         RIGHT JOIN genre AS g ON bg.genre_id = g.id;

-- Запрос с LEFT JOIN вернет имя фамилию автора, название книги и название жанра
SELECT a.first_name, a.last_name, b.name, g.name
FROM author AS a
         JOIN book_author AS ba ON a.id = ba.author_id
         JOIN book AS b ON ba.book_id = b.id
         JOIN book_genre AS bg ON b.id = bg.book_id
         LEFT JOIN genre AS g ON bg.genre_id = g.id;

-- Запрос с числовой функцией, берет квадратный корень из года публикации книги
SELECT b.name, cbrt(i.publish_year)
FROM item AS i
         JOIN book AS b ON i.book_id = b.id;

-- Запрос использует строковую функцию, чтобы поменять на верхний регистр все буквы логина пользователя
SELECT u.first_name, u.last_name, upper(l.login_name)
FROM system_user AS u
         JOIN user_login AS l ON u.login_id = l.id;

-- Запрос с помощью агрегатной функции, GROUP BY и HAVING находит издательство, которое издало больше все книг
SELECT publish_office, count(id)
FROM item
GROUP BY publish_office
HAVING count(id) = (SELECT max(i.num) FROM (SELECT count(id) AS num FROM item GROUP BY (publish_office)) AS i);

-- Запрос с помощью агрегатной функции, GROUP BY и HAVING подсчитывает все комментарии пользователей,
-- которые их оставляли
SELECT u.first_name, count(cc.id)
FROM system_user AS u
         JOIN composition_comment AS cc ON u.id = cc.user_id
GROUP BY u.first_name;