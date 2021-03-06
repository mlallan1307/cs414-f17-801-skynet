package edu.colostate.cs.cs414.skynet_gym.domain.data.objects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.ExerciseType;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.SetBasedExercise;

public class SetBasedExerciseTest {

	private final int numberOfSets = 4;
	private final int numberOfRepetitions = 10;
	private SetBasedExercise sbe;
	
	@Before
	public void setUp() throws Exception {
		sbe = new SetBasedExercise(
				numberOfSets,
				numberOfRepetitions);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testSetBasedExercise() {
		assertTrue(sbe.getType().equals(ExerciseType.SetBased));
		assertTrue(sbe.getNumberOfSets() == numberOfSets);
		assertTrue(sbe.getNumberOfRepetitions() == numberOfRepetitions);
	}

	@Test
	public final void testEqualsObject() {
		SetBasedExercise tmp = new SetBasedExercise(
				numberOfSets,
				numberOfRepetitions);
		assertTrue(sbe.equals(tmp));
		assertFalse(sbe.equals(null));
	}

	@Test
	public final void testGetNumberOfSets() {
		assertTrue(sbe.getNumberOfSets() == numberOfSets);
	}

	@Test
	public final void testSetNumberOfSets() {
		assertTrue(sbe.getNumberOfSets() == numberOfSets);
		sbe.setNumberOfSets(2);
		assertTrue(sbe.getNumberOfSets() == 2);
	}

	@Test
	public final void testGetNumberOfRepetitions() {
		assertTrue(sbe.getNumberOfRepetitions() == numberOfRepetitions);
	}

	@Test
	public final void testSetNumberOfRepetitions() {
		assertTrue(sbe.getNumberOfRepetitions() == numberOfRepetitions);
		sbe.setNumberOfRepetitions(2);
		assertTrue(sbe.getNumberOfRepetitions() == 2);
	}

	@Test
	public final void testGetType() {
		assertTrue(sbe.getType().equals(ExerciseType.SetBased));
	}

	@Test
	public final void testGetDuration() {
		assertTrue(sbe.getDuration().equals(Duration.ZERO));
	}

}
