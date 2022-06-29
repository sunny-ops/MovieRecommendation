CREATE SCHEMA IF NOT EXISTS MovieRecommendations;
USE MovieRecommendations;
SET sql_mode = 'STRICT_TRANS_TABLES';
SET sql_mode = 'STRICT_ALL_TABLES';

# Drop tables if necessary.
DROP TABLE IF EXISTS Movies;

# Create tables if necessary.
CREATE TABLE Movies (
  MovieId INT,
  PosterPath VARCHAR(255),
  BackdropPath VARCHAR(255),
  BackupPath VARCHAR(255),
  Homepage VARCHAR(255),
  TmdbId INT,
  Language VARCHAR(6),
  OriginalTitle VARCHAR(255),
  Overview VARCHAR(1024),
  Popularity DOUBLE,
  ReleaseDate DATE DEFAULT NULL,
  Runtime DOUBLE,
  Status VARCHAR(32),
  Tagline VARCHAR(500),
  VoteAverage DOUBLE,
  VoteCount INT,
  CONSTRAINT pk_Movies_MovieId PRIMARY KEY (MovieId)
);

INSERT INTO Movies ( 
      MovieId,
      PosterPath,
	  BackdropPath,
      BackupPath,
      Homepage,
      TmdbId,
      Language,
      OriginalTitle,
      Overview,
	  Popularity,
	  ReleaseDate,
	  Runtime,
      Status,
      Tagline,
      VoteAverage,
      VoteCount) 
SELECT MovieId,
	if (
		json_valid(replace(replace(Collections, '''', '"'), 'None', 'null')), 
		json_extract(replace(replace(Collections, '''', '"'), 'None', 'null'), '$.poster_path'),
		null
	) as PosterPath,
    
    if (
		json_valid(replace(replace(Collections, '''', '"'), 'None', 'null')), 
        json_extract(replace(replace(Collections, '''', '"'), 'None', 'null'),'$.backdrop_path'),
		null
	) as BackdropPath,
       PosterPath,
       Homepage,
       TmdbId,
       Language,
       OriginalTitle,
       Overview,
	   Popularity,
	   ReleaseDate,
	   Runtime,
       Status,
       Tagline,
       VoteAverage,
       VoteCount
FROM OriginalMovies
-- where
-- 	collections is not null
--     and collections != ''
--     -- and  not json_valid(replace(replace(Collections, '''', '"'), 'None', 'null'))
    
ORDER BY MovieId ASC 