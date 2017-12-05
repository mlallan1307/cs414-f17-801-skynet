package edu.colostate.cs.cs414.skynet_gym.domain.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Equipment;

public class EquipmentCtrlTest {

	private final String name  = "eqName";
	private final int quantity = 2;
	private File picture;
	
	private final String testFileName = "EquipmentCtrlTestfile_delete_me";
	
	@Before
	public void setUp() throws Exception {
		
		picture = new File(testFileName + ".jpg");
		if (picture.exists()) {
			picture.delete();
		}
		picture.createNewFile();
		
		// Set test data to be in a different file than normal
		EquipmentCtrl.setSerializedName(testFileName);
		EquipmentCtrl.clearData();
	}

	@After
	public void tearDown() throws Exception {
		
		EquipmentCtrl.clearData();
		if (picture.exists()) {
			picture.delete();
		}
	}

	@Test
	public final void testEquipmentExist() {
		assertFalse(EquipmentCtrl.equipmentExists());
		Equipment eq = EquipmentCtrl.buildEquipment(
				name,
				quantity,
				picture);
		EquipmentCtrl.addEquipment(eq);
		assertTrue(EquipmentCtrl.equipmentExists());
	}

	@Test
	public final void testInitialize() {
		assertFalse(EquipmentCtrl.equipmentExists());
		// file does not exist so this is tries to load but fails
		EquipmentCtrl.initialize();
		assertFalse(EquipmentCtrl.equipmentExists());
		Equipment eq = EquipmentCtrl.buildEquipment(
				name,
				quantity,
				picture);
		EquipmentCtrl.addEquipment(eq);
		assertTrue(EquipmentCtrl.equipmentExists());
		// equipment exists so this returns
		EquipmentCtrl.initialize();
		assertTrue(EquipmentCtrl.equipmentExists());
	}

	@Test
	public final void testBuildEquipment() {
		assertFalse(EquipmentCtrl.equipmentExists());
		Equipment eq = EquipmentCtrl.buildEquipment(
				name,
				quantity,
				picture);
		EquipmentCtrl.addEquipment(eq);
		assertTrue(EquipmentCtrl.equipmentExists());
	}
	
	@Test
	public final void testAddEquipmentDuplicateCheck() {
		Equipment eq = EquipmentCtrl.buildEquipment(
				name,
				quantity,
				picture);
		EquipmentCtrl.addEquipment(eq);
		assertEquals(1, EquipmentCtrl.asStringList().size());
		
		try {
			// duplicated based on name
			Equipment eq2 = EquipmentCtrl.buildEquipment(
					name,
					1,
					picture);
			EquipmentCtrl.addEquipment(eq2);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, EquipmentCtrl.asStringList().size());
	}
	
	@Test
	public final void testBuildEquipmentEmptyFields() {
		
		try {
			// empty field test
			EquipmentCtrl.buildEquipment(
					"",
					1,
					picture);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(0, EquipmentCtrl.asStringList().size());
		
		try {
			// default negative
			EquipmentCtrl.buildEquipment(
					name,
					-1,
					picture);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(0, EquipmentCtrl.asStringList().size());
		
		try {
			// file non-existant
			EquipmentCtrl.buildEquipment(
					name,
					quantity,
					new File("doesNotExist"));
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(0, EquipmentCtrl.asStringList().size());
		
	}
	
	@Test
	public final void testBuildEquipmentNull() {
		assertEquals(0, EquipmentCtrl.asStringList().size());
		try {
			// file null
			EquipmentCtrl.buildEquipment(
					name,
					quantity,
					null);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(0, EquipmentCtrl.asStringList().size());
	}

	@Test
	public final void testReplaceEquipment() {
		assertFalse(EquipmentCtrl.equipmentExists());
		Equipment eq = EquipmentCtrl.buildEquipment(
				name,
				quantity,
				picture);
		EquipmentCtrl.addEquipment(eq);
		assertTrue(EquipmentCtrl.equipmentExists());
		
		Equipment eq2 = EquipmentCtrl.buildEquipment(
				"newName",
				quantity,
				picture);
		EquipmentCtrl.replaceEquipment(
				eq2,
				EquipmentCtrl.getEquipment().get(0));
		
		assertEquals("newName",
				EquipmentCtrl.getEquipment()
						.get(0)
						.getName());
		
		Equipment eq3 = EquipmentCtrl.buildEquipment(
				"newName",
				quantity+1,
				picture);
		EquipmentCtrl.replaceEquipment(
				eq3,
				EquipmentCtrl.getEquipment().get(0));
		
		assertEquals(quantity+1,
				EquipmentCtrl.getEquipment()
						.get(0)
						.getQuantity());
	}
	
	@Test
	public final void testReplaceEquipmentNull() {
		try {
			Equipment eq = EquipmentCtrl.buildEquipment(
					name,
					quantity,
					picture);
			EquipmentCtrl.replaceEquipment(
				eq,
				null);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public final void testReplaceEquipmentNameDuplicate() {
		
		assertFalse(EquipmentCtrl.equipmentExists());
		Equipment eq = EquipmentCtrl.buildEquipment(
				name,
				quantity,
				picture);
		EquipmentCtrl.addEquipment(eq);
		assertTrue(EquipmentCtrl.equipmentExists());
		assertEquals(1, EquipmentCtrl.getEquipment().size());
		
		Equipment eq2 = EquipmentCtrl.buildEquipment(
				"nameDup",
				quantity,
				picture);
		EquipmentCtrl.addEquipment(eq2);
		assertEquals(2, EquipmentCtrl.getEquipment().size());
		
		try {
			EquipmentCtrl.replaceEquipment(
					eq2,
					EquipmentCtrl.getEquipment().get(0));
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		
	}
	
	@Test
	public final void testRemoveEquipment() {
		assertFalse(EquipmentCtrl.equipmentExists());
		Equipment eq = EquipmentCtrl.buildEquipment(
				name,
				quantity,
				picture);
		EquipmentCtrl.addEquipment(eq);
		assertEquals(1, EquipmentCtrl.getEquipment().size());
		
		Equipment eq2 = EquipmentCtrl.buildEquipment(
				"new name",
				quantity,
				picture);
		EquipmentCtrl.addEquipment(eq2);
		assertEquals(2, EquipmentCtrl.getEquipment().size());
		
		EquipmentCtrl.removeEquipment(eq);
		assertEquals(1, EquipmentCtrl.getEquipment().size());
		assertTrue(EquipmentCtrl.getEquipment().get(0).equals(eq2));
		
		EquipmentCtrl.removeEquipment(eq2);
		assertEquals(0, EquipmentCtrl.getEquipment().size());
	}


	@Test
	public final void testExistsWithName() {
		assertFalse(EquipmentCtrl.equipmentExists());
		Equipment eq = EquipmentCtrl.buildEquipment(
				name,
				quantity,
				picture);
		EquipmentCtrl.addEquipment(eq);
		assertTrue(EquipmentCtrl.equipmentExists());
		assertEquals(1, EquipmentCtrl.getEquipment().size());
		assertTrue(EquipmentCtrl.existsWithName(name));
		assertFalse(EquipmentCtrl.existsWithName("new"));
	}

	@Test
	public final void testSearchEquipment() {
		assertFalse(EquipmentCtrl.equipmentExists());
		Equipment eq = EquipmentCtrl.buildEquipment(
				"aab",
				1,
				picture);
		EquipmentCtrl.addEquipment(eq);
		assertTrue(EquipmentCtrl.equipmentExists());
		assertEquals(1, EquipmentCtrl.getEquipment().size());
		
		Equipment eq2 = EquipmentCtrl.buildEquipment(
				"abc",
				2,
				picture);
		EquipmentCtrl.addEquipment(eq2);
		assertEquals(2, EquipmentCtrl.getEquipment().size());
		
		assertEquals(2, EquipmentCtrl.searchEquipment(
				"",
				-1).size());
		assertEquals(2, EquipmentCtrl.searchEquipment(
				"a",
				-1).size());
		assertEquals(1, EquipmentCtrl.searchEquipment(
				"aa",
				-1).size());
		assertEquals(0, EquipmentCtrl.searchEquipment(
				"",
				0).size());
		assertEquals(1, EquipmentCtrl.searchEquipment(
				"",
				1).size());
		assertEquals(1, EquipmentCtrl.searchEquipment(
				"",
				2).size());
		assertEquals(1, EquipmentCtrl.searchEquipment(
				"aa",
				1).size());

	}

}
