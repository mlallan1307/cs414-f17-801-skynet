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
		RoutineCtrl.createRoutine(
				name,
				ale);
		assertTrue(RoutineCtrl.routinesExist());
	}

	@Test
	public final void testInitialize() {
		assertFalse(RoutineCtrl.routinesExist());
		// file does not exist so this is tries to load but fails
		RoutineCtrl.initialize();
		assertFalse(RoutineCtrl.routinesExist());
		RoutineCtrl.createRoutine(
				name,
				ale);
		assertTrue(RoutineCtrl.routinesExist());
		// equipment exists so this returns
		RoutineCtrl.initialize();
		assertTrue(RoutineCtrl.routinesExist());
	}

	@Test
	public final void testCreateRoutine() {
		assertFalse(RoutineCtrl.routinesExist());
		RoutineCtrl.createRoutine(
				name,
				ale);
		assertEquals(1, RoutineCtrl.getRoutines().size());
		RoutineCtrl.createRoutine(
				"newName",
				ale);
		assertEquals(2, RoutineCtrl.getRoutines().size());
	}
	
	@Test
	public final void testCreateRoutineDuplicateCheck() {
		RoutineCtrl.createRoutine(
				name,
				ale);
		assertEquals(1, RoutineCtrl.getRoutines().size());
		
		try {
			// duplicated based on name
			RoutineCtrl.createRoutine(
					name,
					ale);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, RoutineCtrl.getRoutines().size());
	}
	
	@Test
	public final void testCreateRoutineEmptyFields() {
		
		try {
			// empty field test
			RoutineCtrl.createRoutine(
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
			RoutineCtrl.createRoutine(
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
		RoutineCtrl.createRoutine(
				name,
				ale);
		assertTrue(RoutineCtrl.routinesExist());
		
		RoutineCtrl.replaceRoutine(
				"newName",
				ale,
				RoutineCtrl.getRoutines().get(0));
		
		assertEquals("newName",
				RoutineCtrl.getRoutines()
						.get(0)
						.getName());
		
		assertEquals(1, ale.size());
		ale.add(new Exercise("newExName", exerciseInfo));
		assertEquals(2, ale.size());
		
		RoutineCtrl.replaceRoutine(
				"newName",
				ale,
				RoutineCtrl.getRoutines().get(0));
		
		assertEquals(2,
				RoutineCtrl.getRoutines()
						.get(0)
						.getExercises().size());
	}
	
	@Test
	public final void testReplaceRoutineNull() {
		try {
			RoutineCtrl.replaceRoutine(
					name,
					ale,
					null);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		
		RoutineCtrl.createRoutine(
				name,
				ale);
		assertTrue(RoutineCtrl.routinesExist());
		
		try {
			RoutineCtrl.replaceRoutine(
					name,
					null,
					RoutineCtrl.getRoutines().get(0));
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public final void testReplaceRoutineEmptyFields() {
		
		RoutineCtrl.createRoutine(
				name,
				ale);
		assertTrue(RoutineCtrl.routinesExist());
		
		try {
			// empty field test
			RoutineCtrl.replaceRoutine(
					"",
					ale,
					RoutineCtrl.getRoutines().get(0));
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, RoutineCtrl.getRoutines().size());
		
	}
	
	@Test
	public final void testReplaceRoutineNameDuplicate() {
		
		assertFalse(RoutineCtrl.routinesExist());
		RoutineCtrl.createRoutine(
				name,
				ale);
		assertTrue(RoutineCtrl.routinesExist());
		assertEquals(1, RoutineCtrl.getRoutines().size());
		
		RoutineCtrl.createRoutine(
				"nameDup",
				ale);
		assertEquals(2, RoutineCtrl.getRoutines().size());
		
		try {
			RoutineCtrl.replaceRoutine(
					"nameDup",
					ale,
					RoutineCtrl.getRoutines().get(0));
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		
	}

	@Test
	public final void testExistsWithName() {
		assertFalse(RoutineCtrl.routinesExist());
		RoutineCtrl.createRoutine(
				name,
				ale);
		assertTrue(RoutineCtrl.routinesExist());
		assertEquals(1, RoutineCtrl.getRoutines().size());
		assertTrue(RoutineCtrl.existsWithName(name));
		assertFalse(RoutineCtrl.existsWithName("new"));
	}

	@Test
	public final void testSearchRoutine() {
		assertFalse(RoutineCtrl.routinesExist());
		RoutineCtrl.createRoutine(
				"aab",
				ale);
		assertTrue(RoutineCtrl.routinesExist());
		assertEquals(1, RoutineCtrl.getRoutines().size());
		
		RoutineCtrl.createRoutine(
				"abc",
				ale);
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
