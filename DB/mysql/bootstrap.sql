SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema comics
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `comics` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `comics` ;

-- -----------------------------------------------------
-- Table `comics`.`Address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comics`.`Address` ;

CREATE TABLE IF NOT EXISTS `comics`.`Address` (
  `idAddress` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NULL,
  `houseNumber` VARCHAR(45) NULL,
  `postalCode` VARCHAR(45) NULL,
  `town` VARCHAR(45) NULL,
  `careOf` VARCHAR(45) NULL,
  `company` VARCHAR(45) NULL,
  PRIMARY KEY (`idAddress`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comics`.`RegistrationDetails`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comics`.`RegistrationDetails` ;

CREATE TABLE IF NOT EXISTS `comics`.`RegistrationDetails` (
  `idRegistrationDetails` INT NOT NULL AUTO_INCREMENT,
  `ip` VARCHAR(45) NULL,
  `date` MEDIUMTEXT NULL,
  `activationKey` VARCHAR(45) NULL,
  PRIMARY KEY (`idRegistrationDetails`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comics`.`ContactDetails`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comics`.`ContactDetails` ;

CREATE TABLE IF NOT EXISTS `comics`.`ContactDetails` (
  `idContactDetails` INT NOT NULL AUTO_INCREMENT,
  `telephoneNumber` VARCHAR(45) NULL,
  `mobileNumber` VARCHAR(45) NULL,
  `fax` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `skypeName` VARCHAR(45) NULL,
  PRIMARY KEY (`idContactDetails`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comics`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comics`.`User` ;

CREATE TABLE IF NOT EXISTS `comics`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `Address_idAddress` INT NOT NULL,
  `RegistrationDetails_idRegistrationDetails` INT NOT NULL,
  `ContactDetails_idContactDetails` INT NOT NULL,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `title` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `role` SET('ROLE_USER', 'ROLE_ADMIN') NULL,
  `username` VARCHAR(45) NULL,
  `status` SET('REGISTRATION_NOT_COMPLETE', 'ACTIVE', 'INACTIVE', 'BLOCKED') NULL,
  `avatarImage` MEDIUMBLOB NULL,
  PRIMARY KEY (`idUser`, `Address_idAddress`, `RegistrationDetails_idRegistrationDetails`, `ContactDetails_idContactDetails`),
  INDEX `fk_User_Address_idx` (`Address_idAddress` ASC),
  INDEX `fk_User_RegistrationDetails1_idx` (`RegistrationDetails_idRegistrationDetails` ASC),
  INDEX `fk_User_ContactDetails1_idx` (`ContactDetails_idContactDetails` ASC),
  CONSTRAINT `fk_User_Address`
    FOREIGN KEY (`Address_idAddress`)
    REFERENCES `comics`.`Address` (`idAddress`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_RegistrationDetails1`
    FOREIGN KEY (`RegistrationDetails_idRegistrationDetails`)
    REFERENCES `comics`.`RegistrationDetails` (`idRegistrationDetails`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_ContactDetails1`
    FOREIGN KEY (`ContactDetails_idContactDetails`)
    REFERENCES `comics`.`ContactDetails` (`idContactDetails`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
