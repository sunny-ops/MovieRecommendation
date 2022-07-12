package review.dal;

import review.model.*;

import review.model.Restaurants.CuisineType;

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
public class TakeOutRestaurantsDao extends RestaurantsDao {
	// Single pattern: instantiation is limited to one object.
	private static TakeOutRestaurantsDao instance = null;
	protected TakeOutRestaurantsDao() {
		super();
	}
	public static TakeOutRestaurantsDao getInstance() {
		if(instance == null) {
			instance = new TakeOutRestaurantsDao();
		}
		return instance;
	}

	public TakeOutRestaurants create(TakeOutRestaurants TakeOutRestaurants) throws SQLException {
		// Insert into the superclass table first.
//		create(new Restaurants(TakeOutRestaurants.getRestaurantId(), TakeOutRestaurants.getName(), TakeOutRestaurants.getDescription(), TakeOutRestaurants.getMenu(),
//				TakeOutRestaurants.getHours(), TakeOutRestaurants.getActive(), TakeOutRestaurants.getCuisine(),TakeOutRestaurants.getStreet1(), TakeOutRestaurants.getStreet2(),
//				TakeOutRestaurants.getCity(), TakeOutRestaurants.getState(), TakeOutRestaurants.getZip(), TakeOutRestaurants.getCompanyName()));

		String insertSitdownRestaurant = "INSERT INTO TakeOutRestaurant(RestaurantId, MaxWaitTime) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSitdownRestaurant);
			insertStmt.setInt(1, TakeOutRestaurants.getRestaurantId());
			insertStmt.setInt(2, TakeOutRestaurants.getMaxWaitTime());
			insertStmt.executeUpdate();
			return TakeOutRestaurants;
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

//	/**
//	 * Update the LastName of the BlogUsers instance.
//	 * This runs a UPDATE statement.
//	 */
//	public BlogUsers updateLastName(BlogUsers blogUser, String newLastName) throws SQLException {
//		// The field to update only exists in the superclass table, so we can
//		// just call the superclass method.
//		super.updateLastName(blogUser, newLastName);
//		return blogUser;
//	}
//
//	/**
//	 * Delete the BlogUsers instance.
//	 * This runs a DELETE statement.
//	 */
	public TakeOutRestaurants delete(Restaurants restaurant) throws SQLException {
		String deleteSitDownRestaurant = "DELETE FROM TakeOutRestaurant WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSitDownRestaurant);
			deleteStmt.setInt(1, restaurant.getRestaurantId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for UserName=" + restaurant.getRestaurantId());
			}

			// Then also delete from the superclass.
			// Notes:
			// 1. Due to the fk constraint (ON DELETE CASCADE), we could simply call
			//    super.delete() without even needing to delete from Administrators first.
			// 2. BlogPosts has a fk constraint on BlogUsers with the reference option
			//    ON DELETE SET NULL. If the BlogPosts fk reference option was instead
			//    ON DELETE RESTRICT, then the caller would need to delete the referencing
			//    BlogPosts before this BlogUser can be deleted.
			//    Example to delete the referencing BlogPosts:
			//    List<BlogPosts> posts = BlogPostsDao.getBlogPostsForUser(blogUser.getUserName());
			//    for(BlogPosts p : posts) BlogPostsDao.delete(p);
			super.delete(restaurant);

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
	public TakeOutRestaurants getSitDownRestaurantById(int sitDownRestaurantId) throws SQLException {
		String selectRestaurant =
				"SELECT SitDownRestaurant.RestaurantId AS RestaurantId, SitDownRestaurant.MaxWaitTime AS maxWaitTime, Name, Description, Menu, Hours, Active, CuisineType, Street1, Street2, City, State, Zip, CompanyName" +
				"FROM SitDownRestaurant INNER JOIN Restaurants " +
				"  ON SitDownRestaurant.RestaurantId = Restaurants.RestaurantId " +
				"WHERE SitDownRestaurant.RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurant);
			selectStmt.setInt(1, sitDownRestaurantId);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				int resultRestaurantId = results.getInt("RestaurantId");
				int maxWaitTime = results.getInt("maxWaitTime");
				String name = results.getString("Name");
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
				TakeOutRestaurants sitdownRestaurant = new TakeOutRestaurants(resultRestaurantId, maxWaitTime, name, description, menu, hours, active, cuisineType, street1, street2, city, state, zip, companyName);
				return sitdownRestaurant;
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

	public List<TakeOutRestaurants> getTakeOutRestaurantsByCompanyName(String companyName)
			throws SQLException {
		List<TakeOutRestaurants> TakeOutRestaurants = new ArrayList<TakeOutRestaurants>();
		String selectRestaurant =
				"SELECT SitDownRestaurant.RestaurantId AS RestaurantId, SitDownRestaurant.maxWaitTime AS maxWaitTime, Name, Description, Menu, Hours, Active, CuisineType, Street1, Street2, City, State, Zip, CompanyName" +
				"FROM SitDownRestaurant INNER JOIN Restaurants " +
				"  ON SitDownRestaurant.RestaurantId = Restaurants.RestaurantId " +
				"WHERE SitDownRestaurant.CompanyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurant);
			selectStmt.setString(1, companyName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int restaurantId = results.getInt("RestaurantId");
				int maxWaitTime = results.getInt("maxWaitTime");
				String name = results.getString("Name");
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
				String resultCompanyName = results.getString("CompanyName");
				TakeOutRestaurants sitdownRestaurant = new TakeOutRestaurants(restaurantId, maxWaitTime, name, description, menu, hours, active, cuisineType, street1, street2, city, state, zip, resultCompanyName);
				TakeOutRestaurants.add(sitdownRestaurant);
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
		return TakeOutRestaurants;
	}
}