package edu.colostate.cs.cs414.skynet_gym.ui.swing.trainer;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.domain.control.RoutineCtrl;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.common.RoutineAbs;

/**
 * This panel is shown to create a routine for the system.
 * 
 * @author Mike Allan
 *
 */
public class CreateRoutine extends RoutineAbs {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3320604020077508040L;

	/**
	 * Create the panel.
	 */
	public CreateRoutine(final JTabbedPane frame) {
		super(frame, "Create Routine", false);
		
		clearAll();
	}

	@Override
	protected void submitPressed() {
		try {
			RoutineCtrl.createRoutine(
					name.getText(),
					selectedExercises);
			JOptionPane.showMessageDialog(null,
				    "Routine Created Successfully",
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
		resetSelectedLists();
	}

}
