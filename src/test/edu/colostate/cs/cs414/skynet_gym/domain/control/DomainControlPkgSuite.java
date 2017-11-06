package test.edu.colostate.cs.cs414.skynet_gym.domain.control;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.colostate.cs.cs414.skynet_gym.domain.control.CustomerCtrlTest;
import edu.colostate.cs.cs414.skynet_gym.domain.control.EquipmentCtrlTest;
import edu.colostate.cs.cs414.skynet_gym.domain.control.ManagerCtrlTest;
import edu.colostate.cs.cs414.skynet_gym.domain.control.TrainerCtrlTest;

@RunWith(Suite.class)
@SuiteClasses({
	CustomerCtrlTest.class,
	EquipmentCtrlTest.class,
	ManagerCtrlTest.class,
	TrainerCtrlTest.class
	
})
public class DomainControlPkgSuite {
	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(DomainControlPkgSuite.class);
	}
}
