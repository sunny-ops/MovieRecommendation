CREATE SCHEMA IF NOT EXISTS MovieRecommendations;
USE MovieRecommendations;

# Drop tables if necessary.
-- DROP TABLE IF EXISTS Genres;

-- # Create tables if necessary.
-- CREATE TABLE Genres (
--   GenreId INT,
--   GenreName VARCHAR(255),
--   CONSTRAINT pk_Genres_GenreId PRIMARY KEY (GenreId)
-- );

INSERT IGNORE INTO Genres (
	  GenreId,
      GenreName
      )
with raw_data as (
select 
    json_extract(genre, '$.id') as genre_id,
    json_extract(genre, '$.name') as genre_name
from (
SELECT 
    json_extract(replace(genres, '''', '"'), '$[0]') genre
FROM MovieRecommendations.OriginalMovies
where  
	json_extract(replace(genres, '''', '"'), '$[0]') is not null

union 

SELECT 
    json_extract(replace(genres, '''', '"'), '$[1]') genre
FROM MovieRecommendations.OriginalMovies
where  
	json_extract(replace(genres, '''', '"'), '$[1]') is not null
    
    
union 

SELECT 
    json_extract(replace(genres, '''', '"'), '$[2]') genre
FROM MovieRecommendations.OriginalMovies
where  
	json_extract(replace(genres, '''', '"'), '$[2]') is not null
    
union 

SELECT
    json_extract(replace(genres, '''', '"'), '$[3]') genre
FROM MovieRecommendations.OriginalMovies
where  
	json_extract(replace(genres, '''', '"'), '$[3]') is not null

union 

SELECT
    json_extract(replace(genres, '''', '"'), '$[4]') genre
FROM MovieRecommendations.OriginalMovies
where  
	json_extract(replace(genres, '''', '"'), '$[4]') is not null

union 

SELECT 
    json_extract(replace(genres, '''', '"'), '$[5]') genre
FROM MovieRecommendations.OriginalMovies
where  
	json_extract(replace(genres, '''', '"'), '$[5]') is not null

union 

SELECT 
    json_extract(replace(genres, '''', '"'), '$[6]') genre
FROM MovieRecommendations.OriginalMovies
where  
	json_extract(replace(genres, '''', '"'), '$[6]') is not null

union 

SELECT
    json_extract(replace(genres, '''', '"'), '$[7]') genre
FROM MovieRecommendations.OriginalMovies
where  
	json_extract(replace(genres, '''', '"'), '$[7]') is not null
) hehe
)
SELECT genre_id,
       genre_name
FROM raw_data
ORDER BY genre_id ASC 