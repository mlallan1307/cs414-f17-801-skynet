package edu.colostate.cs.cs414.skynet_gym.domain.people.user;

import java.io.Serializable;

import edu.colostate.cs.cs414.skynet_gym.domain.data.people.PersonInformation;

/**
 * Stores a system user's information.
 * 
 * @author Mike Allan
 *
 */
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5887389957527165742L;
	private String userType;
	private String username;
	private String password;
	private PersonInformation personInfo;
	
	public User(String userType,
			String username,
			String password,
			PersonInformation personInfo) {
		
		this.userType = userType;
		this.username = username;
		this.password = password;
		this.personInfo = personInfo;
		
	}
	
	@Override
	public boolean equals(Object o) {
		try {
			return (this.toString().equals(User.class.cast(o).toString()));
		} catch (java.lang.ClassCastException e) {
			return false;
		} catch (java.lang.NullPointerException e) {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((personInfo == null) ? 0 : personInfo.hashCode());
		result = prime * result
				+ ((userType == null) ? 0 : userType.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		/**
		 * Note: Password is not shown for security
		 */
		return (this.userType + ":" +
				this.username + ":" +
				"[" + this.personInfo.toString() + "]");
	}
	
	public String toStringShort() {
		return (personInfo.getFirstName() + " " +
				personInfo.getLastName()) + " " +
				this.username;
	}
	
	public Boolean login(String user, String pass){
		if (this.username.equals(user) &&
			isPassword(pass)){
			return true;
		}
		return false;
	}
	
	public String recoverUsername(
			String firstName,
			String lastName,
			String driversLicenseNum) {
		if (firstName == null ||
				lastName == null ||
				driversLicenseNum == null) {
			return "";
		}
		if (firstName.equalsIgnoreCase(this.personInfo.getFirstName()) &&
				lastName.equalsIgnoreCase(this.personInfo.getLastName()) &&
				driversLicenseNum.equals(this.personInfo.getDriversLicenseNumber())) {
			return username;
		}
		
		return "";
	}
	
	public Boolean resetPassword(
			String firstName,
			String lastName,
			String driversLicenseNum,
			String user,
			String pass) {
		if (firstName == null ||
				lastName == null ||
				driversLicenseNum == null ||
				user == null ||
				pass == null) {
			return false;
		}
		if (firstName.equalsIgnoreCase(this.personInfo.getFirstName()) &&
				lastName.equalsIgnoreCase(this.personInfo.getLastName()) &&
				driversLicenseNum.equals(this.personInfo.getDriversLicenseNumber()) &&
				user.equals(this.username)) {
			this.setPassword(pass);
			return true;
		}
		
		return false;
	}
	
	/**
	 * @param password the password to check
	 * 
	 * @return true if password matches, false otherwise
	 */
	public Boolean isPassword(final String password){
		return (this.password.equals(password));
	}
	
	//
	// GETTERS & SETTERS
	//
	
	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	
	/**
	 * param username the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the personInfo
	 */
	public PersonInformation getPersonInfo() {
		return personInfo;
	}

	/**
	 * @param personInfo the personInfo to set
	 * @throws NullPointerException if param is null
	 */
	public void setPersonInfo(PersonInformation personInfo) {
		if (personInfo == null) {
			throw new NullPointerException("Param can not be null");
		}
		this.personInfo = personInfo;
	}
	
	/**
	 * @param password the password to set
	 */
	private void setPassword(String password) {
		this.password = password;
	}

}
