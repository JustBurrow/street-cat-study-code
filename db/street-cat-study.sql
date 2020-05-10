-- MySQL Script generated by MySQL Workbench
-- Sun May 10 18:43:13 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema street-cat-study
-- -----------------------------------------------------
-- 길고양이 연구.
DROP SCHEMA IF EXISTS `street-cat-study` ;

-- -----------------------------------------------------
-- Schema street-cat-study
--
-- 길고양이 연구.
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `street-cat-study` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci ;
SHOW WARNINGS;
USE `street-cat-study` ;

-- -----------------------------------------------------
-- Table `street-cat-study`.`cats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `street-cat-study`.`cats` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `chip_id` VARCHAR(45) NOT NULL,
  `device_id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(128) NOT NULL,
  `memo` TEXT NULL,
  `archived_at` DATETIME NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_cats_join` (`chip_id` ASC, `device_id` ASC),
  INDEX `idx_cats_name` (`name` ASC),
  INDEX `idx_cats_archived_at` (`archived_at` ASC))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `street-cat-study`.`uses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `street-cat-study`.`uses` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `chip_id` VARCHAR(45) NOT NULL,
  `device_id` VARCHAR(45) NOT NULL,
  `type` ENUM('FOOD', 'WATER', 'WEIGHT', 'TIME') NOT NULL,
  `value` INT NOT NULL,
  `measured_at` DATETIME(3) NOT NULL,
  `created_at` DATETIME(3) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_uses` (`measured_at` ASC, `chip_id` ASC, `device_id` ASC))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `street-cat-study`.`devices`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `street-cat-study`.`devices` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `device_id` VARCHAR(45) NOT NULL,
  `type` ENUM('FOOD', 'WATER', 'SCALE') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_device_id` (`device_id` ASC))
ENGINE = InnoDB;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;