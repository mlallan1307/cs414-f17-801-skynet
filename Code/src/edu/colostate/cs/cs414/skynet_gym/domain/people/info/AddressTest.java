package edu.colostate.cs.cs414.skynet_gym.domain.people.info;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddressTest {
	
	private final String s1    = "s1";
	private final String s2    = "s1";
	private final String state = "state";
	private final String city  = "city";
	private final String zip   = "zip";
	private final String type  = "type";
	private final String repl  = "newSt";
	private Address address;

	@Before
	public void setUp() throws Exception {
		address = new Address(
				s1,
				s2,
				state,
				city,
				zip,
				type);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testAddress() {
		Address a = new Address(
				s1,
				s2,
				state,
				city,
				zip,
				type);
		assertTrue(a.getStreet1().equals(s1));
	}

	@Test
	public final void testEqualsObject() {
		Address a = new Address(
				s1,
				s2,
				state,
				city,
				zip,
				type);
		assertTrue(a.equals(address));
		assertFalse(a.equals(null));
	}

	@Test
	public final void testSetAddress() {
		Address a = new Address(
				s1,
				s2,
				state,
				city,
				zip,
				type);
		a.setAddress(
				"s",
				"",
				"co",
				"denver",
				"zp",
				"tp");
		assertTrue(a.equals(new Address(
				"s",
				"",
				"co",
				"denver",
				"zp",
				"tp")));
	}

	@Test
	public final void testGetStreet1() {
		assertTrue(address.getStreet1().equals(s1));
	}

	@Test
	public final void testSetStreet1() {
		assertTrue(address.getStreet1().equals(s1));
		address.setStreet1(repl);
		assertTrue(address.getStreet1().equals(repl));
	}

	@Test
	public final void testGetStreet2() {
		assertTrue(address.getStreet2().equals(s2));
	}

	@Test
	public final void testSetStreet2() {
		assertTrue(address.getStreet2().equals(s2));
		address.setStreet2(repl);
		assertTrue(address.getStreet2().equals(repl));
	}

	@Test
	public final void testGetProvOrState() {
		assertTrue(address.getProvOrState().equals(state));
	}

	@Test
	public final void testSetProvOrState() {
		assertTrue(address.getProvOrState().equals(state));
		address.setProvOrState(repl);
		assertTrue(address.getProvOrState().equals(repl));
	}

	@Test
	public final void testGetCity() {
		assertTrue(address.getCity().equals(city));
	}

	@Test
	public final void testSetCity() {
		assertTrue(address.getCity().equals(city));
		address.setCity(repl);
		assertTrue(address.getCity().equals(repl));
	}

	@Test
	public final void testGetZipCode() {
		assertTrue(address.getZipCode().equals(zip));
	}

	@Test
	public final void testSetZipCode() {
		assertTrue(address.getZipCode().equals(zip));
		address.setZipCode(repl);
		assertTrue(address.getZipCode().equals(repl));
	}

	@Test
	public final void testGetType() {
		assertTrue(address.getType().equals(type));
	}

	@Test
	public final void testSetType() {
		assertTrue(address.getType().equals(type));
		address.setType(repl);
		assertTrue(address.getType().equals(repl));
	}

}
