--DELETE FROM directions;
CREATE TABLE IF NOT EXISTS directions (
    direction_id CHAR(36) PRIMARY KEY NOT NULL,
    d_title VARCHAR(45) NOT NULL,
    grading VARCHAR(45) NOT NULL,
    teacher_id CHAR(36),
    service_id CHAR(36),
    book_id CHAR(36),
    customer_id CHAR(36)
    );

-- DELETE FROM books;
CREATE TABLE IF NOT EXISTS books (
    book_id CHAR(36) PRIMARY KEY NOT NULL,
    b_title VARCHAR(45) NOT NULL,
    author VARCHAR(45) NOT NULL,
    b_price DECIMAL(5, 2) NOT NULL,
    direction_id CHAR(36),
    service_id CHAR(36)
    );

--DELETE FROM services;
CREATE TABLE IF NOT EXISTS services (
    service_id CHAR(36) PRIMARY KEY NOT NULL,
    type VARCHAR(150) NOT NULL,
    s_price DECIMAL(6, 2) NOT NULL,
    direction_id CHAR(36),
    book_id CHAR(36)
    );

--DELETE FROM types_of_learning;
CREATE TABLE IF NOT EXISTS types_of_learning (
    type_id CHAR(36) PRIMARY KEY NOT NULL,
    learning_types VARCHAR(150) NOT NULL,
    special_price DECIMAL(5, 2) NOT NULL,
    teacher_id CHAR(36)
    );

--DELETE FROM ratings;
CREATE TABLE IF NOT EXISTS ratings (
    rating_id CHAR(36) PRIMARY KEY NOT NULL,
    rating_for_teacher INTEGER NOT NULL,
    feedback VARCHAR(150),
    teacher_id CHAR(36)
    );

--DELETE FROM customers;
CREATE TABLE IF NOT EXISTS customers (
    customer_id CHAR(36) PRIMARY KEY NOT NULL,
    first_name VARCHAR(70) NOT NULL,
    last_name VARCHAR(70) NOT NULL,
    c_email VARCHAR(70) NOT NULL,
    location_id CHAR(36),
    direction_id CHAR(36)
    );

--DELETE FROM teachers;
CREATE TABLE IF NOT EXISTS teachers (
    teacher_id CHAR(36) PRIMARY KEY NOT NULL,
    first_name VARCHAR(70) NOT NULL,
    last_name VARCHAR(70) NOT NULL,
    t_email VARCHAR(70) NOT NULL,
    direction_id CHAR(36),
    location_id CHAR(36),
    type_id CHAR(36),
    rating_id CHAR(36)
    );

--DELETE FROM locations;
CREATE TABLE IF NOT EXISTS locations (
    location_id CHAR(36) PRIMARY KEY NOT NULL,
    country VARCHAR(45) NOT NULL,
    city VARCHAR(45) NOT NULL,
    postal_code INTEGER NOT NULL,
    customer_id CHAR(36),
    teacher_id CHAR(36)
    );

-- -- Затем удаляем записи из таблиц teachers
-- DELETE FROM teachers;
--
-- -- Удаляем записи из таблицы types_of_learning
-- DELETE FROM types_of_learning WHERE teacher_id IN (SELECT teacher_id FROM teachers);
--
-- -- Удаляем записи из таблиц locations
-- DELETE FROM locations WHERE customer_id IN (SELECT customer_id FROM customers) OR teacher_id IN (SELECT teacher_id FROM teachers);
--
-- -- Удаляем записи из таблиц directions
-- DELETE FROM directions;
--
-- -- Затем удаляем записи из таблиц services и books
-- DELETE FROM services;
-- DELETE FROM books;
--
-- -- Удаляем записи из таблицы customers
-- DELETE FROM customers;
--
--
--
-- -- Удаляем записи из ratings сначала
-- DELETE FROM ratings WHERE teacher_id IN (SELECT teacher_id FROM teachers);
--
-- -- Затем удаляем записи из teachers
-- DELETE FROM teachers WHERE direction_id IN (SELECT direction_id FROM directions);
--
-- -- Затем удаляем записи из services и books
-- DELETE FROM services WHERE direction_id IN (SELECT direction_id FROM directions);
-- DELETE FROM books WHERE direction_id IN (SELECT direction_id FROM directions);
--
-- -- Затем удаляем записи из types_of_learning
-- DELETE FROM types_of_learning WHERE teacher_id IN (SELECT teacher_id FROM teachers);
--
-- -- Удаляем записи из locations
-- DELETE FROM locations WHERE customer_id IN (SELECT customer_id FROM customers) OR teacher_id IN (SELECT teacher_id FROM teachers);
--
-- -- Удаляем записи из directions
-- DELETE FROM directions;