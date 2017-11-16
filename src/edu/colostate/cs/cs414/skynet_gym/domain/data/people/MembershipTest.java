package edu.colostate.cs.cs414.skynet_gym.domain.data.people;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MembershipTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testMembership() {
		Membership m = new Membership(true);
		assertTrue(m.isValid());
	}

	@Test
	public final void testEqualsObject() {
		Membership m = new Membership(true);
		Membership m2 = new Membership(true);
		
		assertTrue(m.equals(m2));
		assertFalse(m.equals(null));
	}

	@Test
	public final void testIsValid() {
		Membership mt = new Membership(true);
		Membership mf = new Membership(false);
		
		assertTrue(mt.isValid());
		assertFalse(mf.isValid());
	}

	@Test
	public final void testSetValid() {
		Membership m = new Membership(true);
		
		assertTrue(m.isValid());
		m.setValid(false);
		assertFalse(m.isValid());
		m.setValid(true);
		assertTrue(m.isValid());
	}

}
