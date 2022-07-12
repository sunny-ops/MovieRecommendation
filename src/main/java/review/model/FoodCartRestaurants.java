package review.model;



/**
 * BlogUsers is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Persons}).
 */
public class FoodCartRestaurants extends Restaurants {
	protected boolean licensed;
	public FoodCartRestaurants(int restaurantId, boolean licensed, String name, String description, String menu, String hours,
			boolean active, CuisineType cuisine, String street1, String street2, String city, String state, int zip,
			String companyName) {
		super(restaurantId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, companyName);
		this.licensed = licensed;
		
	}
	public boolean getLicensed() {
		return licensed;
	}
	public void setLicensed(boolean licensed) {
		this.licensed = licensed;
	}
	
	


}	