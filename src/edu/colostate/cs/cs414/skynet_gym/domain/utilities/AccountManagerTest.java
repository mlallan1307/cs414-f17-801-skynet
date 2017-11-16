package edu.colostate.cs.cs414.skynet_gym.domain.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.DayOfWeek;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.colostate.cs.cs414.skynet_gym.domain.control.ManagerCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.control.TrainerCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.Address;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.HealthInsurance;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.PersonInformation;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.Qualification;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.Schedule;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.TimePeriod;
import edu.colostate.cs.cs414.skynet_gym.domain.people.user.UserType;

public class AccountManagerTest {

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
	
	private final String testFileName = "AccountManagerTestfile_delete_me";
	
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
		ManagerCtrl.setSerializedName(testFileName);
		ManagerCtrl.clearData();
	}

	@After
	public void tearDown() throws Exception {
		TrainerCtrl.clearData();
		ManagerCtrl.clearData();
	}

	@Test
	public final void testLogin() {
		assertFalse(ManagerCtrl.managerExists());
		ManagerCtrl.createManager(
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
				type);
		assertTrue(ManagerCtrl.managerExists());
		assertFalse(TrainerCtrl.trainersExists());
		TrainerCtrl.createTrainer(
				"trainR",
				password,
				firstName,
				lastName,
				"223344",
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
		TrainerCtrl.createTrainer(
				"trainR2",
				password,
				firstName,
				lastName,
				"22334455",
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
		assertEquals(2, TrainerCtrl.getTrainers().size());
		
		assertTrue(AccountManager.login(username, password)
				== UserType.Manager);
		assertTrue(AccountManager.login("trainR", password)
				== UserType.Trainer);
		assertTrue(AccountManager.login("trainR2", password)
				== UserType.Trainer);
		
		assertTrue(AccountManager.login("not username", password)
				== null);
		assertTrue(AccountManager.login(username, "not password")
				== null);
		assertTrue(AccountManager.login("not username", "not password")
				== null);
		assertTrue(AccountManager.login("nope", password)
				== null);
		assertTrue(AccountManager.login("trainR", "nope")
				== null);
		assertTrue(AccountManager.login("nope", "nope")
				== null);
		
	}

	@Test
	public final void testRecoverUsername() {
		assertFalse(ManagerCtrl.managerExists());
		ManagerCtrl.createManager(
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
				type);
		assertTrue(ManagerCtrl.managerExists());
		assertFalse(TrainerCtrl.trainersExists());
		TrainerCtrl.createTrainer(
				"trainR",
				password,
				firstName,
				lastName,
				"223344",
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
		TrainerCtrl.createTrainer(
				"trainR2",
				password,
				firstName,
				lastName,
				"22334455",
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
		assertEquals(2, TrainerCtrl.getTrainers().size());
		
		assertEquals(username,
				AccountManager.recoverUsername(
						firstName,
						lastName,
						driversLicenseNumber));
		assertEquals("trainR",
				AccountManager.recoverUsername(
						firstName,
						lastName,
						"223344"));
		assertEquals("trainR2",
				AccountManager.recoverUsername(
						firstName,
						lastName,
						"22334455"));
		
		assertTrue("" ==
				AccountManager.recoverUsername(
						"nope",
						lastName,
						driversLicenseNumber));
		assertTrue("" ==
				AccountManager.recoverUsername(
						firstName,
						"nope",
						driversLicenseNumber));
		assertTrue("" ==
				AccountManager.recoverUsername(
						firstName,
						lastName,
						"nope"));
	}

	@Test
	public final void testResetPassword() {
		assertFalse(ManagerCtrl.managerExists());
		ManagerCtrl.createManager(
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
				type);
		assertTrue(ManagerCtrl.managerExists());
		assertFalse(TrainerCtrl.trainersExists());
		TrainerCtrl.createTrainer(
				"trainR",
				password,
				firstName,
				lastName,
				"223344",
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
		TrainerCtrl.createTrainer(
				"trainR2",
				password,
				firstName,
				lastName,
				"22334455",
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
		assertEquals(2, TrainerCtrl.getTrainers().size());
		
		assertTrue(AccountManager.login(username, "pw2")
				== null);
		assertTrue(AccountManager.resetPassword(
				firstName,
				lastName,
				driversLicenseNumber,
				username,
				"pw2"));
		assertTrue(AccountManager.login(username, "pw2")
				== UserType.Manager);
		
		assertTrue(AccountManager.login("trainR", "pwNew")
				== null);
		assertTrue(AccountManager.resetPassword(
				firstName,
				lastName,
				"223344",
				"trainR",
				"pwNew"));
		assertTrue(AccountManager.login("trainR", "pwNew")
				== UserType.Trainer);
		
		assertFalse(AccountManager.resetPassword(
				"NotKnown",
				lastName,
				driversLicenseNumber,
				username,
				"pwNew"));
	}

}
