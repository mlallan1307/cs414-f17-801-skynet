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
	 * Collects all the information needed by the used classes to create a new
	 * exercise
	 * 
	 * @param name, the name of this exercise
	 * @param exerciseType, specifies the exercise type
	 * @param equipment, if not needed then put null
	 * @param duration, needed for time based exercise
	 * @param numberOfSets, needed for set based exercise
	 * @param repsPerSet, needed for set based exercise
	 * @return a new Exercise
	 * 
	 * @throws IllegalArgumentException if given invalid argument
	 */
	public static Exercise buildExercise(
			String name,
			ExerciseType exerciseType,
			Equipment equipment,
			Duration duration,
			int numberOfSets,
			int repsPerSet) {
		
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
			exerciseInfo = buildTimedExerciseType(duration);
		}
		else if (exerciseType.equals(ExerciseType.SetBased)) {
			exerciseInfo = buildSetExerciseType(numberOfSets, repsPerSet);
		}
		else {
			throw new IllegalArgumentException(
					"Invalid: Unknown exerciseType");
		}
		
		Exercise eq = new Exercise(
				name,
				exerciseInfo,
				equipment);
		
		return eq;
	}
	
	/**
	 * Add exercise to add to static list.
	 * 
	 * @param exercise
	 * 
	 * @throws IllegalArgumentException if the given exercise is a duplicate
	 */
	public static void addExercise(
			final Exercise exercise){
		
		if (existsWithName(exercise.getName())) {
			throw new IllegalArgumentException(
					"Invalid: An Exercise with that name already exists");
		}
		
		exercises.add(exercise);
		
		// Save the state
		saveState();
		
	}
	
	/**
	 * Replaces an existing exercise in the static list.
	 * 
	 * @param exercise
	 * @param existingExercise
	 * 
	 * @throws IllegalArgumentException if the existing exercise can't be found
	 * 		or the name is a duplicate of another exercise
	 */
	public static void replaceExercise(
			final Exercise exercise,
			final Exercise existingExercise){
		
		if (existingExercise == null ||
				!exercises.contains(existingExercise)) {
			throw new IllegalArgumentException(
					"Can't find the given exercise.");
		}
		
		// Validate that given name is not associated with another exercise
		if (!exercise.getName().equals(existingExercise.getName()) &&
				existsWithName(exercise.getName())) {
			throw new IllegalArgumentException(
					"An Exercise entry with that name already exists");
		}
		
		// Update exercises
		int index = exercises.indexOf(existingExercise);
		Exercise currentExercise = exercises.get(index);
		if (!exercise.getName().equals(currentExercise.getName())) {
			currentExercise.setName(exercise.getName());
		}
		if (!exercise.getExerciseInfo().equals(currentExercise.getExerciseInfo())) {
			currentExercise.setExerciseInfo(exercise.getExerciseInfo());
		}
		if (exercise.getEquipment() == null) {
			currentExercise.setEquipment(exercise.getEquipment());
		}
		else if (!exercise.getEquipment().equals(currentExercise.getEquipment())) {
			currentExercise.setEquipment(exercise.getEquipment());
		}
		
		exercises.set(index, currentExercise);
		
		// Save the state
		saveState();
		
	}
	
	/**
	 * Remove the given Exercise from the system
	 * 
	 * @param ex the Exercise to remove
	 * 
	 * @throws IllegalArgumentException if this Exercise doesn't exist
	 * @throws NullPointerException if Exercise is null
	 */
	public static void removeExercise(final Exercise ex) {
		if (ex == null) {
			throw new NullPointerException("Given Exercise is null.");
		}
		
		if (!exercises.contains(ex)) {
			throw new IllegalArgumentException(
					"Can't find the given Exercise.");
		}
		
		// Routines contain exercises so let RoutineCtrl handle this change
		RoutineCtrl.exerciseRemoved(ex);
		
		if (!exercises.remove(ex)) {
			throw new RuntimeException(
					"An issue occured when removing Exercise.");
		}
		
		// Save the state
		saveState();
	}
	
	/**
	 * Handle the removal of the given equipment from the system
	 * 
	 * @param eq the equipment being removed
	 * 
	 * @throws NullPointerException if equipment is null
	 */
	public static void equipmentRemoved(final Equipment eq) {
		if (eq == null) {
			throw new NullPointerException("Given equipment is null.");
		}
		
		for (Exercise ex : exercises) {
			if (ex.getEquipment() != null &&
					ex.getEquipment().equals(eq)) {
				ex.setEquipment(null);
			}
		}
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
	 * Creates the ExerciseType for a time based exercise
	 * 
	 * @param duration
	 * @return
	 */
	private static ExerciseTypeIf buildTimedExerciseType(
			final Duration duration){
		
		if (duration == null) {
			throw new IllegalArgumentException(
					"Invalid: The duration field is empty.");
		}
		else if (duration.isZero() || duration.isNegative()) {
			throw new IllegalArgumentException(
					"Invalid: The duration field must be positive.");
		}
		
		ExerciseTypeIf exerciseInfo = new TimeBasedExercise(duration);
		
		return exerciseInfo;
	}
	
	/**
	 * Creates the ExerciseType for a set based exercise
	 * 
	 * @param numberOfSets
	 * @param repsPerSet
	 * @return
	 */
	private static ExerciseTypeIf buildSetExerciseType(
			final int numberOfSets,
			final int repsPerSet){
		
		if (numberOfSets <= 0 || repsPerSet <= 0) {
			throw new IllegalArgumentException(
					"Invalid: The number of sets and reps per set must be greater than zero.");
		}
		
		ExerciseTypeIf exerciseInfo = new SetBasedExercise(numberOfSets, repsPerSet);
		
		return exerciseInfo;
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
