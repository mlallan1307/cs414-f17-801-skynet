package edu.colostate.cs.cs414.skynet_gym.ui.swing.trainer;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Exercise;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.common.SelectExerciseAbs;

/**
 * This panel is shown to select an exercise for the system.
 * 
 * @author Mike Allan
 *
 */
public class SelectExercise extends SelectExerciseAbs {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5078190783895167499L;
	
	/**
	 * Create the panel.
	 */
	public SelectExercise(final JTabbedPane frame) {
		super(frame);
	}

	@Override
	protected void selectPressed() {
		int index = getSelectionList().getSelectedIndex();
		
		if (index >= 0 &&
				index <= getExerciseList().size()) {
			Exercise modE = getExerciseList().get(index);
			JPanel modExercise = new ModifyExercise(myFrame, modE);
			myFrame.addTab("Modify Exercise: " + modE.getName(),
					null,
					modExercise,
					null);
			myFrame.setSelectedIndex(myFrame.getTabCount()-1);
		}
	}

}
	

