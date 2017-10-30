package edu.colostate.cs.cs414.skynet_gym.domain.data.equipment;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EquipmentTest {

	private final String name = "bar";
	private final int quan = 1;
	private final File pic = new File("EquipmentTestFile_delete_me");
	private Equipment eq;
	
	@Before
	public void setUp() throws Exception {
		pic.createNewFile();
		eq = new Equipment(
				name,
				quan,
				pic);
	}

	@After
	public void tearDown() throws Exception {
		assertTrue(pic.delete());
	}

	@Test
	public final void testEquipment() {
		assertTrue(eq.getName().equals(name));
	}

	@Test
	public final void testEqualsObject() {
		Equipment eq2 = new Equipment(
				name,
				quan,
				pic);
		assertTrue(eq.equals(eq2));
		assertFalse(eq.equals(null));
	}

	@Test
	public final void testGetName() {
		assertTrue(eq.getName().equals(name));
	}

	@Test
	public final void testSetName() {
		assertTrue(eq.getName().equals(name));
		eq.setName("new");
		assertTrue(eq.getName().equals("new"));
	}

	@Test
	public final void testGetQuantity() {
		assertTrue(eq.getQuantity() == quan);
	}

	@Test
	public final void testSetQuantity() {
		assertTrue(eq.getQuantity() == quan);
		eq.setQuantity(2);
		assertTrue(eq.getQuantity() == 2);
	}

	@Test
	public final void testGetPicture() {
		assertTrue(eq.getPicture().equals(pic));
	}

	@Test
	public final void testSetPicture() {
		assertTrue(eq.getPicture().equals(pic));
		File pic2 = new File("EquipmentTestFile_delete_me");
		eq.setPicture(pic2);
		assertTrue(eq.getPicture().equals(pic2));
		
		try {
			eq.setPicture(null);
			fail("Expected to throw");
		} catch (NullPointerException e) {
		}
		
		try {
			eq.setPicture(new File("noFile"));
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
	}

}
