// Shi Zhong
package movie.dal;

import movie.model.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying Persons table in your MySQL
 * instance. This is used to store {@link Persons} into your MySQL instance and retrieve 
 * {@link Persons} from MySQL instance.
 */
public class MoviesDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static MoviesDao instance = null;
	protected MoviesDao() {
		connectionManager = new ConnectionManager();
	}
	public static MoviesDao getInstance() {
		if(instance == null) {
			instance = new MoviesDao();
		}
		return instance;
	}

	/**
	 * Save the Movies instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	//Movies(int movieId, int tmdbId, String language, String originalTitle, String overview, Date releaseDate,
	//		double runtime, String status) {
	
	public Movies create(Movies movie) throws SQLException {
		String insertReservation =
			"INSERT INTO Movies(TmdbId, Language, OriginalTitle, Overview, ReleaseDate, Runtime, Status) " +
			"VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReservation,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, movie.getTmdbId());
			insertStmt.setString(2, movie.getLanguage());
			insertStmt.setString(3, movie.getOriginalTitle());
			insertStmt.setString(4, movie.getOverview());
			insertStmt.setDate(5, movie.getReleaseDate());
			insertStmt.setDouble(6, movie.getRuntime());
			insertStmt.setString(7, movie.getStatus());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int movieId = -1;
			if(resultKey.next()) {
				movieId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			movie.setMovieId(movieId);
			return movie;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	

	/**
	 * Get the Persons record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Persons instance.
	 */
	public Movies getMovieByMovieId(int movieId) throws SQLException {
		String selectMovie = "SELECT MovieId, PosterPath, BackdropPath, BackupPath, HomePage, TmdbId, Language, OriginalTitle, Overview, Popularity, ReleaseDate, Runtime, Status, Tagline, VoteAverage, VoteCount FROM Movies WHERE MovieId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMovie);
			selectStmt.setInt(1, movieId);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				int resultMovieId = results.getInt("MovieId");
				String posterPath = results.getString("PosterPath");
				String backdropPath = results.getString("BackdropPath");
				String backupPath = results.getString("BackupPath");
				String homepage = results.getString("HomePage");
				int tmdbId = results.getInt("TmdbId");
				String language = results.getString("Language");
				String originalTitle = results.getString("OriginalTitle");
				String overview = results.getString("Overview");
				double popularity = results.getDouble("Popularity");
				Date releaseDate = results.getDate("ReleaseDate");
				double runtime = results.getDouble("Runtime");
				String status = results.getString("Status");
				String tagline = results.getString("Tagline");
				double voteAverage = results.getDouble("VoteAverage");
				int voteCount = results.getInt("VoteCount");
				Movies movie = new Movies(resultMovieId, posterPath, backdropPath, backupPath, homepage, tmdbId, language, originalTitle, overview, popularity, releaseDate, runtime, status, tagline, voteAverage, voteCount);
				return movie;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}


}
