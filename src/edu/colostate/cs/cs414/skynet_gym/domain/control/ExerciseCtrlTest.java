package edu.colostate.cs.cs414.skynet_gym.domain.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Equipment;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.ExerciseType;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.ExerciseTypeIf;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.SetBasedExercise;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.TimeBasedExercise;

public class ExerciseCtrlTest {

	// Exercise
	private final String name  = "exName";
	private final int numberOfSets = 2;
	private final int numberOfReps = 4;
	private final Duration duration = Duration.ofSeconds(90);
	private final ExerciseTypeIf setExInfo = new SetBasedExercise(numberOfSets, numberOfReps);
	private final ExerciseTypeIf timeExInfo = new TimeBasedExercise(duration);
	private ExerciseTypeIf exerciseInfo;
	
	// Equipment
	private final String eqName  = "eqName";
	private final int eqQuantity = 1; 
	private File picture;
	private Equipment equipment;
	
	private final String testFileName = "ExerciseCtrlTestfile_delete_me";
	
	@Before
	public void setUp() throws Exception {
		
		picture = new File(testFileName + ".jpg");
		if (picture.exists()) {
			picture.delete();
		}
		picture.createNewFile();
		
		equipment = new Equipment(eqName, eqQuantity, picture);
		
		exerciseInfo = setExInfo;
		
		// Set test data to be in a different file than normal
		ExerciseCtrl.setSerializedName(testFileName);
		ExerciseCtrl.clearData();
	}

	@After
	public void tearDown() throws Exception {
		
		ExerciseCtrl.clearData();
		if (picture.exists()) {
			picture.delete();
		}
	}

	@Test
	public final void testExerciseExist() {
		assertFalse(ExerciseCtrl.exercisesExist());
		ExerciseCtrl.createExercise(
				name,
				ExerciseType.SetBased,
				equipment,
				duration,
				numberOfSets,
				numberOfReps);
		assertTrue(ExerciseCtrl.exercisesExist());
	}

	@Test
	public final void testInitialize() {
		assertFalse(ExerciseCtrl.exercisesExist());
		// file does not exist so this is tries to load but fails
		ExerciseCtrl.initialize();
		assertFalse(ExerciseCtrl.exercisesExist());
		ExerciseCtrl.createExercise(
				name,
				ExerciseType.SetBased,
				equipment,
				duration,
				numberOfSets,
				numberOfReps);
		assertTrue(ExerciseCtrl.exercisesExist());
		// equipment exists so this returns
		ExerciseCtrl.initialize();
		assertTrue(ExerciseCtrl.exercisesExist());
	}

	@Test
	public final void testCreateExercise() {
		assertFalse(ExerciseCtrl.exercisesExist());
		ExerciseCtrl.createExercise(
				name,
				ExerciseType.SetBased,
				equipment,
				duration,
				numberOfSets,
				numberOfReps);
		assertEquals(1, ExerciseCtrl.getExercises().size());
		ExerciseCtrl.createExercise(
				"newName",
				ExerciseType.TimeBased,
				equipment,
				duration,
				numberOfSets,
				numberOfReps);
		assertEquals(2, ExerciseCtrl.getExercises().size());
	}
	
	@Test
	public final void testCreateExerciseDuplicateCheck() {
		ExerciseCtrl.createExercise(
				name,
				ExerciseType.SetBased,
				equipment,
				duration,
				numberOfSets,
				numberOfReps);
		assertEquals(1, ExerciseCtrl.getExercises().size());
		
		try {
			// duplicated based on name
			ExerciseCtrl.createExercise(
					name,
					ExerciseType.SetBased,
					equipment,
					duration,
					numberOfSets,
					numberOfReps);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, ExerciseCtrl.getExercises().size());
	}
	
	@Test
	public final void testCreateExerciseEmptyFields() {
		
		try {
			// empty field test
			ExerciseCtrl.createExercise(
					"",
					ExerciseType.SetBased,
					equipment,
					duration,
					numberOfSets,
					numberOfReps);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(0, ExerciseCtrl.getExercises().size());
		
		try {
			// Duration 0
			ExerciseCtrl.createExercise(
					name,
					ExerciseType.TimeBased,
					equipment,
					Duration.ZERO,
					numberOfSets,
					numberOfReps);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(0, ExerciseCtrl.getExercises().size());
		
		try {
			// Sets 0 
			ExerciseCtrl.createExercise(
					name,
					ExerciseType.SetBased,
					equipment,
					duration,
					0,
					numberOfReps);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(0, ExerciseCtrl.getExercises().size());
		
		try {
			// Reps 0 
			ExerciseCtrl.createExercise(
					name,
					ExerciseType.SetBased,
					equipment,
					duration,
					numberOfSets,
					0);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(0, ExerciseCtrl.getExercises().size());
	}
	
	@Test
	public final void testCreateExerciseNull() {
		assertEquals(0, ExerciseCtrl.getExercises().size());
		try {
			ExerciseCtrl.createExercise(
					name,
					null,
					equipment,
					duration,
					numberOfSets,
					numberOfReps);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(0, ExerciseCtrl.getExercises().size());
		
		try {
			ExerciseCtrl.createExercise(
					name,
					ExerciseType.TimeBased,
					equipment,
					null,
					numberOfSets,
					numberOfReps);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(0, ExerciseCtrl.getExercises().size());
		
		try {
			ExerciseCtrl.createExercise(
					name,
					ExerciseType.SetBased,
					null,
					duration,
					numberOfSets,
					numberOfReps);
		} catch (IllegalArgumentException e) {
			fail("Expected to NOT throw");
		}
		assertEquals(1, ExerciseCtrl.getExercises().size());
	}

	@Test
	public final void testReplaceExercise() {
		assertFalse(ExerciseCtrl.exercisesExist());
		ExerciseCtrl.createExercise(
				name,
				ExerciseType.SetBased,
				equipment,
				null,
				numberOfSets,
				numberOfReps);
		assertTrue(ExerciseCtrl.exercisesExist());
		
		ExerciseCtrl.replaceExercise(
				"newName",
				ExerciseType.SetBased,
				equipment,
				null,
				numberOfSets,
				numberOfReps,
				ExerciseCtrl.getExercises().get(0));
		
		assertEquals("newName",
				ExerciseCtrl.getExercises()
						.get(0)
						.getName());
		
		ExerciseCtrl.replaceExercise(
				"newName",
				ExerciseType.SetBased,
				equipment,
				null,
				numberOfSets+1,
				numberOfReps,
				ExerciseCtrl.getExercises().get(0));
		
		assertEquals(numberOfSets+1,
				ExerciseCtrl.getExercises()
						.get(0)
						.getExerciseInfo().getNumberOfSets());
	}
	
	@Test
	public final void testReplaceExerciseNull() {
		try {
			ExerciseCtrl.replaceExercise(
					name,
					ExerciseType.SetBased,
					equipment,
					null,
					numberOfSets,
					numberOfReps,
					null);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public final void testReplaceExerciseEmptyFields() {
		
		ExerciseCtrl.createExercise(
				name,
				ExerciseType.SetBased,
				equipment,
				null,
				numberOfSets,
				numberOfReps);
		assertTrue(ExerciseCtrl.exercisesExist());
		
		try {
			// empty field test
			ExerciseCtrl.replaceExercise(
					"",
					ExerciseType.SetBased,
					equipment,
					null,
					numberOfSets,
					numberOfReps,
					ExerciseCtrl.getExercises().get(0));
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, ExerciseCtrl.getExercises().size());
		
		try {
			// empty field test
			ExerciseCtrl.replaceExercise(
					name,
					ExerciseType.SetBased,
					equipment,
					null,
					0,
					numberOfReps,
					ExerciseCtrl.getExercises().get(0));
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, ExerciseCtrl.getExercises().size());
		
		try {
			// empty field test
			ExerciseCtrl.replaceExercise(
					name,
					ExerciseType.SetBased,
					equipment,
					null,
					numberOfSets,
					0,
					ExerciseCtrl.getExercises().get(0));
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, ExerciseCtrl.getExercises().size());
		
		try {
			// empty field test
			ExerciseCtrl.replaceExercise(
					name,
					ExerciseType.TimeBased,
					equipment,
					Duration.ZERO,
					numberOfSets,
					numberOfReps,
					ExerciseCtrl.getExercises().get(0));
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, ExerciseCtrl.getExercises().size());
		
	}
	
	@Test
	public final void testReplaceExerciseNameDuplicate() {
		
		assertFalse(ExerciseCtrl.exercisesExist());
		ExerciseCtrl.createExercise(
				name,
				ExerciseType.SetBased,
				equipment,
				null,
				numberOfSets,
				numberOfReps);
		assertTrue(ExerciseCtrl.exercisesExist());
		assertEquals(1, ExerciseCtrl.getExercises().size());
		
		ExerciseCtrl.createExercise(
				"nameDup",
				ExerciseType.SetBased,
				equipment,
				null,
				numberOfSets,
				numberOfReps);
		assertEquals(2, ExerciseCtrl.getExercises().size());
		
		try {
			ExerciseCtrl.replaceExercise(
					"nameDup",
					ExerciseType.SetBased,
					equipment,
					null,
					numberOfSets,
					numberOfReps,
					ExerciseCtrl.getExercises().get(0));
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		
	}

	@Test
	public final void testExistsWithName() {
		assertFalse(ExerciseCtrl.exercisesExist());
		ExerciseCtrl.createExercise(
				name,
				ExerciseType.SetBased,
				equipment,
				null,
				numberOfSets,
				numberOfReps);
		assertTrue(ExerciseCtrl.exercisesExist());
		assertEquals(1, ExerciseCtrl.getExercises().size());
		assertTrue(ExerciseCtrl.existsWithName(name));
		assertFalse(ExerciseCtrl.existsWithName("new"));
	}

	@Test
	public final void testSearchExercise() {
		assertFalse(ExerciseCtrl.exercisesExist());
		ExerciseCtrl.createExercise(
				"aab",
				ExerciseType.SetBased,
				equipment,
				null,
				numberOfSets,
				numberOfReps);
		assertTrue(ExerciseCtrl.exercisesExist());
		assertEquals(1, ExerciseCtrl.getExercises().size());
		
		ExerciseCtrl.createExercise(
				"abc",
				ExerciseType.SetBased,
				equipment,
				null,
				numberOfSets,
				numberOfReps);
		assertEquals(2, ExerciseCtrl.getExercises().size());
		
		assertEquals(2, ExerciseCtrl.searchExercises(
				"").size());
		assertEquals(2, ExerciseCtrl.searchExercises(
				"a").size());
		assertEquals(1, ExerciseCtrl.searchExercises(
				"aa").size());
		assertEquals(0, ExerciseCtrl.searchExercises(
				"z").size());

	}

}
