package edu.colostate.cs.cs414.skynet_gym.domain.data.exercise;

import java.time.Duration;

/**
 * Defines the interface for an exercise type
 * 
 * @author Mike Allan
 *
 */
public interface ExerciseTypeIf {
	
	public String toString();
	
	public boolean equals(Object o);
	
	/**
	 * @return the type
	 */
	public String getType();

	
	/**
	 * SET based type
	 * 
	 * @return the numberOfSets
	 */
	public int getNumberOfSets();
	
	/**
	 * SET based type
	 * 
	 * @return the numberOfRepetitions
	 */
	public int getNumberOfRepetitions();
	
	/**
	 * TIME based type
	 * 
	 * @return the duration
	 */
	public Duration getDuration();

}
