SELECT table1.customer_id, table1.customer_name, table1.product_name AS old_product, table2.product_name AS new_product
FROM tblSubscriptionInfo AS table1
JOIN tblSubscriptionInfo AS table2
    ON table1.customer_id = table2.customer_id
    AND table2.product_id - table1.product_id < 100 AND table2.product_id - table1.product_id > 0
WHERE YEAR(table2.subscription_start_date) = 2023;

-- +--------------+
--    ASSUMPTION
-- +--------------+
-- On assumption that each product has a distinct starting number for the product id
-- For example autocad starts with 1xx, autocad 2022 is 101 and autocad 2023 is 102.
--
-- Ideally, there should be a seperate column to store the variable of the version number


-- Output 
-- +-------------+---------------+--------------+--------------+
-- | customer_id | customer_name | old_product  | new_product  |
-- +-------------+---------------+--------------+--------------+
-- |        1001 | Peter         | Autocad 2022 | Autocad 2023 |
-- |        1002 | Patrick       | Autocad 2022 | Autocad 2023 |
-- |        1001 | Peter         | Revit 2022   | Revit 2023   |
-- |        1004 | Chelsea       | Revit 2022   | Revit 2023   |
-- +-------------+---------------+--------------+--------------+

