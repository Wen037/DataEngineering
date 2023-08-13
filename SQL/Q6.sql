SELECT old_sub.customer_id,old_sub.customer_name,old_sub.product_name AS old_product, new_sub.product_name AS new_product
FROM tblSubscriptionInfo AS old_sub, tblSubscriptionInfo AS new_sub
WHERE old_sub.product_id < new_sub.product_id 
    AND old_sub.customer_id = new_sub.customer_id
    AND CAST(old_sub.product_id /100 AS SIGNED) = CAST(new_sub.product_id /100 AS SIGNED)
    AND YEAR(old_sub.subscription_end_date) = 2023 
    AND YEAR(new_sub.subscription_start_date) = 2023;

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

