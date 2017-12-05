package edu.colostate.cs.cs414.skynet_gym.domain.people.user;

import java.io.Serializable;
import java.util.ArrayList;

import edu.colostate.cs.cs414.skynet_gym.domain.data.people.PersonInformation;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.Qualification;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.Schedule;

/**
 * Stores a gym trainer's information.
 * 
 * @author Mike Allan
 *
 */
public class Trainer extends User implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8975698405159871561L;
	private Schedule schedule;
	private ArrayList<Qualification> qualifications = new ArrayList<Qualification>();
	
	public Trainer(
			String username,
			String password,
			PersonInformation personInfo,
			Schedule schedule,
			ArrayList<Qualification> qualifications) {
		super("Trainer",
				username,
				password,
				personInfo);
		
		this.setSchedule(schedule);
		this.setQualifications(qualifications);
		
	}
	
	@Override
	public boolean equals(Object o) {
		try {
			return (this.toString().equals(Trainer.class.cast(o).toString()));
		} catch (java.lang.ClassCastException e) {
			return false;
		} catch (java.lang.NullPointerException e) {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((qualifications == null) ? 0 : qualifications.hashCode());
		result = prime * result
				+ ((schedule == null) ? 0 : schedule.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		String rtnString = (
				"[" + super.toString() + "]" + ":" +
				"[" + this.schedule.toString() + "]");
		// Add all qualifications
		for (Qualification q : this.qualifications) {
			rtnString += ":" + "[" + q.toString() + "]"; 
		}
		return rtnString;
	}
	
	/**
	 * @param qualification the qualification to add
	 * @throws NullPointerException if param is null
	 */
	public void addQualification(Qualification qualification) {
		if (qualification == null) {
			throw new NullPointerException("Parameter is null");
		}
		this.qualifications.add(qualification);
	}
	
	/**
	 * @param qualification the qualification to remove
	 * @throws NullPointerException if param is null
	 */
	public void removeQualification(Qualification qualification) {
		if (qualification == null) {
			throw new NullPointerException("Parameter is null");
		}
		this.qualifications.remove(qualification);
	}
	
	/**
	 * @param qualifications the qualifications to add
	 * @throws NullPointerException if param is null
	 */
	public void addQualification(ArrayList<Qualification> qualifications) {
		if (qualifications == null) {
			throw new NullPointerException("Parameter is null");
		}
		this.qualifications.addAll(qualifications);
	}
	
	/**
	 * @param qualifications the qualifications to remove
	 * @throws NullPointerException if param is null
	 */
	public void removeQualification(ArrayList<Qualification> qualifications) {
		if (qualifications == null) {
			throw new NullPointerException("Parameter is null");
		}
		this.qualifications.removeAll(qualifications);
	}
	
	/**
	 * Clear qualifications
	 */
	public void clearQualification() {
		this.qualifications.clear();
	}
	
	//
	// GETTERS & SETTERS
	//

	/**
	 * @return the workHours
	 */
	public Schedule getSchedule() {
		return schedule;
	}

	/**
	 * @param workHours the workHours to set
	 * @throws NullPointerException if param is null
	 */
	public void setSchedule(Schedule schedule) {
		if (schedule == null) {
			throw new NullPointerException("Parameter is null");
		}
		this.schedule = schedule;
	}

	/**
	 * @return the qualifications
	 */
	public ArrayList<Qualification> getQualifications() {
		return qualifications;
	}

	/**
	 * @param qualifications the qualifications to set
	 * @throws NullPointerException if param is null
	 */
	public void setQualifications(ArrayList<Qualification> qualifications) {
		if (qualifications == null) {
			throw new NullPointerException("Parameter is null");
		}
		this.qualifications = qualifications;
	}
	
	

}
