CREATE SCHEMA IF NOT EXISTS MovieRecommendations;
USE MovieRecommendations;

# Drop tables if necessary.
-- DROP TABLE IF EXISTS Countries;

# Create tables if necessary.
-- CREATE TABLE Countries (
--   CountryId VARCHAR(6),
--   CountryName VARCHAR(255),
--   CONSTRAINT pk_Countries_CountryId PRIMARY KEY (CountryId)
-- );

INSERT IGNORE INTO Countries (
	  CountryId,
      CountryName
      )
with raw_data as (
select 
    json_extract(Country, '$.iso_3166_1') as country_id,
    json_extract(Country, '$.name') as country_name
from (
SELECT 
    json_extract(replace(Country, '''', '"'), '$[0]') country
FROM MovieRecommendations.OriginalMovies
where  
	json_valid(replace(Country, '''', '"')) and json_extract(replace(Country, '''', '"'), '$[0]') is not null
    
union
SELECT 
    json_extract(replace(Country, '''', '"'), '$[1]') country
FROM MovieRecommendations.OriginalMovies
where  
	json_valid(replace(Country, '''', '"')) and json_extract(replace(Country, '''', '"'), '$[1]') is not null

union
SELECT 
    json_extract(replace(Country, '''', '"'), '$[2]') country
FROM MovieRecommendations.OriginalMovies
where  
	json_valid(replace(Country, '''', '"')) and json_extract(replace(Country, '''', '"'), '$[2]') is not null
    
union
SELECT 
    json_extract(replace(Country, '''', '"'), '$[3]') country
FROM MovieRecommendations.OriginalMovies
where  
	json_valid(replace(Country, '''', '"')) and json_extract(replace(Country, '''', '"'), '$[3]') is not null
    
union
SELECT 
    json_extract(replace(Country, '''', '"'), '$[4]') country
FROM MovieRecommendations.OriginalMovies
where  
	json_valid(replace(Country, '''', '"')) and json_extract(replace(Country, '''', '"'), '$[4]') is not null

) hehe
)
SELECT country_id,
       country_name
FROM raw_data
ORDER BY country_id ASC 