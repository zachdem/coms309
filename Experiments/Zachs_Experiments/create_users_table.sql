# Create a users table with some basic information
CREATE TABLE users_tab (user_id INT, first_name VARCHAR(255), last_name VARCHAR(255), address VARCHAR(255), city VARCHAR(255), state VARCHAR(255));

# Insert some fake data
INSERT INTO users_tab
(user_id,
first_name,
last_name,
address,
city,
state)
VALUES
(1, 'Zach', 'DeMaris', '1234 Fake Ln', 'Ames', 'IA'),
(2, 'Eulises', 'Landa', '1234 Kangaroo Ln', 'Ames', 'IA'),
(3, 'Giovanni', 'Mejia', '1234 Butterfly Ave', 'Cupertino', 'CA'),
(4, 'Rene', 'Chavez', '1234 Elephant St', 'Ames', 'IA');

#Verify that records were inserted
SELECT * FROM users_tab