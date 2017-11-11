package edu.colostate.cs.cs414.skynet_gym.ui.swing.trainer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.domain.people.customer.Customer;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.SelectCustomerAbs;

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
		
		JButton btnNewButton = new JButton("Select");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int index = getSelectionList().getSelectedIndex();
				
				if (index >= 0 &&
						index <= getCustomerList().size()) {
					Customer modC = getCustomerList().get(index);
					JPanel assignR = new AssignRoutine(frame, modC);
					frame.addTab("Assign Routines: " + modC.getPersonInfo().getLastName(),
							null,
							assignR,
							null);
					frame.setSelectedIndex(frame.getTabCount()-1);
				}
			}
		});
		add(btnNewButton, "5, 27, right, default");
		
	}

}
