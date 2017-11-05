package test.edu.colostate.cs.cs414.skynet_gym.domain.data;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.colostate.cs.cs414.skynet_gym.domain.data.equipment.EquipmentTest;
import edu.colostate.cs.cs414.skynet_gym.domain.data.exercise.ExerciseTest;
import edu.colostate.cs.cs414.skynet_gym.domain.data.exercise.SetBasedExerciseTest;
import edu.colostate.cs.cs414.skynet_gym.domain.data.exercise.TimeBasedExerciseTest;
import edu.colostate.cs.cs414.skynet_gym.domain.data.routine.RoutineTest;

@RunWith(Suite.class)
@SuiteClasses({
	EquipmentTest.class,
	ExerciseTest.class,
	SetBasedExerciseTest.class,
	TimeBasedExerciseTest.class,
	RoutineTest.class
	
})
public class TestDomainDataPkg {
	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(TestDomainDataPkg.class);
	}
}
