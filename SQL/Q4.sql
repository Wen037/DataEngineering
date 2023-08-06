SELECT product_id, product_name, COUNT(DISTINCT customer_id) as subscriber_count
FROM tblSubscriptionInfo
WHERE (YEAR(subscription_start_date) <= 2022 AND YEAR(subscription_end_date) >= 2022)
GROUP BY product_id, product_name
ORDER BY subscriber_count DESC
LIMIT 3;


-- Output
-- +------------+---------------+------------------+
-- | product_id | product_name  | subscriber_count |
-- +------------+---------------+------------------+
-- |        101 | Autocad 2022  |                4 |
-- |        201 | Revit 2022    |                2 |
-- |        301 | Inventor 2022 |                1 |
-- +------------+---------------+------------------+
