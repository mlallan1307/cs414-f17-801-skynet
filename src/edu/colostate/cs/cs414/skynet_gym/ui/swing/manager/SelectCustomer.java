package edu.colostate.cs.cs414.skynet_gym.ui.swing.manager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.domain.control.CustomerCtrl;
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
	private static final long serialVersionUID = -3467584233509554304L;

	/**
	 * Create the panel.
	 * @param tabbedPane 
	 */
	public SelectCustomer(final JTabbedPane frame) {
		super(frame);
		
		JButton btnNewButton = new JButton("Remove");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int index = getSelectionList().getSelectedIndex();
				
				if (index >= 0 &&
						index <= getCustomerList().size()) {
					
					Object[] options = { "Yes", "No" };
				    int n = JOptionPane.showOptionDialog(null,
				            "Are you sure you want to remove " + 
				            		getCustomerList().get(index)
				            		.getPersonInfo().getLastName()
				            		+ "?",
				            "Confirm",
				            JOptionPane.YES_NO_OPTION,
				            JOptionPane.QUESTION_MESSAGE,
				            null,
				            options,
				            options[1]);
				    if(n == JOptionPane.OK_OPTION){ // Affirmative
				    	try {
				    		CustomerCtrl.removeCustomer(getCustomerList().get(index));
				    		JOptionPane.showMessageDialog(null,
								    "Customer Removed Successfully",
								    "Success",
								    JOptionPane.INFORMATION_MESSAGE);
				    		updateList();
				    		return;
				    	} catch (Exception e) {
				    		JOptionPane.showMessageDialog(null,
								    e.getMessage(),
								    "Error",
								    JOptionPane.ERROR_MESSAGE);
							return;
				    	}
				    }
				    if(n == JOptionPane.NO_OPTION){ // negative
				    	return;
				    }
				    if(n == JOptionPane.CLOSED_OPTION){ // closed the dialog
				    	return;
				    }
				}
			}
		});
		add(btnNewButton, "3, 27, left, default");
	}

	@Override
	protected void selectPressed() {
		int index = getSelectionList().getSelectedIndex();
		
		if (index >= 0 &&
				index <= getCustomerList().size()) {
			Customer modC = getCustomerList().get(index);
			JPanel modCustomer = new ModifyCustomer(myFrame, modC);
			myFrame.addTab("Modify Customer: " + modC.getPersonInfo().getLastName(),
					null,
					modCustomer,
					null);
			myFrame.setSelectedIndex(myFrame.getTabCount()-1);
		}
	}
	
}
