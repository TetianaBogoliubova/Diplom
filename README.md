# Training_Service [Backend]

 This project is designed for people who are looking for additional training or mentoring and coaching services. 

 Data consist of cusromer, teacher, direction, location, rating, service and bookstore
___
___
## Database structure

### Table Customer (Service's customers table)

| Column name  | Type        | Description                                  |
|--------------|-------------|----------------------------------------------|
| customer_id  | binary(16)  | id key of row - primary key, not null        | 
| first_name   | varchar(45) | client's name,  not null                     |
| last_name    | varchar(45) | client's surname, not null                   |
| direction_id | binary(16)  | direction's id, foreign key, not null        |
| location_id  | binary(16)  | location's id, foreign key, not null         |
| c_email      | varchar(45) | client's e-mail, not null                    |                               
                

### Table Teacher (Service's teachers table)

| Column name         | Type        | Description                              |
|---------------------|-------------|------------------------------------------|
| teacher_id          | binary(16)  | id key of row - primary key, not null    |
| first_name          | varchar(45) | teacher's name,  not null                |         
| last_name           | varchar(45) | teacher's name,  not null                |               
| t_email             | varchar(45) | teacher's e-mail, not null               |                           
| direction_id        | binary(16)  | direction's id, foreign key, not null    |                          
| location_id         | binary(16)  | location's id, foreign key, not null     | 
| raiting_id          | binary(16)  | rating's id, foreign key, not   null     |                          
| type_of_learning_id | binary(16)  | type's id, foreign key, not null         |
               
                                                        
### Table Raiting (Service's - raitings for teachers table)

| Column name        | Type           | Description                             |
|--------------------|----------------|-----------------------------------------|
| rating_id          | binary(16)     | id key of row - primary key, not null   |
| rating_for_teacher | int            | evaluation for teacher, not null        | 
| feedback           | varchar(120)   | feedback for teacher                    | 


 ### Table Direction (Service's directions table) 

| Column name     | Type          | Description                              |
|-----------------|---------------|------------------------------------------|
| direction_id    | binary(16)    | id key of row - primary key, not null    | 
| d_title         | varchar(45)   | name of direction, not null              | 
| grading         | varchar(45)   | age ranges, not null                     | 
 

 ### Table Service (Service's favors table)

| Column name  | Type          | Description                              |
|--------------|---------------|------------------------------------------|
| service_id   | binary(16)    | id key of row - primary key, not null    | 
| direction_id | binary(16)    | direction's id, foreign key, not null    | 
| type         | varchar(45)   | service's type, not null                 | 
| s_price      | decimal(6, 2) | service's prices, not null               | 
| bookstore_id | binary(16)    | service's books, not null                | 


### Table Bookstore (Service's books table) 

| Column name   | Type          | Description                               |
|---------------|---------------|-------------------------------------------|
| bookstore_id  | binary(16)    | id key of row - primary key, not null     | 
| direction_id  | binary(16)    | direction's id, foreign key, not null     | 
| b_title       | varchar(45)   | bookstore's title, not null               | 
| author        | varchar(45)   | book's author, not null                   | 
| b_price       | decimal(5,2)  | book's prices, not null                   | 


 ### Table Location (Service's locations table)

| Column name  | Type         | Description                               |
|--------------|--------------|-------------------------------------------|
| location_id  | binary(16)   | id key of row - primary key, not null     | 
| country      | varchar(45)  | location's country, not null              | 
| city         | varchar(45)  | location's city, not null                 | 
| postal_code  | int          | city's postal code, not null              | 


 ### Table Type_of_learning (Service's types of learning table)

| Column name    | Type         | Description                               |
|----------------|--------------|-------------------------------------------|
| type_id        | binary(16)   | id key of row - primary key, not null     | 
| learning_type  | varchar(45)  | types of learning, not null               | 
| special_price  | decimal(5,2) | special price for types, not null         | 
 
 
