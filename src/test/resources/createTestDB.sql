CREATE TABLE IF NOT EXISTS directions (
    direction_id CHAR(36) PRIMARY KEY NOT NULL,
    d_title VARCHAR(45) NOT NULL,
    grading VARCHAR(45) NOT NULL,
    teacher_id CHAR(36),
    service_id CHAR(36),
    book_id CHAR(36),
    customer_id CHAR(36)
    );

CREATE TABLE IF NOT EXISTS books (
    book_id CHAR(36) PRIMARY KEY NOT NULL,
    b_title VARCHAR(45) NOT NULL,
    author VARCHAR(45) NOT NULL,
    b_price DECIMAL(5, 2) NOT NULL,
    direction_id CHAR(36),
    service_id CHAR(36)
    );

CREATE TABLE IF NOT EXISTS services (
    service_id CHAR(36) PRIMARY KEY NOT NULL,
    type VARCHAR(150) NOT NULL,
    s_price DECIMAL(6, 2) NOT NULL,
    direction_id CHAR(36),
    book_id CHAR(36)
    );

CREATE TABLE IF NOT EXISTS types_of_learning (
    type_id CHAR(36) PRIMARY KEY NOT NULL,
    learning_types VARCHAR(150) NOT NULL,
    special_price DECIMAL(5, 2) NOT NULL,
    teacher_id CHAR(36)
    );

CREATE TABLE IF NOT EXISTS ratings (
    rating_id CHAR(36) PRIMARY KEY NOT NULL,
    rating_for_teacher INTEGER NOT NULL,
    feedback VARCHAR(150),
    teacher_id CHAR(36)
    );

CREATE TABLE IF NOT EXISTS customers (
    customer_id CHAR(36) PRIMARY KEY NOT NULL,
    first_name VARCHAR(70) NOT NULL,
    last_name VARCHAR(70) NOT NULL,
    c_email VARCHAR(70) NOT NULL,
    location_id CHAR(36),
    direction_id CHAR(36)
    );

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

CREATE TABLE IF NOT EXISTS locations (
    location_id CHAR(36) PRIMARY KEY NOT NULL,
    country VARCHAR(45) NOT NULL,
    city VARCHAR(45) NOT NULL,
    postal_code INTEGER NOT NULL,
    customer_id CHAR(36),
    teacher_id CHAR(36)
    );