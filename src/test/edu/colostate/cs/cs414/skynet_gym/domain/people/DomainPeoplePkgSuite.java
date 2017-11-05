package test.edu.colostate.cs.cs414.skynet_gym.domain.people;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.colostate.cs.cs414.skynet_gym.domain.people.customer.CustomerTest;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.AddressTest;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.HealthInsuranceTest;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.MembershipTest;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.PersonInformationTest;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.QualificationTest;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.UserTest;
import edu.colostate.cs.cs414.skynet_gym.domain.people.manager.ManagerTest;
import edu.colostate.cs.cs414.skynet_gym.domain.people.trainer.TrainerTest;

@RunWith(Suite.class)
@SuiteClasses({
	CustomerTest.class,
	AddressTest.class,
	HealthInsuranceTest.class,
	MembershipTest.class,
	PersonInformationTest.class,
	QualificationTest.class,
	UserTest.class,
	ManagerTest.class,
	TrainerTest.class
})
public class DomainPeoplePkgSuite {
	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(DomainPeoplePkgSuite.class);
	}
}
