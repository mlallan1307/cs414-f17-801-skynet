package edu.colostate.cs.cs414.skynet_gym.domain.people.info;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	private final String s1    = "s1";
	private final String s2    = "s1";
	private final String state = "state";
	private final String city  = "city";
	private final String zip   = "zip";
	private final String type  = "type";
	private final String repl  = "newSt";
	private Address address;
	private HealthInsurance hi;
	private final String hiN  = "hi Name";
	
	private final String firstName = "fName";
	private final String lastName  = "lName";
	private final String driversLicenseNumber = "dlNum";
	private final String phone     = "ph";
	private final String email     = "em";
	private PersonInformation pi;
	
	private final String userType = "type";
	private final String username = "user";
	private final String password = "superSecret";
	private User user;
	
	@Before
	public void setUp() throws Exception {
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
		user = new User(
				userType,
				username,
				password,
				pi);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testUser() {
		assertTrue(user.getUsername().equals(username));
		assertTrue(user.getUserType().equals(userType));
	}

	@Test
	public final void testEqualsObject() {
		User user2 = new User(
				userType,
				username,
				password,
				pi);
		assertTrue(user.equals(user2));
		assertFalse(user.equals(null));
	}

	@Test
	public final void testLogin() {
		assertTrue(user.login(username, password));
		assertFalse(user.login("n", password));
		assertFalse(user.login(username, "n"));
		assertFalse(user.login("n", "n"));
	}

	@Test
	public final void testRecoverUsername() {
		assertTrue(user.recoverUsername(
				firstName,
				lastName,
				driversLicenseNumber).equals(username));
		assertFalse(user.recoverUsername(
				null,
				lastName,
				driversLicenseNumber).equals(username));
		assertFalse(user.recoverUsername(
				firstName,
				null,
				driversLicenseNumber).equals(username));
		assertFalse(user.recoverUsername(
				firstName,
				lastName,
				null).equals(username));
		assertFalse(user.recoverUsername(
				null,
				null,
				null).equals(username));
	}

	@Test
	public final void testResetPassword() {
		assertFalse(user.isPassword("newPassword"));
		assertTrue(user.resetPassword(
				firstName,
				lastName,
				driversLicenseNumber,
				username,
				"newPassword"));
		assertTrue(user.isPassword("newPassword"));
		assertFalse(user.isPassword(password));
	}

	@Test
	public final void testIsPassword() {
		assertTrue(user.isPassword(password));
		assertFalse(user.isPassword("notPW"));
	}

	@Test
	public final void testGetUserType() {
		assertTrue(user.getUserType().equals(userType));
	}

	@Test
	public final void testSetUsername() {
		assertTrue(user.getUsername().equals(username));
		user.setUsername("newU");
		assertTrue(user.getUsername().equals("newU"));
		assertFalse(user.getUsername().equals(username));
	}

	@Test
	public final void testGetUsername() {
		assertTrue(user.getUsername().equals(username));
	}

	@Test
	public final void testGetPersonInfo() {
		assertTrue(user.getPersonInfo().equals(pi));
	}

	@Test
	public final void testSetPersonInfo() {
		PersonInformation pi2 = new PersonInformation(
				"newName",
				lastName,
				driversLicenseNumber,
				phone,
				email,
				hi,
				address);
		user.setPersonInfo(pi2);
		
		assertTrue(user.getPersonInfo().getFirstName().equals("newName"));
		
		try {
			user.setPersonInfo(null);
			fail("Expected to throw");
		} catch(NullPointerException e){
		}
	}

}
