# 创建Order订单表-外链store_user表id

CREATE TABLE store_order(
	`order_id` VARCHAR(50) PRIMARY KEY,
	`create_time` DATETIME,
	`status` INT,
	`user_id` INT,
	FOREIGN KEY(`user_id`) REFERENCES store_user(`id`)
);

# 创建Order订单项数据表
CREATE TABLE store_order_item(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(100),
	`count` INT,
	`price` DECIMAL(11,2),
	`total_price` DECIMAL(11,2),
	`order_id` VARCHAR(50),
	FOREIGN KEY(`order_id`) REFERENCES store_order(`order_id`)
);