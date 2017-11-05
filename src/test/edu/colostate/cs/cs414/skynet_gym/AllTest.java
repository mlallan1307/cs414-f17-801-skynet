package test.edu.colostate.cs.cs414.skynet_gym;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.edu.colostate.cs.cs414.skynet_gym.domain.DomainPkgSuite;
import test.edu.colostate.cs.cs414.skynet_gym.utility.UtilityPkgSuite;

@RunWith(Suite.class)
@SuiteClasses({
	DomainPkgSuite.class,
	UtilityPkgSuite.class
})
public class AllTest {
	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(AllTest.class);
	}
}
