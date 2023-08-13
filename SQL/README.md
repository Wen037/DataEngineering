# SQL Assignment README

## Introduction 
This assignment focuses on managing a subscription service for Autodesk software. We have a table named 'tblSubscriptionInfo' that stores data about customer subscriptions, including customer information and subscription details.
MySQL is installed on the system based on approriate operating system. https://dev.mysql.com/downloads/mysql/

## Database Table :
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

## Solutions:
The solutions for these questions are provided in individual '.sql' files. 
The 'updateData.sql' file provides the SQL query to update the contact phone number, along with a solution to optimize the query. 
The 'Q1.sql' 'Q6.sql' file provides the solution for the question respectively.
