package edu.colostate.cs.cs414.skynet_gym.domain.control;

import java.util.ArrayList;

import edu.colostate.cs.cs414.skynet_gym.domain.people.info.Address;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.HealthInsurance;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.PersonInformation;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.Qualification;
import edu.colostate.cs.cs414.skynet_gym.domain.people.trainer.Trainer;
import edu.colostate.cs.cs414.skynet_gym.utility.data.Schedule;
import edu.colostate.cs.cs414.skynet_gym.utility.file.ObjectFile;

/**
 * TrainerCtrl class provides static methods and holds the static instances
 * of the trainer. Not intended for instantiation.
 * 
 * @author Mike Allan
 *
 */
public class TrainerCtrl {

	private TrainerCtrl() {
	}
	
	private static String serializedName = "trainers";
	private static ArrayList<Trainer> trainers = new ArrayList<Trainer>();
	
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
	
	public final static ArrayList<String> asStringList(){
		ArrayList<String> rtn = new ArrayList<String>();
		for (Trainer t : trainers) {
			rtn.add(t.toString());
		}
		return rtn;
	}
	
	/**
	 * 
	 * @return true if trainers exist, else false
	 */
	public static Boolean trainersExists(){
		return (trainers != null && trainers.size()>0);
	}
	
	/**
	 * Attempts to reload the state of the system from last save.
	 */
	public static void initialize(){
		if (trainersExists()){
			return;
		}
		loadState();
	}
	
	/**
	 * 
	 * Create trainer instance to add to static list.
	 * Collects all the information needed by the used classes.
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
	 * @param schedule
	 * @param qualifications
	 * 
	 * @throws IllegalArgumentException if an empty string is passed
	 */
	public static void createTrainer(
			String username,
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
			String type,
			Schedule schedule,
			ArrayList<Qualification> qualifications){
		
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
				type.isEmpty() ||
				schedule == null || schedule.isEmpty() ||
				qualifications == null || qualifications.isEmpty()) {
			throw new IllegalArgumentException(
					"Fields are empty. Please fill out all fields.");
		}
		
		if (existsWithDLN(driversLicenseNumber)) {
			throw new IllegalArgumentException(
					"User already exists: drivers license is the same");
		}
		if (existsWithUsername(username)) {
			throw new IllegalArgumentException(
					"User already exists: username is taken");
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
		
		// Create trainer
		Trainer trainer = new Trainer(
				username,
				password,
				personInfo,
				schedule,
				qualifications);
		
		trainers.add(trainer);
		
		// Save the state
		saveState();
		
	}
	
	/**
	 * 
	 * Create trainer instance to add to static list.
	 * Collects all the information needed by the used classes.
	 * Removes the trainer it is replacing
	 * 
	 * @param username
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
	 * @param schedule
	 * @param qualifications
	 * @param existingTrainer
	 * 
	 * @throws IllegalArgumentException if an empty string is passed
	 */
	public static void replaceTrainer(
			String username,
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
			Schedule schedule,
			ArrayList<Qualification> qualifications,
			final Trainer existingTrainer){
		
		if (existingTrainer == null ||
				!trainers.contains(existingTrainer)) {
			throw new IllegalArgumentException(
					"Can't find the given trainer.");
		}
		
		// Throw if a required field is empty
		if (username.isEmpty() ||
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
				type.isEmpty() ||
				schedule == null || schedule.isEmpty() ||
				qualifications == null || qualifications.isEmpty()) {
			throw new IllegalArgumentException(
					"Fields are empty. Please fill out all fields.");
		}
		
		// Validate that given drivers license is not associated with another trainer
		if (!driversLicenseNumber.equals(existingTrainer.getPersonInfo().getDriversLicenseNumber()) &&
				existsWithDLN(driversLicenseNumber)) {
			throw new IllegalArgumentException(
					"User already exists: drivers license is the same");
		}
		if (!username.equals(existingTrainer.getUsername()) &&
				existsWithUsername(username)) {
			throw new IllegalArgumentException(
					"User already exists: username is taken");
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
		int index = trainers.indexOf(existingTrainer);
		Trainer currentTrainer = trainers.get(index);
		if (!username.equals(currentTrainer.getUsername())) {
			currentTrainer.setUsername(username);
		}
		if (!personInfo.equals(currentTrainer.getPersonInfo())) {
			currentTrainer.setPersonInfo(personInfo);
		}
		if (!schedule.equals(currentTrainer.getSchedule())) {
			currentTrainer.setSchedule(schedule);
		}
		if (!qualifications.equals(currentTrainer.getQualifications())) {
			currentTrainer.setQualifications(qualifications);;
		}
		
		trainers.set(index, currentTrainer);
		
		// Save the state
		saveState();
		
	}
	
	/**
	 * 
	 * @param driversLicenseNum
	 * @return true of the given drivers license is associated with an existing
	 * 			trainer or the manager
	 */
	public static Boolean existsWithDLN(String driversLicenseNum) {
		if (ManagerCtrl.managerExists() &&
				ManagerCtrl.getManager()
				.getPersonInfo()
				.getDriversLicenseNumber()
				.equals(driversLicenseNum)) {
			return true;
		}
		for (Trainer t : trainers) {
			if (t.getPersonInfo()
					.getDriversLicenseNumber()
					.equals(driversLicenseNum)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param username
	 * @return true of the given username is associated with an existing trainer
	 * 			or the manager.
	 */
	public static Boolean existsWithUsername(String username) {
		if (ManagerCtrl.managerExists() &&
				ManagerCtrl.getManager()
				.getUsername()
				.equals(username)) {
			return true;
		}
		for (Trainer t : trainers) {
			if (t.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Use this to check if the login information is valid for a trainer.
	 * 
	 * @param user is the attempted login username
	 * @param pass is the attempted login password
	 * 
	 * @return true if login information is valid for a trainer
	 */
	public static Boolean login(String user, String pass){
		for (Trainer t : trainers) {
			if (t.login(user, pass)) {
				return true;
			}
		}
		return false;
	}
	
	public static String recoverUsername(
			String firstName,
			String lastName,
			String driversLicenseNum) {
		for (Trainer t : trainers) {
			String rtn = t.recoverUsername(
					firstName,
					lastName,
					driversLicenseNum);
			if (rtn != "") {
				return rtn;
			}
		}
		return "";
	}
	
	public static Boolean resetPassword(
			String firstName,
			String lastName,
			String driversLicenseNum,
			String user,
			String pass) {
		for (Trainer t : trainers) {
			if (t.resetPassword(
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
		}
		
		return false;
	}
	
	public static ArrayList<Trainer> searchTrainers(
			String firstName,
			String lastName,
			String phone,
			String email,
			String username) {
		
		ArrayList<Trainer> rtn = new ArrayList<Trainer>();
		
		ArrayList<Trainer> tmp = (ArrayList<Trainer>) trainers.clone();
		for (Trainer tr : tmp) {
			if (!firstName.isEmpty()) {
				if (!tr.getPersonInfo().getFirstName().startsWith(firstName)) {
					continue;
				}
			}
			if (!lastName.isEmpty()) {
				if (!tr.getPersonInfo().getLastName().startsWith(lastName)) {
					continue;
				}
			}
			if (!phone.isEmpty()) {
				if (!tr.getPersonInfo().getPhone().startsWith(phone)) {
					continue;
				}
			}
			if (!email.isEmpty()) {
				if (!tr.getPersonInfo().getEmail().startsWith(email)) {
					continue;
				}
			}
			if (!username.isEmpty()) {
				if (!tr.getUsername().startsWith(username)) {
					continue;
				}
			}
			// If nothing could exclude this trainer than add it to return list
			rtn.add(tr);
		}

		return rtn;
	}
	
	public static final ArrayList<Trainer> getTrainers() {
		return trainers;
	}
	
	protected static void setSerializedName(String name) {
		serializedName = name;
	}
	
	protected static void clearData(){
		trainers.clear();
		ObjectFile.removeFile(serializedName);
	}
	
	/**
	 * Saves the state. Trainer information is saved in a file.
	 */
	private static void saveState(){
		ObjectFile.saveAsFile(trainers, serializedName);
	}
	
	/**
	 * Load the state. Trainer information is retrieved from a file.
	 */
	private static void loadState(){
		ArrayList<Trainer> t = null;
		try {
			t = (ArrayList<Trainer>) ObjectFile.loadObjectFile(serializedName);
		} catch (java.lang.ClassCastException e) {
			System.out.println("cast error TrainerCtrl");
			return;
		}
		if (t != null) {
			trainers = t;
			System.out.println(trainers.size() + " trainers loaded");
		}
		
	}

	
}
