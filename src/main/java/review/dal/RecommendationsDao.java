package review.dal;

import review.model.*;
import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class RecommendationsDao {
	protected ConnectionManager connectionManager;

	private static RecommendationsDao instance = null;
	protected RecommendationsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecommendationsDao getInstance() {
		if(instance == null) {
			instance = new RecommendationsDao();
		}
		return instance;
	}

	public Recommendations create(Recommendations recommendation) throws SQLException {
		String insertRecommendation =
			"INSERT INTO Recommendations(UserName, RestaurantId) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecommendation,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, recommendation.getUserName());
			insertStmt.setInt(2, recommendation.getRestaurantId());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int recommendationId = -1;
			if(resultKey.next()) {
				recommendationId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			recommendation.setRecommendationId(recommendationId);
			return recommendation;
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
	 * Delete the Recommendations instance.
	 * This runs a DELETE statement.
	 */
	public Recommendations delete(Recommendations recommendation) throws SQLException {
		String deletereview = "DELETE FROM Recommendations WHERE RecommendationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletereview);
			deleteStmt.setInt(1, recommendation.getRecommendationId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Recommendations instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
//
//	/**
//	 * Get the Recommendations record by fetching it from your MySQL instance.
//	 * This runs a SELECT statement and returns a single Recommendations instance.
//	 * Note that we use BlogPostsDao and BlogUsersDao to retrieve the referenced
//	 * BlogPosts and BlogUsers instances.
//	 * One alternative (possibly more efficient) is using a single SELECT statement
//	 * to join the Recommendations, BlogPosts, BlogUsers tables and then build each object.
//	 */
	public Recommendations getRecommendationById(int recommendationId) throws SQLException {
		String selectReview =
			"SELECT RecommendationId, UserName, RestaurantId " +
			"FROM Recommendations " +
			"WHERE RecommendationId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, recommendationId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultRecommendationId = results.getInt("RecommendationId");
				int restaurantId = results.getInt("RestaurantId");
				String userName = results.getString("UserName");
				Recommendations recommendation = new Recommendations(resultRecommendationId, userName, restaurantId);
				return recommendation;
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
//
//	/**
//	 * Get the all the Recommendations for a user.
//	 */
	public List<Recommendations> getRecommendationsByRestaurantId(int restaurantId) throws SQLException {
		List<Recommendations> recommendations = new ArrayList<Recommendations>();
		String selectRecommendations =
				"SELECT RecommendationId, UserName, RestaurantId " +
						"FROM Recommendations " +
						"WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendations);
			selectStmt.setInt(1, restaurantId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int recommendationId = results.getInt("RecommendationId");
				int resultRestaurantId = results.getInt("RestaurantId");
				String userName = results.getString("UserName");
				Recommendations recommendation = new Recommendations(recommendationId, userName, resultRestaurantId);
				recommendations.add(recommendation);
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
		return recommendations;
	}
	
	public List<Recommendations> getRecommendationsByUserName(String userName) throws SQLException {
		List<Recommendations> Recommendations = new ArrayList<Recommendations>();
		String selectRecommendations =
				"SELECT RecommendationId, UserName, RestaurantId " +
						"FROM Recommendations " +
						"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendations);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int recommendationId = results.getInt("RecommendationId");
				int restaurantId = results.getInt("RestaurantId");
				String resultUserName = results.getString("UserName");
				Recommendations recommendation = new Recommendations(recommendationId, resultUserName, restaurantId);
				Recommendations.add(recommendation);
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
		return Recommendations;
	}

}