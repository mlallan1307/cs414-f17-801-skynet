package edu.colostate.cs.cs414.skynet_gym.domain.people.info;

import java.io.Serializable;

/**
 * Stores qualification data
 * 
 * @author Mike Allan
 *
 */
public class Qualification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4834400992487782873L;
	private String description;
	private String name;

	public Qualification(String name, String description) {
		this.setName(name);
		this.setDescription(description);
	}
	
	public String toString() {
		return (this.description + ":" +
				this.name);
	}
	
	public boolean equals(Object o){
		try {
			return (this.toString().equals(Qualification.class.cast(o).toString()));
		} catch (java.lang.ClassCastException e) {
			return false;
		} catch (java.lang.NullPointerException e) {
			return false;
		}
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	
}
