package edu.colostate.cs.cs414.skynet_gym.domain.data.people;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HealthInsuranceTest {
	
	private final String name = "hi name";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testHealthInsurance() {
		HealthInsurance h = new HealthInsurance(name);
		assertTrue(h.getProviderName().equals(name));
	}

	@Test
	public final void testEqualsObject() {
		HealthInsurance h = new HealthInsurance(name);
		HealthInsurance h2 = new HealthInsurance(name);
		assertTrue(h.equals(h2));
		assertFalse(h.equals(null));
	}

	@Test
	public final void testGetProviderName() {
		HealthInsurance h = new HealthInsurance(name);
		assertTrue(h.getProviderName().equals(name));
	}

	@Test
	public final void testSetProviderName() {
		HealthInsurance h = new HealthInsurance(name);
		assertTrue(h.getProviderName().equals(name));
		h.setProviderName("n2");
		assertTrue(h.getProviderName().equals("n2"));
	}

}
