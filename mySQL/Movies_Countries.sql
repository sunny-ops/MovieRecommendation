CREATE SCHEMA IF NOT EXISTS MovieRecommendations;
USE MovieRecommendations;
SET FOREIGN_KEY_CHECKS=0;

# Drop tables if necessary.
DROP TABLE IF EXISTS Movies_Countries;
DROP TABLE IF EXISTS Countries;

# Create tables if necessary.
CREATE TABLE Countries (
  CountryId VARCHAR(6),
  CountryName VARCHAR(255),
  CONSTRAINT pk_Countries_CountryId PRIMARY KEY (CountryId)
);

CREATE TABLE Movies_Countries (
  ReshareId INT AUTO_INCREMENT,
  MovieId INT,
  CountryId VARCHAR(6),
  CONSTRAINT pk_Movies_Countries_ReshareId PRIMARY KEY (ReshareId),
  CONSTRAINT fk_Movies_Countries_MovieId FOREIGN KEY(MovieId)
  REFERENCES Movies(MovieId)
  ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Movies_Countries_CountryId FOREIGN KEY(CountryId)
  REFERENCES Countries(CountryId)
  ON UPDATE CASCADE ON DELETE SET NULL
);

INSERT INTO Movies_Countries (
      MovieId,
	  CountryId
      ) 
with raw_data as (
select 
	MovieID,
    json_extract(Country, '$.iso_3166_1') as country_id
from (
SELECT 
	MovieId,
    json_extract(replace(Country, '''', '"'), '$[0]') country
FROM MovieRecommendations.OriginalMovies
where  
	json_valid(replace(Country, '''', '"')) and json_extract(replace(Country, '''', '"'), '$[0]') is not null

union 

SELECT 
	MovieId,
    json_extract(replace(Country, '''', '"'), '$[1]') country
FROM MovieRecommendations.OriginalMovies
where  
	json_valid(replace(Country, '''', '"')) and json_extract(replace(Country, '''', '"'), '$[1]') is not null

union 

SELECT 
	MovieId,
    json_extract(replace(Country, '''', '"'), '$[2]') country
FROM MovieRecommendations.OriginalMovies
where  
	json_valid(replace(Country, '''', '"')) and json_extract(replace(Country, '''', '"'), '$[2]') is not null


union
SELECT 
	MovieId,
    json_extract(replace(Country, '''', '"'), '$[3]') country
FROM MovieRecommendations.OriginalMovies
where  
	json_valid(replace(Country, '''', '"')) and json_extract(replace(Country, '''', '"'), '$[3]') is not null

union 

SELECT 
	MovieId,
    json_extract(replace(Country, '''', '"'), '$[4]') country
FROM MovieRecommendations.OriginalMovies
where  
	json_valid(replace(Country, '''', '"')) and json_extract(replace(Country, '''', '"'), '$[4]') is not null
) hehe
)
SELECT MovieId,
	   Country_id
FROM raw_data
ORDER BY MovieId ASC 