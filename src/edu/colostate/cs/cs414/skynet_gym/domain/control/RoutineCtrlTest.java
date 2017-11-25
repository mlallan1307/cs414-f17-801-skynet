package edu.colostate.cs.cs414.skynet_gym.domain.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Equipment;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Exercise;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.ExerciseTypeIf;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Routine;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.SetBasedExercise;

public class RoutineCtrlTest {

	// Exercise
	private final String exName  = "exName";
	private final int numberOfSets = 2;
	private final int numberOfReps = 4;
	private final Duration duration = Duration.ofSeconds(90);
	private final ExerciseTypeIf setExInfo = new SetBasedExercise(numberOfSets, numberOfReps);
	private ExerciseTypeIf exerciseInfo;
	private Exercise exercise;
	
	// Equipment
	private final String eqName  = "eqName";
	private final int eqQuantity = 1; 
	private File picture;
	private Equipment equipment;
	
	// Routine
	private final String name = "rtName";
	private ArrayList<Exercise> ale;
	
	private final String testFileName = "RoutineCtrlTestfile_delete_me";
	
	@Before
	public void setUp() throws Exception {
		
		picture = new File(testFileName + ".jpg");
		if (picture.exists()) {
			picture.delete();
		}
		picture.createNewFile();
		
		equipment = new Equipment(eqName, eqQuantity, picture);
		
		exerciseInfo = setExInfo;
		exercise = new Exercise(exName, exerciseInfo, equipment);
		ale = new ArrayList<Exercise>();
		ale.add(exercise);
		
		// Set test data to be in a different file than normal
		RoutineCtrl.setSerializedName(testFileName);
		RoutineCtrl.clearData();
	}

	@After
	public void tearDown() throws Exception {
		
		RoutineCtrl.clearData();
		if (picture.exists()) {
			picture.delete();
		}
	}

	@Test
	public final void testRoutineExist() {
		assertFalse(RoutineCtrl.routinesExist());
		Routine r = RoutineCtrl.buildRoutine(
				name,
				ale);
		RoutineCtrl.addRoutine(r);
		assertTrue(RoutineCtrl.routinesExist());
	}

	@Test
	public final void testInitialize() {
		assertFalse(RoutineCtrl.routinesExist());
		// file does not exist so this is tries to load but fails
		RoutineCtrl.initialize();
		assertFalse(RoutineCtrl.routinesExist());
		Routine r = RoutineCtrl.buildRoutine(
				name,
				ale);
		RoutineCtrl.addRoutine(r);
		assertTrue(RoutineCtrl.routinesExist());
		// equipment exists so this returns
		RoutineCtrl.initialize();
		assertTrue(RoutineCtrl.routinesExist());
	}

	@Test
	public final void testBuildRoutine() {
		assertFalse(RoutineCtrl.routinesExist());
		Routine r = RoutineCtrl.buildRoutine(
				name,
				ale);
		RoutineCtrl.addRoutine(r);
		assertEquals(1, RoutineCtrl.getRoutines().size());
		Routine r2 = RoutineCtrl.buildRoutine(
				"newName",
				ale);
		RoutineCtrl.addRoutine(r2);
		assertEquals(2, RoutineCtrl.getRoutines().size());
	}
	
	@Test
	public final void testCreateRoutineDuplicateCheck() {
		Routine r = RoutineCtrl.buildRoutine(
				name,
				ale);
		RoutineCtrl.addRoutine(r);
		assertEquals(1, RoutineCtrl.getRoutines().size());
		
		try {
			// duplicated based on name
			Routine r2 = RoutineCtrl.buildRoutine(
					name,
					ale);
			RoutineCtrl.addRoutine(r2);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, RoutineCtrl.getRoutines().size());
	}
	
	@Test
	public final void testBuildRoutineEmptyFields() {
		
		try {
			// empty field test
			RoutineCtrl.buildRoutine(
					"",
					ale);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(0, RoutineCtrl.getRoutines().size());
		
	}
	
	@Test
	public final void testCreateRoutineNull() {
		assertEquals(0, RoutineCtrl.getRoutines().size());
		try {
			// Duration 0
			RoutineCtrl.buildRoutine(
					name,
					null);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(0, RoutineCtrl.getRoutines().size());
		
	}

	@Test
	public final void testReplaceRoutine() {
		assertFalse(RoutineCtrl.routinesExist());
		Routine r = RoutineCtrl.buildRoutine(
				name,
				ale);
		RoutineCtrl.addRoutine(r);
		assertTrue(RoutineCtrl.routinesExist());
		
		Routine r2 = RoutineCtrl.buildRoutine(
				"newName",
				ale);
		RoutineCtrl.replaceRoutine(
				r2, 
				RoutineCtrl.getRoutines().get(0));
		
		assertEquals("newName",
				RoutineCtrl.getRoutines()
						.get(0)
						.getName());
		
		assertEquals(1, ale.size());
		ale.add(new Exercise("newExName", exerciseInfo));
		assertEquals(2, ale.size());
		
		Routine r3 = RoutineCtrl.buildRoutine(
				"newName",
				ale);
		RoutineCtrl.replaceRoutine(
				r3, 
				RoutineCtrl.getRoutines().get(0));
		
		assertEquals(2,
				RoutineCtrl.getRoutines()
						.get(0)
						.getExercises().size());
	}
	
	@Test
	public final void testReplaceRoutineNull() {
		try {
			Routine r = RoutineCtrl.buildRoutine(
					name,
					ale);
			RoutineCtrl.replaceRoutine(
					r, 
					null);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public final void testReplaceRoutineNameDuplicate() {
		
		assertFalse(RoutineCtrl.routinesExist());
		Routine r = RoutineCtrl.buildRoutine(
				name,
				ale);
		RoutineCtrl.addRoutine(r);
		assertTrue(RoutineCtrl.routinesExist());
		assertEquals(1, RoutineCtrl.getRoutines().size());
		
		Routine r2 = RoutineCtrl.buildRoutine(
				"nameDup",
				ale);
		RoutineCtrl.addRoutine(r2);
		assertEquals(2, RoutineCtrl.getRoutines().size());
		
		try {
			Routine r3 = RoutineCtrl.buildRoutine(
					"nameDup",
					ale);
			RoutineCtrl.replaceRoutine(
					r3,
					RoutineCtrl.getRoutines().get(0));
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		
	}

	@Test
	public final void testExistsWithName() {
		assertFalse(RoutineCtrl.routinesExist());
		Routine r = RoutineCtrl.buildRoutine(
				name,
				ale);
		RoutineCtrl.addRoutine(r);
		assertTrue(RoutineCtrl.routinesExist());
		assertEquals(1, RoutineCtrl.getRoutines().size());
		assertTrue(RoutineCtrl.existsWithName(name));
		assertFalse(RoutineCtrl.existsWithName("new"));
	}

	@Test
	public final void testSearchRoutine() {
		assertFalse(RoutineCtrl.routinesExist());
		Routine r = RoutineCtrl.buildRoutine(
				"aab",
				ale);
		RoutineCtrl.addRoutine(r);
		assertTrue(RoutineCtrl.routinesExist());
		assertEquals(1, RoutineCtrl.getRoutines().size());
		
		Routine r2= RoutineCtrl.buildRoutine(
				"abc",
				ale);
		RoutineCtrl.addRoutine(r2);
		assertEquals(2, RoutineCtrl.getRoutines().size());
		
		assertEquals(2, RoutineCtrl.searchRoutines(
				"").size());
		assertEquals(2, RoutineCtrl.searchRoutines(
				"a").size());
		assertEquals(1, RoutineCtrl.searchRoutines(
				"aa").size());
		assertEquals(0, RoutineCtrl.searchRoutines(
				"z").size());

	}

}
