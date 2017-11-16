package edu.colostate.cs.cs414.skynet_gym.ui.swing.trainer;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.domain.people.other.Customer;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.common.SelectCustomerAbs;

/**
 * This panel is shown to select a customer for the system.
 * 
 * @author Mike Allan
 *
 */
public class SelectCustomer extends SelectCustomerAbs {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7092440391318214774L;

	/**
	 * Create the panel.
	 * @param tabbedPane 
	 */
	public SelectCustomer(final JTabbedPane frame) {
		super(frame);
	}

	@Override
	protected void selectPressed() {
		int index = getSelectionList().getSelectedIndex();
		
		if (index >= 0 &&
				index <= getCustomerList().size()) {
			Customer modC = getCustomerList().get(index);
			JPanel assignR = new AssignRoutine(myFrame, modC);
			myFrame.addTab("Assign Routines: " + modC.getPersonInfo().getLastName(),
					null,
					assignR,
					null);
			myFrame.setSelectedIndex(myFrame.getTabCount()-1);
		}
	}

}
