-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema debook_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema debook_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `debook_db` DEFAULT CHARACTER SET utf8 ;
USE `debook_db` ;

-- -----------------------------------------------------
-- Table `debook_db`.`currency`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `debook_db`.`currency` ;

CREATE TABLE IF NOT EXISTS `debook_db`.`currency` (
  `currency_id` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NOT NULL,
  `sign` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`currency_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `debook_db`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `debook_db`.`user` ;

CREATE TABLE IF NOT EXISTS `debook_db`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NULL,
  `email` VARCHAR(45) NOT NULL,
  `activated` TINYINT(1) NOT NULL DEFAULT 0,
  `create_time` DATETIME NOT NULL,
  `update_time` DATETIME NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `debook_db`.`person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `debook_db`.`person` ;

CREATE TABLE IF NOT EXISTS `debook_db`.`person` (
  `person_id` INT NOT NULL AUTO_INCREMENT,
  `owner_user_id` INT NOT NULL,
  `connected_user_id` INT NULL,
  `connection_approved` TINYINT(1) NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NULL,
  `create_time` DATETIME NOT NULL,
  `update_time` DATETIME NOT NULL,
  PRIMARY KEY (`person_id`),
  INDEX `fk_persons_connected_users_idx` (`connected_user_id` ASC),
  INDEX `fk_persons_owner_users` (`owner_user_id` ASC),
  CONSTRAINT `fk_persons_owner_users`
    FOREIGN KEY (`owner_user_id`)
    REFERENCES `debook_db`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_persons_connected_users`
    FOREIGN KEY (`connected_user_id`)
    REFERENCES `debook_db`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `debook_db`.`credit_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `debook_db`.`credit_type` ;

CREATE TABLE IF NOT EXISTS `debook_db`.`credit_type` (
  `credit_type_id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`credit_type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `debook_db`.`debt`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `debook_db`.`debt` ;

CREATE TABLE IF NOT EXISTS `debook_db`.`debt` (
  `debt_id` INT NOT NULL AUTO_INCREMENT,
  `person_id` INT NOT NULL,
  `currency_id` INT NOT NULL,
  `credit_type_id` INT NOT NULL,
  `value` DECIMAL(15,2) NOT NULL,
  `create_time` DATETIME NOT NULL,
  `update_time` DATETIME NOT NULL,
  PRIMARY KEY (`debt_id`),
  INDEX `fk_debt_credit_type_idx` (`credit_type_id` ASC),
  INDEX `fk_debt_person_idx` (`person_id` ASC),
  INDEX `fk_dept_currency` (`currency_id` ASC),
  UNIQUE INDEX `person_currency_UNIQUE` (`person_id` ASC, `currency_id` ASC),
  CONSTRAINT `fk_dept_currency`
    FOREIGN KEY (`currency_id`)
    REFERENCES `debook_db`.`currency` (`currency_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_debt_person`
    FOREIGN KEY (`person_id`)
    REFERENCES `debook_db`.`person` (`person_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_debt_credit_type`
    FOREIGN KEY (`credit_type_id`)
    REFERENCES `debook_db`.`credit_type` (`credit_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `debook_db`.`user_secret`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `debook_db`.`user_secret` ;

CREATE TABLE IF NOT EXISTS `debook_db`.`user_secret` (
  `user_id` INT NOT NULL,
  `hash` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `fk_users_secret_users_idx` (`user_id` ASC),
  CONSTRAINT `fk_users_secret_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `debook_db`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `debook_db`.`authorities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `debook_db`.`authorities` ;

CREATE TABLE IF NOT EXISTS `debook_db`.`authorities` (
  `user_id` INT NOT NULL,
  `authority` VARCHAR(45) NOT NULL,
  INDEX `fk_authorities_user_idx` (`user_id` ASC),
  UNIQUE INDEX `authorities_user_UNIQUE` (`user_id` ASC, `authority` ASC),
  CONSTRAINT `fk_authorities_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `debook_db`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `debook_db`.`event_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `debook_db`.`event_type` ;

CREATE TABLE IF NOT EXISTS `debook_db`.`event_type` (
  `event_type_id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `subtype` VARCHAR(45) NULL,
  PRIMARY KEY (`event_type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `debook_db`.`event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `debook_db`.`event` ;

CREATE TABLE IF NOT EXISTS `debook_db`.`event` (
  `event_id` INT NOT NULL AUTO_INCREMENT,
  `event_type_id` INT NOT NULL,
  `affected_user_id` INT NOT NULL,
  `affected_person_id` INT NULL,
  `processed` TINYINT(1) NOT NULL DEFAULT 0,
  `create_time` DATETIME NOT NULL,
  `update_time` DATETIME NOT NULL,
  PRIMARY KEY (`event_id`),
  INDEX `fk_event_event_type_idx` (`event_type_id` ASC),
  INDEX `fk_event_user_idx` (`affected_user_id` ASC),
  INDEX `fk_event_person_idx` (`affected_person_id` ASC),
  CONSTRAINT `fk_event_event_type`
    FOREIGN KEY (`event_type_id`)
    REFERENCES `debook_db`.`event_type` (`event_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_user`
    FOREIGN KEY (`affected_user_id`)
    REFERENCES `debook_db`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_person1`
    FOREIGN KEY (`affected_person_id`)
    REFERENCES `debook_db`.`person` (`person_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `debook_db`.`request`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `debook_db`.`request` ;

CREATE TABLE IF NOT EXISTS `debook_db`.`request` (
  `request_id` INT NOT NULL AUTO_INCREMENT,
  `type` ENUM('CONNECTION', 'DEBT') NOT NULL,
  `source_user_id` INT NOT NULL,
  `target_user_id` INT NOT NULL,
  `message` VARCHAR(120) NULL,
  `reject_message` VARCHAR(120) NULL,
  `rejected` TINYINT(1) NULL,
  `processed` TINYINT(1) NOT NULL DEFAULT 0,
  `last_updater` INT NOT NULL,
  `create_time` DATETIME NOT NULL,
  `update_time` DATETIME NOT NULL,
  PRIMARY KEY (`request_id`),
  INDEX `fk_request_source_user_idx` (`source_user_id` ASC),
  INDEX `fk_request_target_user_idx` (`target_user_id` ASC),
  INDEX `fk_request_last_updater_idx` (`last_updater` ASC),
  CONSTRAINT `fk_request_source_user`
    FOREIGN KEY (`source_user_id`)
    REFERENCES `debook_db`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_request_target_user`
    FOREIGN KEY (`target_user_id`)
    REFERENCES `debook_db`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_request_last_updater`
    FOREIGN KEY (`last_updater`)
    REFERENCES `debook_db`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `debook_db`.`debt_request_data`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `debook_db`.`debt_request_data` ;

CREATE TABLE IF NOT EXISTS `debook_db`.`debt_request_data` (
  `debt_request_data_id` INT NOT NULL AUTO_INCREMENT,
  `request_id` INT NOT NULL,
  `owner_user_id` INT NOT NULL,
  `currency_id` INT NOT NULL,
  `credit_type_id` INT NOT NULL,
  `value` DECIMAL(15,2) NOT NULL,
  `message` VARCHAR(45) NULL,
  `processed` TINYINT(1) NOT NULL DEFAULT 0,
  `create_time` DATETIME NOT NULL,
  INDEX `fk_debt_request_currency_idx` (`currency_id` ASC),
  INDEX `fk_debt_request_credit_type_idx` (`credit_type_id` ASC),
  PRIMARY KEY (`debt_request_data_id`),
  INDEX `fk_debt_request_data_user_idx` (`owner_user_id` ASC),
  INDEX `fk_debt_request_data_request_idx` (`request_id` ASC),
  CONSTRAINT `fk_debt_request_data_currency`
    FOREIGN KEY (`currency_id`)
    REFERENCES `debook_db`.`currency` (`currency_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_debt_request_data_credit_type`
    FOREIGN KEY (`credit_type_id`)
    REFERENCES `debook_db`.`credit_type` (`credit_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_debt_request_data_request`
    FOREIGN KEY (`request_id`)
    REFERENCES `debook_db`.`request` (`request_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_debt_request_data_user`
    FOREIGN KEY (`owner_user_id`)
    REFERENCES `debook_db`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
