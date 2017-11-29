package edu.colostate.cs.cs414.skynet_gym.domain.data.people;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersonInformationTest {
	
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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testPersonInformation() {
		PersonInformation personI = new PersonInformation(
				firstName,
				lastName,
				driversLicenseNumber,
				phone,
				email,
				hi,
				address);
		
		assertTrue(personI.getFirstName().equals(firstName));
	}

	@Test
	public final void testEqualsObject() {
		PersonInformation personI = new PersonInformation(
				firstName,
				lastName,
				driversLicenseNumber,
				phone,
				email,
				hi,
				address);
		
		assertTrue(personI.equals(pi));
		assertFalse(personI.equals(null));
	}

	@Test
	public final void testSetPersonInformation() {
		pi.setPersonInformation(
				"firstName",
				"lastName",
				"driversLicenseNumber",
				"phone",
				"email",
				hi,
				address);
		
		assertTrue(pi.getFirstName().equals("firstName"));
	}

	@Test
	public final void testGetFirstName() {
		assertTrue(pi.getFirstName().equals(firstName));
	}

	@Test
	public final void testSetFirstName() {
		assertTrue(pi.getFirstName().equals(firstName));
		pi.setFirstName(repl);
		assertTrue(pi.getFirstName().equals(repl));
	}

	@Test
	public final void testGetLastName() {
		assertTrue(pi.getLastName().equals(lastName));
	}

	@Test
	public final void testSetLastName() {
		assertTrue(pi.getLastName().equals(lastName));
		pi.setLastName(repl);
		assertTrue(pi.getLastName().equals(repl));
	}

	@Test
	public final void testGetDriversLicenseNumber() {
		assertTrue(pi.getDriversLicenseNumber()
				.equals(driversLicenseNumber));
	}

	@Test
	public final void testSetDriversLicenseNumber() {
		assertTrue(pi.getDriversLicenseNumber()
				.equals(driversLicenseNumber));
		pi.setDriversLicenseNumber(repl);
		assertTrue(pi.getDriversLicenseNumber()
				.equals(repl));
	}

	@Test
	public final void testGetPhone() {
		assertTrue(pi.getPhone().equals(phone));
	}

	@Test
	public final void testSetPhone() {
		assertTrue(pi.getPhone().equals(phone));
		pi.setPhone(repl);
		assertTrue(pi.getPhone().equals(repl));
	}

	@Test
	public final void testGetEmail() {
		assertTrue(pi.getEmail().equals(email));
	}

	@Test
	public final void testSetEmail() {
		assertTrue(pi.getEmail().equals(email));
		pi.setEmail(repl);
		assertTrue(pi.getEmail().equals(repl));
	}

	@Test
	public final void testGetAddress() {
		assertTrue(pi.getAddress().equals(address));
	}

	@Test
	public final void testSetAddress() {
		assertTrue(pi.getAddress().equals(address));
		Address address2 = new Address(
				"s1",
				s2,
				state,
				city,
				zip,
				type);
		pi.setAddress(address2);
		assertTrue(pi.getAddress().equals(address2));
		
		try {
			pi.setAddress(null);
			fail("Expected to throw");
		} catch(NullPointerException e){
		}
	}

	@Test
	public final void testGetHealthInsurance() {
		assertTrue(pi.getHealthInsurance().equals(hi));
	}

	@Test
	public final void testSetHealthInsurance() {
		assertTrue(pi.getHealthInsurance().equals(hi));
		HealthInsurance hi2 = new HealthInsurance("new hi");
		pi.setHealthInsurance(hi2);
		assertTrue(pi.getHealthInsurance().equals(hi2));
		
		try {
			pi.setHealthInsurance(null);
			fail("Expected to throw");
		} catch(NullPointerException e){
		}
	}

}
