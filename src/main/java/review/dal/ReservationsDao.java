package review.dal;

import review.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReservationsDao {
	protected ConnectionManager connectionManager;

	private static ReservationsDao instance = null;
	protected ReservationsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReservationsDao getInstance() {
		if(instance == null) {
			instance = new ReservationsDao();
		}
		return instance;
	}
	

	public Reservations create(Reservations reservation) throws SQLException {
		String insertReservation =
			"INSERT INTO Reservations(Start, End, Size, UserName, RestaurantId) " +
			"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReservation,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setTimestamp(1, reservation.getStart());
			insertStmt.setTimestamp(2, reservation.getEnd());
			insertStmt.setInt(3, reservation.getSize());
			insertStmt.setString(4, reservation.getUserName());
			insertStmt.setInt(5, reservation.getRestaurantId());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int reservationId = -1;
			if(resultKey.next()) {
				reservationId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			reservation.setReservationId(reservationId);
			return reservation;
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
	 * Delete the Reservations instance.
	 * This runs a DELETE statement.
	 */
	public Reservations delete(Reservations reservation) throws SQLException {
		String deletereservation = "DELETE FROM Reservations WHERE reservationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletereservation);
			deleteStmt.setInt(1, reservation.getReservationId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Reservations instance.
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
//	 * Get the Reservations record by fetching it from your MySQL instance.
//	 * This runs a SELECT statement and returns a single Reservations instance.
//	 * Note that we use BlogPostsDao and BlogUsersDao to retrieve the referenced
//	 * BlogPosts and BlogUsers instances.
//	 * One alternative (possibly more efficient) is using a single SELECT statement
//	 * to join the Reservations, BlogPosts, BlogUsers tables and then build each object.
//	 */
	public Reservations getReservationById(int reservationId) throws SQLException {
		String selectreservation =
			"SELECT reservationId, Start, End, Size, UserName, RestaurantId " +
			"FROM Reservations " +
			"WHERE reservationId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectreservation);
			selectStmt.setInt(1, reservationId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultReservationId = results.getInt("ReservationId");
				Timestamp start =  results.getTimestamp("Start");
				Timestamp end =  results.getTimestamp("End");
				int size = results.getInt("Size");
				int restaurantId = results.getInt("RestaurantId");
				String userName = results.getString("UserName");
				Reservations reservation = new Reservations(resultReservationId, start, end, size, userName, restaurantId);
				return reservation;
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
//	 * Get the all the Reservations for a user.
//	 */
	public List<Reservations> getReservationsBySitDownRestaurantId(int sitdownRestaurantId) throws SQLException {
		List<Reservations> Reservations = new ArrayList<Reservations>();
		String selectReservations =
				"SELECT reservationId, Start, End, Size, UserName, RestaurantId " +
						"FROM Reservations " +
						"WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReservations);
			selectStmt.setInt(1, sitdownRestaurantId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int reservationId = results.getInt("ReservationId");
				Timestamp start =  results.getTimestamp("Start");
				Timestamp end =  results.getTimestamp("End");
				int size = results.getInt("Size");
				int resultRestaurantId = results.getInt("RestaurantId");
				String userName = results.getString("UserName");
				Reservations reservation = new Reservations(reservationId, start, end, size, userName, resultRestaurantId);
				Reservations.add(reservation);
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
		return Reservations;
	}
	
	public List<Reservations> getReservationsByUserName(String userName) throws SQLException {
		List<Reservations> Reservations = new ArrayList<Reservations>();
		String selectReservations =
				"SELECT reservationId, Start, End, Size, UserName, RestaurantId " +
						"FROM Reservations " +
						"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReservations);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int reservationId = results.getInt("ReservationId");
				Timestamp start =  results.getTimestamp("Start");
				Timestamp end =  results.getTimestamp("End");
				int size = results.getInt("Size");
				int restaurantId = results.getInt("RestaurantId");
				String resultUserName = results.getString("UserName");
				Reservations reservation = new Reservations(reservationId, start, end, size, resultUserName, restaurantId);
				Reservations.add(reservation);
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
		return Reservations;
	}

}