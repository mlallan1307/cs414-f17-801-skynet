package edu.colostate.cs.cs414.skynet_gym.domain.data.exercise;

import edu.colostate.cs.cs414.skynet_gym.domain.data.equipment.Equipment;

/**
 * Exercise represents all needed information for an exercise. 
 * 
 * @author Mike Allan
 *
 */
public class Exercise {

	private String name;
	private Equipment equipment;
	private ExerciseTypeIf exerciseInfo;
	
	/**
	 * Constructor with equipment
	 * 
	 * @param name
	 * @param exerciseInfo
	 * @param equipment
	 */
	public Exercise(String name,
			ExerciseTypeIf exerciseInfo,
			Equipment equipment){
		this.setName(name);
		this.setExerciseInfo(exerciseInfo);
		this.setEquipment(equipment);
	}
	
	/**
	 * Constructor without equipment
	 * 
	 * @param name
	 * @param exerciseInfo
	 */
	public Exercise(String name,
			ExerciseTypeIf exerciseInfo){
		this.setName(name);
		this.setExerciseInfo(exerciseInfo);
		this.setEquipment(null);
	}
	
	@Override
	public String toString() {
		String rtn = (this.name + ":");
		if (this.equipment != null) {
			rtn += ("[" + this.equipment.toString() + "]" + ":");
		}
		rtn += ("[" + this.exerciseInfo.toString() + "]");
		return rtn;
	}
	
	@Override
	public boolean equals(Object o){
		try {
			return (this.toString().equals(Exercise.class.cast(o).toString()));
		} catch (java.lang.ClassCastException e) {
			return false;
		} catch (java.lang.NullPointerException e) {
			return false;
		}
	}

	/**
	 * @return the name
	 */
	protected String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the equipment, may be null
	 */
	protected Equipment getEquipment() {
		return equipment;
	}

	/**
	 * @param equipment the equipment to set, allowed to be null
	 */
	protected void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	/**
	 * @return the exerciseInfo
	 */
	protected ExerciseTypeIf getExerciseInfo() {
		return exerciseInfo;
	}

	/**
	 * @param exerciseInfo the exerciseInfo to set
	 * @throws NullPointerException if param is null
	 */
	protected void setExerciseInfo(ExerciseTypeIf exerciseInfo) {
		if (exerciseInfo == null) {
			throw new NullPointerException("param can not be null");
		}
		this.exerciseInfo = exerciseInfo;
	}
}
