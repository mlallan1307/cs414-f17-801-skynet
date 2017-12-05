package edu.colostate.cs.cs414.skynet_gym.ui.swing.manager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.domain.control.TrainerCtrl;
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
		
		JButton btnNewButton = new JButton("Remove");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int index = getSelectionList().getSelectedIndex();
				
				if (index >= 0 &&
						index <= getTrainerList().size()) {
					
					Object[] options = { "Yes", "No" };
				    int n = JOptionPane.showOptionDialog(null,
				            "Are you sure you want to remove " + 
				            		getTrainerList().get(index).getPersonInfo()
				            		.getLastName() + "?",
				            "Confirm",
				            JOptionPane.YES_NO_OPTION,
				            JOptionPane.QUESTION_MESSAGE,
				            null,
				            options,
				            options[1]);
				    if(n == JOptionPane.OK_OPTION){ // Affirmative
				    	try {
				    		TrainerCtrl.removeTrainer(getTrainerList().get(index));
				    		JOptionPane.showMessageDialog(null,
								    "Trainer Removed Successfully",
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
		add(btnNewButton, "3, 29");
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
