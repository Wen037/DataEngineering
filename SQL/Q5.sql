SELECT product_id, product_name, COUNT(customer_id) AS re_subscriber_count
FROM (
  SELECT product_id,product_name, customer_id
  FROM tblSubscriptionInfo
  GROUP BY product_id, product_name, customer_id
  HAVING COUNT(*) > 1
) AS ReSubscriptions
GROUP BY product_id, product_name;


-- Output
-- +------------+--------------+---------------------+
-- | product_id | product_name | re_subscriber_count |
-- +------------+--------------+---------------------+
-- |        101 | Autocad 2022 |                   1 |
-- +------------+--------------+---------------------+