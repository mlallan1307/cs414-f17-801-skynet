package edu.colostate.cs.cs414.skynet_gym.ui.swing.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * This abstract class is intended to be derived for creation and modification
 * of a Customer
 * 
 * @author Mike Allan
 *
 */
public abstract class CustomerAbs extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4235490447083107644L;
	protected JTextField gNameFirst;
	protected JTextField gNameLast;
	protected JTextField gDriversLicense;
	protected JTextField gPhone;
	protected JTextField gEmail;
	protected JTextField hProviderName;
	protected JTextField aStreet1;
	protected JTextField aStreet2;
	protected JTextField aZipcode;
	protected JTextField aType;
	protected JTextField aStateProv;
	protected JTextField aCity;
	
	public CustomerAbs(
			final JTabbedPane frame,
			final String paneLabel,
			boolean closeTabBtn) {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(27dlu;default):grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("center:max(100dlu;min)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("40px:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("max(49dlu;min)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(100dlu;min)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
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
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(10dlu;default)"),
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(10dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("34px:grow"),}));
		
		JLabel lblPane = new JLabel(paneLabel);
		add(lblPane, "3, 1, 9, 1, center, bottom");
		
		if (closeTabBtn) {
			JButton btnCloseTab = new JButton("Close Tab");
			btnCloseTab.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					frame.remove(frame.getSelectedIndex());
				}
			});
			add(btnCloseTab, "13, 1, center, bottom");
		}
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		add(separator, "1, 3, 13, 1");
		
		JLabel lblSub = new JLabel("General:");
		lblSub.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblSub, "3, 5, left, center");
		
		JLabel lblFirstName = new JLabel("First Name");
		add(lblFirstName, "3, 7, right, default");
		
		gNameFirst = new JTextField();
		add(gNameFirst, "5, 7, fill, default");
		gNameFirst.setColumns(20);
		
		JLabel lblLastName = new JLabel("Last Name");
		add(lblLastName, "9, 7, right, default");
		
		gNameLast = new JTextField();
		gNameLast.setColumns(20);
		add(gNameLast, "11, 7, fill, default");
		
		JLabel lblDriversLicense = new JLabel("Drivers License #");
		add(lblDriversLicense, "3, 9, right, default");
		
		gDriversLicense = new JTextField();
		add(gDriversLicense, "5, 9, fill, default");
		gDriversLicense.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone");
		add(lblPhone, "3, 11, right, default");
		
		gPhone = new JTextField();
		add(gPhone, "5, 11, fill, default");
		gPhone.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		add(lblEmail, "9, 11, right, default");
		
		gEmail = new JTextField();
		add(gEmail, "11, 11, fill, default");
		gEmail.setColumns(10);
		
		JLabel lblHealthInsurance = new JLabel("Health Insurance:");
		lblHealthInsurance.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblHealthInsurance, "3, 15");
		
		JLabel lblProviderName = new JLabel("Provider Name");
		add(lblProviderName, "3, 17, right, default");
		
		hProviderName = new JTextField();
		add(hProviderName, "5, 17, fill, default");
		hProviderName.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblAddress, "3, 21");
		
		JLabel lblStreet = new JLabel("Street 1");
		add(lblStreet, "3, 23, right, default");
		
		aStreet1 = new JTextField();
		add(aStreet1, "5, 23, fill, default");
		aStreet1.setColumns(10);
		
		JLabel lblStateprovence = new JLabel("State/Provence");
		add(lblStateprovence, "9, 23, right, default");
		
		aStateProv = new JTextField();
		add(aStateProv, "11, 23, fill, default");
		aStateProv.setColumns(10);
		
		JLabel lblStreet_1 = new JLabel("Street 2");
		add(lblStreet_1, "3, 25, right, default");
		
		aStreet2 = new JTextField();
		add(aStreet2, "5, 25, fill, default");
		aStreet2.setColumns(10);
		
		JLabel lblCity = new JLabel("City");
		add(lblCity, "9, 25, right, default");
		
		aCity = new JTextField();
		add(aCity, "11, 25, fill, default");
		aCity.setColumns(10);
		
		JLabel lblZipcode = new JLabel("Zipcode");
		add(lblZipcode, "3, 27, right, default");
		
		aZipcode = new JTextField();
		add(aZipcode, "5, 27, fill, default");
		aZipcode.setColumns(10);
		
		JLabel lblAddressType = new JLabel("Type");
		add(lblAddressType, "3, 29, right, default");
		
		aType = new JTextField();
		add(aType, "5, 29, fill, default");
		aType.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				submitPressed();
			}
		});
		add(btnSubmit, "11, 33, center, center");
	}
	
	protected abstract void submitPressed();
}
