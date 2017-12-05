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
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Exercise;
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
		Exercise e = ExerciseCtrl.buildExercise(
				name,
				ExerciseType.SetBased,
				equipment,
				duration,
				numberOfSets,
				numberOfReps);
		ExerciseCtrl.addExercise(e);
		assertTrue(ExerciseCtrl.exercisesExist());
	}

	@Test
	public final void testInitialize() {
		assertFalse(ExerciseCtrl.exercisesExist());
		// file does not exist so this is tries to load but fails
		ExerciseCtrl.initialize();
		assertFalse(ExerciseCtrl.exercisesExist());
		Exercise e = ExerciseCtrl.buildExercise(
				name,
				ExerciseType.SetBased,
				equipment,
				duration,
				numberOfSets,
				numberOfReps);
		ExerciseCtrl.addExercise(e);
		assertTrue(ExerciseCtrl.exercisesExist());
		// equipment exists so this returns
		ExerciseCtrl.initialize();
		assertTrue(ExerciseCtrl.exercisesExist());
	}

	@Test
	public final void testAddExercise() {
		assertFalse(ExerciseCtrl.exercisesExist());
		Exercise e = ExerciseCtrl.buildExercise(
				name,
				ExerciseType.SetBased,
				equipment,
				duration,
				numberOfSets,
				numberOfReps);
		ExerciseCtrl.addExercise(e);
		assertEquals(1, ExerciseCtrl.getExercises().size());
		Exercise e2 = ExerciseCtrl.buildExercise(
				"newName",
				ExerciseType.TimeBased,
				equipment,
				duration,
				numberOfSets,
				numberOfReps);
		ExerciseCtrl.addExercise(e2);
		assertEquals(2, ExerciseCtrl.getExercises().size());
	}
	
	@Test
	public final void testBuildExerciseDuplicateCheck() {
		Exercise ex = ExerciseCtrl.buildExercise(
				name,
				ExerciseType.SetBased,
				equipment,
				duration,
				numberOfSets,
				numberOfReps);
		ExerciseCtrl.addExercise(ex);
		assertEquals(1, ExerciseCtrl.getExercises().size());
		
		try {
			// duplicated based on name
			Exercise e2 = ExerciseCtrl.buildExercise(
					name,
					ExerciseType.SetBased,
					equipment,
					duration,
					numberOfSets,
					numberOfReps);
			ExerciseCtrl.addExercise(e2);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, ExerciseCtrl.getExercises().size());
	}
	
	@Test
	public final void testBuildExerciseEmptyFields() {
		
		try {
			// empty field test
			ExerciseCtrl.buildExercise(
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
			ExerciseCtrl.buildExercise(
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
			ExerciseCtrl.buildExercise(
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
			ExerciseCtrl.buildExercise(
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
	public final void testBuildExerciseNull() {
		assertEquals(0, ExerciseCtrl.getExercises().size());
		try {
			ExerciseCtrl.buildExercise(
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
			ExerciseCtrl.buildExercise(
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
			Exercise ex = ExerciseCtrl.buildExercise(
					name,
					ExerciseType.SetBased,
					null,
					duration,
					numberOfSets,
					numberOfReps);
			ExerciseCtrl.addExercise(ex);
		} catch (IllegalArgumentException e) {
			fail("Expected to NOT throw");
		}
		assertEquals(1, ExerciseCtrl.getExercises().size());
	}

	@Test
	public final void testReplaceExercise() {
		assertFalse(ExerciseCtrl.exercisesExist());
		Exercise e = ExerciseCtrl.buildExercise(
				name,
				ExerciseType.SetBased,
				equipment,
				null,
				numberOfSets,
				numberOfReps);
		ExerciseCtrl.addExercise(e);
		assertTrue(ExerciseCtrl.exercisesExist());
		
		Exercise e2 = ExerciseCtrl.buildExercise(
				"newName",
				ExerciseType.SetBased,
				equipment,
				null,
				numberOfSets,
				numberOfReps);
		ExerciseCtrl.replaceExercise(
				e2,
				ExerciseCtrl.getExercises().get(0));
		
		assertEquals("newName",
				ExerciseCtrl.getExercises()
						.get(0)
						.getName());
		
		Exercise e3 = ExerciseCtrl.buildExercise(
				"newName",
				ExerciseType.SetBased,
				equipment,
				null,
				numberOfSets+1,
				numberOfReps);
		ExerciseCtrl.replaceExercise(
				e3,
				ExerciseCtrl.getExercises().get(0));
		
		assertEquals(numberOfSets+1,
				ExerciseCtrl.getExercises()
						.get(0)
						.getExerciseInfo().getNumberOfSets());
	}
	
	@Test
	public final void testReplaceExerciseNull() {
		try {
			Exercise ex = ExerciseCtrl.buildExercise(
					name,
					ExerciseType.SetBased,
					equipment,
					null,
					numberOfSets,
					numberOfReps);
			ExerciseCtrl.replaceExercise(ex, null);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public final void testReplaceExerciseNameDuplicate() {
		
		assertFalse(ExerciseCtrl.exercisesExist());
		Exercise ex = ExerciseCtrl.buildExercise(
				name,
				ExerciseType.SetBased,
				equipment,
				null,
				numberOfSets,
				numberOfReps);
		ExerciseCtrl.addExercise(ex);
		assertTrue(ExerciseCtrl.exercisesExist());
		assertEquals(1, ExerciseCtrl.getExercises().size());
		
		Exercise e2 = ExerciseCtrl.buildExercise(
				"nameDup",
				ExerciseType.SetBased,
				equipment,
				null,
				numberOfSets,
				numberOfReps);
		ExerciseCtrl.addExercise(e2);
		assertEquals(2, ExerciseCtrl.getExercises().size());
		
		try {
			Exercise e3 = ExerciseCtrl.buildExercise(
					"nameDup",
					ExerciseType.SetBased,
					equipment,
					null,
					numberOfSets,
					numberOfReps);
			ExerciseCtrl.replaceExercise(
					e3,
					ExerciseCtrl.getExercises().get(0));
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		
	}
	
	@Test
	public final void testRemoveExercise() {
		Exercise ex = ExerciseCtrl.buildExercise(
				name,
				ExerciseType.SetBased,
				equipment,
				duration,
				numberOfSets,
				numberOfReps);
		ExerciseCtrl.addExercise(ex);
		assertEquals(1, ExerciseCtrl.getExercises().size());
		
		Exercise ex2 = ExerciseCtrl.buildExercise(
				"new name",
				ExerciseType.SetBased,
				equipment,
				duration,
				numberOfSets,
				numberOfReps);
		ExerciseCtrl.addExercise(ex2);
		assertEquals(2, ExerciseCtrl.getExercises().size());
		
		ExerciseCtrl.removeExercise(ex);
		assertEquals(1, ExerciseCtrl.getExercises().size());
		assertTrue(ExerciseCtrl.getExercises().get(0).equals(ex2));
		ExerciseCtrl.removeExercise(ex2);
		assertEquals(0, ExerciseCtrl.getExercises().size());
	}
	
	@Test
	public final void testEquipmentRemoved() {
		Exercise ex = ExerciseCtrl.buildExercise(
				name,
				ExerciseType.SetBased,
				equipment,
				duration,
				numberOfSets,
				numberOfReps);
		ExerciseCtrl.addExercise(ex);
		assertEquals(1, ExerciseCtrl.getExercises().size());
		
		ExerciseCtrl.equipmentRemoved(equipment);
		assertEquals(1, ExerciseCtrl.getExercises().size());
		assertTrue(ExerciseCtrl.getExercises().get(0).getEquipment() == null);
	}

	@Test
	public final void testExistsWithName() {
		assertFalse(ExerciseCtrl.exercisesExist());
		Exercise e = ExerciseCtrl.buildExercise(
				name,
				ExerciseType.SetBased,
				equipment,
				null,
				numberOfSets,
				numberOfReps);
		ExerciseCtrl.addExercise(e);
		assertTrue(ExerciseCtrl.exercisesExist());
		assertEquals(1, ExerciseCtrl.getExercises().size());
		assertTrue(ExerciseCtrl.existsWithName(name));
		assertFalse(ExerciseCtrl.existsWithName("new"));
	}

	@Test
	public final void testSearchExercise() {
		assertFalse(ExerciseCtrl.exercisesExist());
		Exercise e = ExerciseCtrl.buildExercise(
				"aab",
				ExerciseType.SetBased,
				equipment,
				null,
				numberOfSets,
				numberOfReps);
		ExerciseCtrl.addExercise(e);
		assertTrue(ExerciseCtrl.exercisesExist());
		assertEquals(1, ExerciseCtrl.getExercises().size());
		
		Exercise e2 = ExerciseCtrl.buildExercise(
				"abc",
				ExerciseType.SetBased,
				equipment,
				null,
				numberOfSets,
				numberOfReps);
		ExerciseCtrl.addExercise(e2);
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
