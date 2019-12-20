CREATE TABLE IF NOT EXISTS `user` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(30) NOT NULL,
	`middle_name` VARCHAR(30) NULL DEFAULT NULL,
	`last_name` VARCHAR(30) NOT NULL,
	`username` VARCHAR(30) NOT NULL,
	`email` VARCHAR(50) NULL DEFAULT NULL,
	`password` VARCHAR(60) NOT NULL,
	`mobile_number` VARCHAR(10) NOT NULL,
	`locked` BIT(1) NOT NULL DEFAULT b'1',
	`first_login` BIT(1) NULL DEFAULT b'1',
	`enabled` BIT(1) NOT NULL DEFAULT b'1',
	`expired` BIT(1) NOT NULL DEFAULT b'1',
	`registered_date` TIMESTAMP NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
	PRIMARY KEY (`id`),
	UNIQUE INDEX `username` (`username`),
	UNIQUE INDEX `email` (`email`)
);

INSERT INTO `user` (`id`, `first_name`, `middle_name`, `last_name`, `username`, `email`, `password`, `mobile_number`, `locked`, `first_login`, `enabled`, `expired`, `registered_date`)
 VALUES
(1, 'Umesh', 'B.', 'Bhujel', 'yoomes', 'yoomesbhujel@gmail.com', '$2a$11$H9wDLxAPTX5qp0doFKank.w6vgB7xPo1CJojH2AC0ovBY4Iu31oTS', '9849931288', 0, 0, 1, 0, CURRENT_TIMESTAMP());

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

INSERT INTO `user_role`(`user_id`, `role_id`) VALUES (1, 1),
(1, 2);


CREATE TABLE IF NOT EXISTS `form` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`form_id` VARCHAR(50) NOT NULL DEFAULT '0',
	`form_name` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `form_id` (`form_id`)
);


CREATE TABLE IF NOT EXISTS `question` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`question_id` VARCHAR(50) NOT NULL,
	`description` VARCHAR(50) NOT NULL,
	`group` VARCHAR(50) NOT NULL,
	`required` BIT(1) NOT NULL DEFAULT b'1',
	`type_id` INT(11) NOT NULL DEFAULT 1,
	`form_id` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `question_id` (`question_id`)
);


CREATE TABLE IF NOT EXISTS `question_type` (
	`type_id` INT(11) NOT NULL AUTO_INCREMENT,
	`type_name` VARCHAR(20) NOT NULL DEFAULT '0',
	PRIMARY KEY (`type_id`)
);

INSERT INTO `ipalika`.`question_type` (`type_id`, `type_name`) VALUES ('1', 'CHECKBOX');
INSERT INTO `ipalika`.`question_type` (`type_id`, `type_name`) VALUES ('2', 'RADIO');
INSERT INTO `ipalika`.`question_type` (`type_id`, `type_name`) VALUES ('3', 'TEXT');
INSERT INTO `ipalika`.`question_type` (`type_id`, `type_name`) VALUES ('4', 'NUMBER');
INSERT INTO `ipalika`.`question_type` (`type_id`, `type_name`) VALUES ('5', 'DATE');
INSERT INTO `ipalika`.`question_type` (`type_id`, `type_name`) VALUES ('6', 'GPS');
INSERT INTO `ipalika`.`question_type` (`type_id`, `type_name`) VALUES ('7', 'IMAGE');


CREATE TABLE IF NOT EXISTS `option` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`option_id` VARCHAR(50) NOT NULL,
	`option_text` VARCHAR(100) NOT NULL,
	`question_id` INT(11) NOT NULL,
	PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `survey_answer` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`question_id` INT(11) NOT NULL,
	`answer_id` INT(11) NULL DEFAULT NULL,
	`answer_text` VARCHAR(100) NOT NULL,
	PRIMARY KEY (`id`)
);

