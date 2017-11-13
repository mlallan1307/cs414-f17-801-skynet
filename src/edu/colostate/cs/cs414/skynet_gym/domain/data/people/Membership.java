package edu.colostate.cs.cs414.skynet_gym.domain.data.people;

import java.io.Serializable;

/**
 * Stores membership information
 * 
 * @author Mike Allan
 *
 */
public class Membership implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2492547644129376742L;
	private boolean isValid;

	public Membership(boolean isValid) {
		this.setValid(isValid);
	}
	
	public String toString() {
		return String.valueOf(this.isValid);
	}
	
	public boolean equals(Object o){
		try {
			return (this.toString().equals(Membership.class.cast(o).toString()));
		} catch (java.lang.ClassCastException e) {
			return false;
		} catch (java.lang.NullPointerException e) {
			return false;
		}
	}
	
	/**
	 * @return isValid
	 */
	public boolean isValid() {
		return isValid;
	}
	
	/**
	 * @param isValid sets the value of isValid
	 */
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
}
