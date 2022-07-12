package review.model;


/**
 * BlogUsers is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Persons}).
 */
public class SitDownRestaurants extends Restaurants {
	protected int capacity;
	public SitDownRestaurants(int restaurantId, int capacity, String name, String description, String menu, String hours,
			boolean active, CuisineType cuisine, String street1, String street2, String city, String state, int zip,
			String companyName) {
		super(restaurantId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, companyName);
		this.capacity = capacity;
		
	}
	
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	

	
}