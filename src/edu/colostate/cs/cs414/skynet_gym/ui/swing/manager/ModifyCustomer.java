package edu.colostate.cs.cs414.skynet_gym.ui.swing.manager;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.domain.control.CustomerCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.PersonInformation;
import edu.colostate.cs.cs414.skynet_gym.domain.people.other.Customer;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.common.CustomerAbs;

/**
 * This panel is shown to modify a customer for the system.
 * 
 * @author Mike Allan
 *
 */
public class ModifyCustomer extends CustomerAbs {

	/**
	 * 
	 */
	private static final long serialVersionUID = -815306425953796201L;
	private final Customer customerOrig;
	
	/**
	 * Create the panel.
	 */
	public ModifyCustomer(final JTabbedPane frame, final Customer customerOrig) {
		super(frame, "Modify Customer", true);
		
		if (customerOrig == null) {
			throw new IllegalArgumentException("Given customer is null");
		}
		
		this.customerOrig = customerOrig;
		loadInfo();
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
			CustomerCtrl.replaceCustomer(
					c,
					customerOrig);
			JOptionPane.showMessageDialog(null,
				    "Customer Updated Successfully",
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
	
	private void loadInfo() {
		// Load general
		PersonInformation pi = this.customerOrig.getPersonInfo();
		gNameFirst.setText(pi.getFirstName());
		gNameLast.setText(pi.getLastName());
		gDriversLicense.setText(pi.getDriversLicenseNumber());
		gPhone.setText(pi.getPhone());
		gEmail.setText(pi.getEmail());
		// Load health insurance
		hProviderName.setText(pi.getHealthInsurance().getProviderName());
		// Load address
		aStreet1.setText(pi.getAddress().getStreet1());
		aStreet2.setText(pi.getAddress().getStreet2());
		aZipcode.setText(pi.getAddress().getZipCode());
		aType.setText(pi.getAddress().getType());
		aStateProv.setText(pi.getAddress().getProvOrState());
		aCity.setText(pi.getAddress().getCity());
	}

}
