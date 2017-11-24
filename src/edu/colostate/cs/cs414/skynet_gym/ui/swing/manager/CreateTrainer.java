package edu.colostate.cs.cs414.skynet_gym.ui.swing.manager;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.domain.control.TrainerCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.people.user.Trainer;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.common.MyListModel;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.common.TrainerAbs;

/**
 * This panel is shown to create a trainer for the system.
 * 
 * @author Mike Allan
 *
 */
public class CreateTrainer extends TrainerAbs {

	/**
	 * 
	 */
	private static final long serialVersionUID = -770147153577437714L;
	
	/**
	 * Create the panel.
	 */
	public CreateTrainer(final JTabbedPane frame) {
		super(frame, "Create Trainer", false, true);
		
		clearAll();

	}
	
	@Override
	protected void submitPressed() {
		try {
			Trainer trainer = TrainerCtrl.buildTrainer(
					lUsername.getText(),
					String.valueOf(lPassword.getPassword()),
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
					aType.getText(),
					schedule,
					qualifications);
			TrainerCtrl.addTrainer(trainer);
			JOptionPane.showMessageDialog(null,
				    "Trainer Created Successfully",
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
		// clear account
		lUsername.setText("");
		lPassword.setText("");
		// clear qualifications
		qualName.setText("");
		qualDescrip.setText("");
		qualifications.clear();
		qualList.setModel(new MyListModel(qualificationNames()));
		// clear schedule
		schedule.clear();
		workList.setModel(new MyListModel(schedule.asStringList()));
	}

}
