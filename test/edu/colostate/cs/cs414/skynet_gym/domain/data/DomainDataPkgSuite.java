package edu.colostate.cs.cs414.skynet_gym.domain.data;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.EquipmentTest;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.ExerciseTest;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.RoutineTest;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.SetBasedExerciseTest;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.TimeBasedExerciseTest;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.AddressTest;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.HealthInsuranceTest;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.MembershipTest;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.PersonInformationTest;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.QualificationTest;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.ScheduleTest;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.TimePeriodTest;

@RunWith(Suite.class)
@SuiteClasses({
	EquipmentTest.class,
	ExerciseTest.class,
	RoutineTest.class,
	SetBasedExerciseTest.class,
	TimeBasedExerciseTest.class,
	AddressTest.class,
	HealthInsuranceTest.class,
	MembershipTest.class,
	PersonInformationTest.class,
	QualificationTest.class,
	ScheduleTest.class,
	TimePeriodTest.class
})
public class DomainDataPkgSuite {
	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(DomainDataPkgSuite.class);
	}
}
