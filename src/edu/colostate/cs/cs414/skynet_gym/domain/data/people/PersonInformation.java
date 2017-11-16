package edu.colostate.cs.cs414.skynet_gym.domain.data.people;

import java.io.Serializable;

/**
 * Stores information pertaining to a person
 * 
 * @author Mike Allan
 *
 */
public class PersonInformation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8023828402383925748L;
	private String firstName;
	private String lastName;
	private String driversLicenseNumber;
	private String phone;
	private String email;
	private Address address;
	private HealthInsurance healthInsurance;
	
	public PersonInformation(String firstName,
			String lastName,
			String driversLicenseNumber,
			String phone,
			String email,
			HealthInsurance healthInsurance,
			Address address){
		
		this.setPersonInformation(firstName,
				lastName,
				driversLicenseNumber,
				phone,
				email,
				healthInsurance,
				address);

	}
	
	@Override
	public boolean equals(Object o){
		try {
			return (this.toString().equals(PersonInformation.class.cast(o).toString()));
		} catch (java.lang.ClassCastException e) {
			return false;
		} catch (java.lang.NullPointerException e) {
			return false;
		}
	}
	
	@Override
	public String toString(){
		return (this.firstName + ":" +
				this.lastName + ":" +
				this.driversLicenseNumber + ":" +
				this.phone + ":" +
				this.email + ":" +
				"[" + this.address.toString() + "]" + ":" +
				"[" + this.healthInsurance.toString() + "]");
	}
	
	protected void setPersonInformation(String firstName,
			String lastName,
			String driversLicenseNumber,
			String phone,
			String email,
			HealthInsurance healthInsurance,
			Address address){
		
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setDriversLicenseNumber(driversLicenseNumber);
		this.setPhone(phone);
		this.setEmail(email);
		this.setHealthInsurance(healthInsurance);
		this.setAddress(address);
		
	}
	
	//
	// GETTERS & SETTERS
	//

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	protected void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	protected void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the driversLicenseNumber
	 */
	public String getDriversLicenseNumber() {
		return driversLicenseNumber;
	}

	/**
	 * @param driversLicenseNumber the driversLicenseNumber to set
	 */
	protected void setDriversLicenseNumber(String driversLicenseNumber) {
		this.driversLicenseNumber = driversLicenseNumber;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	protected void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	protected void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 * @throws NullPointerException if param is null
	 */
	protected void setAddress(Address address) {
		if (address == null) {
			throw new NullPointerException("Param can not be null");
		}
		this.address = address;
	}

	/**
	 * @return the healthInsurance
	 */
	public HealthInsurance getHealthInsurance() {
		return healthInsurance;
	}

	/**
	 * @param healthInsurance the healthInsurance to set
	 * @throws NullPointerException if param is null
	 */
	protected void setHealthInsurance(HealthInsurance healthInsurance) {
		if (healthInsurance == null) {
			throw new NullPointerException("Param can not be null");
		}
		this.healthInsurance = healthInsurance;
	}
	
}
