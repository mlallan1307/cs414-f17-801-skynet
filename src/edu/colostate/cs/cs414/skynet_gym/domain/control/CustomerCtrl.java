package edu.colostate.cs.cs414.skynet_gym.domain.control;

import java.util.ArrayList;

import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Routine;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.Address;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.HealthInsurance;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.PersonInformation;
import edu.colostate.cs.cs414.skynet_gym.domain.people.other.Customer;
import edu.colostate.cs.cs414.skynet_gym.services.store.ObjectFile;

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
	private static ArrayList<Customer> customers = new ArrayList<Customer>();
	
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
	 * Collects all the information needed by the used classes to create a new
	 * Customer
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
	 * @return new customer instance
	 * 
	 * @throws IllegalArgumentException if an empty string is passed
	 */
	public static Customer buildCustomer(
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
		
		return c;
	}
	
	/**
	 * Add customer instance to add to static list.
	 * 
	 * @param customer
	 * 
	 * @throws IllegalArgumentException if the give customer is a duplicate
	 */
	public static void addCustomer(
			final Customer customer){
		
		if (existsWithDLN(customer.getPersonInfo().getDriversLicenseNumber())) {
			throw new IllegalArgumentException(
					"User already exists: drivers license is the same");
		}
		
		customers.add(customer);
		
		// Save the state
		saveState();
	}
	
	/**
	 * Replace an existing customer entry in the static list.
	 * 
	 * @param newCustomer
	 * @param existingCustomer
	 * 
	 * @throws IllegalArgumentException the existing customer can't be found
	 * 		or the new customer is a duplicate
	 */
	public static void replaceCustomer(
			final Customer newCustomer,
			final Customer existingCustomer){
		
		if (existingCustomer == null ||
				!customers.contains(existingCustomer)) {
			throw new IllegalArgumentException(
					"Can't find the given customer.");
		}
		
		// Validate that given drivers license is not associated with another customer
		if (!newCustomer.getPersonInfo().getDriversLicenseNumber().equals(
				existingCustomer.getPersonInfo().getDriversLicenseNumber()
				) &&
				existsWithDLN(newCustomer.getPersonInfo().getDriversLicenseNumber())) {
			throw new IllegalArgumentException(
					"User already exists: drivers license is the same");
		}
		
		// Create customer
		int index = customers.indexOf(existingCustomer);
		Customer currentCustomer = customers.get(index);
		if (!newCustomer.getPersonInfo().equals(currentCustomer.getPersonInfo())) {
			currentCustomer.setPersonInfo(newCustomer.getPersonInfo());
		}
		
		customers.set(index, currentCustomer);
		
		// Save the state
		saveState();
		
	}
	
	/**
	 * Remove the given Customer from the system
	 * 
	 * @param c the Customer to remove
	 * 
	 * @throws IllegalArgumentException if this Customer doesn't exist
	 * @throws NullPointerException if Customer is null
	 */
	public static void removeCustomer(final Customer c) {
		if (c == null) {
			throw new NullPointerException("Given Customer is null.");
		}
		
		if (!customers.contains(c)) {
			throw new IllegalArgumentException(
					"Can't find the given Customer.");
		}
		
		if (!customers.remove(c)) {
			throw new RuntimeException(
					"An issue occured when removing Customer.");
		}
		
		// Save the state
		saveState();
	}
	
	/**
	 * Remove the given Routine from any customers "assigned" list
	 * 
	 * @param r the Routine to remove
	 * 
	 * @throws IllegalArgumentException if this Routine doesn't exist
	 * @throws NullPointerException if Routine is null
	 */
	public static void routineRemoved(final Routine r) {
		if (r == null) {
			throw new NullPointerException("Given Routine is null.");
		}
		
		for (Customer c: customers) {
			if (c.getRoutines().contains(r)) {
				ArrayList<Routine> ral = new ArrayList<Routine>();
				ral.addAll(c.getRoutines());
				ral.remove(r);
				assignRoutines(c, ral);
			}
		}
		
		// Save the state
		saveState();
	}
	
	/**
	 * Checks if the given drivers license number is already in the static list
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

	/**
	 * Gets a subset of the static list of customers based on the given search
	 * information
	 * 
	 * @param firstName
	 * @param lastName
	 * @param phone
	 * @param email
	 * 
	 * @return array of matching customers
	 */
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
	
	/**
	 * Associates routines with a given customer
	 * 
	 * @param c, the customer getting assigned routines 
	 * @param routines
	 * 
	 * @throws IllegalArgumentException if the given customer is invalid
	 */
	public static void assignRoutines(
			Customer c,
			ArrayList<Routine> routines) {
		if (c == null ||
				routines == null) {
			throw new IllegalArgumentException(
					"Given customer or routine object is null");
		} else if (!customers.contains(c)) {
			throw new IllegalArgumentException(
					"Given customer is unknown");
		}
		
		customers.get(customers.indexOf(c)).setRoutines(routines);
		
		saveState();
	}
	
	/**
	 * 
	 * @return customers, data member
	 */
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
