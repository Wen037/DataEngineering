CREATE INDEX idx_customer_id
ON tblSubscriptionInfo (customer_id);


--Test update
UPDATE tblSubscriptionInfo
SET customer_contact_phone = '+6599991112'
WHERE customer_id = 1005; 


-- For optimisation, make sure that customer_id is indexed. 
-- An index on this column would allow the database to find the relevant row much more quickly when updating the phone number. 
-- If the customer_id field is not indexed, the database must scan the entire table to find the relevant row, which can be slow if the table is large.