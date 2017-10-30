package edu.colostate.cs.cs414.skynet_gym.domain.people.manager;

import java.io.Serializable;

import edu.colostate.cs.cs414.skynet_gym.domain.people.info.PersonInformation;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.User;

/**
 * Stores the system manager information.
 * 
 * @author Mike Allan
 *
 */
public class Manager extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6465414784318606652L;

	public Manager(String username,
			String password,
			PersonInformation personInfo) {
		super("Manager",
				username,
				password,
				personInfo);
		
	}
	
	@Override
	public String toString() {
		return (super.toString());
	}
	
	@Override
	public boolean equals(Object o){
		try {
			return (this.toString().equals(Manager.class.cast(o).toString()));
		} catch (java.lang.ClassCastException e) {
			return false;
		} catch (java.lang.NullPointerException e) {
			return false;
		}
	}

}
