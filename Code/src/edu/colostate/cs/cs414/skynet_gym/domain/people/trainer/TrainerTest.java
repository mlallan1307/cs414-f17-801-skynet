/**
 * 
 */
package edu.colostate.cs.cs414.skynet_gym.domain.people.trainer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.DayOfWeek;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.colostate.cs.cs414.skynet_gym.domain.people.info.Address;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.HealthInsurance;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.PersonInformation;
import edu.colostate.cs.cs414.skynet_gym.domain.people.info.Qualification;
import edu.colostate.cs.cs414.skynet_gym.utility.data.Schedule;
import edu.colostate.cs.cs414.skynet_gym.utility.data.TimePeriod;

/**
 * @author Skynet
 *
 */
public class TrainerTest {

	private Schedule schedule;
	private ArrayList<Qualification> qualifications;
	private Qualification qualification;
	private PersonInformation personInfo;
	private Trainer trainer;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		schedule = new Schedule();
		qualifications = new ArrayList<Qualification>();
		qualification = new Qualification("n", "d");
		personInfo = new PersonInformation(
				"fn",
				"ln",
				"dln",
				"ph",
				"em",
				new HealthInsurance(
						"p"),
				new Address(
						"s1",
						"s2",
						"st",
						"ct",
						"zp",
						"tp"));
		trainer = new Trainer(
				"u",
				"p",
				personInfo,
				schedule,
				qualifications);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link edu.colostate.cs.cs414.skynet_gym.domain.people.trainer.Trainer#Trainer(java.lang.String, java.lang.String, edu.colostate.cs.cs414.skynet_gym.domain.people.info.PersonInformation, edu.colostate.cs.cs414.skynet_gym.utility.data.Schedule, java.util.ArrayList)}.
	 */
	@Test
	public final void testTrainer() {
		Trainer t = new Trainer(
				"u",
				"p",
				personInfo,
				schedule,
				qualifications);
		
		assertTrue(t.getUserType().equals("Trainer"));
	}
	
	@Test
	public final void testEqualsObject() {
		Trainer t = new Trainer(
				"u",
				"p",
				personInfo,
				schedule,
				qualifications);
		assertTrue(t.equals(trainer));
		assertFalse(t.equals(null));
	}

	/**
	 * Test method for {@link edu.colostate.cs.cs414.skynet_gym.domain.people.trainer.Trainer#addQualification(edu.colostate.cs.cs414.skynet_gym.domain.people.info.Qualification)}.
	 */
	@Test
	public final void testAddQualificationQualification() {
		assertEquals(0, trainer.getQualifications().size());
		trainer.addQualification(qualification);
		assertEquals(1, trainer.getQualifications().size());
		
		try {
			trainer.addQualification((Qualification) null);
			fail("Expected to throw");
		} catch(NullPointerException e) {
		}
	}

	/**
	 * Test method for {@link edu.colostate.cs.cs414.skynet_gym.domain.people.trainer.Trainer#removeQualification(edu.colostate.cs.cs414.skynet_gym.domain.people.info.Qualification)}.
	 */
	@Test
	public final void testRemoveQualificationQualification() {
		assertEquals(0, trainer.getQualifications().size());
		trainer.addQualification(qualification);
		assertEquals(1, trainer.getQualifications().size());
		trainer.removeQualification(qualification);
		assertEquals(0, trainer.getQualifications().size());
		
		try {
			trainer.removeQualification((Qualification) null);
			fail("Expected to throw");
		} catch(NullPointerException e) {
		}
	}

	/**
	 * Test method for {@link edu.colostate.cs.cs414.skynet_gym.domain.people.trainer.Trainer#addQualification(java.util.ArrayList)}.
	 */
	@Test
	public final void testAddQualificationArrayListOfQualification() {
		assertEquals(0, trainer.getQualifications().size());
		ArrayList<Qualification> q = new ArrayList<Qualification>();
		q.add(qualification);
		q.add(qualification);
		trainer.addQualification(q);
		assertEquals(2, trainer.getQualifications().size());
		
		try {
			trainer.addQualification((ArrayList<Qualification>) null);
			fail("Expected to throw");
		} catch(NullPointerException e) {
		}
	}

	/**
	 * Test method for {@link edu.colostate.cs.cs414.skynet_gym.domain.people.trainer.Trainer#removeQualification(java.util.ArrayList)}.
	 */
	@Test
	public final void testRemoveQualificationArrayListOfQualification() {
		assertEquals(0, trainer.getQualifications().size());
		ArrayList<Qualification> q = new ArrayList<Qualification>();
		q.add(qualification);
		q.add(new Qualification("q", "w"));
		trainer.addQualification(q);
		assertEquals(2, trainer.getQualifications().size());
		q.remove(qualification);
		trainer.removeQualification(q);
		assertEquals(1, trainer.getQualifications().size());
		
		try {
			trainer.removeQualification((ArrayList<Qualification>) null);
			fail("Expected to throw");
		} catch(NullPointerException e) {
		}
	}

	/**
	 * Test method for {@link edu.colostate.cs.cs414.skynet_gym.domain.people.trainer.Trainer#clearQualification()}.
	 */
	@Test
	public final void testClearQualification() {
		trainer.addQualification(qualification);
		trainer.addQualification(qualification);
		assertEquals(2, trainer.getQualifications().size());
		trainer.clearQualification();
		assertEquals(0, trainer.getQualifications().size());
	}

	/**
	 * Test method for {@link edu.colostate.cs.cs414.skynet_gym.domain.people.trainer.Trainer#getSchedule()}.
	 */
	@Test
	public final void testGetSchedule() {
		assertTrue(trainer.getSchedule().equals(schedule));
	}

	/**
	 * Test method for {@link edu.colostate.cs.cs414.skynet_gym.domain.people.trainer.Trainer#setSchedule(edu.colostate.cs.cs414.skynet_gym.utility.data.Schedule)}.
	 */
	@Test
	public final void testSetSchedule() {
		Schedule s = new Schedule();
		s.addPeriod(new TimePeriod(
				DayOfWeek.MONDAY,
				"08:00",
				DayOfWeek.MONDAY,
				"12:00"));
		trainer.setSchedule(s);
		assertTrue(trainer.getSchedule().equals(s));
		
		try {
			trainer.setSchedule(null);
			fail("Expected to throw");
		} catch(NullPointerException e) {
		}
	}

	/**
	 * Test method for {@link edu.colostate.cs.cs414.skynet_gym.domain.people.trainer.Trainer#getQualifications()}.
	 */
	@Test
	public final void testGetQualifications() {
		assertTrue(trainer.getQualifications().equals(qualifications));
	}

	/**
	 * Test method for {@link edu.colostate.cs.cs414.skynet_gym.domain.people.trainer.Trainer#setQualifications(java.util.ArrayList)}.
	 */
	@Test
	public final void testSetQualifications() {
		ArrayList<Qualification> q = new ArrayList<Qualification>();
		q.add(qualification);
		q.add(qualification);
		trainer.setQualifications(q);
		assertTrue(trainer.getQualifications().equals(q));
		
		try {
			trainer.setQualifications(null);
			fail("Expected to throw");
		} catch(NullPointerException e) {
		}
	}

}
