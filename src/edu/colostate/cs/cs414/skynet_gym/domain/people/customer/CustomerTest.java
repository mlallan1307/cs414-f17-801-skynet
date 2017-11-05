package edu.colostate.cs.cs414.skynet_gym.domain.people.customer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.colostate.cs.cs414.skynet_gym.domain.people.info.Address;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.HealthInsurance;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.Membership;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.PersonInformation;

public class CustomerTest {

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
	private Customer customer;
	
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
		customer = new Customer(pi);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testCustomer() {
		assertTrue(customer.getPersonInfo()
				.getFirstName()
				.equals(firstName));
		
		try {
			Customer f = new Customer(null);
			fail("Expected to throw");
		} catch(NullPointerException e) {
		}
	}

	@Test
	public final void testEqualsObject() {
		Customer c2 = new Customer(pi);
		assertTrue(c2.equals(customer));
		assertFalse(customer.equals(null));
	}

	@Test
	public final void testGetPersonInfo() {
		assertTrue(customer.getPersonInfo()
				.getFirstName()
				.equals(firstName));
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
		customer.setPersonInfo(pi2);
	
		try {
			customer.setPersonInfo(null);
			fail("Expected to throw");
		} catch(NullPointerException e) {
		}
	}

	@Test
	public final void testGetMembership() {
		assertTrue(customer.getMembership().isValid());
	}

	@Test
	public final void testSetMembership() {
		Membership m = new Membership(false);
		
		customer.setMembership(m);
		assertFalse(customer.getMembership().isValid());
		try {
			customer.setMembership(null);
			fail("Expected to throw");
		} catch(NullPointerException e) {
		}
	}

}
