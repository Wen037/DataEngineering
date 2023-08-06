SELECT COUNT(DISTINCT customer_id) as subscriber_lost
FROM tblSubscriptionInfo 
WHERE YEAR(subscription_end_date) = 2023;


-- Output
-- +-----------------+
-- | subscriber_lost |
-- +-----------------+
-- |               5 |
-- +-----------------+