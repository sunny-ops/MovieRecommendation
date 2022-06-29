CREATE SCHEMA IF NOT EXISTS MovieRecommendations;
USE MovieRecommendations;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Administrators;
DROP TABLE IF EXISTS Persons;
DROP TABLE IF EXISTS AdministratorsOriginal;
DROP TABLE IF EXISTS UsersOriginal;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE Persons (
    UserId INT auto_increment,
    CONSTRAINT pk_Persons_UserId PRIMARY KEY (UserId)
);

CREATE TABLE AdministratorsOriginal (
    UserId INT,
    LastUnixTime INT,
    CONSTRAINT pk_Administrators_UserId PRIMARY KEY (UserId)
);

CREATE TABLE Administrators(
    UserId INT,
    LastLogIn TIMESTAMP,
    CONSTRAINT pk_Administrators_UserId PRIMARY KEY (UserId),
    CONSTRAINT fk_Administrators_UserId FOREIGN KEY (UserId)
        REFERENCES Persons (UserId)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE UsersOriginal (
    UserId INT,
    LastUnixTime INT,
    CONSTRAINT pk_Users_UserId PRIMARY KEY (UserId)
);

CREATE TABLE Users (
    UserId INT,
    LastLogin TIMESTAMP,
    CONSTRAINT pk_Users_UserId PRIMARY KEY (UserId),
    CONSTRAINT fk_Users_UserId FOREIGN KEY (UserId)
        REFERENCES Persons (UserId)
        ON UPDATE CASCADE ON DELETE CASCADE
);




LOAD DATA LOCAL INFILE '/Users/shi/NEU/5200/Project/data/Persons.csv' into TABLE Persons
  FIELDS TERMINATED BY ',' ENCLOSED BY '"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;

LOAD DATA LOCAL INFILE '/Users/shi/NEU/5200/Project/data/Administrators.csv' into TABLE AdministratorsOriginal
  FIELDS TERMINATED BY ',' ENCLOSED BY '"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;


LOAD DATA LOCAL INFILE '/Users/shi/NEU/5200/Project/data/Users.csv' into TABLE UsersOriginal
  FIELDS TERMINATED BY ',' ENCLOSED BY '"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;

SELECT * FROM UsersOriginal;

INSERT Users SELECT UserId, FROM_UNIXTIME(LastUnixTime) FROM UsersOriginal;
INSERT Administrators SELECT UserId, FROM_UNIXTIME(LastUnixTime) FROM AdministratorsOriginal;



  
SELECT * FROM Persons;
SELECT * FROM Users;
SELECT * FROM Administrators;