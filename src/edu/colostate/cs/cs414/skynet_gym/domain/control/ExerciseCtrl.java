package edu.colostate.cs.cs414.skynet_gym.domain.control;

import java.time.Duration;
import java.util.ArrayList;

import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Equipment;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Exercise;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.ExerciseType;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.ExerciseTypeIf;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.SetBasedExercise;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.TimeBasedExercise;
import edu.colostate.cs.cs414.skynet_gym.services.store.ObjectFile;

/**
 * ExerciseCtrl class provides static methods and holds the static instances
 * of the exercises. Not intended for instantiation.
 * 
 * @author Mike Allan
 *
 */
public final class ExerciseCtrl {

	private ExerciseCtrl() {
	}
	
	private static String serializedName = "exercises";
	private static ArrayList<Exercise> exercises = new ArrayList<Exercise>();
	
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
		for (Exercise ex : exercises) {
			rtn.add(ex.toString());
		}
		return rtn;
	}
	
	/**
	 * 
	 * @return true if exercises exist, else false
	 */
	public static Boolean exercisesExist(){
		return (exercises != null && exercises.size()>0);
	}
	
	/**
	 * Attempts to reload the state of the system from last save.
	 */
	public static void initialize(){
		if (exercisesExist()){
			return;
		}
		loadState();
	}
	
	/**
	 * 
	 * Create exercise to add to static list.
	 * Collects all the information needed by the used classes.
	 * 
	 * @param name
	 * @param exerciseType
	 * @param equipment
	 * @param duration
	 * @param numberOfSets
	 * @param repsPerSet
	 * 
	 * @throws IllegalArgumentException if given invalid argument
	 */
	public static void createExercise(
			String name,
			ExerciseType exerciseType,
			Equipment equipment,
			Duration duration,
			int numberOfSets,
			int repsPerSet){
		
		// Throw if a required field is invalid
		if (name.isEmpty()) {
			throw new IllegalArgumentException(
					"Invalid: The name field is empty.");
		}
		if (existsWithName(name)) {
			throw new IllegalArgumentException(
					"Invalid: An Exercise with that name already exists");
		}
		
		ExerciseTypeIf exerciseInfo = null;
		
		if (exerciseType == null){
			throw new IllegalArgumentException(
					"Invalid: exerciseType is null");
		}
		else if (exerciseType.equals(ExerciseType.TimeBased)) {
			if (duration == null) {
				throw new IllegalArgumentException(
						"Invalid: The duration field is empty.");
			}
			else if (duration.isZero() || duration.isNegative()) {
				throw new IllegalArgumentException(
						"Invalid: The duration field must be positive.");
			}
			exerciseInfo = new TimeBasedExercise(duration);
		}
		else if (exerciseType.equals(ExerciseType.SetBased)) {
			if (numberOfSets <= 0 || repsPerSet <= 0) {
				throw new IllegalArgumentException(
						"Invalid: The number of sets and reps per set must be greater than zero.");
			}
			exerciseInfo = new SetBasedExercise(numberOfSets, repsPerSet);
		}
		else {
			throw new IllegalArgumentException(
					"Invalid: Unknown exerciseType");
		}
		
		
		Exercise eq = new Exercise(
				name,
				exerciseInfo,
				equipment);
		
		exercises.add(eq);
		
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
	public static void replaceExercise(
			String name,
			ExerciseType exerciseType,
			Equipment equipment,
			Duration duration,
			int numberOfSets,
			int repsPerSet,
			Exercise existingExercise){
		
		// Throw if a required field is invalid
		if (name.isEmpty()) {
			throw new IllegalArgumentException(
					"Invalid: The name field is empty.");
		}
		
		ExerciseTypeIf exerciseInfo = null;
		
		if (exerciseType == null){
			throw new IllegalArgumentException(
					"Invalid: exerciseType is null");
		}
		else if (exerciseType.equals(ExerciseType.TimeBased)) {
			if (duration == null) {
				throw new IllegalArgumentException(
						"Invalid: The duration field is empty.");
			}
			else if (duration.isZero() || duration.isNegative()) {
				throw new IllegalArgumentException(
						"Invalid: The duration field must be positive.");
			}
			exerciseInfo = new TimeBasedExercise(duration);
		}
		else if (exerciseType.equals(ExerciseType.SetBased)) {
			if (numberOfSets <= 0 || repsPerSet <= 0) {
				throw new IllegalArgumentException(
						"Invalid: The number of sets and reps per set must be greater than zero.");
			}
			exerciseInfo = new SetBasedExercise(numberOfSets, repsPerSet);
		}
		else {
			throw new IllegalArgumentException(
					"Invalid: Unknown exerciseType");
		}
		
		if (existingExercise == null ||
				!exercises.contains(existingExercise)) {
			throw new IllegalArgumentException(
					"Can't find the given exercise.");
		}
		
		// Validate that given name is not associated with another exercise
		if (!name.equals(existingExercise.getName()) &&
				existsWithName(name)) {
			throw new IllegalArgumentException(
					"An Exercise entry with that name already exists");
		}
		
		// Update exercises
		int index = exercises.indexOf(existingExercise);
		Exercise currentExercise = exercises.get(index);
		if (!name.equals(currentExercise.getName())) {
			currentExercise.setName(name);
		}
		if (!exerciseInfo.equals(currentExercise.getExerciseInfo())) {
			currentExercise.setExerciseInfo(exerciseInfo);;
		}
		if (equipment == null) {
			currentExercise.setEquipment(null);
		}
		else if (!equipment.equals(currentExercise.getEquipment())) {
			currentExercise.setEquipment(equipment);
		}
		
		exercises.set(index, currentExercise);
		
		// Save the state
		saveState();
		
	}
	
	/**
	 * 
	 * @param name
	 * @return true of the given name is associated with an existing exercise
	 */
	public static Boolean existsWithName(String name) {
		for (Exercise ex : exercises) {
			if (ex.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public static ArrayList<Exercise> searchExercises (
			String name) {
		
		ArrayList<Exercise> rtn = new ArrayList<Exercise>();
		
		ArrayList<Exercise> tmp = (ArrayList<Exercise>) exercises.clone();
		for (Exercise ex : tmp) {
			if (!name.isEmpty()) {
				if (!ex.getName().startsWith(name)) {
					continue;
				}
			}
			// If nothing could exclude this exercise then add it to return list
			rtn.add(ex);
		}

		return rtn;
	}
	
	public static final ArrayList<Exercise> getExercises() {
		return exercises;
	}
	
	protected static void setSerializedName(String name) {
		serializedName = name;
	}
	
	protected static void clearData(){
		exercises.clear();
		ObjectFile.removeFile(serializedName);
	}
	
	/**
	 * Saves the state. Exercise information is saved in a file.
	 */
	private static void saveState(){
		ObjectFile.saveAsFile(exercises, serializedName);
	}
	
	/**
	 * Load the state. Exercise information is retrieved from a file.
	 */
	private static void loadState(){
		ArrayList<Exercise> ex = null;
		try {
			ex = (ArrayList<Exercise>) ObjectFile.loadObjectFile(serializedName);
		} catch (java.lang.ClassCastException e) {
			System.out.println("cast error ExerciseCtrl");
			return;
		}
		if (ex != null) {
			exercises = ex;
			System.out.println(exercises.size() + " exercises loaded");
		}
		
	}

}
