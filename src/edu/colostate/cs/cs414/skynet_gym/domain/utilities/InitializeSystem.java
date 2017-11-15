package edu.colostate.cs.cs414.skynet_gym.domain.utilities;

import edu.colostate.cs.cs414.skynet_gym.domain.control.CustomerCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.control.EquipmentCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.control.ExerciseCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.control.ManagerCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.control.RoutineCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.control.TrainerCtrl;

/**
 * This class contains initialization functionality
 * 
 * @author Mike Allan
 *
 */
public class InitializeSystem {

	private InitializeSystem() {} // Construction disabled
	
	/**
	 * Calls all the methods needed to perform system restoration
	 * 
	 * @return false if no manager exists, true otherwise
	 */
	public static boolean initialize() {
		
		ManagerCtrl.initialize();
		TrainerCtrl.initialize();
		CustomerCtrl.initialize();
		EquipmentCtrl.initialize();
		ExerciseCtrl.initialize();
		RoutineCtrl.initialize();
		
		return ManagerCtrl.managerExists();
	}
}
