package movie.dal;

import movie.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying BlogUsers table in your
 * MySQL instance. This is used to store {@link BlogUsers} into your MySQL instance and 
 * retrieve {@link BlogUsers} from MySQL instance.
 */
public class UsersDao extends PersonsDao {
	// Single pattern: instantiation is limited to one object.
	private static UsersDao instance = null;
	protected UsersDao() {
		super();
	}
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}
	
	public Users create(Users users) throws SQLException {
		// Insert into the superclass table first.
		create(new Persons(users.getUserId(), users.getFirstName(),
				users.getLastName()));

		String insertAdministrator = "INSERT INTO Users(UserId, LastLogin) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAdministrator);
			insertStmt.setInt(1, users.getUserId());
			insertStmt.setTimestamp(2, new Timestamp(users.getLastLogin().getTime()));
			insertStmt.executeUpdate();
			
			
			return users;
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

	/**
	 * Update the LastName of the Administrators instance.
	 * This runs a UPDATE statement.
	 */
	public Users updateLastName(Users user, String newLastName) throws SQLException {
		// The field to update only exists in the superclass table, so we can
		// just call the superclass method.
		super.updateLastName(user, newLastName);
		return user;
	}

	/**
	 * Delete the Administrators instance.
	 * This runs a DELETE statement.
	 */
	public Users delete(Users user) throws SQLException {
		String deleteUser = "DELETE FROM Users WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, user.getUserId());
			deleteStmt.executeUpdate();

			// Then also delete from the superclass.
			// Note: due to the fk constraint (ON DELETE CASCADE), we could simply call
			// super.delete() without even needing to delete from Administrators first.
			super.delete(user);

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
	
	public Users getUserFromUserId(Integer userId) throws SQLException {
		// To build an Administrator object, we need the Persons record, too.
		String selectUser =
			"SELECT Users.UserId AS UserId, Persons.FirstName, Persons.LastName, LastLogin " +
			"FROM Users INNER JOIN Persons " +
			"  ON Users.UserId = Persons.UserId " +
			"WHERE Users.UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				Integer resultUserId = results.getInt("UserId");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				Date lastLogin = new Date(results.getTimestamp("LastLogin").getTime());
				Users user = new Users(resultUserId, firstName, lastName, lastLogin);
				return user;
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

	public List<Users> getUsersFromFirstName(String firstName)
			throws SQLException {
		List<Users> users = new ArrayList<Users>();
		String selectUsers =
			"SELECT Users.UserId AS UserId, Persons.FirstName, Persons.LastName, LastLogin " +
			"FROM Users INNER JOIN Persons " +
			"  ON Users.UserId = Persons.UserId " +
			"WHERE Persons.FirstName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			selectStmt.setString(1, firstName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Integer userId = results.getInt("UserId");
				String resultFirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				Date lastLogin = new Date(results.getTimestamp("LastLogin").getTime());
				Users user = new Users(userId,resultFirstName, lastName, lastLogin);
				users.add(user);
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
		return users;
	}
}
