CREATE SCHEMA IF NOT EXISTS MovieRecommendations;
USE MovieRecommendations;

# Drop tables if necessary.
-- DROP TABLE IF EXISTS Companies;

# Create tables if necessary.
-- CREATE TABLE Companies (
--   CompanyId INT,
--   CompanyName VARCHAR(255),
--   CONSTRAINT pk_Companies_CompanyId PRIMARY KEY (CompanyId)
-- );

INSERT IGNORE INTO Companies (
	  CompanyId,
      CompanyName
      )
with raw_data as (
select 
    json_extract(company, '$.id') as company_id,
    json_extract(company, '$.name') as company_name
from (
SELECT 
    json_extract(replace(Company, '''', '"'), '$[0]') company
FROM MovieRecommendations.OriginalMovies
where  
	json_valid(replace(Company, '''', '"')) and json_extract(replace(Company, '''', '"'), '$[0]') is not null
    
union
SELECT 
    json_extract(replace(Company, '''', '"'), '$[1]') company
FROM MovieRecommendations.OriginalMovies
where  
	json_valid(replace(Company, '''', '"')) and json_extract(replace(Company, '''', '"'), '$[1]') is not null

union
SELECT 
    json_extract(replace(Company, '''', '"'), '$[2]') company
FROM MovieRecommendations.OriginalMovies
where  
	json_valid(replace(Company, '''', '"')) and json_extract(replace(Company, '''', '"'), '$[2]') is not null
    
union
SELECT 
    json_extract(replace(Company, '''', '"'), '$[3]') company
FROM MovieRecommendations.OriginalMovies
where  
	json_valid(replace(Company, '''', '"')) and json_extract(replace(Company, '''', '"'), '$[3]') is not null
    
union
SELECT 
    json_extract(replace(Company, '''', '"'), '$[4]') company
FROM MovieRecommendations.OriginalMovies
where  
	json_valid(replace(Company, '''', '"')) and json_extract(replace(Company, '''', '"'), '$[4]') is not null

) hehe
)
SELECT company_id,
       company_name
FROM raw_data
ORDER BY company_id ASC 