CREATE DATABASE  `CucumberEditor` ;

SET PASSWORD FOR 'root'@'127.0.0.1' = PASSWORD(  'r8MJYUGL6SRaRajt' );
SET PASSWORD FOR  'root'@'localhost' = PASSWORD(  'r8MJYUGL6SRaRajt' )
CREATE USER 'cucumber'@'%' IDENTIFIED BY 'YCdcdf8ddrcWUxW3';
GRANT ALL PRIVILEGES ON `cucumbereditor`.* TO 'cucumber'@'%';

CREATE TABLE IF NOT EXISTS `CucumberEditor`.`Key` (
`ID` INT AUTO_INCREMENT PRIMARY KEY ,
`Name` VARCHAR( 255 ) NOT NULL
);

CREATE TABLE IF NOT EXISTS `CucumberEditor`.`Language` (
    `ID`          INT AUTO_INCREMENT PRIMARY KEY,
    `LanguageKey`	VARCHAR(2) NOT NULL,
	`Language`    VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS `CucumberEditor`.`Keyword` (
    `ID`          INT AUTO_INCREMENT PRIMARY KEY,
    `LanguageId`  INT,
    `KeyId`       INT,
    `Keyword`     VARCHAR(255) NOT NULL,
	FOREIGN KEY (LanguageId) REFERENCES `CucumberEditor`.`Language`(ID) ON DELETE CASCADE,
	FOREIGN KEY (KeyId) REFERENCES `CucumberEditor`.`Key`(ID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `CucumberEditor`.`Sentence` (
    `ID`          INT AUTO_INCREMENT PRIMARY KEY,
    `KeywordID`   INT,
	FOREIGN KEY (KeywordID) REFERENCES `CucumberEditor`.`Keyword`(ID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `CucumberEditor`.`SentenceParts` (
    `ID`          INT AUTO_INCREMENT,
    `SentenceId`   INT NOT NULL,
	`VariablePart` TINYINT(1) NOT NULL DEFAULT 1,
    `SentencePart`    VARCHAR(255) NOT NULL,
    PRIMARY KEY(ID, SentenceId),
	FOREIGN KEY (SentenceId) REFERENCES `CucumberEditor`.`Sentence`(ID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `CucumberEditor`.`UserLanguage` (
	ID		INT AUTO_INCREMENT PRIMARY KEY,
	Name	VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS `CucumberEditor`.`User` (
	`ID`				INT AUTO_INCREMENT PRIMARY KEY,
	`UserId`			VARCHAR(255),
	`Name`				VARCHAR(255) NOT NULL,
	`Email`				VARCHAR(255) NOT NULL UNIQUE,
	`Password`			VARCHAR(255) NOT NULL,
	`Admin`				TINYINT(1) NOT NULL DEFAULT 0,
	`UserLanguageId`	INT NOT NULL,
	FOREIGN KEY (UserLanguageId) REFERENCES `CucumberEditor`.`UserLanguage`(ID) ON DELETE CASCADE
);


