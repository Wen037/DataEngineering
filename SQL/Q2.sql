SELECT COUNT(DISTINCT customer_id) AS subscribers_3_month_2022_count
FROM tblSubscriptionInfo 
WHERE YEAR(subscription_start_date) = 2022 AND 
TIMESTAMPDIFF(MONTH, subscription_start_date, subscription_end_date) > 3;

-- Output
-- +--------------------------------+
-- | subscribers_3_month_2022_count |
-- +--------------------------------+
-- |                              5 |
-- +--------------------------------+