CREATE SCHEMA IF NOT EXISTS MovieRecommendations;
USE MovieRecommendations;
SET FOREIGN_KEY_CHECKS=0;

# Drop tables if necessary.
DROP TABLE IF EXISTS Movies_Companies;
DROP TABLE IF EXISTS Companies;

# Create tables if necessary.
CREATE TABLE Companies (
  CompanyId INT,
  CompanyName VARCHAR(255),
  CONSTRAINT pk_Companies_CompanyId PRIMARY KEY (CompanyId)
);

CREATE TABLE Movies_Companies (
  ReshareId INT AUTO_INCREMENT,
  MovieId INT,
  CompanyId INT,
  CONSTRAINT pk_Movies_Companies_ReshareId PRIMARY KEY (ReshareId),
  CONSTRAINT fk_Movies_Companies_MovieId FOREIGN KEY(MovieId)
  REFERENCES Movies(MovieId)
  ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Movies_Companies_CompanyId FOREIGN KEY(CompanyId)
  REFERENCES Companies(CompanyId)
  ON UPDATE CASCADE ON DELETE SET NULL
);

INSERT INTO Movies_Companies (
      MovieId,
	  CompanyId
      ) 
with raw_data as (
select 
	MovieID,
    json_extract(Company, '$.id') as company_id
    -- json_extract(genre, '$.name') as company_name
from (
SELECT 
	MovieId,
    json_extract(replace(Company, '''', '"'), '$[0]') company
FROM MovieRecommendations.OriginalMovies
where  
	json_valid(replace(Company, '''', '"')) and json_extract(replace(Company, '''', '"'), '$[0]') is not null

union 

SELECT 
	MovieId,
    json_extract(replace(Company, '''', '"'), '$[1]') company
FROM MovieRecommendations.OriginalMovies
where  
	json_valid(replace(Company, '''', '"')) and json_extract(replace(Company, '''', '"'), '$[1]') is not null

union 

SELECT 
	MovieId,
    json_extract(replace(Company, '''', '"'), '$[2]') company
FROM MovieRecommendations.OriginalMovies
where  
	json_valid(replace(Company, '''', '"')) and json_extract(replace(Company, '''', '"'), '$[2]') is not null


union
SELECT 
	MovieId,
    json_extract(replace(Company, '''', '"'), '$[3]') company
FROM MovieRecommendations.OriginalMovies
where  
	json_valid(replace(Company, '''', '"')) and json_extract(replace(Company, '''', '"'), '$[3]') is not null

union 

SELECT 
	MovieId,
    json_extract(replace(Company, '''', '"'), '$[4]') company
FROM MovieRecommendations.OriginalMovies
where  
	json_valid(replace(Company, '''', '"')) and json_extract(replace(Company, '''', '"'), '$[4]') is not null
) hehe
)
SELECT MovieId,
	   company_id
FROM raw_data
ORDER BY MovieId ASC 