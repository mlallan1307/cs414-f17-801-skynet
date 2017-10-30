package edu.colostate.cs.cs414.skynet_gym.utility.data;

import static org.junit.Assert.fail;

import java.time.DayOfWeek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TimePeriodTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testTimePeriodValid() {
		try {
			// Standard day
			new TimePeriod(
					DayOfWeek.MONDAY,
					"08:00",
					DayOfWeek.MONDAY,
					"16:00");
		} catch (Exception e) {
			fail("Unexpected throw: " + e);
		}
		
		try {
			// Full day
			new TimePeriod(
					DayOfWeek.SUNDAY,
					"00:00",
					DayOfWeek.SUNDAY,
					"23:59");
		} catch (Exception e) {
			fail("Unexpected throw: " + e);
		}
		
		try {
			// Same hour
			new TimePeriod(
					DayOfWeek.TUESDAY,
					"10:00",
					DayOfWeek.TUESDAY,
					"10:01");
		} catch (Exception e) {
			fail("Unexpected throw: " + e);
		}
		
		try {
			// Next hour, under a total hour
			new TimePeriod(
					DayOfWeek.TUESDAY,
					"10:27",
					DayOfWeek.TUESDAY,
					"11:11");
		} catch (Exception e) {
			fail("Unexpected throw: " + e);
		}
	}
	
	@Test
	public final void testTimePeriodSameDay() {
		try {
			// Stop before start, -h =m
			new TimePeriod(
					DayOfWeek.MONDAY,
					"10:00",
					DayOfWeek.MONDAY,
					"08:00");
			fail("Expected this to throw");
		} catch (Exception e) {
		}
		
		try {
			// Stop before start =h -m
			new TimePeriod(
					DayOfWeek.MONDAY,
					"08:01",
					DayOfWeek.MONDAY,
					"08:00");
			fail("Expected this to throw");
		} catch (Exception e) {
		}
		
		try {
			// Stop before start, +h -m
			new TimePeriod(
					DayOfWeek.MONDAY,
					"23:12",
					DayOfWeek.MONDAY,
					"22:37");
			fail("Expected this to throw");
		} catch (Exception e) {
		}
		try {
			// same time, =h =m
			new TimePeriod(
					DayOfWeek.MONDAY,
					"23:00",
					DayOfWeek.MONDAY,
					"23:00");
			fail("Expected this to throw");
		} catch (Exception e) {
		}
		try {
			// same time2, =h =m
			new TimePeriod(
					DayOfWeek.MONDAY,
					"02:37",
					DayOfWeek.MONDAY,
					"02:37");
			fail("Expected this to throw");
		} catch (Exception e) {
		}
		
		try {
			// invalid minutes
			new TimePeriod(
					DayOfWeek.MONDAY,
					"02:61",
					DayOfWeek.MONDAY,
					"02:31");
			fail("Expected this to throw");
		} catch (Exception e) {
		}
		try {
			// invalid minutes
			new TimePeriod(
					DayOfWeek.MONDAY,
					"02:30",
					DayOfWeek.MONDAY,
					"02:61");
			fail("Expected this to throw");
		} catch (Exception e) {
		}
		try {
			// invalid minutes
			new TimePeriod(
					DayOfWeek.MONDAY,
					"02:61",
					DayOfWeek.MONDAY,
					"02:61");
			fail("Expected this to throw");
		} catch (Exception e) {
		}
		
		try {
			// invalid minutes
			new TimePeriod(
					DayOfWeek.MONDAY,
					"02:-1",
					DayOfWeek.MONDAY,
					"02:31");
			fail("Expected this to throw");
		} catch (Exception e) {
		}
		
		try {
			// invalid hours
			new TimePeriod(
					DayOfWeek.MONDAY,
					"-1:03",
					DayOfWeek.MONDAY,
					"02:31");
			fail("Expected this to throw");
		} catch (Exception e) {
		}
		try {
			// invalid hours
			new TimePeriod(
					DayOfWeek.MONDAY,
					"24:03",
					DayOfWeek.MONDAY,
					"02:31");
			fail("Expected this to throw");
		} catch (Exception e) {
		}
		try {
			// invalid hours
			new TimePeriod(
					DayOfWeek.MONDAY,
					"03:03",
					DayOfWeek.MONDAY,
					"24:31");
			fail("Expected this to throw");
		} catch (Exception e) {
		}
		try {
			// invalid hours
			new TimePeriod(
					DayOfWeek.MONDAY,
					"25:03",
					DayOfWeek.MONDAY,
					"25:31");
			fail("Expected this to throw");
		} catch (Exception e) {
		}
	}
	
	@Test
	public final void testTimePeriodNextDay() {
		try {
			// valid next day
			new TimePeriod(
					DayOfWeek.MONDAY,
					"23:04",
					DayOfWeek.TUESDAY,
					"02:32");
			
		} catch (Exception e) {
			fail("Unexpected throw: " + e);
		}
		
		try {
			// valid next day
			new TimePeriod(
					DayOfWeek.MONDAY,
					"12:00",
					DayOfWeek.TUESDAY,
					"02:00");
			
		} catch (Exception e) {
			fail("Unexpected throw: " + e);
		}
		
		try {
			// valid next day
			new TimePeriod(
					DayOfWeek.MONDAY,
					"15:00",
					DayOfWeek.TUESDAY,
					"16:00");
			
		} catch (Exception e) {
			fail("Unexpected throw: " + e);
		}
		
		try {
			// valid next day
			new TimePeriod(
					DayOfWeek.FRIDAY,
					"12:00",
					DayOfWeek.SATURDAY,
					"02:00");
			
		} catch (Exception e) {
			fail("Unexpected throw: " + e);
		}
		
		try {
			// valid next day
			new TimePeriod(
					DayOfWeek.SATURDAY,
					"12:00",
					DayOfWeek.SUNDAY,
					"02:00");
			
		} catch (Exception e) {
			fail("Unexpected throw: " + e);
		}
		
		
		
		try {
			// invalid day span more than 1 day
			new TimePeriod(
					DayOfWeek.TUESDAY,
					"20:03",
					DayOfWeek.MONDAY,
					"20:31");
			fail("Expected this to throw");
		} catch (Exception e) {
		}
		
		
	}
	
	@Test
	public final void testTimePeriodWeekWrapNextDay() {
		try {
			// sunday to monday wrap
			new TimePeriod(
					DayOfWeek.SUNDAY,
					"12:00",
					DayOfWeek.MONDAY,
					"02:00");
			
		} catch (Exception e) {
			fail("Unexpected throw: " + e);
		}
		
		try {
			// monday to sunday period, illegal
			new TimePeriod(
					DayOfWeek.MONDAY,
					"20:03",
					DayOfWeek.SUNDAY,
					"20:31");
			fail("Expected this to throw");
		} catch (Exception e) {
		}
	}
	
	@Test
	public final void testTimePeriodMoreThanOneDay() {
		try {
			// invalid day span more than 1 day
			new TimePeriod(
					DayOfWeek.TUESDAY,
					"20:03",
					DayOfWeek.FRIDAY,
					"20:31");
			fail("Expected this to throw");
		} catch (Exception e) {
		}
		
		try {
			// invalid multi-day reverse
			new TimePeriod(
					DayOfWeek.THURSDAY,
					"20:03",
					DayOfWeek.TUESDAY,
					"20:31");
			fail("Expected this to throw");
		} catch (Exception e) {
		}
	}

}
