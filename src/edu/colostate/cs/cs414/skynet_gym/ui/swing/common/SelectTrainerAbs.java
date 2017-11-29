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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import edu.colostate.cs.cs414.skynet_gym.domain.control.TrainerCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Equipment;
import edu.colostate.cs.cs414.skynet_gym.domain.people.user.Trainer;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.common.MyListModel;

/**
 * This abstract class defines the select trainer functionality.
 * Derived classes are responsible for implementing the "selectPressed" method.
 * 
 * @author Mike Allan
 *
 */
public abstract class SelectTrainerAbs extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4527737459621083591L;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField phone;
	private JTextField email;
	private JTextField username;
	private JList matchingTrainers;
	private ArrayList<Trainer> trainerList;
	protected final JTabbedPane myFrame;

	/**
	 * Create the panel.
	 * @param tabbedPane 
	 */
	public SelectTrainerAbs(final JTabbedPane frame) {
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
				FormFactory.LINE_GAP_ROWSPEC,
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
				RowSpec.decode("max(10dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		this.myFrame = frame;
		
		JLabel lblPane = new JLabel("Select Trainer");
		add(lblPane, "1, 1, 7, 1, center, bottom");
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		add(separator, "1, 3, 7, 1");
		
		JLabel lblNewLabel = new JLabel("Search: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblNewLabel, "3, 5, left, default");
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		add(lblNewLabel_1, "3, 7, right, default");
		
		firstName = new JTextField();
		add(firstName, "5, 7, fill, default");
		firstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		add(lblLastName, "3, 9, right, default");
		
		lastName = new JTextField();
		lastName.setColumns(10);
		add(lastName, "5, 9, fill, default");
		
		JLabel lblNewLabel_2 = new JLabel("Phone");
		add(lblNewLabel_2, "3, 11, right, default");
		
		phone = new JTextField();
		add(phone, "5, 11, fill, default");
		phone.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		add(lblNewLabel_3, "3, 13, right, default");
		
		email = new JTextField();
		add(email, "5, 13, fill, default");
		email.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Username");
		add(lblNewLabel_4, "3, 15, right, default");
		
		username = new JTextField();
		add(username, "5, 15, fill, default");
		username.setColumns(10);
		
		JButton submitBtn = new JButton("Search");
		submitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				updateList();
			}
		});
		add(submitBtn, "5, 17, right, center");
		
		JLabel lblTrainers = new JLabel("Trainers: ");
		lblTrainers.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblTrainers, "3, 19");
		
		matchingTrainers = new JList();
		updateList();
		matchingTrainers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		matchingTrainers.setValueIsAdjusting(true);
		add(matchingTrainers, "3, 21, 3, 7, fill, fill");
		
		JButton btnNewButton = new JButton("Modify");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				selectPressed();
			}
		});
		add(btnNewButton, "5, 29, right, default");
		
	}
	
	protected final JList getSelectionList(){
		return matchingTrainers;
	}
	
	protected final ArrayList<Trainer> getTrainerList(){
		return trainerList;
	}
	
	protected abstract void selectPressed();
	
	protected void updateList(){
		matchingTrainers.setModel(new MyListModel(getMatchingTrainers()));
	}
	
	private ArrayList<String> getMatchingTrainers(){
		ArrayList<String> rtn = new ArrayList<String>();
		ArrayList<Trainer> trainers = TrainerCtrl.searchTrainers(
				this.firstName.getText(),
				this.lastName.getText(),
				this.phone.getText(),
				this.email.getText(),
				this.username.getText());
		this.trainerList = new ArrayList<Trainer>();
		for (Trainer tr : trainers){
			rtn.add(tr.toStringShort());
			this.trainerList.add(tr);
		}
		return rtn;
	}

}
