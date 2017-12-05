package edu.colostate.cs.cs414.skynet_gym.ui.swing.manager;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.domain.control.TrainerCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.PersonInformation;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.Qualification;
import edu.colostate.cs.cs414.skynet_gym.domain.people.user.Trainer;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.common.MyListModel;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.common.TrainerAbs;

/**
 * This panel is shown to modify a trainer for the system.
 * 
 * @author Mike Allan
 *
 */
public class ModifyTrainer extends TrainerAbs {

	/**
	 * 
	 */
	private static final long serialVersionUID = -770147153577437714L;
	private final Trainer trainerOrig;
	
	/**
	 * Create the panel.
	 */
	public ModifyTrainer(final JTabbedPane frame, final Trainer trainerOrig) {
		super(frame, "Modify Trainer", true, false);
		
		if (trainerOrig == null) {
			throw new IllegalArgumentException("Given Trainer is null");
		}
		this.trainerOrig = trainerOrig;
		
		loadTrainerInfo();
	}
	
	@Override
	protected void submitPressed() {
		try {
			Trainer trainer = TrainerCtrl.buildTrainer(
					lUsername.getText(),
					"",
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
			TrainerCtrl.replaceTrainer(
					trainer,
					trainerOrig);
			JOptionPane.showMessageDialog(null,
				    "Trainer Updated Successfully",
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
	
	private void loadTrainerInfo(){
		// Load general
		PersonInformation pi = this.trainerOrig.getPersonInfo();
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
		// Load account
		lUsername.setText(this.trainerOrig.getUsername());
		// Load qualifications
		qualName.setText("");
		qualDescrip.setText("");
		qualifications = (ArrayList<Qualification>) this.trainerOrig.getQualifications().clone();
		System.out.println(qualifications);
		qualList.setModel(new MyListModel(qualificationNames()));
		// Load schedule
		schedule = this.trainerOrig.getSchedule();
		workList.setModel(new MyListModel(schedule.asStringList()));
	}

}
