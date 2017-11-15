package edu.colostate.cs.cs414.skynet_gym.ui.swing.start;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import edu.colostate.cs.cs414.skynet_gym.domain.utilities.AccountManager;

/**
 * This is the username recovery pane
 * 
 * @author Mike Allan
 *
 */
public class RecoverUsername extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6340124935891692799L;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField driversLicenseNum;
	
	/**
	 * Create the panel.
	 */
	public RecoverUsername(final Launcher frame) {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("center:max(53dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JButton btnBack = new JButton("");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setPanel(new Login(frame));
			}
		});
		btnBack.setIcon(new ImageIcon(RecoverUsername.class.getResource("/com/sun/javafx/scene/control/skin/caspian/fxvk-backspace-button.png")));
		add(btnBack, "2, 2, left, top");
		
		JLabel lblNewLabel = new JLabel("Recover Username");
		add(lblNewLabel, "4, 2, 3, 1, center, bottom");
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		add(separator, "2, 4, 7, 1, default, center");
		
		JLabel lblUsername = new JLabel("First Name");
		add(lblUsername, "4, 8, right, default");
		
		firstName = new JTextField();
		add(firstName, "6, 8, fill, default");
		firstName.setColumns(20);
		
		JLabel lblPassword = new JLabel("Last Name");
		add(lblPassword, "4, 10, right, default");
		
		lastName = new JTextField();
		add(lastName, "6, 10, fill, default");
		lastName.setColumns(10);
		
		JLabel lblDriversLicense = new JLabel("Drivers License #");
		add(lblDriversLicense, "4, 12, right, default");
		
		driversLicenseNum = new JTextField();
		driversLicenseNum.setColumns(10);
		add(driversLicenseNum, "6, 12, fill, default");
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBackground(UIManager.getColor("Button.background"));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Check login
				String username = AccountManager.recoverUsername(
						firstName.getText(),
						lastName.getText(),
						driversLicenseNum.getText());
				if (username != ""){
					JOptionPane.showMessageDialog(null,
						    "Your username is: " + username,
						    "Recover username",
						    JOptionPane.INFORMATION_MESSAGE);
					frame.setPanel(new Login(frame));
				} else {
					JOptionPane.showMessageDialog(null,
					    "Invalid information",
					    "Recover username",
					    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(btnNewButton, "4, 16, 3, 1, center, center");
		
		frame.setBounds(getLayout().preferredLayoutSize(this));
	}

}
