# Create a orders table with some sample orders
CREATE TABLE orders_tab (order_id INT, item_id INT, customer_id INT, order_time TIMESTAMP);

# Insert some fake orders
INSERT INTO orders_tab
(order_id,
item_id,
customer_id,
order_time)
VALUES
(1, 2, 3, current_timestamp()),
(2, 1, 1, current_timestamp()),
(3, 4, 2, current_timestamp()),
(4, 3, 4, current_timestamp());

#Verify that records were inserted
SELECT * FROM orders_tab