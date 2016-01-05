-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`USER` (
  `USR_ID` INT NOT NULL,
  `USR_NAME` VARCHAR(50) NOT NULL,
  `USR_EMAIL` VARCHAR(50) NOT NULL,
  `USR_PASSWORD` VARCHAR(50) NOT NULL,
  `USR_GENDER` CHAR(1) NULL DEFAULT NULL,
  `USR_BDAY` DATE NULL DEFAULT NULL,
  `USR_TYPE` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`USR_ID`));


-- -----------------------------------------------------
-- Table `mydb`.`BRAND`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`BRAND` (
  `BRAND_ID` VARCHAR(50) NOT NULL,
  `USR_ID` INT NULL DEFAULT NULL,
  `BRAND_NAME` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`BRAND_ID`),
  INDEX `FK_CREATES` (`USR_ID` ASC),
  CONSTRAINT `FK_CREATES`
    FOREIGN KEY (`USR_ID`)
    REFERENCES `mydb`.`USER` (`USR_ID`)
    ON DELETE restrict
    ON UPDATE restrict);


-- -----------------------------------------------------
-- Table `mydb`.`BRANDTIPS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`BRANDTIPS` (
  `TIP_ID` INT NOT NULL,
  `BRAND_ID` VARCHAR(50) NOT NULL,
  `TIP_TXT` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`TIP_ID`));


-- -----------------------------------------------------
-- Table `mydb`.`PLACE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`PLACE` (
  `PLC_ID` INT NOT NULL,
  `PLC_NAME` VARCHAR(50) NOT NULL,
  `PLC_RATING` DECIMAL(2,2) NOT NULL,
  `PLC_DISC` TEXT NOT NULL,
  `PLC_LONG` TEXT NOT NULL,
  `PLC_LAT` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`PLC_ID`));


-- -----------------------------------------------------
-- Table `mydb`.`BRAND_HAS_PLC`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`BRAND_HAS_PLC` (
  `PLC_ID` INT NOT NULL,
  `BRAND_ID` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`PLC_ID`, `BRAND_ID`),
  INDEX `FK_BRAND_HAS_PLC2` (`BRAND_ID` ASC),
  CONSTRAINT `FK_BRAND_HAS_PLC`
    FOREIGN KEY (`PLC_ID`)
    REFERENCES `mydb`.`PLACE` (`PLC_ID`)
    ON DELETE restrict
    ON UPDATE restrict,
  CONSTRAINT `FK_BRAND_HAS_PLC2`
    FOREIGN KEY (`BRAND_ID`)
    REFERENCES `mydb`.`BRAND` (`BRAND_ID`)
    ON DELETE restrict
    ON UPDATE restrict);


-- -----------------------------------------------------
-- Table `mydb`.`CHAT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CHAT` (
  `CHAT_ID` INT NOT NULL,
  PRIMARY KEY (`CHAT_ID`));


-- -----------------------------------------------------
-- Table `mydb`.`POST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`POST` (
  `POSTID` INT NOT NULL AUTO_INCREMENT,
  `PLC_ID` INT NULL DEFAULT NULL,
  `USR_ID` INT NULL DEFAULT NULL,
  `POSTTEXT` VARCHAR(200) NOT NULL,
  `POSTTIME` DATETIME NOT NULL,
  `POST_LIKES` INT NULL DEFAULT NULL,
  `POSTERID` INT NOT NULL,
  PRIMARY KEY (`POSTID`),
  INDEX `FK_RELATIONSHIP_12` (`PLC_ID` ASC),
  INDEX `FK_WRITES` (`USR_ID` ASC),
  CONSTRAINT `FK_RELATIONSHIP_12`
    FOREIGN KEY (`PLC_ID`)
    REFERENCES `mydb`.`PLACE` (`PLC_ID`)
    ON DELETE restrict
    ON UPDATE restrict,
  CONSTRAINT `FK_WRITES`
    FOREIGN KEY (`USR_ID`)
    REFERENCES `mydb`.`USER` (`USR_ID`)
    ON DELETE restrict
    ON UPDATE restrict);


-- -----------------------------------------------------
-- Table `mydb`.`CHECKIN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CHECKIN` (
  `POSTID` INT NOT NULL,
  `USR_ID` INT NULL DEFAULT NULL,
  `PLC_ID` INT NULL DEFAULT NULL,
  `POSTTEXT` VARCHAR(200) NOT NULL,
  `POSTTIME` DATETIME NOT NULL,
  `POST_LIKES` INT NULL DEFAULT NULL,
  `POSTERID` INT NOT NULL,
  `RATE` DECIMAL(2,2) NULL DEFAULT NULL,
  PRIMARY KEY (`POSTID`),
  CONSTRAINT `FK_INHERITANCE_1`
    FOREIGN KEY (`POSTID`)
    REFERENCES `mydb`.`POST` (`POSTID`)
    ON DELETE restrict
    ON UPDATE restrict);


-- -----------------------------------------------------
-- Table `mydb`.`COMMENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`COMMENT` (
  `POS_POSTID` INT NOT NULL,
  `POSTID` INT NOT NULL,
  PRIMARY KEY (`POS_POSTID`, `POSTID`),
  INDEX `FK_PARENTPOST` (`POSTID` ASC),
  CONSTRAINT `FK_PARENTPOST`
    FOREIGN KEY (`POSTID`)
    REFERENCES `mydb`.`POST` (`POSTID`)
    ON DELETE restrict
    ON UPDATE restrict,
  CONSTRAINT `FK_PARENTPOST2`
    FOREIGN KEY (`POS_POSTID`)
    REFERENCES `mydb`.`POST` (`POSTID`)
    ON DELETE restrict
    ON UPDATE restrict);


-- -----------------------------------------------------
-- Table `mydb`.`FOLLOW`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`FOLLOW` (
  `USR_ID` INT NOT NULL,
  `BRAND_ID` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`USR_ID`, `BRAND_ID`),
  INDEX `FK_FOLLOW2` (`BRAND_ID` ASC),
  CONSTRAINT `FK_FOLLOW`
    FOREIGN KEY (`USR_ID`)
    REFERENCES `mydb`.`USER` (`USR_ID`)
    ON DELETE restrict
    ON UPDATE restrict,
  CONSTRAINT `FK_FOLLOW2`
    FOREIGN KEY (`BRAND_ID`)
    REFERENCES `mydb`.`BRAND` (`BRAND_ID`)
    ON DELETE restrict
    ON UPDATE restrict);


-- -----------------------------------------------------
-- Table `mydb`.`FRIENDS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`FRIENDS` (
  `USE_USR_ID` INT NOT NULL,
  `USR_ID` INT NOT NULL,
  PRIMARY KEY (`USE_USR_ID`, `USR_ID`),
  INDEX `FK_FRIENDS` (`USR_ID` ASC),
  CONSTRAINT `FK_FRIENDS`
    FOREIGN KEY (`USR_ID`)
    REFERENCES `mydb`.`USER` (`USR_ID`)
    ON DELETE restrict
    ON UPDATE restrict,
  CONSTRAINT `FK_FRIENDS2`
    FOREIGN KEY (`USE_USR_ID`)
    REFERENCES `mydb`.`USER` (`USR_ID`)
    ON DELETE restrict
    ON UPDATE restrict);


-- -----------------------------------------------------
-- Table `mydb`.`FRIEND_REQUEST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`FRIEND_REQUEST` (
  `USR_ID` INT NOT NULL,
  `USE_USR_ID` INT NOT NULL,
  PRIMARY KEY (`USR_ID`, `USE_USR_ID`),
  INDEX `FK_FRIEND_REQUEST2` (`USE_USR_ID` ASC),
  CONSTRAINT `FK_FRIEND_REQUEST`
    FOREIGN KEY (`USR_ID`)
    REFERENCES `mydb`.`USER` (`USR_ID`)
    ON DELETE restrict
    ON UPDATE restrict,
  CONSTRAINT `FK_FRIEND_REQUEST2`
    FOREIGN KEY (`USE_USR_ID`)
    REFERENCES `mydb`.`USER` (`USR_ID`)
    ON DELETE restrict
    ON UPDATE restrict);


-- -----------------------------------------------------
-- Table `mydb`.`MESSAGE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`MESSAGE` (
  `CHAT_ID` INT NOT NULL,
  `USR_ID` INT NOT NULL,
  `MESSAGE` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`CHAT_ID`, `USR_ID`),
  INDEX `FK_MESSAGE2` (`USR_ID` ASC),
  CONSTRAINT `FK_MESSAGE`
    FOREIGN KEY (`CHAT_ID`)
    REFERENCES `mydb`.`CHAT` (`CHAT_ID`)
    ON DELETE restrict
    ON UPDATE restrict,
  CONSTRAINT `FK_MESSAGE2`
    FOREIGN KEY (`USR_ID`)
    REFERENCES `mydb`.`USER` (`USR_ID`)
    ON DELETE restrict
    ON UPDATE restrict);


-- -----------------------------------------------------
-- Table `mydb`.`NOTIFICATION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`NOTIFICATION` (
  `NOTI_TXT` INT NOT NULL,
  `NOTI_ID` INT NOT NULL,
  `USR_ID` INT NULL DEFAULT NULL,
  PRIMARY KEY (`NOTI_ID`),
  INDEX `FK_USR_GETS` (`USR_ID` ASC),
  CONSTRAINT `FK_USR_GETS`
    FOREIGN KEY (`USR_ID`)
    REFERENCES `mydb`.`USER` (`USR_ID`)
    ON DELETE restrict
    ON UPDATE restrict);


-- -----------------------------------------------------
-- Table `mydb`.`TASTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TASTE` (
  `TASTE_ID` INT NOT NULL,
  `TASTE` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`TASTE_ID`));


-- -----------------------------------------------------
-- Table `mydb`.`PLC_HAS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`PLC_HAS` (
  `TASTE_ID` INT NOT NULL,
  `PLC_ID` INT NOT NULL,
  PRIMARY KEY (`TASTE_ID`, `PLC_ID`),
  INDEX `FK_PLC_HAS2` (`PLC_ID` ASC),
  CONSTRAINT `FK_PLC_HAS`
    FOREIGN KEY (`TASTE_ID`)
    REFERENCES `mydb`.`TASTE` (`TASTE_ID`)
    ON DELETE restrict
    ON UPDATE restrict,
  CONSTRAINT `FK_PLC_HAS2`
    FOREIGN KEY (`PLC_ID`)
    REFERENCES `mydb`.`PLACE` (`PLC_ID`)
    ON DELETE restrict
    ON UPDATE restrict);


-- -----------------------------------------------------
-- Table `mydb`.`USR_HAS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`USR_HAS` (
  `TASTE_ID` INT NOT NULL,
  `USR_ID` INT NOT NULL,
  PRIMARY KEY (`TASTE_ID`, `USR_ID`),
  INDEX `FK_USR_HAS2` (`USR_ID` ASC),
  CONSTRAINT `FK_USR_HAS`
    FOREIGN KEY (`TASTE_ID`)
    REFERENCES `mydb`.`TASTE` (`TASTE_ID`)
    ON DELETE restrict
    ON UPDATE restrict,
  CONSTRAINT `FK_USR_HAS2`
    FOREIGN KEY (`USR_ID`)
    REFERENCES `mydb`.`USER` (`USR_ID`)
    ON DELETE restrict
    ON UPDATE restrict);


-- -----------------------------------------------------
-- Table `mydb`.`USR_IN_CHAT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`USR_IN_CHAT` (
  `CHAT_ID` INT NOT NULL,
  `USR_ID` INT NOT NULL,
  PRIMARY KEY (`CHAT_ID`, `USR_ID`),
  INDEX `FK_USR_IN_CHAT2` (`USR_ID` ASC),
  CONSTRAINT `FK_USR_IN_CHAT`
    FOREIGN KEY (`CHAT_ID`)
    REFERENCES `mydb`.`CHAT` (`CHAT_ID`)
    ON DELETE restrict
    ON UPDATE restrict,
  CONSTRAINT `FK_USR_IN_CHAT2`
    FOREIGN KEY (`USR_ID`)
    REFERENCES `mydb`.`USER` (`USR_ID`)
    ON DELETE restrict
    ON UPDATE restrict);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
