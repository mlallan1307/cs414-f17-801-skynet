package edu.colostate.cs.cs414.skynet_gym.domain.data.people;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This contains a set of TimePeriods
 * 
 * @author Mike Allan
 *
 */
public class Schedule implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1591249830908911480L;
	private ArrayList<TimePeriod> periods = new ArrayList<TimePeriod>();

	public Schedule() {
	}
	
	@Override
	public boolean equals(Object o){
		try {
			return (this.toString().equals(Schedule.class.cast(o).toString()));
		} catch (java.lang.ClassCastException e) {
			return false;
		}
	}

	@Override
	public String toString() {
		String s = "";
		for (String pString : asStringList()) {
			s += "[" + pString + "]";
		}
		return s;
	}
	
	public ArrayList<String> asStringList() {
		ArrayList<String> rtn = new ArrayList<String>();
		for (TimePeriod tp : periods) {
			rtn.add(tp.toString());
		}
		return rtn;
	}
	

	/**
	 * @return the periods
	 */
	public ArrayList<TimePeriod> getPeriods() {
		return periods;
	}

	/**
	 * @param periods the periods to set
	 * @throws NullPointerException if periods is null
	 */
	public void setPeriods(ArrayList<TimePeriod> periods) {
		if (periods == null) {
			throw new NullPointerException("Object is null");
		}
		this.periods = periods;
	}
	
	/**
	 * @param period the period to add
	 * @throws NullPointerException if period is null
	 */
	public void addPeriod(TimePeriod period) {
		if (period == null) {
			throw new NullPointerException("Object is null");
		}
		this.periods.add(period);
	}
	
	/**
	 * @param index the index to remove
	 */
	public void removePeriod(int index) {
		if (index >= this.periods.size() ||
				index < 0) {
			return;
		}
		this.periods.remove(index);
	}

	public void clear() {
		this.periods.clear();
		
	}
	
	public Boolean isEmpty() {
		return this.periods.isEmpty();
	}
	
	/*
	private Boolean causesOverlap(TimePeriod newTp){
		for (TimePeriod tp: this.periods) {
			if (tp.causesOverlap(newTp)) {
				return true;
			}
		}
		return false;
	}
	*/
	
}
