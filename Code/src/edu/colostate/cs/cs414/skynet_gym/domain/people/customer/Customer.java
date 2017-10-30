package edu.colostate.cs.cs414.skynet_gym.domain.people.customer;

import java.io.Serializable;

import edu.colostate.cs.cs414.skynet_gym.domain.people.info.Membership;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.PersonInformation;

/**
 * Customer information
 * 
 * @author Mike Allan
 *
 */
public class Customer implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6520337216284807311L;
	private Membership membership;
	private PersonInformation personInfo;
	
	public Customer(PersonInformation personInfo) {
		
		this.membership = new Membership(true);
		this.setPersonInfo(personInfo);
		
	}

	@Override
	public String toString() {
		return ("[" + personInfo.toString() + "]" + ":" +
				"[" + membership.toString() + "]");
	}
	
	@Override
	public boolean equals(Object o){
		try {
			return (this.toString().equals(Customer.class.cast(o).toString()));
		} catch (java.lang.ClassCastException e) {
			return false;
		} catch (java.lang.NullPointerException e) {
			return false;
		}
	}
	
	/**
	 * @return the personInfo
	 */
	public PersonInformation getPersonInfo() {
		return personInfo;
	}

	/**
	 * @param personInfo the personInfo to set
	 * @throws NullPointerException if param is null
	 */
	public void setPersonInfo(PersonInformation personInfo) {
		if (personInfo == null) {
			throw new NullPointerException("Param can not be null");
		}
		this.personInfo = personInfo;
	}
	
	/**
	 * @return the membership
	 */
	public Membership getMembership() {
		return membership;
	}

	/**
	 * @param membership the membership to set
	 * @throws NullPointerException if param is null
	 */
	public void setMembership(Membership membership) {
		if (membership == null) {
			throw new NullPointerException("Param can not be null");
		}
		this.membership = membership;
	}
	

}
