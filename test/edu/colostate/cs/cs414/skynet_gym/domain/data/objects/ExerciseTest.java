package edu.colostate.cs.cs414.skynet_gym.domain.data.objects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Equipment;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Exercise;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.ExerciseType;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.ExerciseTypeIf;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.SetBasedExercise;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.TimeBasedExercise;

public class ExerciseTest {
	
	private final String name = "name";
	private Equipment equipment;
	private ExerciseTypeIf exerciseInfo;
	private Exercise ex;
	private Exercise exE;

	@Before
	public void setUp() throws Exception {
		equipment = null;
		exerciseInfo = new SetBasedExercise(1, 1);
		ex = new Exercise(
				name,
				exerciseInfo);
		exE = new Exercise(
				name,
				exerciseInfo,
				equipment);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testExerciseStringExerciseTypeIfEquipment() {
		assertTrue(exE.getName().equals(name));
		try {
			Exercise f = new Exercise(
					name,
					null,
					null);
			fail("Expected to throw");
		} catch (NullPointerException e) {
		}
	}

	@Test
	public final void testExerciseStringExerciseTypeIf() {
		assertTrue(ex.getName().equals(name));
		try {
			Exercise f = new Exercise(
					name,
					null);
			fail("Expected to throw");
		} catch (NullPointerException e) {
		}
	}

	@Test
	public final void testEqualsObject() {
		Exercise f = new Exercise(
				name,
				exerciseInfo);
		assertTrue(f.equals(ex));
		assertFalse(f.equals(null));
	}

	@Test
	public final void testGetName() {
		assertTrue(ex.getName().equals(name));
	}

	@Test
	public final void testSetName() {
		assertTrue(ex.getName().equals(name));
		ex.setName("n2");
		assertTrue(ex.getName().equals("n2"));
	}

	@Test
	public final void testGetEquipment() {
		assertTrue(ex.getEquipment() == null);
	}

	@Test
	public final void testSetEquipment() {
		assertTrue(ex.getEquipment() == null);
		ex.setEquipment(null);
		assertTrue(ex.getEquipment() == null);
	}

	@Test
	public final void testGetExerciseInfo() {
		assertTrue(ex.getExerciseInfo().getType().equals(ExerciseType.SetBased));
		assertTrue(ex.getExerciseInfo().equals(exerciseInfo));
	}

	@Test
	public final void testSetExerciseInfo() {
		assertTrue(ex.getExerciseInfo().equals(exerciseInfo));
		ExerciseTypeIf ei = new TimeBasedExercise(Duration.ofSeconds(4));
		ex.setExerciseInfo(ei);
		assertTrue(ex.getExerciseInfo().equals(ei));
		
		try {
			ex.setExerciseInfo(null);
			fail("Expected to throw");
		} catch (NullPointerException e) {
		}
	}

}
