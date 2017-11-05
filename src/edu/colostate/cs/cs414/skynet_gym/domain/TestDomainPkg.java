package edu.colostate.cs.cs414.skynet_gym.domain;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.colostate.cs.cs414.skynet_gym.domain.control.TestDomainControlPkg;
import edu.colostate.cs.cs414.skynet_gym.domain.data.TestDomainDataPkg;
import edu.colostate.cs.cs414.skynet_gym.domain.people.TestDomainPeoplePkg;

@RunWith(Suite.class)
@SuiteClasses({
	TestDomainControlPkg.class,
	TestDomainDataPkg.class,
	TestDomainPeoplePkg.class
	
})
public class TestDomainPkg {
	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(TestDomainPkg.class);
	}
}
