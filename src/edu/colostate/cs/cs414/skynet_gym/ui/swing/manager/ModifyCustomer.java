package edu.colostate.cs.cs414.skynet_gym.ui.swing.manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import edu.colostate.cs.cs414.skynet_gym.domain.control.CustomerCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.people.customer.Customer;

/**
 * This panel is shown to modify a customer for the system.
 * 
 * @author Mike Allan
 *
 */
public class ModifyCustomer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -815306425953796201L;
	private JTextField gNameFirst;
	private JTextField gNameLast;
	private JTextField gDriversLicense;
	private JTextField gPhone;
	private JTextField gEmail;
	private JTextField hProviderName;
	private JTextField aStreet1;
	private JTextField aStreet2;
	private JTextField aZipcode;
	private JTextField aType;
	private JTextField aStateProv;
	private JTextField aCity;
	private final Customer customerOrig;
	
	/**
	 * Create the panel.
	 */
	public ModifyCustomer(final JTabbedPane frame, final Customer customerOrig) {
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
		
		this.customerOrig = customerOrig;
		
		JLabel lblCreateManagerAccount = new JLabel("Modify Customer");
		add(lblCreateManagerAccount, "3, 1, 9, 1, center, bottom");
		
		JButton btnCloseTab = new JButton("Close Tab");
		btnCloseTab.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				frame.remove(frame.getSelectedIndex());
			}
		});
		add(btnCloseTab, "13, 1, center, bottom");
		
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
		
		JButton btnSubmit = new JButton("Submit Updates");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				//setPanel(new sub2(frame));
				try {
					CustomerCtrl.replaceCustomer(
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
							customerOrig);
					JOptionPane.showMessageDialog(null,
						    "Customer Updated Successfully",
						    "Success",
						    JOptionPane.INFORMATION_MESSAGE);
					//frame.setSelectedIndex(0);
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null,
						    e.getMessage(),
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		add(btnSubmit, "11, 33, center, center");
		
		loadTrainerInfo();
		
		//frame.setBounds(getLayout().preferredLayoutSize(this));
	}
	
	public void loadTrainerInfo(){
		// Load general
		gNameFirst.setText(customerOrig.getPersonInfo().getFirstName());
		gNameLast.setText(customerOrig.getPersonInfo().getLastName());
		gDriversLicense.setText(customerOrig.getPersonInfo().getDriversLicenseNumber());
		gPhone.setText(customerOrig.getPersonInfo().getPhone());
		gEmail.setText(customerOrig.getPersonInfo().getEmail());
		// Load health insurance
		hProviderName.setText(customerOrig.getPersonInfo().getHealthInsurance().getProviderName());
		// Load address
		aStreet1.setText(customerOrig.getPersonInfo().getAddress().getStreet1());
		aStreet2.setText(customerOrig.getPersonInfo().getAddress().getStreet2());
		aZipcode.setText(customerOrig.getPersonInfo().getAddress().getZipCode());
		aType.setText(customerOrig.getPersonInfo().getAddress().getType());
		aStateProv.setText(customerOrig.getPersonInfo().getAddress().getProvOrState());
		aCity.setText(customerOrig.getPersonInfo().getAddress().getCity());
	}

}
