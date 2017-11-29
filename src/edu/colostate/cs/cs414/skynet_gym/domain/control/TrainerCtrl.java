package edu.colostate.cs.cs414.skynet_gym.domain.control;

import java.util.ArrayList;

import edu.colostate.cs.cs414.skynet_gym.domain.data.people.Address;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.HealthInsurance;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.PersonInformation;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.Qualification;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.Schedule;
import edu.colostate.cs.cs414.skynet_gym.domain.people.user.Trainer;
import edu.colostate.cs.cs414.skynet_gym.services.store.ObjectFile;

/**
 * TrainerCtrl class provides static methods and holds the static instances
 * of the trainer. Not intended for instantiation.
 * 
 * @author Mike Allan
 *
 */
public class TrainerCtrl {

	private TrainerCtrl() {} // Construction disabled
	
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
	 * Collects all the information needed by the used classes to create a
	 * trainer
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
	 * @return new Trainer instance
	 * 
	 * @throws IllegalArgumentException if an empty string is passed
	 */
	public static Trainer buildTrainer(
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
		
		return trainer;
	}
	
	/**
	 * 
	 * Add trainer instance to add to static list.
	 * Collects all the information needed by the used classes.
	 * 
	 * @param trainer to add
	 * 
	 * @throws IllegalArgumentException if password if empty or this is a
	 * 		duplicate
	 * @throws NullPointerException if trainer is null
	 */
	public static void addTrainer(final Trainer trainer) {
		
		if (trainer.isPassword("")) {
			throw new IllegalArgumentException(
					"Password can not be empty");
		}
			
		if (existsWithDLN(trainer.getPersonInfo().getDriversLicenseNumber())) {
			throw new IllegalArgumentException(
					"User already exists: drivers license is the same");
		}
		if (existsWithUsername(trainer.getUsername())) {
			throw new IllegalArgumentException(
					"User already exists: username is taken");
		}
		
		trainers.add(trainer);
		
		// Save the state
		saveState();
	}
	
	/**
	 * 
	 * Replace an existing trainer with new information
	 * Removes the trainer it is replacing
	 * 
	 * @param trainer, the new trainer instance
	 * @param existingTrainer, the current trainer
	 * 
	 * @throws IllegalArgumentException if an empty string is passed
	 * @throws NullPointerException if trainer is null
	 */
	public static void replaceTrainer(
			final Trainer trainer,
			final Trainer existingTrainer){
		
		if (existingTrainer == null ||
				!trainers.contains(existingTrainer)) {
			throw new IllegalArgumentException(
					"Can't find the given trainer.");
		}
		
		// Validate that given drivers license is not associated with another trainer
		if (!trainer.getPersonInfo().getDriversLicenseNumber().equals(
				existingTrainer.getPersonInfo().getDriversLicenseNumber()
				) &&
				existsWithDLN(trainer.getPersonInfo().getDriversLicenseNumber())) {
			throw new IllegalArgumentException(
					"User already exists: drivers license is the same");
		}
		if (!trainer.getUsername().equals(existingTrainer.getUsername()) &&
				existsWithUsername(trainer.getUsername())) {
			throw new IllegalArgumentException(
					"User already exists: username is taken");
		}
		
		// 
		int index = trainers.indexOf(existingTrainer);
		Trainer currentTrainer = trainers.get(index);
		if (!trainer.getUsername().equals(currentTrainer.getUsername())) {
			currentTrainer.setUsername(trainer.getUsername());
		}
		if (!trainer.getPersonInfo().equals(currentTrainer.getPersonInfo())) {
			currentTrainer.setPersonInfo(trainer.getPersonInfo());
		}
		if (!trainer.getSchedule().equals(currentTrainer.getSchedule())) {
			currentTrainer.setSchedule(trainer.getSchedule());
		}
		if (!trainer.getQualifications().equals(currentTrainer.getQualifications())) {
			currentTrainer.setQualifications(trainer.getQualifications());
		}
		
		trainers.set(index, currentTrainer);
		
		// Save the state
		saveState();
		
	}
	
	
	/**
	 * Remove the given trainer from the system
	 * 
	 * @param trainer the trainer to remove
	 * 
	 * @throws IllegalArgumentException if this trainer doesn't exist
	 * @throws NullPointerException if trainer is null
	 */
	public static void removeTrainer(final Trainer trainer) {
		
		if (!trainers.contains(trainer)) {
			throw new IllegalArgumentException(
					"Can't find the given trainer.");
		}
		
		if (!trainers.remove(trainer)) {
			throw new RuntimeException(
					"An issue occured when removing trainer.");
		}
		
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
	
	public static void setSerializedName(String name) {
		serializedName = name;
	}
	
	public static void clearData(){
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
