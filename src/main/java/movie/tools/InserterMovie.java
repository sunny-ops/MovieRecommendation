//Shi Zhong
package movie.tools;

import movie.dal.*;
import movie.model.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.math.BigDecimal;
//import java.util.Date;
import java.sql.Date;
import java.util.List;






/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class InserterMovie {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		MoviesDao moviesDao = MoviesDao.getInstance();
		
		
		// INSERT objects from our model.
		Date date = Date.valueOf("2018-10-01");
		Movies movie1 = new Movies(-200, "CN", "Hello Hello", "abcdefg", date, 120.00, "released");
		movie1 = moviesDao.create(movie1);
		
		// Read
		Movies m1 = moviesDao.getMovieByMovieId(1);
		System.out.format("Reading movie: u:%s f:%s l:%s \n",
			m1.getMovieId(), m1.getOriginalTitle(), m1.getRuntime());
		System.out.println(m1.getOverview());
		System.out.println(m1.getReleaseDate());

	}
}
