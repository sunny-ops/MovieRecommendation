package review.tools;

import review.dal.*;
import review.model.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.math.BigDecimal;
//import java.util.Date;
import java.sql.Date;
import java.util.List;

import review.model.Restaurants.CuisineType;





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
		UsersDao usersDao = UsersDao.getInstance();
		CreditCardsDao creditcardsDao = CreditCardsDao.getInstance();
		CompaniesDao companiesDao = CompaniesDao.getInstance();
	    RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
	    SitDownRestaurantsDao sitdownRestaurantsDao = SitDownRestaurantsDao.getInstance();
	    TakeOutRestaurantsDao takeoutRestaurantsDao = TakeOutRestaurantsDao.getInstance();
	    FoodCartRestaurantsDao foodcartRestaurantsDao = FoodCartRestaurantsDao.getInstance();
	    ReviewsDao reviewsDao = ReviewsDao.getInstance();
	    RecommendationsDao recommendationsDao = RecommendationsDao.getInstance();
	    ReservationsDao reservationsDao = ReservationsDao.getInstance();
		
		// INSERT objects from our model.
		Users user1 = new Users("Bruce","password","Bruce","C","bruce@mail.com","5555555");
		user1 = usersDao.create(user1);
		Users user2 = new Users("TT","password","Tony","D","tony@mail.com","5555555");
		user2 = usersDao.create(user2);
		Users user3 = new Users("DK","password","Daniel","K","dan@mail.com","5555555");
		user3 = usersDao.create(user3);
		Users user4 = new Users("James","password","James","M","james@mail.com","5555555");
		user4 = usersDao.create(user4);
		Users user5 = new Users("Steve","password","Steve","N","steve@mail.com","5555555");
		user5 = usersDao.create(user5);
		
//		Date date = new Date(2018,10,1);
		Date date = Date.valueOf("2018-10-01");
		CreditCards credit1 = new CreditCards(3499432187650987L, date, "Bruce");
		credit1 = creditcardsDao.create(credit1);
		CreditCards credit2 = new CreditCards(3488432187650987L, date, "Bruce");
		credit2 = creditcardsDao.create(credit2);
		CreditCards credit3 = new CreditCards(3799432187650987L, date, "Bruce");
		credit3 = creditcardsDao.create(credit3);
		CreditCards credit4 = new CreditCards(6011432187650987L, date, "Bruce");
		credit4 = creditcardsDao.create(credit4);
		
		Companies com1 = new Companies("company1","about company1");
		com1 = companiesDao.create(com1);
		Companies com2 = new Companies("company2","about company2");
		com2 = companiesDao.create(com2);
		Companies com3 = new Companies("company3","about company1");
		com3 = companiesDao.create(com3);
		
		Restaurants r1 = new Restaurants(1, "restaurant1","about restaurant","menu","hours",true, CuisineType.AFRICAN,"street1","street2","seattle","wa",98195,"company1");
		r1 = restaurantsDao.create(r1);
		Restaurants r2 = new Restaurants(2, "restaurant2","about restaurant","menu","hours",true, CuisineType.AMERICAN,"street1","street2","seattle","wa",98195,"company1");
		r2 = restaurantsDao.create(r2);
		Restaurants r3 = new Restaurants(3, "restaurant3","about restaurant","menu","hours",true, CuisineType.ASIAN,"street1","street2","seattle","wa",98195,"company1");
		r3 = restaurantsDao.create(r3);
		Restaurants r4 = new Restaurants(4, "restaurant4","about restaurant","menu","hours",true, CuisineType.EUROPEAN,"street1","street2","seattle","wa",98195,"company1");
		r4 = restaurantsDao.create(r4);
		Restaurants r5 = new Restaurants(5, "restaurant5","about restaurant","menu","hours",true, CuisineType.EUROPEAN,"street1","street2","seattle","wa",98195,"company1");
		r5 = restaurantsDao.create(r5);
		Restaurants r6 = new Restaurants(6, "restaurant6","about restaurant","menu","hours",true, CuisineType.EUROPEAN,"street1","street2","seattle","wa",98195,"company1");
		r6 = restaurantsDao.create(r6);
		Restaurants r7 = new Restaurants(7, "restaurant7","about restaurant","menu","hours",true, CuisineType.EUROPEAN,"street1","street2","seattle","wa",98195,"company1");
		r7 = restaurantsDao.create(r7);
		Restaurants r8 = new Restaurants(8, "restaurant8","about restaurant","menu","hours",true, CuisineType.EUROPEAN,"street1","street2","seattle","wa",98195,"company1");
		r8 = restaurantsDao.create(r8);
		Restaurants r9 = new Restaurants(9, "restaurant9","about restaurant","menu","hours",true, CuisineType.EUROPEAN,"street1","street2","seattle","wa",98195,"company1");
		r9 = restaurantsDao.create(r9);
		Restaurants r10 = new Restaurants(10, "restaurant10","about restaurant","menu","hours",true, CuisineType.EUROPEAN,"street1","street2","seattle","wa",98195,"company1");
		r10 = restaurantsDao.create(r10);
		
		SitDownRestaurants s1 = new SitDownRestaurants(1, 100, "restaurant1","about restaurant","menu","hours",true, CuisineType.AFRICAN,"street1","street2","seattle","wa",98195,"company1");
		s1 = sitdownRestaurantsDao.create(s1);
		SitDownRestaurants s2 = new SitDownRestaurants(2, 200, "restaurant2","about restaurant","menu","hours",true, CuisineType.AMERICAN,"street1","street2","seattle","wa",98195,"company1");
		s2 = sitdownRestaurantsDao.create(s2);
		SitDownRestaurants s3 = new SitDownRestaurants(3, 200, "restaurant3","about restaurant","menu","hours",true, CuisineType.ASIAN,"street1","street2","seattle","wa",98195,"company1");
		s3 = sitdownRestaurantsDao.create(s3);
		
		TakeOutRestaurants t1 = new TakeOutRestaurants(4, 60, "restaurant4","about restaurant","menu","hours",true, CuisineType.EUROPEAN,"street1","street2","seattle","wa",98195,"company1");
		t1 = takeoutRestaurantsDao.create(t1);
		TakeOutRestaurants t2 = new TakeOutRestaurants(5, 90, "restaurant4","about restaurant","menu","hours",true, CuisineType.EUROPEAN,"street1","street2","seattle","wa",98195,"company1");
		t2 = takeoutRestaurantsDao.create(t2);
		
		FoodCartRestaurants f1 = new FoodCartRestaurants(6, true, "restaurant6","about restaurant","menu","hours",true, CuisineType.EUROPEAN,"street1","street2","seattle","wa",98195,"company1");
		f1 = foodcartRestaurantsDao.create(f1);
		FoodCartRestaurants f2 = new FoodCartRestaurants(7, true, "restaurant7","about restaurant","menu","hours",true, CuisineType.EUROPEAN,"street1","street2","seattle","wa",98195,"company1");
		f2 = foodcartRestaurantsDao.create(f2);
		FoodCartRestaurants f3 = new FoodCartRestaurants(8, true, "restaurant8","about restaurant","menu","hours",true, CuisineType.EUROPEAN,"street1","street2","seattle","wa",98195,"company1");
		f3 = foodcartRestaurantsDao.create(f3);
		FoodCartRestaurants f4 = new FoodCartRestaurants(9, true, "restaurant9","about restaurant","menu","hours",true, CuisineType.EUROPEAN,"street1","street2","seattle","wa",98195,"company1");
		f4 = foodcartRestaurantsDao.create(f4);
		FoodCartRestaurants f5 = new FoodCartRestaurants(10, true, "restaurant10","about restaurant","menu","hours",true, CuisineType.EUROPEAN,"street1","street2","seattle","wa",98195,"company1");
		f5 = foodcartRestaurantsDao.create(f5);
		
		Reviews review1 = new Reviews("Delightful!",new BigDecimal("5.0"),"Bruce",5);
		review1 = reviewsDao.create(review1);
		Reviews review2 = new Reviews("Superb!",new BigDecimal("5.0"),"Bruce",2);
		review2 = reviewsDao.create(review2);
		Reviews review3 = new Reviews("Delightful!",new BigDecimal("5.0"),"Bruce",9);
		review3 = reviewsDao.create(review3);
		Reviews review4 = new Reviews("Not good!",new BigDecimal("1.0"),"James",9);
		review4 = reviewsDao.create(review4);
		Reviews review5 = new Reviews("Not good at all!",new BigDecimal("1.0"),"James",10);
		review5 = reviewsDao.create(review5);
		
		
		Recommendations recommendation1 = new Recommendations("Bruce",1);
		recommendation1 = recommendationsDao.create(recommendation1);
		Recommendations recommendation2 = new Recommendations("Bruce",2);
		recommendation2 = recommendationsDao.create(recommendation2);
		Recommendations recommendation3 = new Recommendations("Bruce",3);
		recommendation3 = recommendationsDao.create(recommendation3);
		Recommendations recommendation4 = new Recommendations("Bruce",4);
		recommendation4 = recommendationsDao.create(recommendation4);
		Recommendations recommendation5 = new Recommendations("Bruce",5);
		recommendation5 = recommendationsDao.create(recommendation5);
		Recommendations recommendation6 = new Recommendations("DK",2);
		recommendation6 = recommendationsDao.create(recommendation6);
		Recommendations recommendation7 = new Recommendations("DK",3);
		recommendation7 = recommendationsDao.create(recommendation7);
		Recommendations recommendation8 = new Recommendations("TT",3);
		recommendation8 = recommendationsDao.create(recommendation8);
		
		
		Reservations reservation1 = new Reservations(Timestamp.valueOf("2015-09-05 18:30:00"), Timestamp.valueOf("2015-09-05 20:00:00"),2,"Bruce",1);
		reservation1 = reservationsDao.create(reservation1);
		Reservations reservation2 = new Reservations(Timestamp.valueOf("2015-09-05 18:30:00"), Timestamp.valueOf("2015-09-05 20:00:00"),2,"Bruce",1);
		reservation1 = reservationsDao.create(reservation2);
		Reservations reservation3 = new Reservations(Timestamp.valueOf("2015-09-05 18:30:00"), Timestamp.valueOf("2015-09-05 20:00:00"),2,"Bruce",1);
		reservation3 = reservationsDao.create(reservation3);
		Reservations reservation4 = new Reservations(Timestamp.valueOf("2015-09-05 18:30:00"), Timestamp.valueOf("2015-09-05 20:00:00"),2,"Bruce",2);
		reservation4 = reservationsDao.create(reservation4);
		Reservations reservation5 = new Reservations(Timestamp.valueOf("2015-09-05 18:30:00"), Timestamp.valueOf("2015-09-05 20:00:00"),2,"Bruce",2);
		reservation5 = reservationsDao.create(reservation5);
		Reservations reservation6 = new Reservations(Timestamp.valueOf("2015-09-05 18:30:00"), Timestamp.valueOf("2015-09-05 20:00:00"),2,"Bruce",3);
		reservation6 = reservationsDao.create(reservation6);
		Reservations reservation7 = new Reservations(Timestamp.valueOf("2015-09-05 18:30:00"), Timestamp.valueOf("2015-09-05 20:00:00"),2,"Bruce",3);
		reservation7 = reservationsDao.create(reservation7);
		Reservations reservation8 = new Reservations(Timestamp.valueOf("2015-09-05 18:30:00"), Timestamp.valueOf("2015-09-05 20:00:00"),2,"TT",3);
		reservation8 = reservationsDao.create(reservation8);
		
		
		// READ.
		Users u1 = usersDao.getUserByUserName("Bruce");
		System.out.format("Reading user: u:%s f:%s l:%s e:%s \n",
			u1.getUserName(), u1.getFirstName(), u1.getLastName(), u1.getEmail());
		
		CreditCards c1 = creditcardsDao.getCreditCardByCardNumber(3499432187650987L);
		System.out.format("Reading creditcard: c:%s e:%s u:%s\n",
			c1.getCardNumber(), c1.getExpiration(), c1.getUserName());
		List<CreditCards> cList1 = creditcardsDao.getCreditCardsByUserName("Bruce");
		for(CreditCards c : cList1) {
			System.out.format("Looping creditcards: c:%s e:%s u:%s \n",
				c.getCardNumber(), c.getExpiration(), c.getUserName());
		}
		
		Companies com = companiesDao.getCompanyByCompanyName("company1");
		System.out.format("Reading companies: n:%s a:%s \n",
			com.getCompanyName(), com.getAbout());
		Restaurants r = restaurantsDao.getRestaurantById(3);
		System.out.format("Reading restaurant: n:%s d:%s \n",
			r.getName(), r.getDescription());
		Reviews review = reviewsDao.getReviewById(3);
		System.out.format("Reading review: c:%s n:%s r:%s \n",
			review.getContent(), review.getUserName(), review.getRating());
		List<Reviews> rList1 = reviewsDao.getReviewsByRestaurantId(1);
		for(Reviews rr : rList1) {
			System.out.format("Reading reviews: c:%s n:%s r:%s \n",
					rr.getContent(), rr.getUserName(), rr.getRating());
		}
		List<Reviews> rList2 = reviewsDao.getReviewsByUserName("Bruce");
		for(Reviews rr : rList2) {
			System.out.format("Reading reviews: c:%s n:%s r:%s \n",
					rr.getContent(), rr.getUserName(), rr.getRating());
		}
		Recommendations recommendation = recommendationsDao.getRecommendationById(3);
		System.out.format("Reading recommendation: r:%s u:%s r:%s \n",
			recommendation.getRecommendationId(), recommendation.getUserName(), recommendation.getRestaurantId());
		List<Recommendations> reList1 = recommendationsDao.getRecommendationsByRestaurantId(1);
		for(Recommendations re : reList1) {
			System.out.format("Reading recommendations: r:%s u:%s r:%s \n",
					re.getRecommendationId(), re.getUserName(), re.getRestaurantId());
		}
		Reservations reservation = reservationsDao.getReservationById(4);
		System.out.format("Reading reservation: r:%s u:%s r:%s \n",
			reservation.getReservationId(), reservation.getUserName(), reservation.getRestaurantId());
		List<Reservations> resList1 = reservationsDao.getReservationsByUserName("Bruce");
		for(Reservations res : resList1) {
			System.out.format("Reading reservations: r:%s u:%s r:%s \n",
					res.getReservationId(), res.getUserName(), res.getRestaurantId());
		}
		
//		SitDownRestaurants s = sitdownRestaurantsDao.getSitDownRestaurantById(1);
//		System.out.format("Reading sitdownRestaurant: n:%s d:%s c:%d \n",
//			s.getName(), s.getDescription(), s.getCapacity());
//		
		
		// DELETE
		usersDao.delete(user5);
		creditcardsDao.delete(credit4);
		companiesDao.delete(com3);
		restaurantsDao.delete(r1);
		sitdownRestaurantsDao.delete(s3);
		takeoutRestaurantsDao.delete(t1);
		reviewsDao.delete(review5);
		recommendationsDao.delete(recommendation8);
		reservationsDao.delete(reservation3);
		
		// UPDATE
		Date newExpiration = Date.valueOf("2022-10-01");
		CreditCards updatedCreditcards = creditcardsDao.updateExpiration(credit1, newExpiration);
		Companies updatedCompanies = companiesDao.updateAbout(com1,"Hello");
		
	}
}
