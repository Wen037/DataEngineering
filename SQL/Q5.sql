SELECT customer_id ,customer_name, product_id, product_name, COUNT(product_id)AS subscribe_count
FROM tblSubscriptionInfo
GROUP BY customer_id ,customer_name, product_id, product_name
HAVING subscribe_count >=2;


-- Output
--+-------------+---------------+------------+--------------+-----------------+
--| customer_id | customer_name | product_id | product_name | subscribe_count |
--+-------------+---------------+------------+--------------+-----------------+
--|        1005 | Tom           |        101 | Autocad 2022 |               2 |
--+-------------+---------------+------------+--------------+-----------------+
