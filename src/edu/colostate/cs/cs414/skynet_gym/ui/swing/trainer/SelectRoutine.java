package edu.colostate.cs.cs414.skynet_gym.ui.swing.trainer;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Routine;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.common.SelectRoutineAbs;

/**
 * This panel is shown to select a routine for the system.
 * 
 * @author Mike Allan
 *
 */
public class SelectRoutine extends SelectRoutineAbs {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4181457799076680945L;
	
	/**
	 * Create the panel.
	 */
	public SelectRoutine(final JTabbedPane frame) {
		super(frame);
	}

	@Override
	protected void selectPressed() {
		int index = getSelectionList().getSelectedIndex();
		
		if (index >= 0 &&
				index <= getRoutineList().size()) {
			Routine modR = getRoutineList().get(index);
			JPanel modExercise = new ModifyRoutine(myFrame, modR);
			myFrame.addTab("Modify Routine: " + modR.getName(),
					null,
					modExercise,
					null);
			myFrame.setSelectedIndex(myFrame.getTabCount()-1);
		}
		
	}

}
	

