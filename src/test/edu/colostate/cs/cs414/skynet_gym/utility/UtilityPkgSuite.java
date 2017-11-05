package test.edu.colostate.cs.cs414.skynet_gym.utility;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.colostate.cs.cs414.skynet_gym.utility.data.ScheduleTest;
import edu.colostate.cs.cs414.skynet_gym.utility.data.TimePeriodTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	ScheduleTest.class,
	TimePeriodTest.class
})
public class UtilityPkgSuite {
	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(UtilityPkgSuite.class);
	}
}
