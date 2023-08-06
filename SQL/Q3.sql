SELECT customer_id, customer_name, COUNT(DISTINCT product_id) AS product_count
FROM tblSubscriptionInfo 
GROUP BY customer_id, customer_name
HAVING COUNT(DISTINCT product_id) > 2;


-- Output
-- +-------------+---------------+---------------+
-- | customer_id | customer_name | product_count |
-- +-------------+---------------+---------------+
-- |        1001 | Peter         |             4 |
-- +-------------+---------------+---------------+