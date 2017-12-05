package edu.colostate.cs.cs414.skynet_gym.ui.swing.manager;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.domain.control.CustomerCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.people.other.Customer;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.common.CustomerAbs;

/**
 * This panel is shown to create a customer for the system.
 * 
 * @author Mike Allan
 *
 */
public class CreateCustomer extends CustomerAbs {

	/**
	 * 
	 */
	private static final long serialVersionUID = -997143915753099531L;
	
	/**
	 * Create the panel.
	 */
	public CreateCustomer(final JTabbedPane frame) {
		super(frame, "Create Customer", false);
		clearAll();
	}
	
	@Override
	protected void submitPressed() {
		try {
			Customer c = CustomerCtrl.buildCustomer(
					gNameFirst.getText(), // PersonInformation
					gNameLast.getText(),
					gDriversLicense.getText(),
					gPhone.getText(),
					gEmail.getText(),
					hProviderName.getText(), // HealthInsurance
					aStreet1.getText(), // Address
					aStreet2.getText(),
					aStateProv.getText(),
					aCity.getText(),
					aZipcode.getText(),
					aType.getText());
			CustomerCtrl.addCustomer(c);
			JOptionPane.showMessageDialog(null,
				    "Customer Created Successfully",
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
	
	private void clearAll(){
		// Clear general
		gNameFirst.setText("");
		gNameLast.setText("");
		gDriversLicense.setText("");
		gPhone.setText("");
		gEmail.setText("");
		// clear health insurance
		hProviderName.setText("");
		// clear address
		aStreet1.setText("");
		aStreet2.setText("");
		aZipcode.setText("");
		aType.setText("");
		aStateProv.setText("");
		aCity.setText("");
	}

}
