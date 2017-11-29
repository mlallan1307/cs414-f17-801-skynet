package edu.colostate.cs.cs414.skynet_gym.domain.data.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Exercise;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Routine;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.SetBasedExercise;

public class RoutineTest {

	private final String name = "my rt";
	private ArrayList<Exercise> exercises;
	private Routine routine;
	private Exercise e = new Exercise(
			"eName",
			new SetBasedExercise(
					2,
					3));
	private Exercise e2 = new Exercise(
			"eName2",
			new SetBasedExercise(
					3,
					4));
	
	@Before
	public void setUp() throws Exception {
		routine = new Routine(name);
		exercises = new ArrayList<Exercise>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testRoutine() {
		assertTrue(routine.getName().equals(name));
	}

	@Test
	public final void testEqualsObject() {
		Routine r = new Routine(name);
		assertTrue(routine.equals(r));
		assertFalse(routine.equals(null));
		
		r.addExercise(e);
		routine.addExercise(e);
		assertTrue(routine.equals(r));
	}

	@Test
	public final void testAddExerciseExercise() {
		routine.addExercise(e);
		assertTrue(routine.getExercises().contains(e));
		
		try {
			routine.addExercise((Exercise) null);
			fail("Expected to throw");
		} catch (NullPointerException m) {
		}
	}

	@Test
	public final void testRemoveExerciseExercise() {
		routine.addExercise(e);
		assertTrue(routine.getExercises().contains(e));
		routine.removeExercise(e);
		
		try {
			routine.removeExercise((Exercise) null);
			fail("Expected to throw");
		} catch (NullPointerException m) {
		}
		
		routine.removeExercise(e);
	}

	@Test
	public final void testAddExerciseArrayListOfExercise() {
		exercises.add(e);
		exercises.add(e2);
		
		routine.addExercise(exercises);
		assertTrue(routine.getExercises().contains(e));
		assertTrue(routine.getExercises().contains(e2));
		assertTrue(routine.getExercises().size() == 2);
		
		try {
			routine.addExercise((ArrayList<Exercise>) null);
			fail("Expected to throw");
		} catch (NullPointerException m) {
		}
		
	}

	@Test
	public final void testRemoveExerciseArrayListOfExercise() {
		exercises.add(e);
		exercises.add(e2);
		exercises.add(new Exercise(
				"eName3",
				new SetBasedExercise(
						4,
						5)));
		
		ArrayList<Exercise> rma = new ArrayList<Exercise>();
		rma.add(e);
		rma.add(e2);
		
		routine.addExercise(exercises);
		assertTrue(routine.getExercises().contains(e));
		assertTrue(routine.getExercises().contains(e2));
		assertEquals(3, routine.getExercises().size());
		routine.removeExercise(rma);
		assertFalse(routine.getExercises().contains(e));
		assertFalse(routine.getExercises().contains(e2));
		assertEquals(1, routine.getExercises().size());
		
		//try to remove elements not there
		routine.removeExercise(rma);
		assertFalse(routine.getExercises().contains(e));
		assertFalse(routine.getExercises().contains(e2));
		assertTrue(routine.getExercises().size() == 1);
		
		try {
			routine.removeExercise((ArrayList<Exercise>) null);
			fail("Expected to throw");
		} catch (NullPointerException m) {
		}
	}

	@Test
	public final void testGetName() {
		assertEquals(name, routine.getName());
	}

	@Test
	public final void testSetName() {
		assertEquals(name, routine.getName());
		routine.setName("n2");
		assertEquals("n2", routine.getName());
	}

	@Test
	public final void testGetExercises() {
		assertEquals(exercises.toString(), routine.getExercises().toString());
	}

	@Test
	public final void testSetExercises() {
		ArrayList<Exercise> rma = new ArrayList<Exercise>();
		rma.add(e);
		rma.add(e2);

		routine.setExercises(rma);
		assertTrue(routine.getExercises().equals(rma));
		
		try {
			routine.setExercises(null);
			fail("Expected to throw");
		} catch (NullPointerException m) {
		}
	}

}
