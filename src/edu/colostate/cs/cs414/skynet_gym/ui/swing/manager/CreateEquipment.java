package edu.colostate.cs.cs414.skynet_gym.ui.swing.manager;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.domain.control.EquipmentCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Equipment;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.common.EquipmentAbs;

/**
 * This panel is shown to create equipment for the system.
 * 
 * @author Mike Allan
 *
 */
public class CreateEquipment extends EquipmentAbs {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2485263844163272000L;
	
	/**
	 * Create the panel.
	 */
	public CreateEquipment(final JTabbedPane frame) {
		super(frame, "Create Equipment", false);
		
		clearAll();
	}
	
	@Override
	protected void submitPressed() {
		try {
			Equipment eq = EquipmentCtrl.buildEquipment(
					name.getText(),
					Integer.parseInt(quantity.getValue().toString()),
					picture);
			EquipmentCtrl.addEquipment(eq);
			JOptionPane.showMessageDialog(null,
				    "Equipment Created Successfully",
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
		name.setText("");
		quantity.setValue(0);
		picture = null;
		selectedPictureName.setText("None");
	}

}
