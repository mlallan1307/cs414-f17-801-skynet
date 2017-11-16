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
		EquipmentCtrl.createEquipment(
				name,
				quantity,
				picture);
		assertTrue(EquipmentCtrl.equipmentExists());
	}

	@Test
	public final void testInitialize() {
		assertFalse(EquipmentCtrl.equipmentExists());
		// file does not exist so this is tries to load but fails
		EquipmentCtrl.initialize();
		assertFalse(EquipmentCtrl.equipmentExists());
		EquipmentCtrl.createEquipment(
				name,
				quantity,
				picture);
		assertTrue(EquipmentCtrl.equipmentExists());
		// equipment exists so this returns
		EquipmentCtrl.initialize();
		assertTrue(EquipmentCtrl.equipmentExists());
	}

	@Test
	public final void testCreateEquipment() {
		assertFalse(EquipmentCtrl.equipmentExists());
		EquipmentCtrl.createEquipment(
				name,
				quantity,
				picture);
		assertTrue(EquipmentCtrl.equipmentExists());
	}
	
	@Test
	public final void testCreateEquipmentDuplicateCheck() {
		EquipmentCtrl.createEquipment(
				name,
				quantity,
				picture);
		assertEquals(1, EquipmentCtrl.asStringList().size());
		
		try {
			// duplicated based on name
			EquipmentCtrl.createEquipment(
					name,
					1,
					picture);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, EquipmentCtrl.asStringList().size());
	}
	
	@Test
	public final void testCreateEquipmentEmptyFields() {
		
		try {
			// empty field test
			EquipmentCtrl.createEquipment(
					"",
					1,
					picture);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(0, EquipmentCtrl.asStringList().size());
		
		try {
			// default negative
			EquipmentCtrl.createEquipment(
					name,
					-1,
					picture);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(0, EquipmentCtrl.asStringList().size());
		
		try {
			// file non-existant
			EquipmentCtrl.createEquipment(
					name,
					quantity,
					new File("doesNotExist"));
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(0, EquipmentCtrl.asStringList().size());
		
	}
	
	@Test
	public final void testCreateEquipmentNull() {
		assertEquals(0, EquipmentCtrl.asStringList().size());
		try {
			// file null
			EquipmentCtrl.createEquipment(
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
		Equipment eq = new Equipment(
				name,
				quantity,
				picture);
		EquipmentCtrl.createEquipment(
				name,
				quantity,
				picture);
		assertTrue(EquipmentCtrl.equipmentExists());
		
		EquipmentCtrl.replaceEquipment(
				"newName",
				quantity,
				picture,
				eq);
		
		assertEquals("newName",
				EquipmentCtrl.getEquipment()
						.get(0)
						.getName());
		
		EquipmentCtrl.replaceEquipment(
				"newName",
				quantity+1,
				picture,
				EquipmentCtrl.getEquipment().get(0));
		
		assertEquals(quantity+1,
				EquipmentCtrl.getEquipment()
						.get(0)
						.getQuantity());
	}
	
	@Test
	public final void testReplaceEquipmentNull() {
		try {
			EquipmentCtrl.replaceEquipment(
				name,
				quantity,
				picture,
				null);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public final void testReplaceEquipmentEmptyFields() {
		
		Equipment eq = new Equipment(
				name,
				quantity,
				picture);
		EquipmentCtrl.createEquipment(
				name,
				quantity,
				picture);
		assertTrue(EquipmentCtrl.equipmentExists());
		
		try {
			// empty field test
			EquipmentCtrl.replaceEquipment(
					"",
					quantity,
					picture,
					eq);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, EquipmentCtrl.asStringList().size());
		
		try {
			// empty field test
			EquipmentCtrl.replaceEquipment(
					name,
					-1,
					picture,
					eq);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, EquipmentCtrl.asStringList().size());
		
		try {
			// empty field test
			EquipmentCtrl.replaceEquipment(
					name,
					quantity,
					new File("doesNotExist"),
					eq);
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		assertEquals(1, EquipmentCtrl.asStringList().size());
		
	}
	
	@Test
	public final void testReplaceEquipmentNameDuplicate() {
		
		assertFalse(EquipmentCtrl.equipmentExists());
		EquipmentCtrl.createEquipment(
				name,
				quantity,
				picture);
		assertTrue(EquipmentCtrl.equipmentExists());
		assertEquals(1, EquipmentCtrl.getEquipment().size());
		
		EquipmentCtrl.createEquipment(
				"nameDup",
				quantity,
				picture);
		assertEquals(2, EquipmentCtrl.getEquipment().size());
		
		try {
			EquipmentCtrl.replaceEquipment(
					"nameDup",
					quantity,
					picture,
					EquipmentCtrl.getEquipment().get(0));
			fail("Expected to throw");
		} catch (IllegalArgumentException e) {
		}
		
	}

	@Test
	public final void testExistsWithName() {
		assertFalse(EquipmentCtrl.equipmentExists());
		EquipmentCtrl.createEquipment(
				name,
				quantity,
				picture);
		assertTrue(EquipmentCtrl.equipmentExists());
		assertEquals(1, EquipmentCtrl.getEquipment().size());
		assertTrue(EquipmentCtrl.existsWithName(name));
		assertFalse(EquipmentCtrl.existsWithName("new"));
	}

	@Test
	public final void testSearchEquipment() {
		assertFalse(EquipmentCtrl.equipmentExists());
		EquipmentCtrl.createEquipment(
				"aab",
				1,
				picture);
		assertTrue(EquipmentCtrl.equipmentExists());
		assertEquals(1, EquipmentCtrl.getEquipment().size());
		
		EquipmentCtrl.createEquipment(
				"abc",
				2,
				picture);
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
