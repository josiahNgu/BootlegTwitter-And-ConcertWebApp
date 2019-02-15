SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE SCHEMA IF NOT EXISTS `jngu` DEFAULT CHARACTER SET utf8mb4 ;
USE `jngu` ;

-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `users` ;

CREATE TABLE IF NOT EXISTS `users` (
  `Id` INT(11) NOT NULL,
  `FirstName` VARCHAR(45) NULL DEFAULT NULL,
  `LastName` VARCHAR(45) NULL DEFAULT NULL,
  `Address` VARCHAR(45) NULL DEFAULT NULL,
  `City` VARCHAR(45) NULL DEFAULT NULL,
  `State` VARCHAR(45) NULL DEFAULT NULL,
  `PostalCode` VARCHAR(45) NULL DEFAULT NULL,
  `EmailAddress` VARCHAR(45) NULL DEFAULT NULL,
  `PhoneNumber` VARCHAR(45) NULL DEFAULT NULL,
  `Birthday` VARCHAR(45) NULL DEFAULT NULL,
  `Type` TINYINT(1) NULL DEFAULT NULL,
  `Status` TINYINT(1) NULL DEFAULT NULL,
  `NumOfVisits` INT(4) NULL DEFAULT NULL,
  `Username` VARCHAR(255) NULL DEFAULT NULL,
  `Password` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- Table `creditcards`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `creditcards` ;

CREATE TABLE IF NOT EXISTS `creditcards` (
  `Id` INT(11) NOT NULL,
  `CardHolderName` VARCHAR(255) NULL DEFAULT NULL,
  `CreditCardNumber` CHAR(20) NOT NULL,
  `Balance` DECIMAL(16,2) NULL DEFAULT NULL,
  `CardType` VARCHAR(45) NULL DEFAULT NULL,
  `UserId` INT(11) NULL DEFAULT NULL,
  `CVV` CHAR(4) NULL DEFAULT NULL,
  `ExpirationDate` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `CreditCardNumber` (`CreditCardNumber` ASC),
  INDEX `UserId` (`UserId` ASC),
  CONSTRAINT `creditcards_ibfk_1`
    FOREIGN KEY (`UserId`)
    REFERENCES `users` (`Id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `concert`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `concert` ;

CREATE TABLE IF NOT EXISTS `concert` (
  `id` INT NOT NULL,
  `Movie name` VARCHAR(45) NULL DEFAULT NULL,
  `Description` VARCHAR(45) NULL DEFAULT NULL,
  `Thumbnail` BLOB NULL DEFAULT NULL,
  `Rating` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `venue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `venue` ;

CREATE TABLE IF NOT EXISTS `venue` (
  `Id` INT(11) NOT NULL,
  `Name` VARCHAR(45) NULL,
  `Address` VARCHAR(45) NULL,
  `ownerID` INT NULL,
  `City` VARCHAR(45) NULL,
  `State` VARCHAR(45) NULL,
  `PostalCode` VARCHAR(45) NULL,
  PRIMARY KEY (`Id`),
  INDEX `ownerID_idx` (`ownerID` ASC),
  CONSTRAINT `ownerID_venue`
    FOREIGN KEY (`ownerID`)
    REFERENCES `users` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `performance`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `performance` ;

CREATE TABLE IF NOT EXISTS `performance` (
  `Id` INT(11) NOT NULL,
  `StartTime` DATETIME NULL DEFAULT NULL,
  `EndTime` DATETIME NULL DEFAULT NULL,
  `concertID` INT NULL,
  `venueID` INT NULL,
  PRIMARY KEY (`Id`),
  INDEX `performanceID_idx` (`concertID` ASC),
  INDEX `venueID_idx` (`venueID` ASC),
  CONSTRAINT `concertID_perf`
    FOREIGN KEY (`concertID`)
    REFERENCES `concert` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `venueID_perf`
    FOREIGN KEY (`venueID`)
    REFERENCES `venue` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `customerreviews`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `customerreviews` ;

CREATE TABLE IF NOT EXISTS `customerreviews` (
  `Id` INT(11) NOT NULL,
  `concertID` INT(11) NULL DEFAULT NULL,
  `userID` INT(11) NULL DEFAULT NULL,
  `ReviewDate` VARCHAR(40) NULL DEFAULT NULL,
  `Rating` TINYINT(1) NULL DEFAULT NULL,
  `Review` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `concertID` (`concertID` ASC),
  INDEX `UserId` (`userID` ASC),
  CONSTRAINT `concertID_cr`
    FOREIGN KEY (`concertID`)
    REFERENCES `concert` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `userID_cr`
    FOREIGN KEY (`userID`)
    REFERENCES `users` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orders` ;

CREATE TABLE IF NOT EXISTS `orders` (
  `Id` INT(11) NOT NULL,
  `CustomerId` INT(11) NULL DEFAULT NULL,
  `TotalCost` INT(11) NULL DEFAULT NULL,
  `OrderDate` VARCHAR(40) NULL DEFAULT NULL,
  `BillingAddress` VARCHAR(255) NULL DEFAULT NULL,
  `CreditCardNumber` CHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `CustomerId` (`CustomerId` ASC),
  CONSTRAINT `CustomerID_orders`
    FOREIGN KEY (`CustomerId`)
    REFERENCES `users` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `orderitems`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orderitems` ;

CREATE TABLE IF NOT EXISTS `orderitems` (
  `Id` INT(11) NOT NULL,
  `OrderId` INT(11) NULL DEFAULT NULL,
  `PerformanceID` INT(11) NULL DEFAULT NULL,
  `Quantity` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `OrderId` (`OrderId` ASC),
  INDEX `ProductId` (`PerformanceID` ASC),
  CONSTRAINT `performanceID_orderitem`
    FOREIGN KEY (`PerformanceID`)
    REFERENCES `performance` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `OrderID_orderitem`
    FOREIGN KEY (`OrderId`)
    REFERENCES `orders` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `Ticket Types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Ticket Types` ;

CREATE TABLE IF NOT EXISTS `Ticket Types` (
  `idTicket Types` INT NOT NULL,
  `SeatName` VARCHAR(45) NULL,
  `Ticket Typescol` VARCHAR(45) NULL,
  PRIMARY KEY (`idTicket Types`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TicketVenuePrices`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TicketVenuePrices` ;

CREATE TABLE IF NOT EXISTS `TicketVenuePrices` (
  `idTicketVenuePrices` INT NOT NULL,
  `TicketPrice` INT NULL,
  `venueID` INT NULL,
  `ticketTypeID` INT NULL,
  `performanceID` INT NULL,
  PRIMARY KEY (`idTicketVenuePrices`),
  INDEX `TicketTypeID_idx` (`ticketTypeID` ASC),
  INDEX `VenueID_idx` (`venueID` ASC),
  INDEX `PerformanceID_idx` (`performanceID` ASC),
  CONSTRAINT `TicketTypeID_tick`
    FOREIGN KEY (`ticketTypeID`)
    REFERENCES `Ticket Types` (`idTicket Types`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `VenueID_tick`
    FOREIGN KEY (`venueID`)
    REFERENCES `venue` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `PerformanceID_tick`
    FOREIGN KEY (`performanceID`)
    REFERENCES `performance` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;