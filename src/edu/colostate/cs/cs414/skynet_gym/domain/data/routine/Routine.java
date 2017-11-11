package edu.colostate.cs.cs414.skynet_gym.domain.data.routine;

import java.io.Serializable;
import java.util.ArrayList;

import edu.colostate.cs.cs414.skynet_gym.domain.data.exercise.Exercise;

/**
 * This is a workout routine 
 * 
 * @author Mike Allan
 *
 */
public class Routine implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6694284744010261151L;
	private String name;
	private ArrayList<Exercise> exercises;
	
	public Routine(String name){
		this.exercises = new ArrayList<Exercise>();
		this.setName(name);
	}
	
	public Routine(
			String name,
			ArrayList<Exercise> exercises){
		this.setName(name);
		if (exercises == null){
			throw new IllegalArgumentException(
					"Invalid: exercise list is null");
		}
		this.exercises = exercises;
	}
	
	@Override
	public String toString() {
		String rtn = this.name;
		for (Exercise e : exercises) {
			rtn += (":" + "[" + e.toString() + "]");
		}
		return rtn;
	}
	
	@Override
	public boolean equals(Object o){
		try {
			return (this.toString().equals(Routine.class.cast(o).toString()));
		} catch (java.lang.ClassCastException e) {
			return false;
		} catch (java.lang.NullPointerException e) {
			return false;
		}
	}
	
	/**
	 * @param exercise the exercise to add
	 * @throws NullPointerException if param is null
	 */
	protected void addExercise(Exercise exercise) {
		if (exercise == null) {
			throw new NullPointerException("param can not be null");
		}
		this.exercises.add(exercise);
	}
	
	/**
	 * @param exercise the exercise to remove
	 * @throws NullPointerException if param is null
	 */
	protected void removeExercise(Exercise exercise) {
		if (exercise == null) {
			throw new NullPointerException("param can not be null");
		}
		this.exercises.remove(exercise);
	}
	
	/**
	 * @param exercises the exercises to add
	 * @throws NullPointerException if param is null
	 */
	protected void addExercise(ArrayList<Exercise> exercises) {
		if (exercises == null) {
			throw new NullPointerException("param can not be null");
		}
		this.exercises.addAll(exercises);
	}
	
	/**
	 * @param exercises the exercises to remove
	 * @throws NullPointerException if param is null
	 */
	protected void removeExercise(ArrayList<Exercise> exercises) {
		if (exercises == null) {
			throw new NullPointerException("param can not be null");
		}
		this.exercises.removeAll(exercises);
	}

	/**
	 * @return the routine name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the exercises
	 */
	public ArrayList<Exercise> getExercises() {
		return exercises;
	}

	/**
	 * @param exercises the exercises to set
	 * @throws NullPointerException if param is null
	 */
	public void setExercises(ArrayList<Exercise> exercises) {
		if (exercises == null) {
			throw new NullPointerException("param can not be null");
		}
		this.exercises = exercises;
	}
	
	
}
