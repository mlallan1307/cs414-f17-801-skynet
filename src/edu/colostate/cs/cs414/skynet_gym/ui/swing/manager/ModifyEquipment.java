package edu.colostate.cs.cs414.skynet_gym.ui.swing.manager;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import edu.colostate.cs.cs414.skynet_gym.domain.control.EquipmentCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Equipment;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.common.EquipmentAbs;

/**
 * This panel is shown to modify equipment for the system.
 * 
 * @author Mike Allan
 *
 */
public class ModifyEquipment extends EquipmentAbs {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5385497603539228739L;
	private final Equipment equipmentOrig;
	
	/**
	 * Create the panel.
	 */
	public ModifyEquipment(final JTabbedPane frame, final Equipment equipmentOrig) {
		super(frame, "Modify Equipment", true);
		
		if (equipmentOrig == null) {
			throw new IllegalArgumentException("Given equipment is null");
		}
		this.equipmentOrig = equipmentOrig;
		
		loadInfo();
	}
	
	
	
	@Override
	protected void submitPressed() {
		try {
			EquipmentCtrl.replaceEquipment(
					name.getText(),
					Integer.parseInt(quantity.getValue().toString()),
					picture,
					equipmentOrig);
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
	
	private void loadInfo(){
		name.setText(equipmentOrig.getName());
		quantity.setValue(equipmentOrig.getQuantity());
		picture = equipmentOrig.getPicture();
		selectedPictureName.setText(picture.getName());
	}

}
