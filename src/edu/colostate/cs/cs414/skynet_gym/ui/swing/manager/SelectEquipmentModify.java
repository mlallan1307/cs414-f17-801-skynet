package edu.colostate.cs.cs414.skynet_gym.ui.swing.manager;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.domain.data.equipment.Equipment;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.SelectEquipmentAbs;

/**
 * This panel is shown to select equipment for modification.
 * 
 * @author Mike Allan
 *
 */
public class SelectEquipmentModify extends SelectEquipmentAbs {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3014707820954804104L;

	/**
	 * Create the panel.
	 * @param tabbedPane 
	 */
	public SelectEquipmentModify(final JTabbedPane frame) {
		super(frame);

	}

	@Override
	public void selectPressed() {
		int index = matchingEquipment.getSelectedIndex();
		
		if (index >= 0 &&
				index <= equipmentList.size()) {
			Equipment modEq = equipmentList.get(index);
			JPanel modEquipment = new ModifyEquipment(myFrame, modEq);
			myFrame.addTab("Modify Equipment: " + modEq.getName(),
					null,
					modEquipment,
					null);
			myFrame.setSelectedIndex(myFrame.getTabCount()-1);
		}
	}
}
