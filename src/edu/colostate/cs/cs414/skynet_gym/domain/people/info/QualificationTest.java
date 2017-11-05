package edu.colostate.cs.cs414.skynet_gym.domain.people.info;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QualificationTest {
	
	private Qualification qual;
	private final String name = "name";
	private final String desc = "Descrip";

	@Before
	public void setUp() throws Exception {
		qual = new Qualification(
				name,
				desc);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testQualification() {
		Qualification q = new Qualification(
				name,
				desc);
		assertTrue(q.getName().equals(name));
		assertTrue(q.getDescription().equals(desc));
	}

	@Test
	public final void testEqualsObject() {
		Qualification q = new Qualification(
				name,
				desc);
		assertTrue(q.equals(qual));
		assertFalse(q.equals(null));
	}

	@Test
	public final void testGetDescription() {
		assertTrue(qual.getDescription().equals(desc));
	}

	@Test
	public final void testSetDescription() {
		qual.setDescription("d2");
		assertTrue(qual.getDescription().equals("d2"));
	}

	@Test
	public final void testGetName() {
		assertTrue(qual.getName().equals(name));
	}

	@Test
	public final void testSetName() {
		qual.setName("n2");
		assertTrue(qual.getName().equals("n2"));
	}

}
