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
 * Data access object (DAO) class to interact with the underlying Administrators table in your
 * MySQL instance. This is used to store {@link Administrators} into your MySQL instance and 
 * retrieve {@link Administrators} from MySQL instance.
 */
public class AdministratorsDao extends PersonsDao {
	// Single pattern: instantiation is limited to one object.
	private static AdministratorsDao instance = null;
	protected AdministratorsDao() {
		super();
	}
	public static AdministratorsDao getInstance() {
		if(instance == null) {
			instance = new AdministratorsDao();
		}
		return instance;
	}
	
	public Administrators create(Administrators administrator) throws SQLException {
		// Insert into the superclass table first.
		create(new Persons(administrator.getUserId(), administrator.getFirstName(),
			administrator.getLastName()));

		String insertAdministrator = "INSERT INTO Administrators(UserId, LastLogin) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAdministrator);
			insertStmt.setInt(1, administrator.getUserId());
			insertStmt.setTimestamp(2, new Timestamp(administrator.getLastLogin().getTime()));
			insertStmt.executeUpdate();
			return administrator;
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
	public Administrators updateLastName(Administrators administrator, String newLastName) throws SQLException {
		// The field to update only exists in the superclass table, so we can
		// just call the superclass method.
		super.updateLastName(administrator, newLastName);
		return administrator;
	}

	/**
	 * Delete the Administrators instance.
	 * This runs a DELETE statement.
	 */
	public Administrators delete(Administrators administrator) throws SQLException {
		String deleteAdministrator = "DELETE FROM Administrators WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAdministrator);
			deleteStmt.setInt(1, administrator.getUserId());
			deleteStmt.executeUpdate();

			// Then also delete from the superclass.
			// Note: due to the fk constraint (ON DELETE CASCADE), we could simply call
			// super.delete() without even needing to delete from Administrators first.
			super.delete(administrator);

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
	
	public Administrators getAdministratorFromUserId(Integer userId) throws SQLException {
		// To build an Administrator object, we need the Persons record, too.
		String selectAdministrator =
			"SELECT Administrators.UserId AS UserId, Persons.FirstName, Persons.LastName, LastLogin " +
			"FROM Administrators INNER JOIN Persons " +
			"  ON Administrators.UserId = Persons.UserId " +
			"WHERE Administrators.UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAdministrator);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				Integer resultUserId = results.getInt("UserId");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				Date lastLogin = new Date(results.getTimestamp("LastLogin").getTime());
				Administrators administrator = new Administrators(resultUserId, firstName, lastName, lastLogin);
				return administrator;
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

	public List<Administrators> getAdministratorsFromFirstName(String firstName)
			throws SQLException {
		List<Administrators> administrators = new ArrayList<Administrators>();
		String selectAdministrators =
			"SELECT Administrators.UserId AS UserId, Persons.FirstName, Persons.LastName, LastLogin " +
			"FROM Administrators INNER JOIN Persons " +
			"  ON Administrators.UserId = Persons.UserId " +
			"WHERE Persons.FirstName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAdministrators);
			selectStmt.setString(1, firstName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Integer userId = results.getInt("UserId");
				String resultFirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				Date lastLogin = new Date(results.getTimestamp("LastLogin").getTime());
				Administrators administrator = new Administrators(userId, resultFirstName, lastName, lastLogin);
				administrators.add(administrator);
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
		return administrators;
	}
}
