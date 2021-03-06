-- MySQL Script generated by MySQL Workbench
-- Sun May  6 03:19:56 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema ChampionsLeague
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ChampionsLeague
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ChampionsLeague` DEFAULT CHARACTER SET utf8 ;
USE `ChampionsLeague` ;

-- -----------------------------------------------------
-- Table `ChampionsLeague`.`PERSON_TYPE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChampionsLeague`.`PERSON_TYPE` (
  `PersonTypeID` INT NOT NULL,
  `PersonType` VARCHAR(45) NOT NULL COMMENT '\'Player\', \'Manager\', or \'Referee\'',
  PRIMARY KEY (`PersonTypeID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ChampionsLeague`.`PERSON`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChampionsLeague`.`PERSON` (
  `PersonID` INT NOT NULL,
  `PersonType` INT NULL,
  `FirstName` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NULL,
  `Age` INT NULL,
  `Nationality` VARCHAR(45) NULL,
  PRIMARY KEY (`PersonID`),
  INDEX `PersonType_idx` (`PersonType` ASC),
  CONSTRAINT `PersonType`
    FOREIGN KEY (`PersonType`)
    REFERENCES `ChampionsLeague`.`PERSON_TYPE` (`PersonTypeID`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ChampionsLeague`.`GROUP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChampionsLeague`.`GROUP` (
  `GroupID` CHAR(1) NOT NULL,
--  `WinnerTeam` INT NULL DEFAULT NULL,
--  `RunnerupTeam` INT NULL DEFAULT NULL,
--  INDEX `WinnerTeam_idx` (`WinnerTeam` ASC),
--  INDEX `RunnerupTeam_idx` (`RunnerupTeam` ASC),
	PRIMARY KEY (`GroupID`))
--  CONSTRAINT `WinnerTeam`
--    FOREIGN KEY (`WinnerTeam`)
--    REFERENCES `ChampionsLeague`.`TEAM` (`TeamID`)
--    ON DELETE SET NULL
--    ON UPDATE SET NULL,
--  CONSTRAINT `Runner-upTeam`
--    FOREIGN KEY (`RunnerupTeam`)
--    REFERENCES `ChampionsLeague`.`TEAM` (`TeamID`)
--    ON DELETE SET NULL
--    ON UPDATE SET NULL)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ChampionsLeague`.`TEAM`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChampionsLeague`.`TEAM` (
  `TeamID` INT NOT NULL,
  `ManagerID` INT NULL,
  `GroupID` CHAR(1) NOT NULL,
  `TeamName` VARCHAR(45) NOT NULL,
  `TeamNationality` VARCHAR(45) NOT NULL,
  `Wins` INT NOT NULL DEFAULT 0,
  `Losses` INT NOT NULL DEFAULT 0,
  `Draws` INT NOT NULL DEFAULT 0,
  `GoalsFor` INT NOT NULL DEFAULT 0,
  `GoalsAgainst` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`TeamID`),
  INDEX `GroupID_idx` (`GroupID` ASC),
  INDEX `ManagerID_idx` (`ManagerID` ASC),
  CONSTRAINT `GroupID`
    FOREIGN KEY (`GroupID`)
    REFERENCES `ChampionsLeague`.`GROUP` (`GroupID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `ManagerID`
    FOREIGN KEY (`ManagerID`)
    REFERENCES `ChampionsLeague`.`PERSON` (`PersonID`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ChampionsLeague`.`POSITION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChampionsLeague`.`POSITION` (
  `PositionID` INT NOT NULL,
  `Position` VARCHAR(45) NULL COMMENT '\'Goalkeeper\', \'Defender\', \'Midfielder\', or \'Forward\'',
  PRIMARY KEY (`PositionID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ChampionsLeague`.`PLAYER_ROLE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChampionsLeague`.`PLAYER_ROLE` (
  `PlayerRoleID` INT NOT NULL,
  `PlayerRole` VARCHAR(45) NULL COMMENT '\'Starter\', \'Substitute\', or \'Reserve\'',
  PRIMARY KEY (`PlayerRoleID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ChampionsLeague`.`STATUS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChampionsLeague`.`STATUS` (
  `StatusID` INT NOT NULL,
  `Status` VARCHAR(45) NULL COMMENT '\'Available\', \'Injured\', \'Suspended\'',
  PRIMARY KEY (`StatusID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ChampionsLeague`.`PLAYER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChampionsLeague`.`PLAYER` (
  `PlayerID` INT NOT NULL,
  `PersonID` INT NOT NULL,
  `TeamID` INT NOT NULL,
  `FieldPosition` INT NULL DEFAULT NULL,
  `PlayerRole` INT NULL DEFAULT NULL,
  `Status` INT NULL DEFAULT 1,
  `Goals` INT NOT NULL DEFAULT 0,
  `Assists` INT NOT NULL DEFAULT 0,
  `RedCards` INT NOT NULL DEFAULT 0,
  `YellowCards` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`PlayerID`),
  INDEX `TeamID_idx` (`TeamID` ASC),
  INDEX `PersonID_idx` (`PersonID` ASC),
  INDEX `FieldPosition_idx` (`FieldPosition` ASC),
  INDEX `PlayerRole_idx` (`PlayerRole` ASC),
  INDEX `Status_idx` (`Status` ASC),
  ------------------------------------------------------------------------------------------
  INDEX `GoalsAssists_idx` (`Goals` DESC, `Assists` DESC),----------------------------------
  INDEX `AssistsGoals_idx` (`Assists` DESC, `Goals` DESC),----------------------------------
  ------------------------------------------------------------------------------------------
  CONSTRAINT `TeamID`
    FOREIGN KEY (`TeamID`)
    REFERENCES `ChampionsLeague`.`TEAM` (`TeamID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `Player_PersonID`
    FOREIGN KEY (`PersonID`)
    REFERENCES `ChampionsLeague`.`PERSON` (`PersonID`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `FieldPosition`
    FOREIGN KEY (`FieldPosition`)
    REFERENCES `ChampionsLeague`.`POSITION` (`PositionID`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `PlayerRole`
    FOREIGN KEY (`PlayerRole`)
    REFERENCES `ChampionsLeague`.`PLAYER_ROLE` (`PlayerRoleID`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `Status`
    FOREIGN KEY (`Status`)
    REFERENCES `ChampionsLeague`.`STATUS` (`StatusID`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ChampionsLeague`.`MATCH_TYPE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChampionsLeague`.`MATCH_TYPE` (
  `MatchTypeID` INT NOT NULL,
  `MatchType` VARCHAR(45) NOT NULL COMMENT '\'Group Stage\', \'Round of 16\', \'Quarter-final\', \'Semi-final\', or \'Final\'',
  PRIMARY KEY (`MatchTypeID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ChampionsLeague`.`MATCH`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChampionsLeague`.`MATCH` (
  `MatchID` INT NOT NULL,
  `MatchType` INT NULL COMMENT '\'Group Stage\', \'Round of 16\', \'Quarter-final\', \'Semi-final\', or \'Final\'',
  `RefereeID` INT NULL DEFAULT NULL,
  `Location` VARCHAR(45) NULL DEFAULT NULL,
  `Date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`MatchID`),
  INDEX `RefereeID_idx` (`RefereeID` ASC),
  INDEX `MatchType_idx` (`MatchType` ASC),
  CONSTRAINT `RefereeID`
    FOREIGN KEY (`RefereeID`)
    REFERENCES `ChampionsLeague`.`PERSON` (`PersonID`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `MatchType`
    FOREIGN KEY (`MatchType`)
    REFERENCES `ChampionsLeague`.`MATCH_TYPE` (`MatchTypeID`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ChampionsLeague`.`AWARD`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChampionsLeague`.`AWARD` (
  `AwardID` INT NOT NULL,
  `PersonID` INT NULL DEFAULT NULL,
  `AwardName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`AwardID`),
  INDEX `PersonID_idx` (`PersonID` ASC),
  CONSTRAINT `Award_PersonID`
    FOREIGN KEY (`PersonID`)
    REFERENCES `ChampionsLeague`.`PERSON` (`PersonID`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ChampionsLeague`.`TEAM_plays_MATCH`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChampionsLeague`.`TEAM_plays_MATCH` (
  `TeamMatchID` INT NOT NULL,
  `MatchID` INT NOT NULL,
  `TeamID` INT NOT NULL,
  `GoalsScored` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`TeamMatchID`),
  INDEX `MatchNo_idx` (`MatchID` ASC),
  INDEX `TeamID_idx` (`TeamID` ASC),
  CONSTRAINT `Match_ID`
    FOREIGN KEY (`MatchID`)
    REFERENCES `ChampionsLeague`.`MATCH` (`MatchID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `Team_ID`
    FOREIGN KEY (`TeamID`)
    REFERENCES `ChampionsLeague`.`TEAM` (`TeamID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- ----------------------------------------------------------- --
-- ------------------- GROUP ALTERS -------------------------- --
-- ----------------------------------------------------------- --
ALTER TABLE ChampionsLeague.GROUP
ADD (WinnerTeam INT NULL DEFAULT NULL,
	 RunnerupTeam INT NULL DEFAULT NULL);
        
ALTER TABLE ChampionsLeague.GROUP        
ADD	INDEX WinnerTeam_idx (WinnerTeam ASC);

ALTER TABLE ChampionsLeague.GROUP        
ADD	INDEX RunnerupTeam_idx (RunnerupTeam ASC);

ALTER TABLE ChampionsLeague.GROUP        
ADD	CONSTRAINT WinnerTeam
	FOREIGN KEY (WinnerTeam)
	REFERENCES ChampionsLeague.TEAM (TeamID)
		ON DELETE SET NULL
		ON UPDATE CASCADE;

ALTER TABLE ChampionsLeague.GROUP        
ADD	CONSTRAINT RunnerupTeam
	FOREIGN KEY (RunnerupTeam)
	REFERENCES ChampionsLeague.TEAM (TeamID)
		ON DELETE SET NULL
		ON UPDATE CASCADE;