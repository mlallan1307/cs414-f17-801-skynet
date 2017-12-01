package edu.colostate.cs.cs414.skynet_gym.ui.swing.trainer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.domain.control.RoutineCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Routine;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.common.SelectRoutineAbs;

/**
 * This panel is shown to select a routine for the system.
 * 
 * @author Mike Allan
 *
 */
public class SelectRoutine extends SelectRoutineAbs {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4181457799076680945L;
	
	/**
	 * Create the panel.
	 */
	public SelectRoutine(final JTabbedPane frame) {
		super(frame);
		
		JButton btnNewButton = new JButton("Remove");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int index = getSelectionList().getSelectedIndex();
				
				if (index >= 0 &&
						index <= getRoutineList().size()) {
					
					Object[] options = { "Yes", "No" };
				    int n = JOptionPane.showOptionDialog(null,
				            "Are you sure you want to remove " + 
				            		getRoutineList().get(index).getName()
				            		+ "?",
				            "Confirm",
				            JOptionPane.YES_NO_OPTION,
				            JOptionPane.QUESTION_MESSAGE,
				            null,
				            options,
				            options[1]);
				    if(n == JOptionPane.OK_OPTION){ // Affirmative
				    	try {
				    		RoutineCtrl.removeRoutine(getRoutineList().get(index));
				    		JOptionPane.showMessageDialog(null,
								    "Routine Removed Successfully",
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
		add(btnNewButton, "3, 15, left, default");
	}

	@Override
	protected void selectPressed() {
		int index = getSelectionList().getSelectedIndex();
		
		if (index >= 0 &&
				index <= getRoutineList().size()) {
			Routine modR = getRoutineList().get(index);
			JPanel modExercise = new ModifyRoutine(myFrame, modR);
			myFrame.addTab("Modify Routine: " + modR.getName(),
					null,
					modExercise,
					null);
			myFrame.setSelectedIndex(myFrame.getTabCount()-1);
		}
		
	}

}
	

