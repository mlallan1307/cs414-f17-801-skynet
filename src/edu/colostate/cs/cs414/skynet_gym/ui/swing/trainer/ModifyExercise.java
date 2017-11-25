package edu.colostate.cs.cs414.skynet_gym.ui.swing.trainer;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.domain.control.ExerciseCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Exercise;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.ExerciseType;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.common.ExerciseAbs;

/**
 * This panel is shown to modify an exercise for the system.
 * 
 * @author Mike Allan
 *
 */
public class ModifyExercise extends ExerciseAbs {

	/**
	 * 
	 */
	private static final long serialVersionUID = -814407781198347031L;
	private final Exercise exerciseOrig;
	
	
	/**
	 * Create the panel.
	 */
	public ModifyExercise(final JTabbedPane frame, final Exercise exerciseOrig) {
		super(frame, "Modify Exercise", true);
		
		if (exerciseOrig == null) {
			throw new IllegalArgumentException("Given Exercise is null");
		}
		this.exerciseOrig = exerciseOrig;
		
		loadExerciseInfo();
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
					getSelectedEquipment(),
					getDuration(),
					getSets(),
					getReps());
			ExerciseCtrl.replaceExercise(
					exercise,
					exerciseOrig);
			JOptionPane.showMessageDialog(null,
				    "Exercise Updated Successfully",
				    "Success",
				    JOptionPane.INFORMATION_MESSAGE);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,
				    e.getMessage(),
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	private void loadExerciseInfo() {
		name.setText(exerciseOrig.getName());
		if (exerciseOrig.getExerciseInfo().getType().equals(ExerciseType.TimeBased)) {
			dMinutes.setValue(exerciseOrig.getExerciseInfo().getDuration().getSeconds()/60);
			dSeconds.setValue(exerciseOrig.getExerciseInfo().getDuration().getSeconds()%60);
			toggleType(false);
		} else if (exerciseOrig.getExerciseInfo().getType().equals(ExerciseType.SetBased)) {
			sSets.setValue(exerciseOrig.getExerciseInfo().getNumberOfSets());
			sReps.setValue(exerciseOrig.getExerciseInfo().getNumberOfRepetitions());
			toggleType(true);
		} else {
			throw new IllegalArgumentException(
					"Invalid: Exercise type unknown");
		}
		selectEquipmentPanel.setVisible(false);
		btnSelectEquipment.setText("Select");
		setSelectedEquipment(exerciseOrig.getEquipment());
	}


}
