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
import edu.colostate.cs.cs414.skynet_gym.domain.people.user.UserType;
import edu.colostate.cs.cs414.skynet_gym.domain.utilities.AccountManager;

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
		Trainer t = TrainerCtrl.buildTrainer(
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
		TrainerCtrl.addTrainer(t);
		assertTrue(TrainerCtrl.trainersExists());
	}

	@Test
	public final void testInitialize() {
		assertFalse(TrainerCtrl.trainersExists());
		// file does not exist so this is tries to load but fails
		TrainerCtrl.initialize();
		assertFalse(TrainerCtrl.trainersExists());
		Trainer t = TrainerCtrl.buildTrainer(
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
		TrainerCtrl.addTrainer(t);
		assertTrue(TrainerCtrl.trainersExists());
		// manager exists so this returns
		TrainerCtrl.initialize();
		assertTrue(TrainerCtrl.trainersExists());
	}

	@Test
	public final void testCreateTrainer() {
		assertFalse(TrainerCtrl.trainersExists());
		Trainer t = TrainerCtrl.buildTrainer(
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
		TrainerCtrl.addTrainer(t);
		assertTrue(TrainerCtrl.trainersExists());
		assertEquals(username, TrainerCtrl.getTrainers().get(0).getUsername());
	}
	
	@Test
	public final void testBuildTrainerEmptyFields() {
		assertFalse(TrainerCtrl.trainersExists());
		
		try {
			TrainerCtrl.buildTrainer(
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
			TrainerCtrl.buildTrainer(
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
			TrainerCtrl.buildTrainer(
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
			TrainerCtrl.buildTrainer(
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
			TrainerCtrl.buildTrainer(
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
			TrainerCtrl.buildTrainer(
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
			TrainerCtrl.buildTrainer(
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
			TrainerCtrl.buildTrainer(
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
			TrainerCtrl.buildTrainer(
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
			TrainerCtrl.buildTrainer(
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
			TrainerCtrl.buildTrainer(
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
			TrainerCtrl.buildTrainer(
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
			TrainerCtrl.buildTrainer(
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
			TrainerCtrl.buildTrainer(
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
			TrainerCtrl.buildTrainer(
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
		} catch (IllegalArgumentException e) {
			fail("Expected to NOT throw");
		}
		assertFalse(TrainerCtrl.trainersExists());
		try {
			TrainerCtrl.buildTrainer(
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
		assertFalse(TrainerCtrl.trainersExists());
		
	}
	
	@Test
	public final void testCreateTrainerNull() {
		
		assertFalse(TrainerCtrl.trainersExists());
		try {
			TrainerCtrl.buildTrainer(
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
			TrainerCtrl.buildTrainer(
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
		Trainer t = TrainerCtrl.buildTrainer(
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
		TrainerCtrl.addTrainer(t);
		assertTrue(TrainerCtrl.trainersExists());
		assertEquals(1, TrainerCtrl.getTrainers().size());
		
		try {
			Trainer t2 = TrainerCtrl.buildTrainer(
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
			TrainerCtrl.addTrainer(t2);
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
		assertEquals(1, TrainerCtrl.getTrainers().size());
	}
	
	@Test
	public final void testCreateTrainerUsernameDuplicate() {
		assertFalse(TrainerCtrl.trainersExists());
		Trainer t = TrainerCtrl.buildTrainer(
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
		TrainerCtrl.addTrainer(t);
		assertTrue(TrainerCtrl.trainersExists());
		assertEquals(1, TrainerCtrl.getTrainers().size());
		
		try {
			Trainer t2 = TrainerCtrl.buildTrainer(
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
			TrainerCtrl.addTrainer(t2);
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
		assertEquals(1, TrainerCtrl.getTrainers().size());
	}

	@Test
	public final void testReplaceTrainer() {
		assertFalse(TrainerCtrl.trainersExists());
		Trainer t = TrainerCtrl.buildTrainer(
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
		TrainerCtrl.addTrainer(t);
		assertTrue(TrainerCtrl.trainersExists());
		
		Trainer t2 = TrainerCtrl.buildTrainer(
				username,
				"",
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
				qualifications);
		TrainerCtrl.replaceTrainer(
				t2,
				TrainerCtrl.getTrainers().get(0));
		assertEquals(1, TrainerCtrl.getTrainers().size());
		assertEquals("newName",
				TrainerCtrl.getTrainers().get(0).getPersonInfo().getFirstName());

	}
	
	@Test
	public final void testReplaceTrainerNull() {
		assertFalse(TrainerCtrl.trainersExists());
		Trainer t = TrainerCtrl.buildTrainer(
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
		TrainerCtrl.addTrainer(t);
		assertTrue(TrainerCtrl.trainersExists());
		
		try {
			Trainer t2 = TrainerCtrl.buildTrainer(
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
			TrainerCtrl.replaceTrainer(
					t2,
					null);
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}

	}
	
	@Test
	public final void testReplaceTrainerDLNDuplicate() {
		assertFalse(TrainerCtrl.trainersExists());
		Trainer t = TrainerCtrl.buildTrainer(
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
		TrainerCtrl.addTrainer(t);
		assertTrue(TrainerCtrl.trainersExists());
		
		Trainer t2 = TrainerCtrl.buildTrainer(
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
		TrainerCtrl.addTrainer(t2);
		assertTrue(TrainerCtrl.trainersExists());
		assertEquals(2, TrainerCtrl.getTrainers().size());
		
		try {
			Trainer t3 = TrainerCtrl.buildTrainer(
					"new uname",
					password,
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
					qualifications);
			TrainerCtrl.replaceTrainer(
					t3,
					TrainerCtrl.getTrainers().get(0));
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public final void testReplaceTrainerUsernameDuplicate() {
		assertFalse(TrainerCtrl.trainersExists());
		Trainer t = TrainerCtrl.buildTrainer(
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
		TrainerCtrl.addTrainer(t);
		assertTrue(TrainerCtrl.trainersExists());
		
		Trainer t2 = TrainerCtrl.buildTrainer(
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
		TrainerCtrl.addTrainer(t2);
		assertTrue(TrainerCtrl.trainersExists());
		assertEquals(2, TrainerCtrl.getTrainers().size());
		
		try {
			Trainer t3 = TrainerCtrl.buildTrainer(
					"uname DUP",
					password,
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
					qualifications);
			TrainerCtrl.replaceTrainer(
					t3,
					TrainerCtrl.getTrainers().get(0));
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public final void testReplaceTrainerDoesNotExist() {
		assertFalse(TrainerCtrl.trainersExists());
		Trainer t = TrainerCtrl.buildTrainer(
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
		TrainerCtrl.addTrainer(t);
		assertTrue(TrainerCtrl.trainersExists());
		
		Trainer tmp = TrainerCtrl.buildTrainer(
				"random uname",
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
		
		try {
			Trainer t2 = TrainerCtrl.buildTrainer(
					"new uname",
					password,
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
					qualifications);
			TrainerCtrl.replaceTrainer(
					t2,
					tmp);
			fail("Expected to throw");
		} catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public final void testRemoveTrainer() {
		assertFalse(TrainerCtrl.trainersExists());
		Trainer t = TrainerCtrl.buildTrainer(
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
		TrainerCtrl.addTrainer(t);
		assertEquals(1, TrainerCtrl.getTrainers().size());
		
		Trainer t2 = TrainerCtrl.buildTrainer(
				"new un",
				password,
				firstName,
				lastName,
				"new dln",
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
		TrainerCtrl.addTrainer(t2);
		assertEquals(2, TrainerCtrl.getTrainers().size());
		
		TrainerCtrl.removeTrainer(t);
		assertEquals(1, TrainerCtrl.getTrainers().size());
		assertTrue(TrainerCtrl.getTrainers().get(0).equals(t2));
		
		TrainerCtrl.removeTrainer(t2);
		assertEquals(0, TrainerCtrl.getTrainers().size());
	}

	@Test
	public final void testExistsWithDLN() {
		assertFalse(TrainerCtrl.trainersExists());
		Trainer t = TrainerCtrl.buildTrainer(
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
		TrainerCtrl.addTrainer(t);
		assertTrue(TrainerCtrl.trainersExists());
		
		assertTrue(TrainerCtrl.existsWithDLN(driversLicenseNumber));
	}

	@Test
	public final void testExistsWithUsername() {
		assertFalse(TrainerCtrl.trainersExists());
		Trainer t = TrainerCtrl.buildTrainer(
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
		TrainerCtrl.addTrainer(t);
		assertTrue(TrainerCtrl.trainersExists());
		
		assertTrue(TrainerCtrl.existsWithUsername(username));
	}

	@Test
	public final void testResetPassword() {
		assertFalse(TrainerCtrl.trainersExists());
		Trainer t = TrainerCtrl.buildTrainer(
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
		TrainerCtrl.addTrainer(t);
		assertTrue(TrainerCtrl.trainersExists());
		
		assertTrue(AccountManager.login(username, "pw2")
				== null);
		assertTrue(TrainerCtrl.resetPassword(
				firstName,
				lastName,
				driversLicenseNumber,
				username,
				"pw2"));
		assertTrue(AccountManager.login(username, "pw2")
				== UserType.Trainer);
		
	}

	@Test
	public final void testSearchTrainers() {
		assertFalse(TrainerCtrl.trainersExists());
		{
			Trainer t = TrainerCtrl.buildTrainer(
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
			TrainerCtrl.addTrainer(t);
		}
		assertTrue(TrainerCtrl.trainersExists());
		
		{
			Trainer t = TrainerCtrl.buildTrainer(
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
			TrainerCtrl.addTrainer(t);
		}
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
