CREATE SCHEMA IF NOT EXISTS MovieRecommendations;
USE MovieRecommendations;
SET FOREIGN_KEY_CHECKS=0;

# Drop tables if necessary.
DROP TABLE IF EXISTS Movies_Genres;
DROP TABLE IF EXISTS Genres;

# Create tables if necessary.
CREATE TABLE Genres (
  GenreId INT,
  GenreName VARCHAR(255),
  CONSTRAINT pk_Genres_GenreId PRIMARY KEY (GenreId)
);

CREATE TABLE Movies_Genres (
  ReshareId INT AUTO_INCREMENT,
  MovieId INT,
  GenreId INT,
  CONSTRAINT pk_Movies_Genres_ReshareId PRIMARY KEY (ReshareId),
  CONSTRAINT fk_Movies_Genres_MovieId FOREIGN KEY(MovieId)
  REFERENCES Movies(MovieId)
  ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Movies_Genres_GenreId FOREIGN KEY(GenreId)
  REFERENCES Genres(GenreId)
  ON UPDATE CASCADE ON DELETE SET NULL
);

INSERT INTO Movies_Genres (
      MovieId,
	  GenreId
      ) 
with raw_data as (
select 
	MovieID,
    json_extract(genre, '$.id') as genre_id
    -- json_extract(genre, '$.name') as genre_name
from (
SELECT 
	MovieId,
    json_extract(replace(genres, '''', '"'), '$[0]') genre
FROM MovieRecommendations.OriginalMovies
where  
	json_extract(replace(genres, '''', '"'), '$[0]') is not null

union 

SELECT 
	MovieId,
    json_extract(replace(genres, '''', '"'), '$[1]') genre
FROM MovieRecommendations.OriginalMovies
where  
	json_extract(replace(genres, '''', '"'), '$[1]') is not null
    
    
union 

SELECT 
	MovieId,
    json_extract(replace(genres, '''', '"'), '$[2]') genre
FROM MovieRecommendations.OriginalMovies
where  
	json_extract(replace(genres, '''', '"'), '$[2]') is not null
    
union 

SELECT 
	MovieId,
    json_extract(replace(genres, '''', '"'), '$[3]') genre
FROM MovieRecommendations.OriginalMovies
where  
	json_extract(replace(genres, '''', '"'), '$[3]') is not null

union 

SELECT 
	MovieId,
    json_extract(replace(genres, '''', '"'), '$[4]') genre
FROM MovieRecommendations.OriginalMovies
where  
	json_extract(replace(genres, '''', '"'), '$[4]') is not null

union 

SELECT 
	MovieId,
    json_extract(replace(genres, '''', '"'), '$[5]') genre
FROM MovieRecommendations.OriginalMovies
where  
	json_extract(replace(genres, '''', '"'), '$[5]') is not null

union 

SELECT 
	MovieId,
    json_extract(replace(genres, '''', '"'), '$[6]') genre
FROM MovieRecommendations.OriginalMovies
where  
	json_extract(replace(genres, '''', '"'), '$[6]') is not null

union 

SELECT
	MovieId,
    json_extract(replace(genres, '''', '"'), '$[7]') genre
FROM MovieRecommendations.OriginalMovies
where  
	json_extract(replace(genres, '''', '"'), '$[7]') is not null
) hehe
)
SELECT MovieId,
	   genre_id
FROM raw_data
ORDER BY MovieId ASC 