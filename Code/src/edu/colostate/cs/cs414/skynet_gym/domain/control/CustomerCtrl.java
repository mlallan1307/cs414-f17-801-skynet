package edu.colostate.cs.cs414.skynet_gym.domain.control;

import java.util.ArrayList;

import edu.colostate.cs.cs414.skynet_gym.domain.people.customer.Customer;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.Address;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.HealthInsurance;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.PersonInformation;
import edu.colostate.cs.cs414.skynet_gym.utility.file.ObjectFile;

/**
 * CustomerCtrl class provides static methods and holds the static instances
 * of the customers. Not intended for instantiation.
 * 
 * @author Mike Allan
 *
 */
public final class CustomerCtrl {

	private CustomerCtrl() {
	}
	
	private static String serializedName = "customers";
	
	@Override
	public String toString() {
		String s = "";
		for (String pString : asStringList()) {
			s += "[" + pString + "]";
		}
		return s;
	}
	
	@Override
	public boolean equals(Object o){
		// All static class, can not have multiple instances
		return false;
	}
	
	public static ArrayList<String> asStringList(){
		ArrayList<String> rtn = new ArrayList<String>();
		for (Customer c : customers) {
			rtn.add(c.toString());
		}
		return rtn;
	}
	
	/**
	 * 
	 * @return true if customers exist, else false
	 */
	public static Boolean customersExist(){
		return (customers != null && customers.size()>0);
	}
	
	/**
	 * Attempts to reload the state of the system from last save.
	 */
	public static void initialize(){
		if (customersExist()){
			return;
		}
		loadState();
	}
	
	/**
	 * 
	 * Create customer instance to add to static list.
	 * Collects all the information needed by the used classes.
	 * 
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
	public static void createCustomer(
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
		if (firstName.isEmpty() ||
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
			throw new IllegalArgumentException(
					"Fields are empty. Please fill out all fields.");
		}
		
		if (existsWithDLN(driversLicenseNumber)) {
			throw new IllegalArgumentException(
					"User already exists: drivers license is the same");
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
		Customer c = new Customer(personInfo);
		
		customers.add(c);
		
		// Save the state
		saveState();
		
	}
	
	/**
	 * 
	 * Create customer instance to add to static list.
	 * Collects all the information needed by the used classes.
	 * Replaces and existing customer entry in the static list.
	 * 
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
	public static void replaceCustomer(
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
			String type,
			Customer existingCustomer){
		
		if (existingCustomer == null ||
				!customers.contains(existingCustomer)) {
			throw new IllegalArgumentException(
					"Can't find the given customer.");
		}
		
		// Throw if a required field is empty
		if (firstName.isEmpty() ||
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
			throw new IllegalArgumentException(
					"Fields are empty. Please fill out all fields.");
		}
		
		// Validate that given drivers license is not associated with another customer
		if (!driversLicenseNumber.equals(existingCustomer.getPersonInfo().getDriversLicenseNumber()) &&
				existsWithDLN(driversLicenseNumber)) {
			throw new IllegalArgumentException(
					"User already exists: drivers license is the same");
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
		
		// Create customer
		int index = customers.indexOf(existingCustomer);
		Customer currentCustomer = customers.get(index);
		if (!personInfo.equals(currentCustomer.getPersonInfo())) {
			currentCustomer.setPersonInfo(personInfo);
		}
		
		customers.set(index, currentCustomer);
		
		// Save the state
		saveState();
		
	}
	
	/**
	 * 
	 * @param driversLicenseNum
	 * @return true of the given drivers license is associated with an existing
	 * 			customer
	 */
	public static Boolean existsWithDLN(String driversLicenseNum) {
		for (Customer c : customers) {
			if (c.getPersonInfo()
					.getDriversLicenseNumber()
					.equals(driversLicenseNum)) {
				return true;
			}
		}
		return false;
	}

	public static ArrayList<Customer> searchCustomers(
			String firstName,
			String lastName,
			String phone,
			String email) {
		
		ArrayList<Customer> rtn = new ArrayList<Customer>();
		
		ArrayList<Customer> tmp = (ArrayList<Customer>) customers.clone();
		for (Customer cu : tmp) {
			if (!firstName.isEmpty()) {
				if (!cu.getPersonInfo().getFirstName().startsWith(firstName)) {
					continue;
				}
			}
			if (!lastName.isEmpty()) {
				if (!cu.getPersonInfo().getLastName().startsWith(lastName)) {
					continue;
				}
			}
			if (!phone.isEmpty()) {
				if (!cu.getPersonInfo().getPhone().startsWith(phone)) {
					continue;
				}
			}
			if (!email.isEmpty()) {
				if (!cu.getPersonInfo().getEmail().startsWith(email)) {
					continue;
				}
			}
			// If nothing could exclude this customer than add it to return list
			rtn.add(cu);
		}

		return rtn;
	}
	
	public static final ArrayList<Customer> getCustomers() {
		return customers;
	}
	
	protected static void setSerializedName(String name) {
		serializedName = name;
	}
	
	protected static void clearData(){
		customers.clear();
		ObjectFile.removeFile(serializedName);
	}
	
	/**
	 * Saves the state. Customer information is saved in a file.
	 */
	private static void saveState(){
		ObjectFile.saveAsFile(customers, serializedName);
	}
	
	/**
	 * Load the state. Customer information is retrieved from a file.
	 */
	private static void loadState(){
		ArrayList<Customer> c = null;
		try {
			c = (ArrayList<Customer>) ObjectFile.loadObjectFile(serializedName);
		} catch (java.lang.ClassCastException e) {
			System.out.println("cast error CustomerCtrl");
			return;
		}
		if (c != null) {
			customers = c;
			System.out.println(customers.size() + " customers loaded");
		}
		
	}

}
