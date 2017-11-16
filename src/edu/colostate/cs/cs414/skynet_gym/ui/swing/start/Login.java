package edu.colostate.cs.cs414.skynet_gym.ui.swing.start;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

import edu.colostate.cs.cs414.skynet_gym.domain.people.user.UserType;
import edu.colostate.cs.cs414.skynet_gym.domain.utilities.AccountManager;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.manager.ManagerScreen;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.trainer.TrainerScreen;

/**
 * This is the login panel that allows users to login
 * 
 * @author Mike Allan
 *
 */
public class Login extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -817287960075854424L;
	private JTextField username;
	private JPasswordField password;
	
	/**
	 * Create the panel.
	 */
	public Login(final Launcher frame) {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Account Login");
		add(lblNewLabel, "1, 1, 9, 1, center, bottom");
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		add(separator, "1, 3, 9, 1, default, center");
		
		JLabel lblUsername = new JLabel("Username");
		add(lblUsername, "3, 7, right, default");
		
		username = new JTextField();
		add(username, "5, 7, fill, default");
		username.setColumns(20);
		
		JLabel lblPassword = new JLabel("Password");
		add(lblPassword, "3, 9, right, default");
		
		password = new JPasswordField();
		add(password, "5, 9, fill, default");
		password.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBackground(UIManager.getColor("Button.focus"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				// Check login
				//frame.setPanel(new ManagerScreen(frame)); // DEBUG
				//frame.setPanel(new TrainerScreen(frame)); // DEBUG
				UserType user = AccountManager.login(
						username.getText(),
						String.valueOf(password.getPassword()));
				if (UserType.Manager.equals(user)){
					frame.setPanel(new ManagerScreen(frame));
				} else if (UserType.Trainer.equals(user)) {
					frame.setPanel(new TrainerScreen(frame));
				} else {
					JOptionPane.showMessageDialog(null,
					    "Invalid login",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		add(btnNewButton, "1, 13, 9, 1, center, center");
		
		JButton btnRecoverUsername = new JButton("Recover Username");
		btnRecoverUsername.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnRecoverUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				frame.setPanel(new RecoverUsername(frame));
			}
		});
		btnRecoverUsername.setBackground(UIManager.getColor("Button.background"));
		add(btnRecoverUsername, "1, 17, 9, 1, center, center");
		
		JButton btnResetPw = new JButton("Reset Password");
		btnResetPw.setBackground(UIManager.getColor("Button.background"));
		btnResetPw.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnResetPw.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				frame.setPanel(new ResetPassword(frame));
			}
		});
		btnResetPw.setForeground(UIManager.getColor("Button.foreground"));
		add(btnResetPw, "1, 19, 9, 1, center, center");
		
		frame.setBounds(getLayout().preferredLayoutSize(this));
	}

}
