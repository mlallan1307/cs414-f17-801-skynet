package edu.colostate.cs.cs414.skynet_gym.ui.swing.trainer;

import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.ui.swing.common.SelectEquipmentAbs;

/**
 * This panel is shown to select equipment for an exercise.
 * 
 * @author Mike Allan
 *
 */
public class SelectEquipment extends SelectEquipmentAbs {

	private ExerciseAbs myCaller;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5717803730702994084L;

	/**
	 * Create the panel.
	 * @param tabbedPane 
	 */
	public SelectEquipment(final JTabbedPane frame, ExerciseAbs caller) {
		super(frame);
		if (caller == null) {
			throw new IllegalArgumentException(
					"Invalid: caller is null");
		}
		this.myCaller = caller;
	}

	@Override
	public void selectPressed() {
		int index = getSelectionList().getSelectedIndex();
		
		if (index >= 0 &&
				index <= getEquipmentList().size()) {
			myCaller.setSelectedEquipment(getEquipmentList().get(index));
		}
	}

}
