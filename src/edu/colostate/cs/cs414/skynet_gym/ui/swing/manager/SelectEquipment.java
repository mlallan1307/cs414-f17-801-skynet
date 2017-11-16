package edu.colostate.cs.cs414.skynet_gym.ui.swing.manager;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
