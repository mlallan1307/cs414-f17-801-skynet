package edu.colostate.cs.cs414.skynet_gym.domain.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Routine;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.Address;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.HealthInsurance;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.PersonInformation;
import edu.colostate.cs.cs414.skynet_gym.domain.people.other.Customer;

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
	
	private Routine routine;
	
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
		
		routine = new Routine("rt");
		
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
		Customer c = CustomerCtrl.buildCustomer(
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
		CustomerCtrl.addCustomer(c);
		assertTrue(CustomerCtrl.customersExist());
	}

	@Test
	public final void testInitialize() {
		assertFalse(CustomerCtrl.customersExist());
		// file does not exist so this is tries to load but fails
		CustomerCtrl.initialize();
		assertFalse(CustomerCtrl.customersExist());
		Customer c = CustomerCtrl.buildCustomer(
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
		CustomerCtrl.addCustomer(c);
		assertTrue(CustomerCtrl.customersExist());
		// customers exists so this returns
		CustomerCtrl.initialize();
		assertTrue(CustomerCtrl.customersExist());
	}

	@Test
	public final void testAddCustomer() {
		assertFalse(CustomerCtrl.customersExist());
		Customer c = CustomerCtrl.buildCustomer(
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
		CustomerCtrl.addCustomer(c);
		assertTrue(CustomerCtrl.customersExist());
	}
	
	@Test
	public final void testAddCustomerDuplicateCheck() {
		Customer c = CustomerCtrl.buildCustomer(
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
		CustomerCtrl.addCustomer(c);
		assertEquals(1, CustomerCtrl.asStringList().size());
		
		try {
			// duplicated based on drivers license
			Customer c2 = CustomerCtrl.buildCustomer(
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
			CustomerCtrl.addCustomer(c2);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, CustomerCtrl.asStringList().size());
	}
	
	@Test
	public final void testBuildCustomerEmptyFields() {
		
		try {
			// empty field test
			CustomerCtrl.buildCustomer(
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
			CustomerCtrl.buildCustomer(
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
			CustomerCtrl.buildCustomer(
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
			CustomerCtrl.buildCustomer(
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
			CustomerCtrl.buildCustomer(
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
			CustomerCtrl.buildCustomer(
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
			CustomerCtrl.buildCustomer(
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
			CustomerCtrl.buildCustomer(
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
			CustomerCtrl.buildCustomer(
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
			CustomerCtrl.buildCustomer(
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
			CustomerCtrl.buildCustomer(
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
			CustomerCtrl.buildCustomer(
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
		assertEquals(0, CustomerCtrl.asStringList().size());
		
	}

	@Test
	public final void testReplaceCustomer() {
		assertFalse(CustomerCtrl.customersExist());
		Customer c = CustomerCtrl.buildCustomer(
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
		CustomerCtrl.addCustomer(c);
		assertTrue(CustomerCtrl.customersExist());
		
		Customer c2 = CustomerCtrl.buildCustomer(
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
				type);
		CustomerCtrl.replaceCustomer(
				c2,
				CustomerCtrl.getCustomers().get(0));
		
		assertEquals("newFirstName",
				CustomerCtrl.getCustomers()
						.get(0)
						.getPersonInfo()
						.getFirstName());
	}
	
	@Test
	public final void testReplaceCustomerNull() {
		try {
			Customer c = CustomerCtrl.buildCustomer(
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
			CustomerCtrl.replaceCustomer(c, null);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public final void testReplaceCustomerDLNDuplicate() {
		
		assertFalse(CustomerCtrl.customersExist());
		Customer c = CustomerCtrl.buildCustomer(
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
		CustomerCtrl.addCustomer(c);
		assertTrue(CustomerCtrl.customersExist());
		assertEquals(1, CustomerCtrl.getCustomers().size());
		
		Customer c2 = CustomerCtrl.buildCustomer(
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
		CustomerCtrl.addCustomer(c2);
		assertEquals(2, CustomerCtrl.getCustomers().size());
		
		try {
			Customer c3 = CustomerCtrl.buildCustomer(
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
			CustomerCtrl.replaceCustomer(
					c3, 
					CustomerCtrl.getCustomers().get(0));
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		
	}

	@Test
	public final void testRemoveCustomer() {
		assertFalse(CustomerCtrl.customersExist());
		Customer c = CustomerCtrl.buildCustomer(
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
		CustomerCtrl.addCustomer(c);
		assertEquals(1, CustomerCtrl.getCustomers().size());
		
		Customer c2 = CustomerCtrl.buildCustomer(
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
				type);
		CustomerCtrl.addCustomer(c2);
		assertEquals(2, CustomerCtrl.getCustomers().size());
		
		CustomerCtrl.removeCustomer(c);
		assertEquals(1, CustomerCtrl.getCustomers().size());
		assertTrue(CustomerCtrl.getCustomers().get(0).equals(c2));
		CustomerCtrl.removeCustomer(c2);
		assertEquals(0, CustomerCtrl.getCustomers().size());
	}
	
	@Test
	public final void testExistsWithDLN() {
		assertFalse(CustomerCtrl.customersExist());
		Customer c = CustomerCtrl.buildCustomer(
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
		CustomerCtrl.addCustomer(c);
		assertTrue(CustomerCtrl.customersExist());
		assertEquals(1, CustomerCtrl.getCustomers().size());
		assertTrue(CustomerCtrl.existsWithDLN(driversLicenseNumber));
	}

	@Test
	public final void testSearchCustomers() {
		assertFalse(CustomerCtrl.customersExist());
		Customer c = CustomerCtrl.buildCustomer(
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
		CustomerCtrl.addCustomer(c);
		assertTrue(CustomerCtrl.customersExist());
		assertEquals(1, CustomerCtrl.getCustomers().size());
		
		Customer c2 = CustomerCtrl.buildCustomer(
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
		CustomerCtrl.addCustomer(c2);
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
	
	@Test
	public final void testAssignRoutines() {
		Customer c = CustomerCtrl.buildCustomer(
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
		CustomerCtrl.addCustomer(c);
		assertTrue(CustomerCtrl.customersExist());
		ArrayList<Routine> rts = new ArrayList<Routine>();
		rts.add(routine);
		
		CustomerCtrl.assignRoutines(
				CustomerCtrl.getCustomers().get(0),
				rts);
		
		assertEquals(
				rts.size(),
				CustomerCtrl.getCustomers().get(0).getRoutines().size());
	}
	
	@Test
	public final void testAssignRoutinesInvalid() {
		Customer c = CustomerCtrl.buildCustomer(
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
		CustomerCtrl.addCustomer(c);
		assertTrue(CustomerCtrl.customersExist());
		ArrayList<Routine> rts = new ArrayList<Routine>();
		rts.add(routine);
		
		Customer c2 = CustomerCtrl.buildCustomer(
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
				type);
		
		try {
			CustomerCtrl.assignRoutines(
					c2,
					rts);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		
	}
	
	@Test
	public final void testAssignRoutinesNull() {
		Customer c = CustomerCtrl.buildCustomer(
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
		CustomerCtrl.addCustomer(c);
		assertTrue(CustomerCtrl.customersExist());
		
		assertEquals(0, CustomerCtrl.getCustomers().get(0).getRoutines().size());
		try {
			CustomerCtrl.assignRoutines(
					CustomerCtrl.getCustomers().get(0),
					null);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(0, CustomerCtrl.getCustomers().get(0).getRoutines().size());
		
		ArrayList<Routine> rts = new ArrayList<Routine>();
		rts.add(routine);
		
		try {
			CustomerCtrl.assignRoutines(
					null,
					rts);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(0, CustomerCtrl.getCustomers().get(0).getRoutines().size());
		
	}

}
