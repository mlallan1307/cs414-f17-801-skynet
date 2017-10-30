package edu.colostate.cs.cs414.skynet_gym.domain.control;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	CustomerCtrlTest.class,
	ManagerCtrlTest.class,
	TrainerCtrlTest.class
	
})
public class TestDomainControlPkg {
	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(TestDomainControlPkg.class);
	}
}
