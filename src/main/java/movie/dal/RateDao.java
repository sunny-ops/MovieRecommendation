package movie.dal;


import movie.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class RateDao {
    // Single pattern: instantiation is limited to one object.
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static RateDao instance = null;
    protected RateDao() {
        connectionManager = new ConnectionManager();
    }
    public static RateDao getInstance() {
        if(instance == null) {
            instance = new RateDao();
        }
        return instance;
    }

    public Rate create(Rate rate) throws SQLException {
        String insertRate = "INSERT INTO Rate(UserId, MovieId, Rating, DateTime) VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertRate, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setInt(1, rate.getUser().getUserId());
            insertStmt.setInt(2, rate.getMovie().getMovieId());
            insertStmt.setDouble(3, rate.getRate());
//            insertStmt.setDate(4, rate.getDate());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int RateId = -1;
            if(resultKey.next()) {
                RateId = resultKey.getInt(1);
            }
            else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            rate.setRateID(RateId);

            return rate;
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


    public Rate delete(Rate rate) throws SQLException {
        String deleteRecommendation = "DELETE FROM Rate WHERE RateId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteRecommendation);
            deleteStmt.setInt(1, rate.getRateID());
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

    public Rate getRateByUserId(Integer userID) throws SQLException {
        String selectRate = "SELECT RateId, UserId, MovieId, Rating, DateTime FROM Rate WHERE UserId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRate);
            selectStmt.setInt(1, userID);
            // Note that we call executeQuery(). This is used for a SELECT statement
            // because it returns a result set. For more information, see:
            // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
            // http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
            results = selectStmt.executeQuery();
            // You can iterate the result set (although the example below only retrieves
            // the first record). The cursor is initially positioned before the row.
            // Furthermore, you can retrieve fields by name and by type.
            if(results.next()) {
                Integer RateID = results.getInt("RateId");
                Integer UserId = results.getInt("UserId");
                Integer MovieId = results.getInt("MovieId");

                Double Rating = results.getDouble("Rating");

                Date DateTime = results.getDate("DateTime");

                Users users = UsersDao.getInstance().getUserFromUserId(UserId);
                Movies movies = MoviesDao.getInstance().getMovieByMovieId(MovieId);

                Rate resultRate = new Rate(RateID, users,movies, Rating, DateTime);
                return resultRate;
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
