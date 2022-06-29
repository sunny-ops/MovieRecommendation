CREATE SCHEMA IF NOT EXISTS MovieRecommendations;

USE MovieRecommendations;

# Drop tables if necessary.
DROP TABLE IF EXISTS OriginalMovies;

# Create tables if necessary.
CREATE TABLE OriginalMovies (
  MovieId INT AUTO_INCREMENT,
  Adult VARCHAR(20),
  Collections VARCHAR(8000),
  Budget BIGINT,
  Genres VARCHAR(500),
  -- Genres JSON DEFAULT NULL,
  Homepage VARCHAR(255),
  TmdbId INT,
  ImdbId VARCHAR(20),
  Language VARCHAR(6),
  OriginalTitle VARCHAR(255),
  Overview VARCHAR(1024),
  Popularity DOUBLE,
  PosterPath VARCHAR(255),
  Company VARCHAR(1500),
  Country VARCHAR(1200),
  ReleaseDate DATE DEFAULT NULL,
  Revenue BIGINT,
  Runtime DOUBLE DEFAULT NULL,
  SpokenLanguages VARCHAR(800),
  Status VARCHAR(32),
  Tagline VARCHAR(500),
  Title VARCHAR(225),
  Video VARCHAR(20),
  VoteAverage DOUBLE,
  VoteCount INT,
  CONSTRAINT pk_Movies_MovieId PRIMARY KEY (MovieId)
);

USE MovieRecommendations;
SET sql_mode = 'STRICT_TRANS_TABLES';
SET sql_mode = 'STRICT_ALL_TABLES';

# Insert data for TABLE CreditCards from file
LOAD DATA LOCAL INFILE '/Users/shi/NEU/5200/Project/data/movies_metadata.csv' INTO TABLE OriginalMovies
  FIELDS TERMINATED BY ','
  OPTIONALLY ENCLOSED BY '"'
  ESCAPED BY '"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (Adult, Collections, Budget, Genres, Homepage, TmdbId, ImdbId, Language, OriginalTitle, Overview, Popularity, PosterPath, Company, Country, ReleaseDate, Revenue, Runtime, SpokenLanguages, Status, Tagline, Title, Video, VoteAverage, VoteCount)
  SET MovieId = NULL;


