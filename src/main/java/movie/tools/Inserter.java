package movie.tools;

import movie.dal.*;
import movie.model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		PersonsDao personsDao = PersonsDao.getInstance();
		AdministratorsDao administratorsDao = AdministratorsDao.getInstance();
		UsersDao usersDao = UsersDao.getInstance();
		
		//INSERT objects from our model.
		Persons person = new Persons("xin", "wang");
		person = personsDao.create(person);
		Persons person1 = new Persons("xin", "chen");
		person1 = personsDao.create(person1);
		Persons person2 = new Persons("kai", "xi");
		person2 = personsDao.create(person2);
//		Date date = new Date();
//		Administrators administrator = new Administrators(4, "xin", "wang",  date);
//		administrator = administratorsDao.create(administrator);
//		Administrators administrator1 = new Administrators(5, "xin", "chen",  date);
//		administrator1 = administratorsDao.create(administrator1);
//		Administrators administrator2 = new Administrators(6, "kai", "xi",  date);
//		administrator2 = administratorsDao.create(administrator2);
//		Users user = new Users(7, "jin", "tao",  date);
//		user = usersDao.create(user);
//		Users user1 = new Users(8, "xin", "wang",  date);
//		user1 = usersDao.create(user1);
//		Users user2 = new Users(9, "xin", "chen",  date);
//		user1 = usersDao.create(user2);
//		
////////////////////////////////////////////
		
		
//		Movies movie1 = new Movies(1, "al","ao","ao",(java.sql.Date) date,1.1, "as");
//		Movies movie2 = new Movies(2, "bl","bo","bo",(java.sql.Date) date,1.1, "bs");
//		Movies movie3 = new Movies(2, "cl","co","co",(java.sql.Date) date,1.1, "cs");
//
//		MoviesDao moviesDao = MoviesDao.getInstance();
//
//		movie1 = moviesDao.create(movie1);
//		movie2 = moviesDao.create(movie2);
//		movie3 = moviesDao.create(movie3);
//		
//		Rate rate1 = new Rate(user,movie1,1.1,date);
//		Rate rate2 = new Rate(user1,movie2,2.2,date);
//		Rate rate3 = new Rate(user2,movie3,3.3,date);
//
//		RateDao rateDao = RateDao.getInstance();
//		rate1 = rateDao.create(rate1);
//		rate2 = rateDao.create(rate2);
//		rate3 = rateDao.create(rate3);
//
//		rateDao.delete(rate3);
//
//		rateDao.getRateByUserId(user1.getUserId());
		
		
		///////////////////////
//		
//		// READ.
		Persons p1 = personsDao.getPersonFromUserId(1);
		List<Persons> pList1 = personsDao.getPersonsFromFirstName("xin");
		System.out.format("Reading person: u:%s f:%s l:%s \n",
			p1.getUserId(), p1.getFirstName(), p1.getLastName());
		for(Persons p : pList1) {
			System.out.format("Looping persons: u:%s f:%s l:%s \n",
				p.getUserId(), p.getFirstName(), p.getLastName());
		}
		Administrators a1 = administratorsDao.getAdministratorFromUserId(4);
		List<Administrators> aList1 = administratorsDao.getAdministratorsFromFirstName("xin");
		System.out.format("Reading administrator: u:%s f:%s l:%s d:%s \n",
			a1.getUserId(), a1.getFirstName(), a1.getLastName(), a1.getLastLogin());
		for(Administrators a : aList1) {
			System.out.format("Looping administrators: u:%s f:%s l:%s \n",
				a.getUserId(), a.getFirstName(), a.getLastName(), a.getLastLogin());
		}
		
		Users u1 = usersDao.getUserFromUserId(7);
		List<Users> uList1 = usersDao.getUsersFromFirstName("xin");
		System.out.format("Reading users: u:%s f:%s l:%s d:%s \n",
			u1.getUserId(), u1.getFirstName(), u1.getLastName(), u1.getLastLogin());
		for(Users u : uList1) {
			System.out.format("Looping users: u:%s f:%s l:%s \n",
				u.getUserId(), u.getFirstName(), u.getLastName(), u.getLastLogin());
		}
		
		// update
//		System.out.format("Updating......................\n");
//		administratorsDao.updateLastName(administrator, "chen");
//		usersDao.updateLastName(user1, "chen");
//		
//		List<Users> uuList1 = usersDao.getUsersFromFirstName("xin");
//		for(Users u : uuList1) {
//			System.out.format("Looping users: u:%s f:%s l:%s \n",
//				u.getUserId(), u.getFirstName(), u.getLastName(), u.getLastLogin());
//		}
//		
//		List<Administrators> uaList1 = administratorsDao.getAdministratorsFromFirstName("xin");
//		for(Administrators a : uaList1) {
//			System.out.format("Looping administrators: u:%s f:%s l:%s \n",
//				a.getUserId(), a.getFirstName(), a.getLastName(), a.getLastLogin());
//		}
//		
//		// delete
//		administratorsDao.delete(administrator2);
//		administratorsDao.delete(user1);
//		administratorsDao.delete(administrator);
//		personsDao.delete(person);
//		
//		System.out.format("Deleting......................\n");
//		
//		List<Users> duList1 = usersDao.getUsersFromFirstName("xin");
//		for(Users u : duList1) {
//			System.out.format("Looping users: u:%s f:%s l:%s \n",
//				u.getUserId(), u.getFirstName(), u.getLastName(), u.getLastLogin());
//		}
//		
//		List<Administrators> daList1 = administratorsDao.getAdministratorsFromFirstName("xin");
//		for(Administrators a : daList1) {
//			System.out.format("Looping administrators: u:%s f:%s l:%s \n",
//				a.getUserId(), a.getFirstName(), a.getLastName(), a.getLastLogin());
//		}
		


	}
}
