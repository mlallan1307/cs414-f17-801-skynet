package edu.colostate.cs.cs414.skynet_gym.ui.swing.manager;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.domain.people.user.Trainer;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.common.SelectTrainerAbs;

/**
 * This panel is shown to select a trainer for the system.
 * 
 * @author Mike Allan
 *
 */
public class SelectTrainer extends SelectTrainerAbs {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5718926038261834339L;

	/**
	 * Create the panel.
	 * @param tabbedPane 
	 */
	public SelectTrainer(final JTabbedPane frame) {
		super(frame);
	}

	@Override
	protected void selectPressed() {
		int index = getSelectionList().getSelectedIndex();
		
		if (index >= 0 &&
				index <= getTrainerList().size()) {
			Trainer modT = getTrainerList().get(index);
			JPanel modTrainer = new ModifyTrainer(myFrame, modT);
			myFrame.addTab("Modify Trainer: " + modT.getPersonInfo().getLastName(),
					null,
					modTrainer,
					null);
			myFrame.setSelectedIndex(myFrame.getTabCount()-1);
		}
		
	}
	

}
