DROP TABLE `item` CASCADE;
DROP TABLE `cart`CASCADE;
CREATE TABLE `cart`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`buyer` VARCHAR(100));

CREATE TABLE `item`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`item_name` VARCHAR(100),
`item_price` DOUBLE,
`item_quantity` INT,
`image` VARCHAR(255),
`cart_id` INT,
FOREIGN KEY (`cart_id`) REFERENCES `cart`(`id`));
