package edu.colostate.cs.cs414.skynet_gym.domain.people.other;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.ArrayList;

import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Routine;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.Membership;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.PersonInformation;

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
	private ArrayList<Routine> routines;
	
	public Customer(PersonInformation personInfo) {
		
		this.membership = new Membership(true);
		this.setPersonInfo(personInfo);
		this.routines = new ArrayList<Routine>();
		
	}

	@Override
	public String toString() {
		return ("[" + personInfo.toString() + "]" + ":" +
				"[" + membership.toString() + "]");
	}
	
	public String toStringShort() {
		return (personInfo.getFirstName() + " " +
				personInfo.getLastName());
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

	/**
	 * @return the routines
	 */
	public final ArrayList<Routine> getRoutines() {
		return routines;
	}

	/**
	 * @param routines the routines to set
	 * @throws NullPointerException if param is null
	 */
	public void setRoutines(ArrayList<Routine> routines) {
		if (routines == null) {
			throw new NullPointerException("Param can not be null");
		}
		this.routines = routines;
	}

}
