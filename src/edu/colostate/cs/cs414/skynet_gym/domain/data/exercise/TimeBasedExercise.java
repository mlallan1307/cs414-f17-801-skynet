package edu.colostate.cs.cs414.skynet_gym.domain.data.exercise;

import java.time.Duration;

/**
 * This is a time based exercise
 * 
 * @author Mike Allan
 *
 */
public class TimeBasedExercise implements ExerciseTypeIf {

	private String type;
	private Duration duration;
	
	public TimeBasedExercise(Duration duration) {
		this.type = "time based";
		this.setDuration(duration);
	}
	
	@Override
	public String toString() {
		return (this.type + ":" +
				this.duration.toString());
	}
	
	@Override
	public boolean equals(Object o){
		try {
			return (this.toString().equals(TimeBasedExercise.class.cast(o).toString()));
		} catch (java.lang.ClassCastException e) {
			return false;
		} catch (java.lang.NullPointerException e) {
			return false;
		}
	}

	/**
	 * @return 0 since this is a time based exercise
	 */
	public int getNumberOfSets() {
		return 0;
	}

	/**
	 * @return 0 since this is a time based exercise
	 */
	public int getNumberOfRepetitions() {
		return 0;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @return the duration of the exercise
	 */
	public Duration getDuration() {
		return this.duration;
	}

	/**
	 * @param duration the duration to set
	 * @throws NullPointerException if param is null
	 * @throws IllegalArgumentException if duration is not positive
	 */
	protected void setDuration(Duration duration) {
		if (duration == null) {
			throw new NullPointerException("param can not be null");
		}
		if (duration.isNegative() || duration.isZero()) {
			throw new IllegalArgumentException("duration must be positive");
		}
		this.duration = duration;
	}
}
