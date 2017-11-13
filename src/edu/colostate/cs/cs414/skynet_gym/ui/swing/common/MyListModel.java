package edu.colostate.cs.cs414.skynet_gym.ui.swing.common;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

/**
 * Overriding AbstractListModel to implement an addValue method 
 * 
 * @author Mike Allan
 *
 */
public class MyListModel extends AbstractListModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1833404236344643942L;
	ArrayList<String> values;
	
	public MyListModel(ArrayList<String> al){
		values = al;
	}
	
	public int getSize() {
		return values.size();
	}
	public Object getElementAt(int index) {
		return values.get(index);
	}
	public void addValue(String v) {
		values.add(v);
	}
};

