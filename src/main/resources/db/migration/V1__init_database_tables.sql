CREATE TABLE IF NOT EXISTS `user` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(30) NOT NULL,
    `middle_name` VARCHAR(30),
    `last_name` VARCHAR(30) NOT NULL,
    `username` VARCHAR(30) NOT NULL UNIQUE,
    `email` VARCHAR(50) UNIQUE KEY,
    `password` VARCHAR(60) NOT NULL,
    `phone_no` VARCHAR(10) NOT NULL,
    `locked` BIT(1) NOT NULL DEFAULT 1,
    `first_login` BIT(1) NOT NULL DEFAULT 1,
    `enabled` BIT(1) NOT NULL DEFAULT 1,
    `expired` BIT(1) NOT NULL DEFAULT 1,
    `role_id` INT NOT NULL DEFAULT 1,
    `reg_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS `role` (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role VARCHAR(15) NOT NULL
);

INSERT INTO `role` (`id`, `role`) VALUES
    (1, 'ADMIN'),
    (2, 'STAFF');

CREATE TABLE IF NOT EXISTS `user_role` (
	`user_id` INT(11) NOT NULL,
	`role_id` INT(11) NOT NULL,
	PRIMARY KEY (`user_id`, `role_id`)
);