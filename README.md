# Training Service [Backend]

Training Service is a dynamic web application for all people who are looking for additional 
learning opportunities, as well as for those who can teach and provide tutoring, mentoring, 
mentoring services. The application allows participants to create, manage, search or offer 
different types of training in a wide variety of areas and for different age groups.  Features 
such as user authentication, categorization of destinations, age gradations, types of services, 
and different types of training are active in the app. Also, you can always see ratings and 
reviews of participants, buy, sell or exchange books here. The application will help everyone to 
find their teacher easily. And each teacher will be able to share their knowledge and experience.

Translated with DeepL.com (free version)

# Technologies
The main stack of technologies:

- Java
- Spring Boot
- MySQL
- REST API
- Security
- Swagger
- Jacoco
- Hibernate
- Liquibase

# Installation
https://github.com/TetianaBogoliubova/Diplom/tree/master/src/main/java/com/bogoliubova/training_service

# Database structure

The database includes:
### Table Customers (people who are looking for mentoring, tutoring services;)

| Column name  | Type        | Description                           |
|--------------|-------------|---------------------------------------|
| customer_id  | char(36)    | id key of row - primary key, not null | 
| first_name   | varchar(45) | client's name,  not null              |
| last_name    | varchar(45) | client's surname, not null            |
| c_email      | varchar(45) | client's e-mail, not null             |
| location_id  | varchar(36) | location's id, foreign key, not null  |
| direction_id | varchar(36) | direction's id                        |


### Table Teachers (people who offer their services in teaching)

| Column name  | Type        | Description                           |
|--------------|-------------|---------------------------------------|
| teacher_id   | char(36)    | id key of row - primary key, not null |
| first_name   | varchar(45) | teacher's name,  not null             |         
| last_name    | varchar(45) | teacher's name,  not null             |               
| t_email      | varchar(45) | teacher's e-mail, not null            |                           
| direction_id | char(36)    | direction's id                        |                          
| location_id  | char(36)    | location's id, foreign key            | 
| type_id      | char(36)    | type's id, type's of learning id      |
| rating_id    | char(36)    | rating's id                           |                          


### Table Ratings (evaluation and feedback for each tutor)

| Column name        | Type         | Description                           |
|--------------------|--------------|---------------------------------------|
| rating_id          | char(36)     | id key of row - primary key, not null |
| rating_for_teacher | int          | evaluation for teacher, not null      | 
| feedback           | varchar(150) | feedback for teacher                  | 
| teacher_id         | char(36)     | teacher's id                          |


 ### Table Directions (subjects and disciplines) 

| Column name  | Type          | Description                           |
|--------------|---------------|---------------------------------------|
| direction_id | char(36)      | id key of row - primary key, not null | 
| d_title      | varchar(45)   | name of direction, not null           | 
| grading      | varchar(45)   | age ranges, not null                  | 
| teacher_id   | char(36)      | teacher's id, foreign key, not null   |
| service_id   | char(36)      | service's id, foreign key, not null   |
| book_id      | char(36)      | book's id, foreign key, not null      |
| customer_id  | char(36)      | customer's id, foreign key, not null  |


 ### Table Services (various training programs, sale, purchase and exchange of books)

| Column name  | Type         | Description                           |
|--------------|--------------|---------------------------------------|
| service_id   | char(36)     | id key of row - primary key, not null |
| type         | varchar(150) | service's type, not null              | 
| s_price      | decimal(6,2) | service's prices, not null            |
| direction_id | char(36)     | direction's id                        | 
| book_id      | char(36)     | book's id                             | 


### Table Books (books, textbooks and other learning materials for sale or exchange) 

| Column name  | Type           | Description                           |
|--------------|----------------|---------------------------------------|
| book_id      | char(36)       | id key of row - primary key, not null |
| b_title      | varchar(45)    | bookstore's title, not null           | 
| author       | varchar(45)    | book's author, not null               | 
| b_price      | decimal(5,2)   | book's prices, not null               |
| direction_id | char(36)       | direction's id                        |
| service_id   | char(36)       | service's id, foreign key, not null   |


 ### Table Locations (the location, namely country and city, of the application participants)

| Column name   | Type        | Description                           |
|---------------|-------------|---------------------------------------|
| location_id   | char(36)    | id key of row - primary key, not null | 
| country       | varchar(45) | location's country, not null          | 
| city          | varchar(45) | location's city, not null             | 
| postal_code   | int         | city's postal code, not null          | 
| customer_id   | char(36)    | customer's id                         |
| teacher_id    | char(36)    | teacher's id                          |


 ### Table Types_of_learning (different types of training)

| Column name    | Type         | Description                           |
|----------------|--------------|---------------------------------------|
| type_id        | char(36)     | id key of row - primary key, not null | 
| learning_types | varchar(150) | types of learning, not null           | 
| special_price  | decimal(5,2) | special price for types, not null     | 
| teacher_id     | char(36)     | teacher's id                          |


### Table Users (entity for authorization)

| Column name | Type         | Description                                    |
|-------------|--------------|------------------------------------------------|
| user_id     | char(36)     | id key of row - primary key, not null          | 
| login       | varchar(20)  | login ore username for authorization, not null | 
| password    | varchar 100) | password for authorization, not null           | 
| customer_id | char(36)     | customer's id, foreign key                     |
| teacher_id  | char(36)     | teacher's id, foreign key                      |
| role        | char(36)     | name of the role for authorization             |

