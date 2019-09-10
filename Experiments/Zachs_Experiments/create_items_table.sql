# Create a table with sample items that are delivered
CREATE TABLE items_tab(item_id INT, item_description VARCHAR(255));

# Insert some fake items
INSERT INTO items_tab
(item_id,
item_description)
VALUES
(1, 'Hamburger'),
(2, 'Hot Dog'),
(3, 'Pizza'),
(4, 'Smoothie');

COMMIT;

#Verify that records were inserted
SELECT * FROM items_tab;