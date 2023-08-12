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
CREATE DATABASE bookstore;
USE bookstore;
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
spring.datasource.username=change
spring.datasource.password=change
spring.jpa.hibernate.ddl-auto=update


## Running the Application
```
mvn spring-boot:run
```

## Run the unit tests for the project using the following command:
```
mvn test
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
Implemented a global exception handler to capture and log any exceptions or errors that occur during the execution of the system. SLF4J used for logging.

## Performance Optimization
Created indexes in your database schema
Optimize Queries by selecting specific columns and using prepared statements.
The HikariCP connection pool is also used to manage database connections efficiently.
