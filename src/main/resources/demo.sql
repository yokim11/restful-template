create database sampledb;

grant all privileges on sampledb.* to maap_dev@'localhost' identified by 'maap_dev@2018';
flush privileges;

CREATE TABLE `tbl_demo` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(45) NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=4
;


INSERT INTO tbl_demo(name) values('korea'),('japan'),('china');