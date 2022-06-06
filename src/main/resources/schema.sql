DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS orderRows;

CREATE TABLE IF NOT EXISTS orders (order_id int PRIMARY KEY, order_number VARCHAR (255) NOT NULL);
CREATE TABLE IF NOT EXISTS orderRows (order_id int, itemName VARCHAR (255) NOT NULL, quantity int NOT NULL, price int NOT NULL);