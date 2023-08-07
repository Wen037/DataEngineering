# SQL Assignment README

## Introduction 
- This assignment focuses on managing a subscription service for Autodesk software. We have a table named 'tblSubscriptionInfo' that stores data about customer subscriptions, including customer information and subscription details.
- MySQL is installed on the system based on approriate operating system. https://dev.mysql.com/downloads/mysql/

## Database Table 
```
CREATE DATABASE subscription_db;
USE subscription_db;
```
The 'tblSubscriptionInfo' table is created using the following SQL query:

``` CREATE TABLE tblSubscriptionInfo
(
  subscription_id INT,
  product_id INT,
  product_name VARCHAR(255),
  subscription_start_date DATETIME,
  subscription_end_date DATETIME,
  customer_id INT,
  customer_contact_phone VARCHAR(11),
  customer_name VARCHAR(255),
  customer_address VARCHAR(255)
);
```

## Insert mock data 
Sample data is inserted into the table using the 'InsertTable.sql' script.

## Questions 
1. number of subscribers whose subscriptions will be ending in 2023;
2. number of subscribers who have subscribed for more than 3 months in 2022;
3. subscribers who have subscribed for more than two products;
4. product with the most/2ndmost/3rdmost number of subscribers in 2022;
5. number of subscribers who have re-subscribed more than once for each product;
6. subscribers who have re-subscribed a higher version of the product in 2023 - for example Autocad 2022 to Autocad 2023.

## Solutions
The solutions for these questions are provided in individual '.sql' files. 
The 'updateData.sql' file provides the SQL query to update the contact phone number, along with a solution to optimize the query. 
The 'Q1.sql' 'Q6.sql' file provides the solution for the question respectively.

# Java Assignment README

This is a simple online bookstore inventory system implemented as a microservice using Java and Spring Boot. It interacts with a SQL database to manage inventory of books.

## Getting Started

To get the project up and running on your local machine, follow these steps:

### Prerequisites

- Java 11 or higher
- MySQL 8.0 or higher
- Maven 3.6.3 or higher
- Git

## Built With
Java
Spring Boot - The web framework used
Maven - Dependency Management
MySQL - Database
HikariCP - Lightweight JDBC connection pooling framework
### MySQL Setup

Setup the MySQL database locally:

```
CREATE DATABASE bookstore_db;
USE bookstore_db;
CREATE TABLE books (
	id VARCHAR(50) PRIMARY KEY,
	title VARCHAR(100),
	author VARCHAR(100),
	isbn VARCHAR(13),
	quantity INT,
	price DOUBLE,
	availability BOOLEAN
);
CREATE INDEX idx_title ON books(title);
CREATE INDEX idx_author ON books(author);
CREATE INDEX idx_isbn ON books(isbn);
```
The idx_title, idx_author and idx_isbn are indexes on the title, author and ISBN columns respectively, which can help speed up search queries.

## Configuration: Replace the values as needed based on your MySQL setup
In src/main/resources/application.properties, configure the database connection details:
spring.datasource.url=jdbc:mysql://localhost:3306/bookstore_db
spring.datasource.username=[CHANGE_YOUR_VALUE]
spring.datasource.password=[CHANGE_YOUR_VALUE]
spring.jpa.hibernate.ddl-auto=update

## Run the unit tests for the project using the following command:
```
mvn test
```

## Running the Application
```
mvn spring-boot:run
```

## API Documentation
## This system exposes the following RESTful endpoints:
POST /books: Add a new book to the inventory
```
curl -X POST "http://localhost:8080/books" -H "Content-Type: application/json" -d "{\"id\":\"3\",\"title\":\"Test Book\",\"author\":\"Test Author\",\"isbn\":\"1234567890\",\"quantity\":100,\"price\":19.99,\"availability\":true}"
```
DELETE /books/{id}: Remove a book from the inventory
```
curl -X DELETE "http://localhost:8080/books/1"
```
PUT /books/{id}: Update the quantity in stock for a given book.
```
curl -X PUT "http://localhost:8080/books/1?quantity=200"
```
GET /books/{id}: Retrieve the quantity in stock for a given book.
```
curl -X GET "http://localhost:8080/books/3"
```
GET /books: List all books in the inventory.
```
curl -X GET "http://localhost:8080/books"
```
GET /books/search: Search for books based on their title, author, or ISBN. Also allows for filtering based on price range and availability.
```
curl -X GET "http://localhost:8080/books/search?title=Test%20Book&author=Test%20Author&isbn=1234567890&minPrice=10.0&maxPrice=30
```

## Design Pattern
This project employs the Singleton design pattern for the DBConnection class to ensure that only a single instance of the database connection is created and shared throughout the application.

## Error Handling and Logging
- Implemented a global exception handler to capture and log any exceptions or errors that occur during the execution of the system.
- SLF4J used for logging.

## Performance Optimization
- Created indexes in your database schema
- Optimize Queries by selecting specific columns and using prepared statements.
- The HikariCP connection pool is also used to manage database connections efficiently.


