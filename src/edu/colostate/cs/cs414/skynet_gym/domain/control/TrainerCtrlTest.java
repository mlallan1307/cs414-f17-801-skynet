package edu.colostate.cs.cs414.skynet_gym.domain.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.DayOfWeek;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.colostate.cs.cs414.skynet_gym.domain.data.people.Address;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.HealthInsurance;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.PersonInformation;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.Qualification;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.Schedule;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.TimePeriod;
import edu.colostate.cs.cs414.skynet_gym.domain.people.user.Trainer;

public class TrainerCtrlTest {

	private final String s1    = "s1";
	private final String s2    = "s1";
	private final String state = "state";
	private final String city  = "city";
	private final String zip   = "zip";
	private final String type  = "type";
	private Address address;
	private HealthInsurance hi;
	private final String hiN  = "hi Name";
	
	private final String firstName = "fName";
	private final String lastName  = "lName";
	private final String driversLicenseNumber = "dlNum";
	private final String phone     = "ph";
	private final String email     = "em";
	private PersonInformation pi;
	
	private final String username = "username";
	private final String password = "password";
	
	private Schedule schedule;
	private ArrayList<Qualification> qualifications;
	
	private final String testFileName = "TrainerCtrlTestfile_delete_me";
	
	@Before
	public void setUp() throws Exception {
		schedule = new Schedule();
		schedule.addPeriod(new TimePeriod(
				DayOfWeek.THURSDAY,
				"08:00",
				DayOfWeek.THURSDAY,
				"08:01"));
		qualifications = new ArrayList<Qualification>();
		qualifications.add(new Qualification("n", "d"));
		address = new Address(
				s1,
				s2,
				state,
				city,
				zip,
				type);
		hi = new HealthInsurance(hiN);
		pi = new PersonInformation(
				firstName,
				lastName,
				driversLicenseNumber,
				phone,
				email,
				hi,
				address);
		
		// Set test data to be in a different file than normal
		TrainerCtrl.setSerializedName(testFileName);
		TrainerCtrl.clearData();
	}

	@After
	public void tearDown() throws Exception {
		TrainerCtrl.clearData();
	}

	@Test
	public final void testTrainersExists() {
		assertFalse(TrainerCtrl.trainersExists());
		TrainerCtrl.createTrainer(
				username,
				password,
				firstName,
				lastName,
				driversLicenseNumber,
				phone,
				email,
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type,
				schedule,
				qualifications);
		assertTrue(TrainerCtrl.trainersExists());
	}

	@Test
	public final void testInitialize() {
		assertFalse(TrainerCtrl.trainersExists());
		// file does not exist so this is tries to load but fails
		TrainerCtrl.initialize();
		assertFalse(TrainerCtrl.trainersExists());
		TrainerCtrl.createTrainer(
				username,
				password,
				firstName,
				lastName,
				driversLicenseNumber,
				phone,
				email,
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type,
				schedule,
				qualifications);
		assertTrue(TrainerCtrl.trainersExists());
		// manager exists so this returns
		TrainerCtrl.initialize();
		assertTrue(TrainerCtrl.trainersExists());
	}

	@Test
	public final void testCreateTrainer() {
		assertFalse(TrainerCtrl.trainersExists());
		TrainerCtrl.createTrainer(
				username,
				password,
				firstName,
				lastName,
				driversLicenseNumber,
				phone,
				email,
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type,
				schedule,
				qualifications);
		assertTrue(TrainerCtrl.trainersExists());
		assertEquals(username, TrainerCtrl.getTrainers().get(0).getUsername());
	}
	
	@Test
	public final void testCreateTrainerEmptyFields() {
		assertFalse(TrainerCtrl.trainersExists());
		
		try {
			TrainerCtrl.createTrainer(
					"",
					password,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					qualifications);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(TrainerCtrl.trainersExists());
		try {
			TrainerCtrl.createTrainer(
					username,
					"",
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					qualifications);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(TrainerCtrl.trainersExists());
		try {
			TrainerCtrl.createTrainer(
					username,
					password,
					"",
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					qualifications);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(TrainerCtrl.trainersExists());
		try {
			TrainerCtrl.createTrainer(
					username,
					password,
					firstName,
					"",
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					qualifications);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(TrainerCtrl.trainersExists());
		try {
			TrainerCtrl.createTrainer(
					username,
					password,
					firstName,
					lastName,
					"",
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					qualifications);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(TrainerCtrl.trainersExists());
		try {
			TrainerCtrl.createTrainer(
					username,
					password,
					firstName,
					lastName,
					driversLicenseNumber,
					"",
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					qualifications);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(TrainerCtrl.trainersExists());
		try {
			TrainerCtrl.createTrainer(
					username,
					password,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					"",
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					qualifications);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(TrainerCtrl.trainersExists());
		try {
			TrainerCtrl.createTrainer(
					username,
					password,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					"",
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					qualifications);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(TrainerCtrl.trainersExists());
		try {
			TrainerCtrl.createTrainer(
					username,
					password,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					"",
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					qualifications);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(TrainerCtrl.trainersExists());
		try {
			TrainerCtrl.createTrainer(
					username,
					password,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					"",
					city,
					zip,
					type,
					schedule,
					qualifications);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(TrainerCtrl.trainersExists());
		try {
			TrainerCtrl.createTrainer(
					username,
					password,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					"",
					zip,
					type,
					schedule,
					qualifications);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(TrainerCtrl.trainersExists());
		try {
			TrainerCtrl.createTrainer(
					username,
					password,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					"",
					type,
					schedule,
					qualifications);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(TrainerCtrl.trainersExists());
		try {
			TrainerCtrl.createTrainer(
					username,
					password,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					"",
					schedule,
					qualifications);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(TrainerCtrl.trainersExists());
		try {
			TrainerCtrl.createTrainer(
					username,
					password,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					new Schedule(),
					qualifications);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(TrainerCtrl.trainersExists());
		try {
			TrainerCtrl.createTrainer(
					username,
					password,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					new ArrayList<Qualification>());
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(TrainerCtrl.trainersExists());
		
		
		// TESTING NOT REQUIRED FIELDS
		
		try {
			TrainerCtrl.createTrainer(
					username,
					password,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					"",
					state,
					city,
					zip,
					type,
					schedule,
					qualifications);
		} catch (IllegalArgumentException e) {
			fail("Expected to NOT throw");
		}
		assertTrue(TrainerCtrl.trainersExists());
	}
	
	@Test
	public final void testCreateTrainerNull() {
		
		assertFalse(TrainerCtrl.trainersExists());
		try {
			TrainerCtrl.createTrainer(
					username,
					password,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					null,
					qualifications);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(TrainerCtrl.trainersExists());
		try {
			TrainerCtrl.createTrainer(
					username,
					password,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					null);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(TrainerCtrl.trainersExists());
	}
	
	@Test
	public final void testCreateTrainerDLNDuplicate() {
		assertFalse(TrainerCtrl.trainersExists());
		TrainerCtrl.createTrainer(
				username,
				password,
				firstName,
				lastName,
				driversLicenseNumber,
				phone,
				email,
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type,
				schedule,
				qualifications);
		assertTrue(TrainerCtrl.trainersExists());
		assertEquals(1, TrainerCtrl.getTrainers().size());
		
		try {
		TrainerCtrl.createTrainer(
				"new",
				"new",
				"new",
				"new",
				driversLicenseNumber,
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				schedule,
				qualifications);
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
		assertEquals(1, TrainerCtrl.getTrainers().size());
	}
	
	@Test
	public final void testCreateTrainerUsernameDuplicate() {
		assertFalse(TrainerCtrl.trainersExists());
		TrainerCtrl.createTrainer(
				username,
				password,
				firstName,
				lastName,
				driversLicenseNumber,
				phone,
				email,
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type,
				schedule,
				qualifications);
		assertTrue(TrainerCtrl.trainersExists());
		assertEquals(1, TrainerCtrl.getTrainers().size());
		
		try {
		TrainerCtrl.createTrainer(
				username,
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				schedule,
				qualifications);
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
		assertEquals(1, TrainerCtrl.getTrainers().size());
	}

	@Test
	public final void testReplaceTrainer() {
		assertFalse(TrainerCtrl.trainersExists());
		TrainerCtrl.createTrainer(
				username,
				password,
				firstName,
				lastName,
				driversLicenseNumber,
				phone,
				email,
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type,
				schedule,
				qualifications);
		assertTrue(TrainerCtrl.trainersExists());
		
		TrainerCtrl.replaceTrainer(
				username,
				"newName",
				lastName,
				driversLicenseNumber,
				phone,
				email,
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type,
				schedule,
				qualifications,
				TrainerCtrl.getTrainers().get(0));
		assertEquals(1, TrainerCtrl.getTrainers().size());
		assertEquals("newName",
				TrainerCtrl.getTrainers().get(0).getPersonInfo().getFirstName());

	}
	
	@Test
	public final void testReplaceTrainerEmptyFields() {
		assertFalse(TrainerCtrl.trainersExists());
		TrainerCtrl.createTrainer(
				username,
				password,
				firstName,
				lastName,
				driversLicenseNumber,
				phone,
				email,
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type,
				schedule,
				qualifications);
		assertTrue(TrainerCtrl.trainersExists());
		
		try {
			TrainerCtrl.replaceTrainer(
					"",
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					qualifications,
					TrainerCtrl.getTrainers().get(0));
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
		assertEquals(1, TrainerCtrl.getTrainers().size());

		try {
			TrainerCtrl.replaceTrainer(
					username,
					"",
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					qualifications,
					TrainerCtrl.getTrainers().get(0));
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
		assertEquals(1, TrainerCtrl.getTrainers().size());

		try {
			TrainerCtrl.replaceTrainer(
					username,
					firstName,
					"",
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					qualifications,
					TrainerCtrl.getTrainers().get(0));
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
		assertEquals(1, TrainerCtrl.getTrainers().size());

		try {
			TrainerCtrl.replaceTrainer(
					username,
					firstName,
					lastName,
					"",
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					qualifications,
					TrainerCtrl.getTrainers().get(0));
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
		assertEquals(1, TrainerCtrl.getTrainers().size());

		try {
			TrainerCtrl.replaceTrainer(
					username,
					firstName,
					lastName,
					driversLicenseNumber,
					"",
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					qualifications,
					TrainerCtrl.getTrainers().get(0));
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
		assertEquals(1, TrainerCtrl.getTrainers().size());

		try {
			TrainerCtrl.replaceTrainer(
					username,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					"",
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					qualifications,
					TrainerCtrl.getTrainers().get(0));
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
		assertEquals(1, TrainerCtrl.getTrainers().size());

		try {
			TrainerCtrl.replaceTrainer(
					username,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					"",
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					qualifications,
					TrainerCtrl.getTrainers().get(0));
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
		assertEquals(1, TrainerCtrl.getTrainers().size());

		try {
			TrainerCtrl.replaceTrainer(
					username,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					"",
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					qualifications,
					TrainerCtrl.getTrainers().get(0));
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
		assertEquals(1, TrainerCtrl.getTrainers().size());

		try {
			TrainerCtrl.replaceTrainer(
					username,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					"",
					city,
					zip,
					type,
					schedule,
					qualifications,
					TrainerCtrl.getTrainers().get(0));
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
		assertEquals(1, TrainerCtrl.getTrainers().size());

		try {
			TrainerCtrl.replaceTrainer(
					username,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					"",
					zip,
					type,
					schedule,
					qualifications,
					TrainerCtrl.getTrainers().get(0));
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
		assertEquals(1, TrainerCtrl.getTrainers().size());

		try {
			TrainerCtrl.replaceTrainer(
					username,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					"",
					type,
					schedule,
					qualifications,
					TrainerCtrl.getTrainers().get(0));
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
		assertEquals(1, TrainerCtrl.getTrainers().size());

		try {
			TrainerCtrl.replaceTrainer(
					username,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					"",
					schedule,
					qualifications,
					TrainerCtrl.getTrainers().get(0));
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
		assertEquals(1, TrainerCtrl.getTrainers().size());

		try {
			TrainerCtrl.replaceTrainer(
					username,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					new Schedule(),
					qualifications,
					TrainerCtrl.getTrainers().get(0));
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
		assertEquals(1, TrainerCtrl.getTrainers().size());

		try {
			TrainerCtrl.replaceTrainer(
					username,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					new ArrayList<Qualification>(),
					TrainerCtrl.getTrainers().get(0));
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
		assertEquals(1, TrainerCtrl.getTrainers().size());

		
		// TESTING NOT REQUIRED FIELDS
		
		try {
			TrainerCtrl.replaceTrainer(
					username,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					"",
					state,
					city,
					zip,
					type,
					schedule,
					qualifications,
					TrainerCtrl.getTrainers().get(0));
		} catch(IllegalArgumentException e) {
			fail("Expected to NOT throw");
		}
		assertEquals(1, TrainerCtrl.getTrainers().size());

	}
	
	@Test
	public final void testReplaceTrainerNull() {
		assertFalse(TrainerCtrl.trainersExists());
		TrainerCtrl.createTrainer(
				username,
				password,
				firstName,
				lastName,
				driversLicenseNumber,
				phone,
				email,
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type,
				schedule,
				qualifications);
		assertTrue(TrainerCtrl.trainersExists());
		
		try {
			TrainerCtrl.replaceTrainer(
					username,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					null,
					qualifications,
					TrainerCtrl.getTrainers().get(0));
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
		try {
			TrainerCtrl.replaceTrainer(
					username,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					null,
					TrainerCtrl.getTrainers().get(0));
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
		
		try {
			TrainerCtrl.replaceTrainer(
					username,
					firstName,
					lastName,
					driversLicenseNumber,
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					qualifications,
					null);
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}

	}
	
	@Test
	public final void testReplaceTrainerDLNDuplicate() {
		assertFalse(TrainerCtrl.trainersExists());
		TrainerCtrl.createTrainer(
				username,
				password,
				firstName,
				lastName,
				driversLicenseNumber,
				phone,
				email,
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type,
				schedule,
				qualifications);
		assertTrue(TrainerCtrl.trainersExists());
		
		TrainerCtrl.createTrainer(
				"new",
				"new",
				"new",
				"new",
				"dlnum DUP",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				schedule,
				qualifications);
		assertTrue(TrainerCtrl.trainersExists());
		assertEquals(2, TrainerCtrl.getTrainers().size());
		
		try {
			TrainerCtrl.replaceTrainer(
					"new uname",
					firstName,
					lastName,
					"dlnum DUP",
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					qualifications,
					TrainerCtrl.getTrainers().get(0));
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public final void testReplaceTrainerUsernameDuplicate() {
		assertFalse(TrainerCtrl.trainersExists());
		TrainerCtrl.createTrainer(
				username,
				password,
				firstName,
				lastName,
				driversLicenseNumber,
				phone,
				email,
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type,
				schedule,
				qualifications);
		assertTrue(TrainerCtrl.trainersExists());
		
		TrainerCtrl.createTrainer(
				"uname DUP",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				"new",
				schedule,
				qualifications);
		assertTrue(TrainerCtrl.trainersExists());
		assertEquals(2, TrainerCtrl.getTrainers().size());
		
		try {
			TrainerCtrl.replaceTrainer(
					"uname DUP",
					firstName,
					lastName,
					"new DLNUM",
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					qualifications,
					TrainerCtrl.getTrainers().get(0));
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public final void testReplaceTrainerDoesNotExist() {
		assertFalse(TrainerCtrl.trainersExists());
		TrainerCtrl.createTrainer(
				username,
				password,
				firstName,
				lastName,
				driversLicenseNumber,
				phone,
				email,
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type,
				schedule,
				qualifications);
		assertTrue(TrainerCtrl.trainersExists());
		
		Trainer tmp = new Trainer(
				"random uname",
				password,
				pi,
				schedule,
				qualifications);
		
		try {
			TrainerCtrl.replaceTrainer(
					"new uname",
					firstName,
					lastName,
					"new DLNUM",
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					schedule,
					qualifications,
					tmp);
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
	}

	@Test
	public final void testExistsWithDLN() {
		assertFalse(TrainerCtrl.trainersExists());
		TrainerCtrl.createTrainer(
				username,
				password,
				firstName,
				lastName,
				driversLicenseNumber,
				phone,
				email,
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type,
				schedule,
				qualifications);
		assertTrue(TrainerCtrl.trainersExists());
		
		assertTrue(TrainerCtrl.existsWithDLN(driversLicenseNumber));
	}

	@Test
	public final void testExistsWithUsername() {
		assertFalse(TrainerCtrl.trainersExists());
		TrainerCtrl.createTrainer(
				username,
				password,
				firstName,
				lastName,
				driversLicenseNumber,
				phone,
				email,
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type,
				schedule,
				qualifications);
		assertTrue(TrainerCtrl.trainersExists());
		
		assertTrue(TrainerCtrl.existsWithUsername(username));
	}

	@Test
	public final void testLogin() {
		assertFalse(TrainerCtrl.trainersExists());
		TrainerCtrl.createTrainer(
				username,
				password,
				firstName,
				lastName,
				driversLicenseNumber,
				phone,
				email,
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type,
				schedule,
				qualifications);
		assertTrue(TrainerCtrl.trainersExists());
		
		assertTrue(TrainerCtrl.login(username, password));
		assertFalse(TrainerCtrl.login("nope", password));
		assertFalse(TrainerCtrl.login(username, "nope"));
		assertFalse(TrainerCtrl.login("nope", "nope"));
	}

	@Test
	public final void testRecoverUsername() {
		assertFalse(TrainerCtrl.trainersExists());
		TrainerCtrl.createTrainer(
				username,
				password,
				firstName,
				lastName,
				driversLicenseNumber,
				phone,
				email,
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type,
				schedule,
				qualifications);
		assertTrue(TrainerCtrl.trainersExists());
		
		assertEquals(username,
				TrainerCtrl.recoverUsername(
						firstName,
						lastName,
						driversLicenseNumber));
		
		assertTrue("" ==
				TrainerCtrl.recoverUsername(
						"nope",
						lastName,
						driversLicenseNumber));
		assertTrue("" ==
				TrainerCtrl.recoverUsername(
						firstName,
						"nope",
						driversLicenseNumber));
		assertTrue("" ==
				TrainerCtrl.recoverUsername(
						firstName,
						lastName,
						"nope"));
	}

	@Test
	public final void testResetPassword() {
		assertFalse(TrainerCtrl.trainersExists());
		TrainerCtrl.createTrainer(
				username,
				password,
				firstName,
				lastName,
				driversLicenseNumber,
				phone,
				email,
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type,
				schedule,
				qualifications);
		assertTrue(TrainerCtrl.trainersExists());
		
		assertFalse(TrainerCtrl.login(username, "pw2"));
		assertTrue(TrainerCtrl.resetPassword(
				firstName,
				lastName,
				driversLicenseNumber,
				username,
				"pw2"));
		assertTrue(TrainerCtrl.login(username, "pw2"));
		
	}

	@Test
	public final void testSearchTrainers() {
		assertFalse(TrainerCtrl.trainersExists());
		TrainerCtrl.createTrainer(
				"aab",
				password,
				"aab",
				"aab",
				driversLicenseNumber,
				"aab",
				"aab",
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type,
				schedule,
				qualifications);
		assertTrue(TrainerCtrl.trainersExists());
		
		TrainerCtrl.createTrainer(
				"abc",
				password,
				"abc",
				"abc",
				"dln2",
				"abc",
				"abc",
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type,
				schedule,
				qualifications);
		assertEquals(2, TrainerCtrl.getTrainers().size());
		
		assertEquals(2, TrainerCtrl.searchTrainers(
				"",
				"",
				"",
				"",
				"").size());
		assertEquals(2, TrainerCtrl.searchTrainers(
				"a",
				"",
				"",
				"",
				"").size());
		assertEquals(1, TrainerCtrl.searchTrainers(
				"aa",
				"",
				"",
				"",
				"").size());
		assertEquals(2, TrainerCtrl.searchTrainers(
				"",
				"a",
				"",
				"",
				"").size());
		assertEquals(1, TrainerCtrl.searchTrainers(
				"",
				"aa",
				"",
				"",
				"").size());
		assertEquals(2, TrainerCtrl.searchTrainers(
				"",
				"",
				"a",
				"",
				"").size());
		assertEquals(1, TrainerCtrl.searchTrainers(
				"",
				"",
				"aa",
				"",
				"").size());
		assertEquals(2, TrainerCtrl.searchTrainers(
				"",
				"",
				"",
				"a",
				"").size());
		assertEquals(1, TrainerCtrl.searchTrainers(
				"",
				"",
				"",
				"aa",
				"").size());
		assertEquals(2, TrainerCtrl.searchTrainers(
				"",
				"",
				"",
				"",
				"a").size());
		assertEquals(1, TrainerCtrl.searchTrainers(
				"",
				"",
				"",
				"",
				"aa").size());
		assertEquals(0, TrainerCtrl.searchTrainers(
				"",
				"",
				"",
				"",
				"z").size());
	}

}
