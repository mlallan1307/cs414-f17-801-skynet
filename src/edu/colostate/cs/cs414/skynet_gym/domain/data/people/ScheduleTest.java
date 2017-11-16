/**
 * 
 */
package edu.colostate.cs.cs414.skynet_gym.domain.data.people;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.DayOfWeek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Skynet
 *
 */
public class ScheduleTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link edu.colostate.cs.cs414.skynet_gym.domain.data.people.Schedule#Schedule()}.
	 */
	@Test
	public final void testSchedule() {
		Schedule s = new Schedule();
		{
			TimePeriod tp = new TimePeriod(
					DayOfWeek.MONDAY,
					"08:00",
					DayOfWeek.MONDAY,
					"16:00");
			try {
				s.addPeriod(tp);
			} catch (Exception e) {
				fail("Unexpected throw: " + e);
			}
			
			assertEquals(1, s.asStringList().size());
			assertTrue(tp.toString().equals(s.asStringList().get(0)));
			
		}
		
		{
			TimePeriod tp = new TimePeriod(
					DayOfWeek.TUESDAY,
					"08:00",
					DayOfWeek.TUESDAY,
					"16:00");
			try {
				s.addPeriod(tp);
			} catch (Exception e) {
				fail("Unexpected throw: " + e);
			}
			
			assertEquals(2, s.asStringList().size());
			assertTrue(tp.toString().equals(s.asStringList().get(1)));
			
		}
		
	}

	@Test
	public final void testRemovePeriod() {
		Schedule s = new Schedule();
		{
			TimePeriod tp = new TimePeriod(
					DayOfWeek.MONDAY,
					"08:00",
					DayOfWeek.MONDAY,
					"16:00");
			try {
				s.addPeriod(tp);
			} catch (Exception e) {
				fail("Unexpected throw: " + e);
			}
			
			assertEquals(1, s.asStringList().size());
			assertTrue(tp.toString().equals(s.asStringList().get(0)));
			
		}
		
		{
			TimePeriod tp = new TimePeriod(
					DayOfWeek.TUESDAY,
					"08:00",
					DayOfWeek.TUESDAY,
					"16:00");
			try {
				s.addPeriod(tp);
			} catch (Exception e) {
				fail("Unexpected throw: " + e);
			}
			
			assertEquals(2, s.asStringList().size());
			assertTrue(tp.toString().equals(s.asStringList().get(1)));
			
			// remove first period, validate size, and that one is left
			s.removePeriod(0);
			assertEquals(1, s.asStringList().size());
			assertTrue(tp.toString().equals(s.asStringList().get(0)));
			
			// remove first period, validate size, and that none are left
			s.removePeriod(0);
			assertEquals(0, s.asStringList().size());
			assertTrue(s.isEmpty());
			
			// Invalid calls, no throw
			s.removePeriod(3);
			s.removePeriod(-2);
			
		}
		
	}
	
	
	@Test
	public final void testClear() {
		Schedule s = new Schedule();
		{
			TimePeriod tp = new TimePeriod(
					DayOfWeek.MONDAY,
					"08:00",
					DayOfWeek.MONDAY,
					"16:00");
			try {
				s.addPeriod(tp);
			} catch (Exception e) {
				fail("Unexpected throw: " + e);
			}
			
		}
		
		{
			TimePeriod tp = new TimePeriod(
					DayOfWeek.TUESDAY,
					"08:00",
					DayOfWeek.TUESDAY,
					"16:00");
			try {
				s.addPeriod(tp);
			} catch (Exception e) {
				fail("Unexpected throw: " + e);
			}
		}
		
		assertEquals(2, s.asStringList().size());
		s.clear();
		assertEquals(0, s.asStringList().size());
		assertTrue(s.isEmpty());
		
		{
			TimePeriod tp = new TimePeriod(
					DayOfWeek.MONDAY,
					"08:00",
					DayOfWeek.MONDAY,
					"16:00");
			try {
				s.addPeriod(tp);
			} catch (Exception e) {
				fail("Unexpected throw: " + e);
			}
		}
		
		assertEquals(1, s.asStringList().size());
		s.clear();
		assertEquals(0, s.asStringList().size());
		assertTrue(s.isEmpty());
	}
	
	@Test
	public final void testNullPeriod() {
		
		Schedule s = new Schedule();
		try {
			s.addPeriod(null);
			fail("Expected throw");
		} catch (NullPointerException e) {
		}
		
		try {
			s.setPeriods(null);
			fail("Expected throw");
		} catch (NullPointerException e) {
		}
		
	}

}
