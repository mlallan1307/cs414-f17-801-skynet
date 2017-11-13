package edu.colostate.cs.cs414.skynet_gym.ui.swing.trainer;

import java.time.Duration;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Equipment;

/**
 * This is an abstract class that implements common functionality related to exercises.
 * 
 * @author Mike Allan
 *
 */
public class ExerciseAbs extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7596873601460780054L;
	protected JTextField name;
	protected JSpinner dMinutes;
	protected JSpinner sSets;
	protected JSpinner dSeconds;
	protected JSpinner sReps;
	protected ButtonGroup exerciseButtonGroup;
	protected JRadioButton useSetBased;
	protected JRadioButton useTimeBased;
	protected JPanel selectEquipmentPanel;
	protected JTextField selectedEquipmentName;
	protected Equipment selectedEquipment;
	protected JButton btnSelectEquipment;
	public final int initalSetValue = 1;
	public final int initalTimeValue = 0;
	
	
	/**
	 * Create the panel.
	 */
	public ExerciseAbs(final JTabbedPane frame) {
		exerciseButtonGroup = new ButtonGroup();
	}
	
	protected void clearAll() {
		name.setText("");
		dMinutes.setValue(initalTimeValue);
		dSeconds.setValue(initalTimeValue);
		sSets.setValue(initalSetValue);
		sReps.setValue(initalSetValue);
		toggleType(false);
		setSelectedEquipment(null);
		selectEquipmentPanel.setVisible(false);
		btnSelectEquipment.setText("Select");
	}
	
	protected void commitEdits() {
		try {
			dMinutes.commitEdit();
			dSeconds.commitEdit();
			sSets.commitEdit();
			sReps.commitEdit();
		} catch ( java.text.ParseException e ) {}
	}
	
	protected Duration getDuration() {
		Integer minutes = Integer.parseInt(dMinutes.getValue().toString());
		Integer seconds = Integer.parseInt(dSeconds.getValue().toString());
		return Duration.ofSeconds((seconds+(minutes*60)));
	}
	
	protected int getSets() {
		return Integer.class.cast(sSets.getValue());
	}
	
	protected int getReps() {
		return Integer.class.cast(sReps.getValue());
	}
	
	protected void toggleType(Boolean setBased) {

		useSetBased.setSelected(setBased);
		useTimeBased.setSelected(!setBased);

		dMinutes.setEnabled(!setBased);
		dSeconds.setEnabled(!setBased);
		sSets.setEnabled(setBased);
		sReps.setEnabled(setBased);
		
	}
	
	protected void toggleEquipment() {
		if (selectEquipmentPanel.isVisible()) {
			selectEquipmentPanel.setVisible(false);
			btnSelectEquipment.setText("Select");
		} else {
			selectEquipmentPanel.setVisible(true);
			btnSelectEquipment.setText("Clear");
		}
	}
	
	/**
	 * @return the selectedEquipment
	 */
	protected Equipment getSelectedEquipment() {
		return selectedEquipment;
	}

	/**
	 * @param selectedEquipment the selectedEquipment to set
	 */
	protected void setSelectedEquipment(Equipment selectedEquipment) {
		this.selectedEquipment = selectedEquipment;
		if (selectedEquipment != null) {
			selectedEquipmentName.setText(selectedEquipment.getName());
			toggleEquipment();
		}
		else {
			selectedEquipmentName.setText("None");
		}
	}


}
