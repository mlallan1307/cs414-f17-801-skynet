
package edu.colostate.cs.cs414.skynet_gym.domain.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.colostate.cs.cs414.skynet_gym.domain.people.user.UserType;
import edu.colostate.cs.cs414.skynet_gym.domain.utilities.AccountManager;

/**
 * @author Skynet
 *
 */
public class ManagerCtrlTest {

	private final String s1    = "s1";
	private final String s2    = "s1";
	private final String state = "state";
	private final String city  = "city";
	private final String zip   = "zip";
	private final String type  = "type";
	private final String hiN  = "hi Name";
	
	private final String firstName = "fName";
	private final String lastName  = "lName";
	private final String driversLicenseNumber = "dlNum";
	private final String phone     = "ph";
	private final String email     = "em";
	
	private final String username = "username";
	private final String password = "password";
	
	private final String testFileName = "ManagerCtrlTestfile_delete_me";
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Set test data to be in a different file than normal
		ManagerCtrl.setSerializedName(testFileName);
		ManagerCtrl.clearData();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		ManagerCtrl.clearData();
	}

	/**
	 * Test method for {@link edu.colostate.cs.cs414.skynet_gym.domain.control.ManagerCtrl#managerExists()}.
	 */
	@Test
	public final void testManagerExists() {
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
	}
	
	@Test
	public final void testInitialize() {
		assertFalse(ManagerCtrl.managerExists());
		// file does not exist so this is tries to load but fails
		ManagerCtrl.initialize();
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
		// manager exists so this returns
		ManagerCtrl.initialize();
		assertTrue(ManagerCtrl.managerExists());
	}

	/**
	 * Test method for {@link edu.colostate.cs.cs414.skynet_gym.domain.control.ManagerCtrl#createManager(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testCreateManager() {
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
		assertTrue(ManagerCtrl.getManager().login(username, password));
	}
	
	/**
	 * Testing throws for {@link edu.colostate.cs.cs414.skynet_gym.domain.control.ManagerCtrl#createManager(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testCreateManagerEmptyFields(){
		
		assertFalse(ManagerCtrl.managerExists());
		
		try {
			ManagerCtrl.createManager(
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
					type);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(ManagerCtrl.managerExists());
		
		try {
			ManagerCtrl.createManager(
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
					type);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(ManagerCtrl.managerExists());
		
		try {
			ManagerCtrl.createManager(
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
					type);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(ManagerCtrl.managerExists());
		
		try {
			ManagerCtrl.createManager(
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
					type);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(ManagerCtrl.managerExists());
		
		try {
			ManagerCtrl.createManager(
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
					type);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(ManagerCtrl.managerExists());
		
		try {
			ManagerCtrl.createManager(
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
					type);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(ManagerCtrl.managerExists());
		
		try {
			ManagerCtrl.createManager(
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
					type);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(ManagerCtrl.managerExists());
		
		try {
			ManagerCtrl.createManager(
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
					type);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(ManagerCtrl.managerExists());
		
		try {
			ManagerCtrl.createManager(
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
					type);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(ManagerCtrl.managerExists());
		
		try {
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
					"",
					city,
					zip,
					type);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(ManagerCtrl.managerExists());
		
		try {
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
					"",
					zip,
					type);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(ManagerCtrl.managerExists());
		
		try {
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
					"",
					type);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(ManagerCtrl.managerExists());
		
		try {
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
					"");
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertFalse(ManagerCtrl.managerExists());
		
		
		// TESTING NOT REQUIRED FIELDS
		
		try {
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
					"",
					state,
					city,
					zip,
					type);
		} catch (IllegalArgumentException e) {
			fail("Expected to NOT throw");
		}
		assertTrue(ManagerCtrl.managerExists());
		
	}

	@Test
	public final void testGetManager() {
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
		assertEquals(username, ManagerCtrl.getManager().getUsername());
		assertEquals(firstName, ManagerCtrl.getManager().getPersonInfo().getFirstName());
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
		
		assertTrue(AccountManager.login(username, "newPassword")
				== null);
		assertTrue(ManagerCtrl.resetPassword(
				firstName,
				lastName,
				driversLicenseNumber,
				username,
				"newPassword"));
		assertTrue(AccountManager.login(username, "newPassword")
				== UserType.Manager);
		assertTrue(AccountManager.login(username, password)
				== null);
	}

}
