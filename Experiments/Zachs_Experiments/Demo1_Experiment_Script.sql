#Show all the created tables
SELECT * FROM users_tab;
SELECT * FROM items_tab;
SELECT * FROM orders_tab;

# Show how we can join these tables to retrieve information about our orders
# Join users and orders tables
SELECT
t1.order_id,
t1.item_id,
t1.order_time,
t2.first_name,
t2.last_name,
t2.address,
t2.city,
t2.state
FROM orders_tab t1
INNER JOIN users_tab t2 ON t1.customer_id = t2.user_id;

#Join users, orders, and items tables
SELECT
t1.order_id,
t3.item_description,
t1.order_time,
t2.first_name,
t2.last_name,
t2.address,
t2.city,
t2.state
FROM orders_tab t1, users_tab t2, items_tab t3
WHERE t1.customer_id = t2.user_id
AND t1.item_id = t3.item_id;