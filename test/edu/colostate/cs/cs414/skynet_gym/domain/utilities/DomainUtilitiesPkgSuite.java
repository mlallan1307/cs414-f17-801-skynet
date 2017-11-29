package edu.colostate.cs.cs414.skynet_gym.domain.utilities;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.colostate.cs.cs414.skynet_gym.domain.utilities.AccountManagerTest;

@RunWith(Suite.class)
@SuiteClasses({
	AccountManagerTest.class
})
public class DomainUtilitiesPkgSuite {
	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(DomainUtilitiesPkgSuite.class);
	}
}
