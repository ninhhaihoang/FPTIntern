CREATE DATABASE `intern`;

USE `intern`;

CREATE TABLE customers (
	customer_id  BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    customer_name VARCHAR(255),
    phone_number VARCHAR(45),
	sex VARCHAR(45),
	birthday DATE,
	address VARCHAR(255)
);

CREATE TABLE products (
	product_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    product_name VARCHAR(255),
    product_description VARCHAR(255),
    price BIGINT
);

CREATE TABLE status (
	status_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    status_name VARCHAR(45)
);

INSERT INTO `status` (status_name) VALUES ('Pending');
INSERT INTO `status` (status_name) VALUES ('Processing');
INSERT INTO `status` (status_name) VALUES ('Delivering');
INSERT INTO `status` (status_name) VALUES ('Doproductsstatusproductsne');

CREATE TABLE orders (
	order_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    customer_id BIGINT,
    product_id BIGINT,
    quantity INT,
    status_id BIGINT
);

ALTER TABLE orders
	ADD CONSTRAINT orders_FK1 FOREIGN KEY (customer_id) REFERENCES customers(customer_id);

ALTER TABLE orders
	ADD CONSTRAINT orders_FK2 FOREIGN KEY (product_id) REFERENCES products(product_id);
    
ALTER TABLE orders
	ADD CONSTRAINT orders_FK3 FOREIGN KEY (status_id) REFERENCES status(status_id);