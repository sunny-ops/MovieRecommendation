package review.model;

import review.model.Restaurants.CuisineType;

/**
 * BlogUsers is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Persons}).
 */
public class TakeOutRestaurants extends Restaurants {
	protected int maxWaitTime;
	public TakeOutRestaurants(int restaurantId, int maxWaitTime, String name, String description, String menu, String hours,
			boolean active, CuisineType cuisine, String street1, String street2, String city, String state, int zip,
			String companyName) {
		super(restaurantId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, companyName);
		this.maxWaitTime = maxWaitTime;
		
	}
	public int getMaxWaitTime() {
		return maxWaitTime;
	}
	public void setMaxWaitTime(int maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}
	


}	

	