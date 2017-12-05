package edu.colostate.cs.cs414.skynet_gym.domain.people;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.colostate.cs.cs414.skynet_gym.domain.people.other.CustomerTest;
import edu.colostate.cs.cs414.skynet_gym.domain.people.user.ManagerTest;
import edu.colostate.cs.cs414.skynet_gym.domain.people.user.TrainerTest;
import edu.colostate.cs.cs414.skynet_gym.domain.people.user.UserTest;

@RunWith(Suite.class)
@SuiteClasses({
	CustomerTest.class,
	ManagerTest.class,
	TrainerTest.class,
	UserTest.class
})
public class DomainPeoplePkgSuite {
	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(DomainPeoplePkgSuite.class);
	}
}
