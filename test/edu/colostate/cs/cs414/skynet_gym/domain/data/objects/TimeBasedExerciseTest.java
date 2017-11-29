package edu.colostate.cs.cs414.skynet_gym.domain.data.objects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.ExerciseType;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.TimeBasedExercise;

public class TimeBasedExerciseTest {

	private String type;
	private final long sec = 10;
	private Duration duration;
	private TimeBasedExercise tbe;
	
	@Before
	public void setUp() throws Exception {
		duration = Duration.ofSeconds(sec);
		tbe = new TimeBasedExercise(duration);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testTimeBasedExercise() {
		assertTrue(tbe.getDuration().equals(duration));
		try {
			TimeBasedExercise tmp = 
					new TimeBasedExercise(Duration.ofSeconds(0));
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		try {
			TimeBasedExercise tmp = 
					new TimeBasedExercise(Duration.ofSeconds(-1));
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		try {
			TimeBasedExercise tmp = 
					new TimeBasedExercise(null);
			fail("Expected to throw");
		} catch (NullPointerException e) {
		}
	}

	@Test
	public final void testEqualsObject() {
		TimeBasedExercise tmp = 
				new TimeBasedExercise(duration);
		assertTrue(tbe.equals(tmp));
		assertFalse(tbe.equals(null));
	}

	@Test
	public final void testGetNumberOfSets() {
		assertTrue(tbe.getNumberOfSets() == 0);
	}

	@Test
	public final void testGetNumberOfRepetitions() {
		assertTrue(tbe.getNumberOfRepetitions() == 0);
	}

	@Test
	public final void testGetType() {
		assertTrue(tbe.getType().equals(ExerciseType.TimeBased));
	}

	@Test
	public final void testGetDuration() {
		assertTrue(tbe.getDuration().equals(duration));
	}

	@Test
	public final void testSetDuration() {
		tbe.setDuration(Duration.ofSeconds(2));
		assertTrue(tbe.getDuration().equals(Duration.ofSeconds(2)));
		try {
			tbe.setDuration(Duration.ofSeconds(0));
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		try {
			tbe.setDuration(Duration.ofSeconds(-1));
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		try {
			tbe.setDuration(null);
			fail("Expected to throw");
		} catch (NullPointerException e) {
		}
	}

}
