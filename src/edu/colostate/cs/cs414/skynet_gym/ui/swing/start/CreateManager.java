package edu.colostate.cs.cs414.skynet_gym.ui.swing.start;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import edu.colostate.cs.cs414.skynet_gym.domain.control.ManagerCtrl;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.manager.ManagerScreen;

/**
 * This panel is shown to create a manager for the system.
 * 
 * @author Mike Allan
 *
 */
public class CreateManager extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -321817178059806753L;
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
	private JTextField lUsername;
	private JPasswordField lPassword;
	
	/**
	 * Create the panel.
	 */
	public CreateManager(final Launcher frame) {
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
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.MIN_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.MIN_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
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
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("34px:grow"),}));
		
		JLabel lblCreateManagerAccount = new JLabel("Create Manager Account");
		add(lblCreateManagerAccount, "1, 1, 13, 1, center, bottom");
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		add(separator, "1, 3, 13, 1");
		
		JLabel lblSub = new JLabel("General:");
		lblSub.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblSub, "3, 7, left, center");
		
		JLabel lblFirstName = new JLabel("First Name");
		add(lblFirstName, "3, 9, right, default");
		
		gNameFirst = new JTextField();
		add(gNameFirst, "5, 9, fill, default");
		gNameFirst.setColumns(20);
		
		JLabel lblLastName = new JLabel("Last Name");
		add(lblLastName, "9, 9, right, default");
		
		gNameLast = new JTextField();
		gNameLast.setColumns(20);
		add(gNameLast, "11, 9, fill, default");
		
		JLabel lblDriversLicense = new JLabel("Drivers License #");
		add(lblDriversLicense, "3, 11, right, default");
		
		gDriversLicense = new JTextField();
		add(gDriversLicense, "5, 11, fill, default");
		gDriversLicense.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone");
		add(lblPhone, "3, 13, right, default");
		
		gPhone = new JTextField();
		add(gPhone, "5, 13, fill, default");
		gPhone.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		add(lblEmail, "9, 13, right, default");
		
		gEmail = new JTextField();
		add(gEmail, "11, 13, fill, default");
		gEmail.setColumns(10);
		
		JLabel lblHealthInsurance = new JLabel("Health Insurance:");
		lblHealthInsurance.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblHealthInsurance, "3, 17");
		
		JLabel lblProviderName = new JLabel("Provider Name");
		add(lblProviderName, "3, 19, right, default");
		
		hProviderName = new JTextField();
		add(hProviderName, "5, 19, fill, default");
		hProviderName.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblAddress, "3, 23");
		
		JLabel lblStreet = new JLabel("Street 1");
		add(lblStreet, "3, 25, right, default");
		
		aStreet1 = new JTextField();
		add(aStreet1, "5, 25, fill, default");
		aStreet1.setColumns(10);
		
		JLabel lblStateprovence = new JLabel("State/Provence");
		add(lblStateprovence, "9, 25, right, default");
		
		aStateProv = new JTextField();
		add(aStateProv, "11, 25, fill, default");
		aStateProv.setColumns(10);
		
		JLabel lblStreet_1 = new JLabel("Street 2");
		add(lblStreet_1, "3, 27, right, default");
		
		aStreet2 = new JTextField();
		add(aStreet2, "5, 27, fill, default");
		aStreet2.setColumns(10);
		
		JLabel lblCity = new JLabel("City");
		add(lblCity, "9, 27, right, default");
		
		aCity = new JTextField();
		add(aCity, "11, 27, fill, default");
		aCity.setColumns(10);
		
		JLabel lblZipcode = new JLabel("Zipcode");
		add(lblZipcode, "3, 29, right, default");
		
		aZipcode = new JTextField();
		add(aZipcode, "5, 29, fill, default");
		aZipcode.setColumns(10);
		
		JLabel lblAddressType = new JLabel("Type");
		add(lblAddressType, "3, 31, right, default");
		
		aType = new JTextField();
		add(aType, "5, 31, fill, default");
		aType.setColumns(10);
		
		JLabel lblAccount = new JLabel("Create Account:");
		lblAccount.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblAccount, "3, 35");
		
		JLabel lblUsername = new JLabel("Username");
		add(lblUsername, "3, 37, right, default");
		
		lUsername = new JTextField();
		add(lUsername, "5, 37, fill, default");
		lUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		add(lblPassword, "9, 37, right, default");
		
		lPassword = new JPasswordField();
		add(lPassword, "11, 37, fill, default");
		lPassword.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//setPanel(new sub2(frame));
				try {
					ManagerCtrl.createManager(
							lUsername.getText(),
							String.valueOf(lPassword.getPassword()),
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
							aType.getText());
					frame.setPanel(new ManagerScreen(frame));
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
		add(btnSubmit, "11, 41, center, center");
		
		frame.setBounds(getLayout().preferredLayoutSize(this));
	}

}
