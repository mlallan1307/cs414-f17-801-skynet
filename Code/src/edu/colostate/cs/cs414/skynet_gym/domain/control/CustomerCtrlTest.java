package edu.colostate.cs.cs414.skynet_gym.domain.control;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.colostate.cs.cs414.skynet_gym.domain.people.customer.Customer;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.Address;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.HealthInsurance;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.PersonInformation;

public class CustomerCtrlTest {

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
	
	private final String testFileName = "CustomerCtrlTestfile_delete_me";
	
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
		
		// Set test data to be in a different file than normal
		CustomerCtrl.setSerializedName(testFileName);
		CustomerCtrl.clearData();
	}

	@After
	public void tearDown() throws Exception {
		
		CustomerCtrl.clearData();
	}

	@Test
	public final void testCustomersExist() {
		assertFalse(CustomerCtrl.customersExist());
		CustomerCtrl.createCustomer(
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
		assertTrue(CustomerCtrl.customersExist());
	}

	@Test
	public final void testInitialize() {
		assertFalse(CustomerCtrl.customersExist());
		// file does not exist so this is tries to load but fails
		CustomerCtrl.initialize();
		assertFalse(CustomerCtrl.customersExist());
		CustomerCtrl.createCustomer(
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
		assertTrue(CustomerCtrl.customersExist());
		// customers exists so this returns
		CustomerCtrl.initialize();
		assertTrue(CustomerCtrl.customersExist());
	}

	@Test
	public final void testCreateCustomer() {
		assertFalse(CustomerCtrl.customersExist());
		CustomerCtrl.createCustomer(
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
		assertTrue(CustomerCtrl.customersExist());
	}
	
	@Test
	public final void testCreateCustomerDuplicateCheck() {
		CustomerCtrl.createCustomer(
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
		assertEquals(1, CustomerCtrl.asStringList().size());
		
		try {
			// duplicated based on drivers license
			CustomerCtrl.createCustomer(
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
					"new");
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, CustomerCtrl.asStringList().size());
	}
	
	@Test
	public final void testCreateCustomerEmptyFields() {
		
		try {
			// empty field test
			CustomerCtrl.createCustomer(
					"",
					lastName,
					"newDl",
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
		assertEquals(0, CustomerCtrl.asStringList().size());
		
		try {
			// empty field test
			CustomerCtrl.createCustomer(
					firstName,
					"",
					"newDl",
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
		assertEquals(0, CustomerCtrl.asStringList().size());
		
		try {
			// empty field test
			CustomerCtrl.createCustomer(
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
		assertEquals(0, CustomerCtrl.asStringList().size());
		
		try {
			// empty field test
			CustomerCtrl.createCustomer(
					firstName,
					lastName,
					"newDl",
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
		assertEquals(0, CustomerCtrl.asStringList().size());
		
		try {
			// empty field test
			CustomerCtrl.createCustomer(
					firstName,
					lastName,
					"newDl",
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
		assertEquals(0, CustomerCtrl.asStringList().size());
		
		try {
			// empty field test
			CustomerCtrl.createCustomer(
					firstName,
					lastName,
					"newDl",
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
		assertEquals(0, CustomerCtrl.asStringList().size());
		
		try {
			// empty field test
			CustomerCtrl.createCustomer(
					firstName,
					lastName,
					"newDl",
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
		assertEquals(0, CustomerCtrl.asStringList().size());
		
		try {
			// empty field test
			CustomerCtrl.createCustomer(
					firstName,
					lastName,
					"newDl",
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
		assertEquals(0, CustomerCtrl.asStringList().size());
		
		try {
			// empty field test
			CustomerCtrl.createCustomer(
					firstName,
					lastName,
					"newDl",
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
		assertEquals(0, CustomerCtrl.asStringList().size());
		
		try {
			// empty field test
			CustomerCtrl.createCustomer(
					firstName,
					lastName,
					"newDl",
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
		assertEquals(0, CustomerCtrl.asStringList().size());
		
		try {
			// empty field test
			CustomerCtrl.createCustomer(
					firstName,
					lastName,
					"newDl",
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
		assertEquals(0, CustomerCtrl.asStringList().size());
		
		
		
		// TESTING NOT REQUIRED FIELDS
		
		try {
			// empty field test
			CustomerCtrl.createCustomer(
					firstName,
					lastName,
					"newDl",
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
		assertEquals(1, CustomerCtrl.asStringList().size());
		
	}

	@Test
	public final void testReplaceCustomer() {
		assertFalse(CustomerCtrl.customersExist());
		Customer c = new Customer(pi);
		CustomerCtrl.createCustomer(
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
		assertTrue(CustomerCtrl.customersExist());
		
		CustomerCtrl.replaceCustomer(
				"newFirstName",
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
				c);
		
		assertEquals("newFirstName",
				CustomerCtrl.getCustomers()
						.get(0)
						.getPersonInfo()
						.getFirstName());
	}
	
	@Test
	public final void testReplaceCustomerNull() {
		try {
		CustomerCtrl.replaceCustomer(
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
				null);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public final void testReplaceCustomerEmptyFields() {
		
		Customer c = new Customer(pi);
		CustomerCtrl.createCustomer(
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
		assertTrue(CustomerCtrl.customersExist());
		
		try {
			// empty field test
			CustomerCtrl.replaceCustomer(
					"",
					lastName,
					"newDl",
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					c);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, CustomerCtrl.asStringList().size());
		
		try {
			// empty field test
			CustomerCtrl.replaceCustomer(
					firstName,
					"",
					"newDl",
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					c);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, CustomerCtrl.asStringList().size());
		
		try {
			// empty field test
			CustomerCtrl.replaceCustomer(
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
					c);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, CustomerCtrl.asStringList().size());
		
		try {
			// empty field test
			CustomerCtrl.replaceCustomer(
					firstName,
					lastName,
					"newDl",
					"",
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					c);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, CustomerCtrl.asStringList().size());
		
		try {
			// empty field test
			CustomerCtrl.replaceCustomer(
					firstName,
					lastName,
					"newDl",
					phone,
					"",
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					c);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, CustomerCtrl.asStringList().size());
		
		try {
			// empty field test
			CustomerCtrl.replaceCustomer(
					firstName,
					lastName,
					"newDl",
					phone,
					email,
					"",
					s1,
					s2,
					state,
					city,
					zip,
					type,
					c);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, CustomerCtrl.asStringList().size());
		
		try {
			// empty field test
			CustomerCtrl.replaceCustomer(
					firstName,
					lastName,
					"newDl",
					phone,
					email,
					hiN,
					"",
					s2,
					state,
					city,
					zip,
					type,
					c);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, CustomerCtrl.asStringList().size());
		
		try {
			// empty field test
			CustomerCtrl.replaceCustomer(
					firstName,
					lastName,
					"newDl",
					phone,
					email,
					hiN,
					s1,
					"",
					state,
					city,
					zip,
					type,
					c);
		} catch (IllegalArgumentException e) {
			fail("Expected to NOT throw");
		}
		assertEquals(1, CustomerCtrl.asStringList().size());
		
		try {
			// empty field test
			CustomerCtrl.replaceCustomer(
					firstName,
					lastName,
					"newDl",
					phone,
					email,
					hiN,
					s1,
					s2,
					"",
					city,
					zip,
					type,
					c);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, CustomerCtrl.asStringList().size());
		
		try {
			// empty field test
			CustomerCtrl.replaceCustomer(
					firstName,
					lastName,
					"newDl",
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					"",
					zip,
					type,
					c);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, CustomerCtrl.asStringList().size());
		
		try {
			// empty field test
			CustomerCtrl.replaceCustomer(
					firstName,
					lastName,
					"newDl",
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					"",
					type,
					c);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, CustomerCtrl.asStringList().size());
		
		try {
			// empty field test
			CustomerCtrl.replaceCustomer(
					firstName,
					lastName,
					"newDl",
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					"",
					c);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, CustomerCtrl.asStringList().size());
	}
	
	@Test
	public final void testReplaceCustomerDLNDuplicate() {
		
		assertFalse(CustomerCtrl.customersExist());
		CustomerCtrl.createCustomer(
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
		assertTrue(CustomerCtrl.customersExist());
		assertEquals(1, CustomerCtrl.getCustomers().size());
		
		CustomerCtrl.createCustomer(
				firstName,
				lastName,
				"DupDLN",
				phone,
				email,
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type);
		assertEquals(2, CustomerCtrl.getCustomers().size());
		
		try {
			CustomerCtrl.replaceCustomer(
					firstName,
					lastName,
					"DupDLN",
					phone,
					email,
					hiN,
					s1,
					s2,
					state,
					city,
					zip,
					type,
					CustomerCtrl.getCustomers().get(0));
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		
	}

	@Test
	public final void testExistsWithDLN() {
		assertFalse(CustomerCtrl.customersExist());
		CustomerCtrl.createCustomer(
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
		assertTrue(CustomerCtrl.customersExist());
		assertEquals(1, CustomerCtrl.getCustomers().size());
		assertTrue(CustomerCtrl.existsWithDLN(driversLicenseNumber));
	}

	@Test
	public final void testSearchCustomers() {
		assertFalse(CustomerCtrl.customersExist());
		CustomerCtrl.createCustomer(
				"aac",
				"aac",
				"d1",
				"aac",
				"aac",
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type);
		assertTrue(CustomerCtrl.customersExist());
		assertEquals(1, CustomerCtrl.getCustomers().size());
		
		CustomerCtrl.createCustomer(
				"abc",
				"abc",
				"d2",
				"abc",
				"abc",
				hiN,
				s1,
				s2,
				state,
				city,
				zip,
				type);
		assertEquals(2, CustomerCtrl.getCustomers().size());
		
		assertEquals(2, CustomerCtrl.searchCustomers(
				"",
				"",
				"",
				"").size());
		assertEquals(2, CustomerCtrl.searchCustomers(
				"a",
				"",
				"",
				"").size());
		assertEquals(1, CustomerCtrl.searchCustomers(
				"aa",
				"",
				"",
				"").size());
		assertEquals(2, CustomerCtrl.searchCustomers(
				"",
				"a",
				"",
				"").size());
		assertEquals(1, CustomerCtrl.searchCustomers(
				"",
				"aa",
				"",
				"").size());
		assertEquals(2, CustomerCtrl.searchCustomers(
				"",
				"",
				"a",
				"").size());
		assertEquals(1, CustomerCtrl.searchCustomers(
				"",
				"",
				"aa",
				"").size());
		assertEquals(2, CustomerCtrl.searchCustomers(
				"",
				"",
				"",
				"a").size());
		assertEquals(1, CustomerCtrl.searchCustomers(
				"",
				"",
				"",
				"aa").size());
		
		assertEquals(0, CustomerCtrl.searchCustomers(
				"",
				"",
				"",
				"z").size());
	}

}
