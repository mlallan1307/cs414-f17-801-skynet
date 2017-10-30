package edu.colostate.cs.cs414.skynet_gym.domain.data.equipment;

import java.io.File;

/**
 * Equipment represents a piece of equipment in the gym and contains the
 * relevant information.
 * 
 * @author Mike Allan
 *
 */
public class Equipment {
	
	private String name;
	private int quantity;
	private File picture;
	
	public Equipment(String name,
			int quantity,
			File picture){
		this.setName(name);
		this.setQuantity(quantity);
		this.setPicture(picture);
	}
	
	@Override
	public String toString() {
		return (this.name + ":" +
				String.valueOf(this.quantity) + ":" +
				this.picture.getName());
	}
	
	@Override
	public boolean equals(Object o){
		try {
			return (this.toString().equals(Equipment.class.cast(o).toString()));
		} catch (java.lang.ClassCastException e) {
			return false;
		} catch (java.lang.NullPointerException e) {
			return false;
		}
	}

	/**
	 * @return the name
	 */
	protected String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the quantity
	 */
	protected int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	protected void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the picture
	 */
	protected File getPicture() {
		return picture;
	}

	/**
	 * @param picture the picture to set
	 * @throws IllegalArgumentException if file does not exist
	 * @throws NullPointerException if param is null
	 */
	protected void setPicture(File picture) {
		if (picture == null){
			throw new NullPointerException("param can not be null");
		}
		if (!picture.exists()) {
			throw new IllegalArgumentException("File does not exist");
		}
		this.picture = picture;
	}
}
