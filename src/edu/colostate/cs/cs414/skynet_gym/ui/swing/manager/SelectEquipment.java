package edu.colostate.cs.cs414.skynet_gym.ui.swing.manager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.domain.control.EquipmentCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Equipment;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.common.SelectEquipmentAbs;

/**
 * This panel is shown to select equipment for modification.
 * 
 * @author Mike Allan
 *
 */
public class SelectEquipment extends SelectEquipmentAbs {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3014707820954804104L;

	/**
	 * Create the panel.
	 * @param tabbedPane 
	 */
	public SelectEquipment(final JTabbedPane frame) {
		super(frame);
		
		JButton btnNewButton = new JButton("Remove");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int index = getSelectionList().getSelectedIndex();
				
				if (index >= 0 &&
						index <= getEquipmentList().size()) {
					
					Object[] options = { "Yes", "No" };
				    int n = JOptionPane.showOptionDialog(null,
				            "Are you sure you want to remove " + 
				            		getEquipmentList().get(index).getName()
				            		+ "?",
				            "Confirm",
				            JOptionPane.YES_NO_OPTION,
				            JOptionPane.QUESTION_MESSAGE,
				            null,
				            options,
				            options[1]);
				    if(n == JOptionPane.OK_OPTION){ // Affirmative
				    	try {
				    		EquipmentCtrl.removeEquipment(getEquipmentList().get(index));
				    		JOptionPane.showMessageDialog(null,
								    "Equipment Removed Successfully",
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
		add(btnNewButton, "3, 23");

	}

	@Override
	public void selectPressed() {
		int index = getSelectionList().getSelectedIndex();
		
		if (index >= 0 &&
				index <= getEquipmentList().size()) {
			Equipment modEq = getEquipmentList().get(index);
			JPanel modEquipment = new ModifyEquipment(myFrame, modEq);
			myFrame.addTab("Modify Equipment: " + modEq.getName(),
					null,
					modEquipment,
					null);
			myFrame.setSelectedIndex(myFrame.getTabCount()-1);
		}
	}
}
