package movie.dal;

import movie.model.Recommendations;
import movie.model.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class RecommendationsDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
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
    public Recommendations create(Recommendations recommendations) throws SQLException {
        String insertRecommendation = "INSERT INTO Recommendation(userId, movieId) VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertRecommendation, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setInt(1, recommendations.getUser().getUserId());
            insertStmt.setInt(2, recommendations.getMovie().getMovieId());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int recommendationID = -1;
            if(resultKey.next()) {
                recommendationID = resultKey.getInt(1);
            }
            else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            recommendations.setRecommendationID(recommendationID);

            return recommendations;
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
        }
    }

    public Recommendations delete(Recommendations recommendation) throws SQLException {
        String deleteRecommendation = "DELETE FROM Recommendations WHERE RecommendationID=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteRecommendation);
            deleteStmt.setInt(1, recommendation.getRecommendationID());
            deleteStmt.executeUpdate();

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




}
