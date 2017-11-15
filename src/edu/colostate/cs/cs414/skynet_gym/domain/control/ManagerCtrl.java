package edu.colostate.cs.cs414.skynet_gym.domain.control;

import edu.colostate.cs.cs414.skynet_gym.domain.data.people.Address;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.HealthInsurance;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.PersonInformation;
import edu.colostate.cs.cs414.skynet_gym.domain.people.user.Manager;
import edu.colostate.cs.cs414.skynet_gym.services.store.ObjectFile;

/**
 * ManagerCtrl class provides static methods and holds the static instance of
 * the manager. Not intended for instantiation.
 * 
 * @author Mike Allan
 *
 */
public final class ManagerCtrl{

	private ManagerCtrl() {} // Construction disabled
	
	private static String serializedName = "manager";
	private static Manager manager;
	
	@Override
	public String toString() {
		return ("[" + manager.toString() + "]");
	}
	
	@Override
	public boolean equals(Object o){
		// All static class, can not have multiple instances
		return false;
	}
	
	/**
	 * 
	 * @return true if manager exists, else false
	 */
	public static Boolean managerExists(){
		return (manager != null);
	}
	
	/**
	 * Attempts to reload the state of the system from last save.
	 */
	public static void initialize(){
		if (managerExists()){
			return;
		}
		loadState();
	}
	
	/**
	 * 
	 * Create static manager instance with all the information needed by the used classes.
	 * 
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param driversLicenseNumber
	 * @param phone
	 * @param email
	 * @param healthInsuranceProviderName
	 * @param street1
	 * @param street2
	 * @param provOrState
	 * @param city
	 * @param zipCode
	 * @param type
	 * 
	 * @throws IllegalArgumentException if an empty string is passed
	 */
	public static void createManager(String username,
			String password,
			String firstName, // PersonInformation
			String lastName,
			String driversLicenseNumber,
			String phone,
			String email,
			String healthInsuranceProviderName, // HealthInsurance
			String street1, // Address
			String street2,
			String provOrState,
			String city,
			String zipCode,
			String type){
		
		// Throw if a required field is empty
		if (username.isEmpty() ||
				password.isEmpty() ||
				firstName.isEmpty() ||
				lastName.isEmpty() ||
				driversLicenseNumber.isEmpty() ||
				phone.isEmpty() ||
				email.isEmpty() ||
				healthInsuranceProviderName.isEmpty() ||
				street1.isEmpty() ||
				provOrState.isEmpty() ||
				city.isEmpty() ||
				zipCode.isEmpty() ||
				type.isEmpty()) {
			throw new IllegalArgumentException("Fields are empty. Please fill out all fields.");
		}
		
		Address address = new Address(
				street1,
				street2,
				provOrState,
				city,
				zipCode,
				type);
		
		HealthInsurance healthInsurance = new HealthInsurance(
				healthInsuranceProviderName);
		
		PersonInformation personInfo = new PersonInformation(
				firstName,
				lastName,
				driversLicenseNumber,
				phone,
				email,
				healthInsurance,
				address);
		
		// Create manager
		manager = new Manager(username,
				password,
				personInfo);
		
		// Save the state
		saveState();
		
	}
	
	/**
	 * 
	 * @return static manager instance
	 */
	public final static Manager getManager(){
		return manager;
	}
	
	public static boolean resetPassword(
			String firstName,
			String lastName,
			String driversLicenseNum,
			String user,
			String pass) {
		if (managerExists() &&
				manager.resetPassword(
					firstName,
					lastName,
					driversLicenseNum,
					user,
					pass)) {
			// Password changed
			
			// Save the state
			saveState();
			return true;
		}
		
		return false;
	}
	
	public static void setSerializedName(String name) {
		serializedName = name;
	}
	
	public static void clearData(){
		manager = null;
		ObjectFile.removeFile(serializedName);
	}
	
	/**
	 * Saves the state. Manager information is saved in a file.
	 */
	private static void saveState(){
		ObjectFile.saveAsFile(manager, serializedName);
	}
	
	/**
	 * Load the state. Manager information is retrieved from a file.
	 */
	private static void loadState(){
		Manager m = null;
		try {
			m = Manager.class.cast(ObjectFile.loadObjectFile(serializedName));
		} catch (java.lang.ClassCastException e) {
			System.out.println("cast error ManagerCtrl");
			return;
		}
		if (m != null) {
			manager = m;
			System.out.println("Manager loaded");
		}
	}

	
	
}
