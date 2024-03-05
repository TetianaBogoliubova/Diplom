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
--     FOREIGN KEY (direction_id) REFERENCES directions (direction_id)
    );

CREATE TABLE IF NOT EXISTS services (
    service_id CHAR(36) PRIMARY KEY NOT NULL,
    type VARCHAR(150) NOT NULL,
    s_price DECIMAL(6, 2) NOT NULL,
    direction_id CHAR(36),
    book_id CHAR(36),
    FOREIGN KEY (direction_id) REFERENCES directions (direction_id),
    FOREIGN KEY (book_id) REFERENCES books (book_id)
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

CREATE TABLE IF NOT EXISTS roles (
    role_id CHAR(36) PRIMARY KEY NOT NULL,
    role_name VARCHAR(10) NOT NULL
    );

CREATE TABLE IF NOT EXISTS authorities (
    authority_id CHAR(36) PRIMARY KEY NOT NULL,
    authority_name VARCHAR(10) NOT NULL
    );

CREATE TABLE IF NOT EXISTS role_authorities (
    rol_id CHAR(36) NOT NULL,
    authority_id CHAR(36) NOT NULL,
    PRIMARY KEY (rol_id, authority_id),
    FOREIGN KEY (rol_id) REFERENCES roles (role_id),
    FOREIGN KEY (authority_id) REFERENCES authorities (authority_id)
    );

CREATE TABLE IF NOT EXISTS customers (
    customer_id CHAR(36) PRIMARY KEY NOT NULL,
    first_name VARCHAR(70) NOT NULL,
    last_name VARCHAR(70) NOT NULL,
    c_email VARCHAR(70) NOT NULL,
    location_id CHAR(36),
    direction_id CHAR(36),
    FOREIGN KEY (direction_id) REFERENCES directions (direction_id)
    );

CREATE TABLE IF NOT EXISTS customer_role (
    cus_id CHAR(36) NOT NULL,
    rol_id CHAR(36) NOT NULL,
    PRIMARY KEY (cus_id, rol_id),
    FOREIGN KEY (cus_id) REFERENCES customers (customer_id),
    FOREIGN KEY (rol_id) REFERENCES roles (role_id)
    );

CREATE TABLE IF NOT EXISTS teachers (
    teacher_id CHAR(36) PRIMARY KEY NOT NULL,
    first_name VARCHAR(70) NOT NULL,
    last_name VARCHAR(70) NOT NULL,
    t_email VARCHAR(70) NOT NULL,
    direction_id CHAR(36),
    location_id CHAR(36),
    type_id CHAR(36),
    rating_id CHAR(36),
    FOREIGN KEY (direction_id) REFERENCES directions (direction_id),
    FOREIGN KEY (type_id) REFERENCES types_of_learning (type_id),
    FOREIGN KEY (rating_id) REFERENCES ratings (rating_id)
    );

CREATE TABLE IF NOT EXISTS teacher_role (
    teach_id CHAR(36) NOT NULL,
    rol_id CHAR(36) NOT NULL,
    PRIMARY KEY (teach_id, rol_id),
    FOREIGN KEY (teach_id) REFERENCES teachers (teacher_id),
    FOREIGN KEY (rol_id) REFERENCES roles (role_id)
    );

CREATE TABLE IF NOT EXISTS locations (
    location_id CHAR(36) PRIMARY KEY NOT NULL,
    country VARCHAR(45) NOT NULL,
    city VARCHAR(45) NOT NULL,
    postal_code INTEGER NOT NULL,
    customer_id CHAR(36),
    teacher_id CHAR(36),
    FOREIGN KEY (customer_id) REFERENCES customers (customer_id),
    FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id)
    );