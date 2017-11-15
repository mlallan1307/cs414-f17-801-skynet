package edu.colostate.cs.cs414.skynet_gym.ui.swing.trainer;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.domain.control.RoutineCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Exercise;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Routine;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.common.RoutineAbs;

/**
 * This panel is shown to modify a routine for the system.
 * 
 * @author Mike Allan
 *
 */
public class ModifyRoutine extends RoutineAbs {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6086793370635305053L;
	private final Routine routineOrig;

	/**
	 * Create the panel.
	 */
	public ModifyRoutine(final JTabbedPane frame, final Routine routineOrig) {
		super(frame, "Modify Routine", true);
		
		if (routineOrig == null) {
			throw new IllegalArgumentException("Given Routine is null");
		}
		this.routineOrig = routineOrig; 
		
		loadRoutineInfo();
	}
	
	@Override
	protected void submitPressed() {
		try {
			RoutineCtrl.replaceRoutine(
					name.getText(),
					selectedExercises,
					routineOrig);
			JOptionPane.showMessageDialog(null,
				    "Routine Modifed Successfully",
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
	
	private void loadRoutineInfo() {
		name.setText(routineOrig.getName());
		for (Exercise ex : (ArrayList<Exercise>) routineOrig.getExercises().clone()) {
			addSelected(ex);
		}
		
	}

}
