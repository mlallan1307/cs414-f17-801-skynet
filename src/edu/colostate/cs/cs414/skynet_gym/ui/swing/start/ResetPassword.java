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
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import edu.colostate.cs.cs414.skynet_gym.domain.control.ManagerCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.control.TrainerCtrl;

/**
 * This is the password reset pane
 * 
 * @author Mike Allan
 *
 */
public class ResetPassword extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8643688305849739700L;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField driversLicenseNum;
	private JTextField username;
	private JPasswordField password;
	
	/**
	 * Create the panel.
	 */
	public ResetPassword(final Launcher frame) {
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
		btnBack.setIcon(new ImageIcon(ResetPassword.class.getResource("/com/sun/javafx/scene/control/skin/caspian/fxvk-backspace-button.png")));
		add(btnBack, "2, 2, left, top");
		
		JLabel lblNewLabel = new JLabel("Reset Password");
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
		
		
		
		JLabel lblUsername_1 = new JLabel("Username");
		add(lblUsername_1, "4, 14, right, default");
		
		username = new JTextField();
		username.setColumns(10);
		add(username, "6, 14, fill, default");
		
		JLabel lblNewPassword = new JLabel("New Password");
		add(lblNewPassword, "4, 16, right, default");
		
		password = new JPasswordField();
		password.setColumns(10);
		add(password, "6, 16, fill, default");
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBackground(UIManager.getColor("Button.background"));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Check login
				Boolean resetWorked = ManagerCtrl.resetPassword(
						firstName.getText(),
						lastName.getText(),
						driversLicenseNum.getText(),
						username.getText(),
						String.valueOf(password.getPassword()));
				
				if (!resetWorked) {
					resetWorked = TrainerCtrl.resetPassword(
							firstName.getText(),
							lastName.getText(),
							driversLicenseNum.getText(),
							username.getText(),
							String.valueOf(password.getPassword()));
				}
				if (resetWorked) {
					JOptionPane.showMessageDialog(null,
						    "Password Reset",
						    "Reset password",
						    JOptionPane.INFORMATION_MESSAGE);
					frame.setPanel(new Login(frame));
				}
				else {
					JOptionPane.showMessageDialog(null,
					    "Invalid information",
					    "Reset password",
					    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(btnNewButton, "4, 20, 3, 1, center, center");
		
		frame.setBounds(getLayout().preferredLayoutSize(this));
	}

}
