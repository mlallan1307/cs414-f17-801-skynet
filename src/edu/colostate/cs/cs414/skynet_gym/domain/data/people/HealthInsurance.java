package edu.colostate.cs.cs414.skynet_gym.domain.data.people;

import java.io.Serializable;

/**
 * Stores Health Insurance information
 * 
 * @author Mike Allan
 *
 */
public class HealthInsurance implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2741125306293943716L;
	private String providerName;
	
	public HealthInsurance(String providerName){
		
		this.providerName = providerName;

	}
	
	public boolean equals(Object o){
		try {
			return (this.toString().equals(HealthInsurance.class.cast(o).toString()));
		} catch (java.lang.ClassCastException e) {
			return false;
		} catch (java.lang.NullPointerException e) {
			return false;
		}
	}
	
	public String toString(){
		return (this.providerName);
	}
	
	//
	// GETTERS & SETTERS
	//

	/**
	 * @return providerName
	 */
	public String getProviderName() {
		return providerName;
	}

	/**
	 * @param providerName the providerName to set
	 */
	protected void setProviderName(String providerName) {
		this.providerName = providerName;
	}
}
