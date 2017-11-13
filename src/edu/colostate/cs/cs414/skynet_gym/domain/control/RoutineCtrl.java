package edu.colostate.cs.cs414.skynet_gym.domain.control;

import java.util.ArrayList;

import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Exercise;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Routine;
import edu.colostate.cs.cs414.skynet_gym.services.store.ObjectFile;

/**
 * RoutineCtrl class provides static methods and holds the static instances
 * of the routines. Not intended for instantiation.
 * 
 * @author Mike Allan
 *
 */
public final class RoutineCtrl {

	private RoutineCtrl() {
	}
	
	private static String serializedName = "routines";
	private static ArrayList<Routine> routines = new ArrayList<Routine>();
	
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
		for (Routine r : routines) {
			rtn.add(r.toString());
		}
		return rtn;
	}
	
	/**
	 * 
	 * @return true if Routines exist, else false
	 */
	public static Boolean routinesExist(){
		return (routines != null && routines.size()>0);
	}
	
	/**
	 * Attempts to reload the state of the system from last save.
	 */
	public static void initialize(){
		if (routinesExist()){
			return;
		}
		loadState();
	}
	
	/**
	 * 
	 * Create Routine to add to static list.
	 * Collects all the information needed by the used classes.
	 * 
	 * @param name
	 * @param exercises
	 * 
	 * @throws IllegalArgumentException if given invalid argument
	 */
	public static void createRoutine(
			String name,
			ArrayList<Exercise> exercises){
		
		// Throw if a required field is invalid
		if (name.isEmpty()) {
			throw new IllegalArgumentException(
					"Invalid: The name field is empty.");
		}
		if (existsWithName(name)) {
			throw new IllegalArgumentException(
					"Invalid: An Exercise with that name already exists");
		}
		if (exercises == null){
			throw new IllegalArgumentException(
					"Invalid: exercise list is null");
		}
		
		Routine r = new Routine(
				name,
				exercises);
		
		routines.add(r);
		
		// Save the state
		saveState();
		
	}
	
	/**
	 * 
	 * Create exercise to add to static list.
	 * Collects all the information needed by the used classes.
	 * Replaces an existing exercise in the static list.
	 * 
	 * @param name
	 * @param exerciseType
	 * @param equipment
	 * @param duration
	 * @param numberOfSets
	 * @param repsPerSet
	 * @param existingExercise
	 * 
	 * @throws IllegalArgumentException if given invalid argument
	 */
	public static void replaceRoutine(
			String name,
			ArrayList<Exercise> exercises,
			Routine existingRoutine){
	
		// Throw if a required field is invalid
		if (name.isEmpty()) {
			throw new IllegalArgumentException(
					"Invalid: The name field is empty.");
		}
		if (exercises == null){
			throw new IllegalArgumentException(
					"Invalid: exercise list is null");
		}
		
		if (existingRoutine == null ||
				!routines.contains(existingRoutine)) {
			throw new IllegalArgumentException(
					"Can't find the given exercise.");
		}
		
		// Validate that given name is not associated with another routine
		if (!name.equals(existingRoutine.getName()) &&
				existsWithName(name)) {
			throw new IllegalArgumentException(
					"A Routine with that name already exists");
		}
		
		// Update routines
		int index = routines.indexOf(existingRoutine);
		Routine updatedRoutine = routines.get(index);
		if (!name.equals(existingRoutine.getName())) {
			updatedRoutine.setName(name);
		}
		if (!existingRoutine.getExercises().equals(exercises)) {
			updatedRoutine.setExercises(exercises);
		}
		
		routines.set(index, updatedRoutine);
		
		// Save the state
		saveState();
		
	}
	
	/**
	 * 
	 * @param name
	 * @return true of the given name is associated with an existing routine
	 */
	public static Boolean existsWithName(String name) {
		for (Routine r : routines) {
			if (r.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public static ArrayList<Routine> searchRoutines (
			String name) {
		
		ArrayList<Routine> rtn = new ArrayList<Routine>();
		
		ArrayList<Routine> tmp = (ArrayList<Routine>) routines.clone();
		for (Routine r : tmp) {
			if (!name.isEmpty()) {
				if (!r.getName().startsWith(name)) {
					continue;
				}
			}
			// If nothing could exclude this Routine then add it to return list
			rtn.add(r);
		}

		return rtn;
	}
	
	public static final ArrayList<Routine> getRoutines() {
		return routines;
	}
	
	protected static void setSerializedName(String name) {
		serializedName = name;
	}
	
	protected static void clearData(){
		routines.clear();
		ObjectFile.removeFile(serializedName);
	}
	
	/**
	 * Saves the state. Routine information is saved in a file.
	 */
	private static void saveState(){
		ObjectFile.saveAsFile(routines, serializedName);
	}
	
	/**
	 * Load the state. Routine information is retrieved from a file.
	 */
	private static void loadState(){
		ArrayList<Routine> r = null;
		try {
			r = (ArrayList<Routine>) ObjectFile.loadObjectFile(serializedName);
		} catch (java.lang.ClassCastException e) {
			System.out.println("cast error RoutineCtrl");
			return;
		}
		if (r != null) {
			routines = r;
			System.out.println(routines.size() + " routines loaded");
		}
		
	}

}
