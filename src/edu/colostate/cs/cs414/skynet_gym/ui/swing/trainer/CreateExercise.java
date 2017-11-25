package edu.colostate.cs.cs414.skynet_gym.ui.swing.trainer;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.domain.control.ExerciseCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Exercise;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.ExerciseType;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.common.ExerciseAbs;

/**
 * This panel is shown to create an exercise for the system.
 * 
 * @author Mike Allan
 *
 */
public class CreateExercise extends ExerciseAbs {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1340298483307988928L;
	
	/**
	 * Create the panel.
	 */
	public CreateExercise(final JTabbedPane frame) {
		super(frame, "Create Exercise", false);
		
		clearAll();
	}
	
	@Override
	protected void submitPressed() {
		try {
			ExerciseType exerciseType;
			if (useTimeBased.isSelected()) {
				exerciseType = ExerciseType.TimeBased;
			} else if (useSetBased.isSelected()) {
				exerciseType = ExerciseType.SetBased;
			} else {
				throw new IllegalArgumentException(
						"Invalid: Exercise type unknown");
			}
			commitEdits();
			Exercise exercise = ExerciseCtrl.buildExercise(
					name.getText(),
					exerciseType,
					selectedEquipment,
					getDuration(),
					getSets(),
					getReps());
			ExerciseCtrl.addExercise(exercise);
			JOptionPane.showMessageDialog(null,
				    "Equipment Created Successfully",
				    "Success",
				    JOptionPane.INFORMATION_MESSAGE);
			clearAll();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,
				    e.getMessage(),
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	private void clearAll() {
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

}
