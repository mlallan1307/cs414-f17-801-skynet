package edu.colostate.cs.cs414.skynet_gym.domain.utilities;

import edu.colostate.cs.cs414.skynet_gym.domain.control.ManagerCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.control.TrainerCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.people.user.Trainer;
import edu.colostate.cs.cs414.skynet_gym.domain.people.user.UserType;

/**
 * Manages common user account functions. Provides a common interface to access
 * all existing user accounts.
 * 
 * @author Mike Allan
 *
 */
public class AccountManager {
	
	/**
	 * Use this to check if the login information is valid.
	 * 
	 * @param user is the attempted login username
	 * @param pass is the attempted login password
	 * 
	 * @return UserType if login information is valid for a user type, else null
	 */
	public static UserType login(String user, String pass){
		if (ManagerCtrl.getManager().login(user, pass)) {
			return UserType.Manager;
		}

		for (Trainer t : TrainerCtrl.getTrainers()) {
			if (t.login(user, pass)) {
				return UserType.Trainer;
			}
		}
		
		return null;
	}

}
