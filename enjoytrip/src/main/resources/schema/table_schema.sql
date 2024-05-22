-- MySQL Script generated by MySQL Workbench
-- Thu May  9 11:59:58 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema enjoytrip
-- -----------------------------------------------------
-- mini&seuli enjoytrip project database ERD
-- 

-- -----------------------------------------------------
-- Schema enjoytrip
--
-- mini&seuli enjoytrip project database ERD
-- 
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `enjoytrip` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `enjoytrip` ;

-- -----------------------------------------------------
-- Table `enjoytrip`.`member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`member` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`member` (
  `member_id` VARCHAR(20) NOT NULL,
  `member_password` VARCHAR(80) NOT NULL,
  `member_name` VARCHAR(20) NOT NULL,
  `member_email_id` VARCHAR(20) NOT NULL,
  `member_email_domain` VARCHAR(20) NOT NULL,
  `member_birthdate` VARCHAR(10) NULL,
  `token` VARCHAR(1000) NULL,
  `member_image_name` TEXT NULL,
  `member_image_url` TEXT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE INDEX `membercol_UNIQUE` (`member_id` ASC) )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `enjoytrip`.`hotplace`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`hotplace` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`hotplace` (
  `hotplace_id` VARCHAR(20) NOT NULL,
  `hotplace_name` VARCHAR(100) NOT NULL,
  `hotplace_category` VARCHAR(20) NOT NULL,
  `hotplace_lag` DOUBLE NOT NULL,
  `hotplace_lat` DOUBLE NOT NULL,
  `hotplace_address` VARCHAR(60) NOT NULL,
  `hotplace_phone` VARCHAR(20) NULL,
  `hotplace_createdAt` TIMESTAMP NOT NULL DEFAULT current_timestamp,
  `hotplace_deletedAt` TIMESTAMP NULL,
  
  PRIMARY KEY (`hotplace_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`review` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`review` (
  `hotplace_id` VARCHAR(20) NOT NULL,
  `member_id` VARCHAR(20) NOT NULL,
  `score` INT NULL,
  `comment` TEXT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT current_timestamp,
  `review_image_name` TEXT NULL,
  `review_image_url` TEXT NULL,
  `helpful_count` INT DEFAULT 0,
  PRIMARY KEY (`hotplace_id`, `member_id`),
  INDEX `member_id_idx` (`member_id` ASC),
  CONSTRAINT `hotplace_id`
    FOREIGN KEY (`hotplace_id`)
    REFERENCES `enjoytrip`.`hotplace` (`hotplace_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `member_id`
    FOREIGN KEY (`member_id`)
    REFERENCES `enjoytrip`.`member` (`member_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

DROP TABLE IF EXISTS `enjoytrip`.`helpful` ;
CREATE TABLE IF NOT EXISTS `enjoytrip`.`helpful`(
	`hotplace_id` VARCHAR(20) NOT NULL,
	`writer_id` VARCHAR(20) NOT NULL,
    `member_id` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`hotplace_id`, `writer_id`, `member_id`),
    CONSTRAINT `helpful_hotplace_id`
		FOREIGN KEY (`hotplace_id`)
		REFERENCES `enjoytrip`.`review`(`hotplace_id`)
		ON DELETE CASCADE
		ON UPDATE NO ACTION,
    CONSTRAINT `helpful_writer_id`
		FOREIGN KEY (`writer_id`)
		REFERENCES `enjoytrip`.`review` (`member_id`)
		ON DELETE CASCADE
		ON UPDATE NO ACTION,
	CONSTRAINT `helpful_member_id`
		FOREIGN KEY (`member_id`)
		REFERENCES `enjoytrip`.`member` (`member_id`)
		ON DELETE CASCADE
		ON UPDATE NO ACTION
);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

select * from hotplace;
