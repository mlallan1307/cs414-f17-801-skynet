package edu.colostate.cs.cs414.skynet_gym.domain.control;

import java.io.File;
import java.util.ArrayList;

import edu.colostate.cs.cs414.skynet_gym.domain.data.equipment.Equipment;
import edu.colostate.cs.cs414.skynet_gym.utility.file.ObjectFile;

/**
 * EquipmentCtrl class provides static methods and holds the static instances
 * of the equipment. Not intended for instantiation.
 * 
 * @author Mike Allan
 *
 */
public final class EquipmentCtrl {

	private EquipmentCtrl() {
	}
	
	private static String serializedName = "equipment";
	private static ArrayList<Equipment> equipment = new ArrayList<Equipment>();
	
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
		for (Equipment eq : equipment) {
			rtn.add(eq.toString());
		}
		return rtn;
	}
	
	/**
	 * 
	 * @return true if equipment exist, else false
	 */
	public static Boolean equipmentExists(){
		return (equipment != null && equipment.size()>0);
	}
	
	/**
	 * Attempts to reload the state of the system from last save.
	 */
	public static void initialize(){
		if (equipmentExists()){
			return;
		}
		loadState();
	}
	
	/**
	 * 
	 * Create equipment instance to add to static list.
	 * Collects all the information needed by the used classes.
	 * 
	 * @param name
	 * @param quantity
	 * @param picture
	 * 
	 * @throws IllegalArgumentException if given invalid argument
	 */
	public static void createEquipment(
			String name,
			int quantity,
			File picture){
		
		// Throw if a required field is empty
		if (name.isEmpty()) {
			throw new IllegalArgumentException(
					"Invalid: The name field is empty.");
		}
		if (picture == null) {
			throw new IllegalArgumentException(
					"Invalid: Please select a picture for this equipment.");
		}
		if (!picture.exists()) {
			throw new IllegalArgumentException(
					"Invalid: Selected picture file does not exist.");
		}
		if (quantity < 0) {
			throw new IllegalArgumentException(
					"Invalid: The quantity must be positive");
		}
		
		if (existsWithName(name)) {
			throw new IllegalArgumentException(
					"Invalid: An Equipment entry with that name already exists");
		}
		
		Equipment eq = new Equipment(
				name,
				quantity,
				picture);
		
		equipment.add(eq);
		
		// Save the state
		saveState();
		
	}
	
	/**
	 * 
	 * Create equipment instance to add to static list.
	 * Collects all the information needed by the used classes.
	 * Replaces and existing equipment entry in the static list.
	 * 
	 * @param name
	 * @param quantity
	 * @param picture
	 * @param existingEquipment
	 * 
	 * @throws IllegalArgumentException if given invalid argument
	 */
	public static void replaceEquipment(
			String name,
			int quantity,
			File picture,
			Equipment existingEquipment){
		
		// Throw if a required field is empty
		if (name.isEmpty()) {
			throw new IllegalArgumentException(
					"Invalid: The name field is empty.");
		}
		if (picture == null) {
			throw new IllegalArgumentException(
					"Invalid: Please select a picture for this equipment.");
		}
		if (!picture.exists()) {
			throw new IllegalArgumentException(
					"Invalid: Selected picture file does not exist.");
		}
		if (quantity < 0) {
			throw new IllegalArgumentException(
					"Invalid: The quantity must be positive");
		}
		
		if (existingEquipment == null ||
				!equipment.contains(existingEquipment)) {
			throw new IllegalArgumentException(
					"Can't find the given equipment entry.");
		}
		
		// Validate that given name is not associated with another equipment
		if (!name.equals(existingEquipment.getName()) &&
				existsWithName(name)) {
			throw new IllegalArgumentException(
					"An Equipment entry with that name already exists");
		}
		
		// Update equipment
		int index = equipment.indexOf(existingEquipment);
		Equipment currentEquipment = equipment.get(index);
		if (!name.equals(currentEquipment.getName())) {
			currentEquipment.setName(name);
		}
		if (quantity != currentEquipment.getQuantity()) {
			currentEquipment.setQuantity(quantity);
		}
		if (!picture.getAbsolutePath().equals(currentEquipment.getPicture().getAbsolutePath())) {
			currentEquipment.setPicture(picture);
		}
		
		equipment.set(index, currentEquipment);
		
		// Save the state
		saveState();
		
	}
	
	/**
	 * 
	 * @param name
	 * @return true of the given name is associated with an existing equipment entry
	 */
	public static Boolean existsWithName(String name) {
		for (Equipment e : equipment) {
			if (e.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public static ArrayList<Equipment> searchEquipment (
			String name,
			int quantity) {
		
		ArrayList<Equipment> rtn = new ArrayList<Equipment>();
		
		ArrayList<Equipment> tmp = (ArrayList<Equipment>) equipment.clone();
		for (Equipment eq : tmp) {
			if (!name.isEmpty()) {
				if (!eq.getName().startsWith(name)) {
					continue;
				}
			}
			if (quantity >= 0) {
				if (eq.getQuantity() != quantity) {
					continue;
				}
			}
			// If nothing could exclude this equipment then add it to return list
			rtn.add(eq);
		}

		return rtn;
	}
	
	public static final ArrayList<Equipment> getEquipment() {
		return equipment;
	}
	
	protected static void setSerializedName(String name) {
		serializedName = name;
	}
	
	protected static void clearData(){
		equipment.clear();
		ObjectFile.removeFile(serializedName);
	}
	
	/**
	 * Saves the state. Equipment information is saved in a file.
	 */
	private static void saveState(){
		ObjectFile.saveAsFile(equipment, serializedName);
	}
	
	/**
	 * Load the state. Equipment information is retrieved from a file.
	 */
	private static void loadState(){
		ArrayList<Equipment> eq = null;
		try {
			eq = (ArrayList<Equipment>) ObjectFile.loadObjectFile(serializedName);
		} catch (java.lang.ClassCastException e) {
			System.out.println("cast error EquipmentCtrl");
			return;
		}
		if (eq != null) {
			equipment = eq;
			System.out.println(equipment.size() + " equipment loaded");
		}
		
	}

}
