-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema myfmadev
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `myfmadev` ;

-- -----------------------------------------------------
-- Schema myfmadev
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `myfmadev` DEFAULT CHARACTER SET utf8 ;
USE `myfmadev` ;

-- -----------------------------------------------------
-- Table `myfmadev`.`fma_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myfmadev`.`fma_user` ;

CREATE TABLE IF NOT EXISTS `myfmadev`.`fma_user` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `version` INT(11) NOT NULL,
  `userid` VARCHAR(100) NOT NULL,
  `display_name` VARCHAR(200) NOT NULL,
  `email` VARCHAR(200) NULL DEFAULT NULL,
  `deleted` BIT(1) NOT NULL,
  `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_updated_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_updated_by` INT(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fmauser_userid_idx` ON `myfmadev`.`fma_user` (`userid` ASC);


-- -----------------------------------------------------
-- Table `myfmadev`.`offering`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myfmadev`.`offering` ;

CREATE TABLE IF NOT EXISTS `myfmadev`.`offering` (
  `id` INT(10) UNSIGNED NOT NULL,
  `version` INT(11) NOT NULL,
  `fma_user_id` INT(11) NOT NULL,
  `description` VARCHAR(2000) NOT NULL,
  `pickup_flag` BIT(1) NULL DEFAULT NULL,
  `post_flag` BIT(1) NULL DEFAULT NULL,
  `pickup_location` VARCHAR(100) NULL DEFAULT NULL,
  `postage_price` VARCHAR(100) NULL DEFAULT NULL,
  `status` INT(11) NOT NULL,
  `deleted` BIT(1) NOT NULL,
  `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_updated_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_updated_by` INT(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `myfmadev`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myfmadev`.`role` ;

CREATE TABLE IF NOT EXISTS `myfmadev`.`role` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `version` INT(11) NOT NULL,
  `role_name` VARCHAR(100) NOT NULL,
  `role_description` VARCHAR(400) NOT NULL,
  `email` VARCHAR(200) NULL DEFAULT NULL,
  `deleted` BIT(1) NOT NULL,
  `last_updated_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_updated_by` INT(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `role_updateby_fk`
    FOREIGN KEY (`last_updated_by`)
    REFERENCES `myfmadev`.`fma_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `role_updateby_fk_idx` ON `myfmadev`.`role` (`last_updated_by` ASC);


-- -----------------------------------------------------
-- Table `myfmadev`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myfmadev`.`user_role` ;

CREATE TABLE IF NOT EXISTS `myfmadev`.`user_role` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `version` INT(11) NOT NULL,
  `fma_user_id` INT(10) UNSIGNED NOT NULL,
  `role_id` INT(10) UNSIGNED NOT NULL,
  `deleted` BIT(1) NOT NULL,
  `last_updated_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_updated_by` INT(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `userrole_role_fk`
    FOREIGN KEY (`role_id`)
    REFERENCES `myfmadev`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `userrole_updateby_fk`
    FOREIGN KEY (`last_updated_by`)
    REFERENCES `myfmadev`.`fma_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `userrole_user_fk`
    FOREIGN KEY (`fma_user_id`)
    REFERENCES `myfmadev`.`fma_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `updateby_fk_idx` ON `myfmadev`.`user_role` (`last_updated_by` ASC);

CREATE INDEX `user_fk_idx` ON `myfmadev`.`user_role` (`fma_user_id` ASC);

CREATE INDEX `role_fk_idx` ON `myfmadev`.`user_role` (`role_id` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
