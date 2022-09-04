package movie.model;

import java.util.Date;

/**
 * Administrators is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Persons}).
 */
public class Administrators extends Persons {
	protected Date lastLogin;
	
	public Administrators(Integer userId, String firstName, String lastName, Date lastLogin) {
		super(userId, firstName, lastName);
		this.lastLogin = lastLogin;
	}
	
	public Administrators(Integer userId) {
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
