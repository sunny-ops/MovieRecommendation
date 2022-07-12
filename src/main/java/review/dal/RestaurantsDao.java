package review.dal;

import review.model.*;
import review.model.Restaurants.CuisineType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


//import java.util.Date;
import java.sql.Date;


/**
 * Data access object (DAO) class to interact with the underlying Restaurants table in your MySQL
 * instance. This is used to store {@link Restaurants} into your MySQL instance and retrieve 
 * {@link Restaurants} from MySQL instance.
 */
public class RestaurantsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static RestaurantsDao instance = null;
	protected RestaurantsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RestaurantsDao getInstance() {
		if(instance == null) {
			instance = new RestaurantsDao();
		}
		return instance;
	}

	/**
	 * Save the Restaurants instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Restaurants create(Restaurants restaurant) throws SQLException {
		String insertRestaurant = "INSERT INTO Restaurants(RestaurantId, Name, Description, Menu, Hours, Active, CuisineType, Street1, Street2, City, State, Zip, CompanyName) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRestaurant);
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt.setInt(1, restaurant.getRestaurantId());
			insertStmt.setString(2, restaurant.getName());
			insertStmt.setString(3, restaurant.getDescription());
			insertStmt.setString(4, restaurant.getMenu());
			insertStmt.setString(5, restaurant.getHours());
			insertStmt.setBoolean(6, restaurant.getActive());
			insertStmt.setString(7, restaurant.getCuisine().name());
			insertStmt.setString(8, restaurant.getStreet1());
			insertStmt.setString(9, restaurant.getStreet2());
			insertStmt.setString(10, restaurant.getCity());
			insertStmt.setString(11, restaurant.getState());
			insertStmt.setInt(12, restaurant.getZip());
			insertStmt.setString(13, restaurant.getCompanyName());
			// Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
			// statements, and it returns an int for the row counts affected (or 0 if the
			// statement returns nothing). For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// I'll leave it as an exercise for you to write UPDATE/DELETE methods.
			insertStmt.executeUpdate();
			
			// Note 1: if this was an UPDATE statement, then the person fields should be
			// updated before returning to the caller.
			// Note 2: there are no auto-generated keys, so no update to perform on the
			// input param person.
			return restaurant;
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
	 * Get the Restaurants record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Restaurants instance.
	 */
	public Restaurants getRestaurantById(int restaurantId) throws SQLException {
		String selectRestaurant = "SELECT Name, Description, Menu, Hours, Active, CuisineType, Street1, Street2, City, State, Zip, CompanyName FROM Restaurants WHERE restaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurant);
			selectStmt.setInt(1, restaurantId);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				String resultName = results.getString("Name");
				String description = results.getString("Description");
				String menu = results.getString("Menu");
				String hours = results.getString("Hours");
				boolean active = results.getBoolean("Active");
				CuisineType cuisineType = CuisineType.valueOf(
						results.getString("CuisineTYpe"));
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				String companyName = results.getString("CompanyName");
				Restaurants restaurant = new Restaurants(restaurantId, resultName, description, menu, hours, active, cuisineType, street1, street2, city, state, zip, companyName);
				return restaurant;
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
	
	public Restaurants delete(Restaurants restaurant) throws SQLException {
		String deleteRestaurant = "DELETE FROM Restaurants WHERE Name=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRestaurant);
			deleteStmt.setString(1, restaurant.getName());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for Name=" + restaurant.getName());
			}
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
	
	public List<Restaurants> getRestaurantsByCompanyName(String companyName) throws SQLException {
		List<Restaurants> restaurants = new ArrayList<Restaurants>();
		String selectRestaurants =
				"SELECT Name, Desciption, Menu, Hours, Active, CuisineType, Street1, Street2, City, State, Zip, CompanyName FROM Restaurants WHERE companyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurants);
			selectStmt.setString(1, companyName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int restaurantId = results.getInt("RestaurantId");
				String resultName = results.getString("Name");
				String description = results.getString("Description");
				String menu = results.getString("Menu");
				String hours = results.getString("Hours");
				boolean active = results.getBoolean("Active");
				CuisineType cuisineType = CuisineType.valueOf(
						results.getString("CuisineType"));
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				String resultCompanyName = results.getString("CompanyName");
				Restaurants restaurant = new Restaurants(restaurantId, resultName, description, menu, hours, active, cuisineType, street1, street2, city, state, zip, resultCompanyName);
				restaurants.add(restaurant);
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
		return restaurants;
     }
	public List<Restaurants> getRestaurantsByCuisine(Restaurants.CuisineType cuisine) throws SQLException {
		List<Restaurants> restaurants = new ArrayList<Restaurants>();
		String selectRestaurants =
				"SELECT Name, Desciption, Menu, Hours, Active, CuisineType, Street1, Street2, City, State, Zip, CompanyName FROM Restaurants WHERE cuisineType=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurants);
			selectStmt.setString(1, cuisine.name());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int restaurantId = results.getInt("RestaurantId");
				String resultName = results.getString("Name");
				String description = results.getString("Description");
				String menu = results.getString("Menu");
				String hours = results.getString("Hours");
				boolean active = results.getBoolean("Active");
				CuisineType resultCuisineType = CuisineType.valueOf(
						results.getString("CuisineTYpe"));
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				String companyName = results.getString("CompanyName");
				Restaurants restaurant = new Restaurants(restaurantId, resultName, description, menu, hours, active, resultCuisineType, street1, street2, city, state, zip, companyName);
				restaurants.add(restaurant);
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
		return restaurants;
		
		
	}
}