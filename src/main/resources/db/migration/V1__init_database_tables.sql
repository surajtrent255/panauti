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
	UNIQUE INDEX `mobile_number` (`mobile_number`),
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
	`description` TEXT NOT NULL,
	`group` VARCHAR(50) NOT NULL,
	`required` INT(11) NOT NULL DEFAULT 0,
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
INSERT INTO `ipalika`.`question_type` (`type_id`, `type_name`) VALUES ('8', 'MULTI_TEXT');
INSERT INTO `ipalika`.`question_type` (`type_id`, `type_name`) VALUES ('9', 'RADIO_D');
INSERT INTO `ipalika`.`question_type` (`type_id`, `type_name`) VALUES ('10', 'RATING_M');
INSERT INTO `ipalika`.`question_type` (`type_id`, `type_name`) VALUES ('11', 'RATING');
INSERT INTO `ipalika`.`question_type` (`type_id`, `type_name`) VALUES ('12', 'CHECKBOX_N');

CREATE TABLE IF NOT EXISTS `option` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`option_id` INT NOT NULL,
	`option_text` VARCHAR(100) NOT NULL,
	`question_id` INT(11) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `answer` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`form_id` INT(11) NOT NULL DEFAULT 0,
	`entry_date` DATETIME NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
	`duration` VARCHAR(50) NOT NULL DEFAULT '0',
	`filled_id` VARCHAR(50) NOT NULL DEFAULT '0',
	`added_by` INT(11) NOT NULL DEFAULT 0,
	`verified_by` INT(11) NOT NULL DEFAULT 0,
	`modified_by` INT(11) NOT NULL DEFAULT 0,
	`a_1` TEXT NULL DEFAULT '0',
	`a_2` TEXT NULL DEFAULT '0',
	`a_3` TEXT NULL DEFAULT '0',
	`a_4` TEXT NULL DEFAULT '0',
	`a_5` TEXT NULL DEFAULT '0',
	`a_6` TEXT NULL DEFAULT '0',
	`a_7` TEXT NULL DEFAULT '0',
	`a_8` TEXT NULL DEFAULT '0',
	`a_9` TEXT NULL DEFAULT '0',
	`a_10` TEXT NULL DEFAULT '0',
	`a_11` TEXT NULL DEFAULT '0',
	`a_12` TEXT NULL DEFAULT '0',
	`a_13` TEXT NULL DEFAULT '0',
	`a_14` TEXT NULL DEFAULT '0',
	`a_15` TEXT NULL DEFAULT '0',
	`a_16` TEXT NULL DEFAULT '0',
	`a_17` TEXT NULL DEFAULT '0',
	`a_18` TEXT NULL DEFAULT '0',
	`a_19` TEXT NULL DEFAULT '0',
	`a_20` TEXT NULL DEFAULT '0',
	`a_21` TEXT NULL DEFAULT '0',
	`a_22` TEXT NULL DEFAULT '0',
	`a_23` TEXT NULL DEFAULT '0',
	`a_24` TEXT NULL DEFAULT '0',
	`a_25` TEXT NULL DEFAULT '0',
	`a_26` TEXT NULL DEFAULT '0',
	`a_27` TEXT NULL DEFAULT '0',
	`a_28` TEXT NULL DEFAULT '0',
	`a_29` TEXT NULL DEFAULT '0',
	`a_30` TEXT NULL DEFAULT '0',
	`a_31` TEXT NULL DEFAULT '0',
	`a_32` TEXT NULL DEFAULT '0',
	`a_33` TEXT NULL DEFAULT '0',
	`a_34` TEXT NULL DEFAULT '0',
	`a_35` TEXT NULL DEFAULT '0',
	`a_36` TEXT NULL DEFAULT '0',
	`a_37` TEXT NULL DEFAULT '0',
	`a_38` TEXT NULL DEFAULT '0',
	`a_39` TEXT NULL DEFAULT '0',
	`a_40` TEXT NULL DEFAULT '0',
	`a_41` TEXT NULL DEFAULT '0',
	`a_42` TEXT NULL DEFAULT '0',
	`a_43` TEXT NULL DEFAULT '0',
	`a_44` TEXT NULL DEFAULT '0',
	`a_45` TEXT NULL DEFAULT '0',
	`a_46` TEXT NULL DEFAULT '0',
	`a_47` TEXT NULL DEFAULT '0',
	`a_48` TEXT NULL DEFAULT '0',
	`a_49` TEXT NULL DEFAULT '0',
	`a_50` TEXT NULL DEFAULT '0',
	`a_51` TEXT NULL DEFAULT '0',
	`a_52` TEXT NULL DEFAULT '0',
	`a_53` TEXT NULL DEFAULT '0',
	`a_54` TEXT NULL DEFAULT '0',
	`a_55` TEXT NULL DEFAULT '0',
	`a_56` TEXT NULL DEFAULT '0',
	`a_57` TEXT NULL DEFAULT '0',
	`a_58` TEXT NULL DEFAULT '0',
	`a_59` TEXT NULL DEFAULT '0',
	`a_60` TEXT NULL DEFAULT '0',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `filled_id` (`filled_id`)
);




CREATE TABLE `survey_answer` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`question_id` VARCHAR(50) NOT NULL DEFAULT '',
	`answer_text` VARCHAR(100) NULL DEFAULT NULL,
	`filled_id` VARCHAR(100) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `survey_answer_info` (
	`survey_answer_extra_id` INT(11) NOT NULL AUTO_INCREMENT,
	`entry_date` DATETIME NOT NULL,
	`duration` VARCHAR(50) NOT NULL DEFAULT '',
	`filled_id` VARCHAR(100) NOT NULL,
	PRIMARY KEY (`survey_answer_extra_id`),
	UNIQUE INDEX `filled_id` (`filled_id`)
);

CREATE TABLE `family_member` (
	`f_id` INT(11) NOT NULL AUTO_INCREMENT,
	`full_name` VARCHAR(50) NULL DEFAULT NULL,
	`relation_id` INT(11) NOT NULL,
	`age` INT(11) NOT NULL,
	`gender_id` INT(11) NOT NULL,
	`maritial_status` INT(11) NOT NULL,
	`qualificatin_id` INT(11) NOT NULL,
	`occupation` VARCHAR(50) NOT NULL,
	`has_voter_id` BIT(1) NOT NULL DEFAULT b'0',
	`migration` VARCHAR(50) NOT NULL DEFAULT '0',
	`health_status` VARCHAR(50) NOT NULL DEFAULT '0',
	`filled_id` VARCHAR(50) NOT NULL DEFAULT '0',
	PRIMARY KEY (`f_id`)
);


CREATE TABLE `family_relation` (
  `relation_id` int(11) NOT NULL AUTO_INCREMENT,
  `relation_nepali` varchar(50) NOT NULL DEFAULT '0',
  `relation_english` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`relation_id`)
);

CREATE TABLE `academic_qualification` (
  `qualification_id` int(11) NOT NULL AUTO_INCREMENT,
  `qualification_nep` varchar(50) DEFAULT NULL,
  `qualification_eng` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`qualification_id`)
);