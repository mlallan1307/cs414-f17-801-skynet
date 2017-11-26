package edu.colostate.cs.cs414.skynet_gym.domain.data.objects;

import java.io.Serializable;

/**
 * Exercise represents all needed information for an exercise. 
 * 
 * @author Mike Allan
 *
 */
public class Exercise implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2965666889611098854L;
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
	public boolean equals(Object o){
		try {
			return (this.toString().equals(Exercise.class.cast(o).toString()));
		} catch (java.lang.ClassCastException e) {
			return false;
		} catch (java.lang.NullPointerException e) {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((equipment == null) ? 0 : equipment.hashCode());
		result = prime * result
				+ ((exerciseInfo == null) ? 0 : exerciseInfo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
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
	
	public String toStringShort() {
		return (this.name + " " +
				String.valueOf(this.exerciseInfo.getType()));
	}

	/**
	 * @return the name
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
	 * @return the equipment, may be null
	 */
	public Equipment getEquipment() {
		return equipment;
	}

	/**
	 * @param equipment the equipment to set, allowed to be null
	 */
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	/**
	 * @return the exerciseInfo
	 */
	public ExerciseTypeIf getExerciseInfo() {
		return exerciseInfo;
	}

	/**
	 * @param exerciseInfo the exerciseInfo to set
	 * @throws NullPointerException if param is null
	 */
	public void setExerciseInfo(ExerciseTypeIf exerciseInfo) {
		if (exerciseInfo == null) {
			throw new NullPointerException("param can not be null");
		}
		this.exerciseInfo = exerciseInfo;
	}
}
