package edu.colostate.cs.cs414.skynet_gym.ui.swing.trainer;

import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Equipment;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.SelectEquipmentAbs;

/**
 * This panel is shown to select equipment for an exercise.
 * 
 * @author Mike Allan
 *
 */
public class SelectEquipmentExercise extends SelectEquipmentAbs {

	private ExerciseAbs myCaller;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5717803730702994084L;

	/**
	 * Create the panel.
	 * @param tabbedPane 
	 */
	public SelectEquipmentExercise(final JTabbedPane frame, ExerciseAbs caller) {
		super(frame);
		if (caller == null) {
			throw new IllegalArgumentException(
					"Invalid: caller is null");
		}
		this.myCaller = caller;
	}

	@Override
	public void selectPressed() {
		int index = matchingEquipment.getSelectedIndex();
		
		if (index >= 0 &&
				index <= equipmentList.size()) {
			setSelectedEquipment(equipmentList.get(index));
		}
	}


	/**
	 * @param selectedEquipment the selectedEquipment to set
	 */
	public void setSelectedEquipment(Equipment selectedEquipment) {
		myCaller.setSelectedEquipment(selectedEquipment);
	}
	
	/**
	 * 
	 */
	public void clearSelectedEquipment() {
		myCaller.setSelectedEquipment(null);
	}
}
