package movie.model;

import java.util.Date;

/**
 * BlogUsers is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Persons}).
 */
public class Users extends Persons {
	protected Date lastLogin;
	
	
	public Users(Integer userId, String firstName, String lastName,  Date lastLogin) {
		super(userId, firstName, lastName);
		this.lastLogin = lastLogin;
	}
	
	public Users(Integer userId) {
		super(userId);
	}

	/** Getters and setters. */
	
	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}	
}
