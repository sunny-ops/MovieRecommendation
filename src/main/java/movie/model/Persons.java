package movie.model;

/**
 * Persons is a simple, plain old java objects (POJO).
 * 
 * Persons/PersonsDao is the superclass for Administrators/AdministratorsDao and
 * BlogUsers/BlogUsersDao. Our implementation of Persons is a concrete class. This allows 
 * us to create records in the Persons MySQL table without having the associated records
 * in the Administrators or BlogUsers MySQL tables. Alternatively, Persons could be an
 * interface or an abstract class, which would force a Persons record to be created only
 * if an Administrators or BlogUsers record is created, too.
 */
public class Persons {
	protected Integer userId;
	protected String firstName;
	protected String lastName;
	
	public Persons(Integer userId, String firstName, String lastName) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Persons(Integer userId) {
		this.userId = userId;
	}
	
	public Persons(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
