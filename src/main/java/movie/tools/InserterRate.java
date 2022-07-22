package movie.tools;

import movie.dal.*;
import movie.model.*;
import jdk.jfr.Timestamp;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import java.sql.SQLException;
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
public class InserterRate {

	public static void main(String[] args) throws SQLException {

		Date date = Date.valueOf("2018-10-01");


//		Persons persons1 = new Persons(1,"Li1", "Ji1");
//		Persons persons2 = new Persons(2,"Li2", "Ji2");
//		Persons persons3 = new Persons(3,"Li3", "Ji3");
//
//
//		Users user1 = new Users(1,"Li1", "Ji1", date);
//		Users user2 = new Users(2,"Li2", "Ji2", date);
//		Users user3 = new Users(3,"Li3", "Ji3", date);
//
//		UsersDao usersDao = UsersDao.getInstance();
//		user1 = usersDao.create(user1);
//		user2 = usersDao.create(user2);
//		user3 = usersDao.create(user3);

		Movies movie1 = new Movies(1, "al","ao","ao",(java.sql.Date) date,1.1, "as");
		Movies movie2 = new Movies(2, "bl","bo","bo",(java.sql.Date) date,1.1, "bs");
		Movies movie3 = new Movies(2, "cl","co","co",(java.sql.Date) date,1.1, "cs");

		MoviesDao moviesDao = MoviesDao.getInstance();

		movie1 = moviesDao.create(movie1);
		movie2 = moviesDao.create(movie2);
		movie3 = moviesDao.create(movie3);



//		Recommendations recommendations1 = new Recommendations(user1,movie1);
//		Recommendations recommendations2 = new Recommendations(user2,movie2);
//		Recommendations recommendations3 = new Recommendations(user3,movie3);
//
//
//		RecommendationsDao recommendationsDao = RecommendationsDao.getInstance();
//		recommendations1 = recommendationsDao.create(recommendations1);
//		recommendations2 = recommendationsDao.create(recommendations2);
//		recommendations3 = recommendationsDao.create(recommendations3);

//		recommendationsDao.delete(recommendations3);


		Rate rate1 = new Rate(user1,movie1,1.1,date);
		Rate rate2 = new Rate(user2,movie2,2.2,date);
		Rate rate3 = new Rate(user3,movie3,3.3,date);

		RateDao rateDao = RateDao.getInstance();
		rate1 = rateDao.create(rate1);
		rate2 = rateDao.create(rate2);
		rate3 = rateDao.create(rate3);

		rateDao.delete(rate3);

		rateDao.getRateByUserId(user1.getUserId());

		System.out.println();







	}
}
