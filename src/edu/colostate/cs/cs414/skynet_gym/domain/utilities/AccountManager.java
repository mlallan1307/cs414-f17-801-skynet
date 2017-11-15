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
	
	private AccountManager(){} // Construction disabled
	
	/**
	 * Use this to check if the login information is valid.
	 * 
	 * @param user is the attempted login username
	 * @param pass is the attempted login password
	 * 
	 * @return UserType if login information is valid for a user type, else null
	 */
	public static UserType login(
			String user,
			String pass){
		if (ManagerCtrl.managerExists() &&
				ManagerCtrl.getManager().login(user, pass)) {
			return UserType.Manager;
		}

		for (Trainer t : TrainerCtrl.getTrainers()) {
			if (t.login(user, pass)) {
				return UserType.Trainer;
			}
		}
		
		return null;
	}
	
	/**
	 * Returns the username of a user account if the given information corresponds
	 * to a valid user.
	 * 
	 * @param firstName - First Name
	 * @param lastName - Last name
	 * @param driversLicenseNum - Drivers license number
	 * @return the username if information is valid, empty string otherwise
	 */
	public static String recoverUsername(
			String firstName,
			String lastName,
			String driversLicenseNum) {
		String username = "";
		if (ManagerCtrl.managerExists()) {
			username = ManagerCtrl.getManager()
					.recoverUsername(
							firstName,
							lastName,
							driversLicenseNum);
		}
		if (username != "") {
			return username;
		} else {
			for (Trainer t : TrainerCtrl.getTrainers()) {
				username = t.recoverUsername(
						firstName,
						lastName,
						driversLicenseNum);
				if (username != "") {
					return username;
				}
			}
		}
		return "";
	}
	
	/**
	 * Resets the password of a user account if the given information corresponds
	 * to a valid user.
	 * 
	 * @param firstName - First Name
	 * @param lastName - Last name
	 * @param driversLicenseNum - Drivers license number
	 * @param user - Username
	 * @param pass - New Password desired
	 * @return true if information was valid and the password reset
	 */
	public static boolean resetPassword(
			String firstName,
			String lastName,
			String driversLicenseNum,
			String user,
			String pass) {
		
		if (ManagerCtrl.resetPassword(
				firstName,
				lastName,
				driversLicenseNum,
				user,
				pass)) {
			return true;
		} else if (TrainerCtrl.resetPassword(
				firstName,
				lastName,
				driversLicenseNum,
				user,
				pass)) {
			return true;
		}
		return false;
	}

}
