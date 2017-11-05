package edu.colostate.cs.cs414.skynet_gym.utility.data;

import java.io.Serializable;
import java.time.DayOfWeek;

/**
 * This identifies a time period
 * 
 * @author Mike Allan
 *
 */
public class TimePeriod implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7707955412844859799L;
	private DayOfWeek startDay;
	private String startTime;
	private DayOfWeek stopDay;
	private String stopTime;
	/**
	 * @param startDay
	 * @param startTime
	 * @param stopDay
	 * @param stopTime
	 * @throws IllegalArgumentException
	 */
	public TimePeriod(
			DayOfWeek startDay,
			String startTime,
			DayOfWeek stopDay,
			String stopTime) {
		
		// If invalid, this will throw
		validPeriod(startDay, startTime, stopDay, stopTime);
		
		this.startDay  = startDay;
		this.startTime = startTime;
		this.stopDay   = stopDay;
		this.stopTime  = stopTime;
	}

	@Override
	public boolean equals(Object o){
		try {
			return (this.toString().equals(TimePeriod.class.cast(o).toString()));
		} catch (java.lang.ClassCastException e) {
			return false;
		}
	}

	@Override
	public String toString() {
		return (startDay + "@" + startTime + " to " +
				stopDay + "@" + stopTime);
	}

	/**
	 * @return the startDay
	 */
	public DayOfWeek getStartDay() {
		return startDay;
	}
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @return the startTime hour
	 */
	public int getStartTimeHour() {
		return getHour(startTime);
	}
	/**
	 * @return the startTime minute
	 */
	public int getStartTimeMin() {
		return getMinute(startTime);
	}
	
	/**
	 * @return the stopDay
	 */
	public DayOfWeek getStopDay() {
		return stopDay;
	}
	/**
	 * @return the stopTime
	 */
	public String getStopTime() {
		return stopTime;
	}
	/**
	 * @return the stopTime hour
	 */
	public int getStopTimeHour() {
		return getHour(stopTime);
	}
	/**
	 * @return the stopTime minute
	 */
	public int getStopTimeMin() {
		return getMinute(stopTime);
	}
	
	
	// SETTERS
	
	/**
	 * @param startDay the startDay to set
	 */
	public void setStartDay(DayOfWeek startDay) {
		// If invalid, this will throw
		validPeriod(startDay, this.startTime, this.stopDay, this.stopTime);
		this.startDay = startDay;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		// If invalid, this will throw
		validPeriod(this.startDay, startTime, this.stopDay, this.stopTime);
		this.startTime = startTime;
	}
	/**
	 * @param stopDay the stopDay to set
	 */
	public void setStopDay(DayOfWeek stopDay) {
		// If invalid, this will throw
		validPeriod(this.startDay, this.startTime, stopDay, this.stopTime);
		this.stopDay = stopDay;
	}
	/**
	 * @param stopTime the stopTime to set
	 */
	public void setStopTime(String stopTime) {
		// If invalid, this will throw
		validPeriod(this.startDay, this.startTime, this.stopDay, stopTime);
		this.stopTime = stopTime;
	}
	
	private int getHour(String h){
		return digitStringGetValue(h, 0);
	}
	
	private int getMinute(String m){
		return digitStringGetValue(m, 1);
	}
	
	private int digitStringGetValue(String str, int index) {
		String [] s = str.split(":");
		if (s.length <= index) {
			return -1;
		}
		try {
			return Integer.parseInt(s[index]);
		} catch (java.lang.NumberFormatException e) {
			return -1;
		}
	}
	
	private Boolean validTime(String t) {
		int hour = getHour(t);
		int minute = getMinute(t);

		if (hour < 0 ||
				hour > 23) {
			return false;
		}
		if (minute < 0 ||
				minute > 59) {
			return false;
		}
		return true;
	}
	
	private Boolean startB4Stop(String start, String stop) {
		int startHour = getHour(start);
		int startMin  = getMinute(start);
		int stopHour  = getHour(stop);
		int stopMin   = getMinute(stop);
		
		if (startHour > stopHour) {
			// start hour after stop hour
			return false;
		}
		if (startHour == stopHour) {
			// stop and stop in same hour
			if (startMin > stopMin) {
				// Start after stop
				return false;
			}
			if (startMin == stopMin) {
				// Start and stop at same time
				return false;
			}
		}
		return true;
	}
	
	private void validPeriod(
			DayOfWeek startDay,
			String startTime,
			DayOfWeek stopDay,
			String stopTime){
		
		if (!validTime(startTime)) {
			throw new IllegalArgumentException("Invalid start time");
		}
		if (!validTime(stopTime)) {
			throw new IllegalArgumentException("Invalid stop time");
		}
		
		int compDays = stopDay.compareTo(startDay);
		
		if (compDays == 0) {
			// Same day
			if (!startB4Stop(startTime, stopTime)) {
				throw new IllegalArgumentException("Stop time is before start time");
			}
		}
		else if (compDays < 0){
			if (!(startDay.equals(DayOfWeek.SUNDAY) &&
					stopDay.equals(DayOfWeek.MONDAY))) {
				// Days span more than one day
				throw new IllegalArgumentException("Period can't span more than 1 day");
			}
		}
		else if (compDays > 1) {
			// negative compare, stop day before start day
			
			throw new IllegalArgumentException("Period can't span more than 1 day");
		}
	}

	public boolean causesOverlap(TimePeriod newTp) {
		return true;
	}

}
