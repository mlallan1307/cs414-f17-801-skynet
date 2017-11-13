package edu.colostate.cs.cs414.skynet_gym.ui.swing.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import edu.colostate.cs.cs414.skynet_gym.domain.control.EquipmentCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Equipment;

/**
 * This abstract class defines the select equipment functionality.
 * Derived classes are responsible for implementing the "selectPressed" method.
 * 
 * @author Mike Allan
 *
 */
public abstract class SelectEquipmentAbs extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2439525020625651411L;
	private JTextField name;
	private JSpinner quantity;
	private JList matchingEquipment;
	private ArrayList<Equipment> equipmentList;
	private JButton btnSelect;
	protected final JTabbedPane myFrame;

	/**
	 * Create the panel.
	 * @param tabbedPane 
	 */
	public SelectEquipmentAbs(final JTabbedPane frame) {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(11dlu;default):grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("max(47dlu;pref)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("center:max(100dlu;min)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("max(17dlu;default):grow"),},
			new RowSpec[] {
				RowSpec.decode("max(20dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(10dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.MIN_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.MIN_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(10dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		myFrame = frame;
		equipmentList = new ArrayList<Equipment>();
		
		JLabel lblCreateManagerAccount = new JLabel("Select Equipment");
		add(lblCreateManagerAccount, "1, 1, 7, 1, center, bottom");
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		add(separator, "1, 3, 7, 1");
		
		JLabel lblNewLabel = new JLabel("Search: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblNewLabel, "3, 5, left, default");
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		add(lblNewLabel_1, "3, 7, right, default");
		
		name = new JTextField();
		add(name, "5, 7, fill, default");
		name.setColumns(10);
		
		JLabel lblLastName = new JLabel("Quantity");
		add(lblLastName, "3, 9, right, default");
		
		quantity = new JSpinner();
		quantity.setModel(new SpinnerNumberModel(new Integer(-1), new Integer(-1), null, new Integer(1)));
		add(quantity, "5, 9, left, default");
		
		JButton submitBtn = new JButton("Search");
		submitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				matchingEquipment.setModel(new MyListModel(getMatchingEquipment()));
			}
		});
		add(submitBtn, "5, 11, right, center");
		
		JLabel lblTrainers = new JLabel("Equipment:");
		lblTrainers.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblTrainers, "3, 13");
		
		matchingEquipment = new JList();
		matchingEquipment.setModel(new MyListModel(getMatchingEquipment()));
		matchingEquipment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		matchingEquipment.setValueIsAdjusting(true);
		add(matchingEquipment, "3, 15, 3, 7, fill, fill");
		
		btnSelect = new JButton("Select");
		btnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				selectPressed();
			}
		});
		add(btnSelect, "5, 23, right, default");
		
	}
	
	protected final JList getSelectionList(){
		return matchingEquipment;
	}
	
	protected final ArrayList<Equipment> getEquipmentList(){
		return equipmentList;
	}
	
	protected abstract void selectPressed();
	
	private ArrayList<String> getMatchingEquipment(){
		ArrayList<String> rtn = new ArrayList<String>();
		ArrayList<Equipment> equipment = EquipmentCtrl.searchEquipment(
				this.name.getText(),
				Integer.parseInt(quantity.getValue().toString()));
		this.equipmentList = new ArrayList<Equipment>();
		for (Equipment eq : equipment){
			rtn.add(eq.toStringShort());
			this.equipmentList.add(eq);
		}
		return rtn;
	}

}
