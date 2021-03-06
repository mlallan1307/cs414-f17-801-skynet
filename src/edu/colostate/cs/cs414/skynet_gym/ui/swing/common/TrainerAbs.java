package edu.colostate.cs.cs414.skynet_gym.ui.swing.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import edu.colostate.cs.cs414.skynet_gym.domain.data.people.Qualification;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.Schedule;
import edu.colostate.cs.cs414.skynet_gym.domain.data.people.TimePeriod;

/**
 * This abstract class is intended to be derived for creation and modification
 * of a Trainer
 * 
 * @author Mike Allan
 *
 */
public abstract class TrainerAbs extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1900716686608133512L;
	protected ArrayList<Qualification> qualifications;
	protected JList qualList;
	protected JList workList;
	protected Schedule schedule;
	protected JSpinner startTime;
	protected JSpinner stopTime;
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
	protected JTextField lUsername;
	protected JTextField qualName;
	protected JTextArea qualDescrip;
	protected JComboBox stopDay;
	protected JComboBox startDay;
	protected JPasswordField lPassword;
	
	public TrainerAbs(
			final JTabbedPane frame,
			final String paneLabel,
			boolean closeTabBtn,
			boolean pwField) {
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(10dlu;default)"),
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
				RowSpec.decode("34px:grow"),}));
		
		qualifications = new ArrayList<Qualification>();
		schedule = new Schedule();
		
		JLabel lblCreateManagerAccount = new JLabel(paneLabel);
		add(lblCreateManagerAccount, "3, 1, 9, 1, center, bottom");
		
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
		
		JLabel lblAccount = new JLabel("Account:");
		lblAccount.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblAccount, "3, 33");
		
		JLabel lblUsername = new JLabel("Username");
		add(lblUsername, "3, 35, right, default");
		
		lUsername = new JTextField();
		add(lUsername, "5, 35, fill, default");
		lUsername.setColumns(10);
		
		if (pwField) {
			JLabel lblPassword = new JLabel("Password");
			add(lblPassword, "9, 35, right, default");
			
			lPassword = new JPasswordField();
			add(lPassword, "11, 35, fill, default");
			lPassword.setColumns(10);
		}
		
		JLabel lblAddQualification = new JLabel("Add Qualification:");
		lblAddQualification.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblAddQualification, "3, 39, left, default");
		
		JLabel lblQualifications = new JLabel("Remove Qualifications:");
		lblQualifications.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblQualifications, "9, 39, left, default");
		
		JLabel lblDescription = new JLabel("Description");
		add(lblDescription, "3, 41, right, default");
		
		JLabel lblNames = new JLabel("Names");
		add(lblNames, "9, 41, right, default");
		
		qualList = new JList();
		qualList.setModel(new MyListModel(qualificationNames()));
		qualList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		qualList.setValueIsAdjusting(true);
		add(qualList, "11, 41, fill, fill");
		
		JLabel lblName = new JLabel("Name");
		add(lblName, "3, 43, right, default");
		
		qualName = new JTextField();
		add(qualName, "5, 43, fill, default");
		qualName.setColumns(10);
		
		qualDescrip = new JTextArea();
		qualDescrip.setLineWrap(true);
		add(qualDescrip, "5, 41, fill, fill");
		
		JButton qualRemoveBtn = new JButton("Remove");
		qualRemoveBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					int index = qualList.getSelectedIndex();
					if (index < 0 ||
							index >= qualifications.size()){
						return;
					}
					qualifications.remove(index);
					qualList.setModel(new MyListModel(qualificationNames()));
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
		add(qualRemoveBtn, "11, 43, left, center");
		
		JButton qualAddBtn = new JButton("Add");
		qualAddBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					String name = qualName.getText();
					String desc = qualDescrip.getText();
					if (name.isEmpty() ||
							desc.isEmpty()) {
						JOptionPane.showMessageDialog(frame,
							    "Invalid information",
							    "Add Qualification",
							    JOptionPane.ERROR_MESSAGE);
					}
					else {
						Qualification q = new Qualification(name, desc);
						qualifications.add(q);
						qualName.setText("");
						qualDescrip.setText("");
						
						qualList.setModel(new MyListModel(qualificationNames()));
					}
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
		add(qualAddBtn, "5, 45, left, center");
		
		JLabel lblWorkHours = new JLabel("Add Work Hours:");
		lblWorkHours.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblWorkHours, "3, 49, left, default");
		
		JLabel lblDay = new JLabel("Start Day");
		add(lblDay, "3, 51, right, default");
		
		startDay = new JComboBox();
		startDay.setModel(new DefaultComboBoxModel(new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}));
		add(startDay, "5, 51, fill, default");
		
		JLabel lblCurrentSchedule = new JLabel("Current Schedule");
		add(lblCurrentSchedule, "9, 51, right, default");
		
		workList = new JList();
		workList.setModel(new MyListModel(schedule.asStringList()));
		workList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		workList.setValueIsAdjusting(true);
		add(workList, "11, 51, fill, fill");
		
		JLabel lblStartTime = new JLabel("Start Time");
		add(lblStartTime, "3, 53, right, default");
		
		startTime = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor timeEditorStart = new JSpinner.DateEditor(startTime, "HH:mm");
		startTime.setEditor(timeEditorStart);
		add(startTime, "5, 53, left, default");
		
		JButton removeSched = new JButton("Remove");
		removeSched.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					int index = workList.getSelectedIndex();
					schedule.removePeriod(index);
					workList.setModel(new MyListModel(schedule.asStringList()));
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
		add(removeSched, "11, 53, left, default");
		
		JLabel lblStopDay = new JLabel("Stop Day");
		add(lblStopDay, "3, 55, right, default");
		
		stopDay = new JComboBox();
		stopDay.setModel(new DefaultComboBoxModel(new String[] {
				"Monday",
				"Tuesday",
				"Wednesday",
				"Thursday",
				"Friday",
				"Saturday",
				"Sunday"
				}));
		add(stopDay, "5, 55, fill, default");
		
		JLabel lblStopTime = new JLabel("Stop Time");
		add(lblStopTime, "3, 57, right, default");
		
		stopTime = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor timeEditorStop = new JSpinner.DateEditor(stopTime, "HH:mm");
		stopTime.setEditor(timeEditorStop);
		add(stopTime, "5, 57, left, default");
		
		JButton addSched = new JButton("Add");
		addSched.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					SimpleDateFormat df = new SimpleDateFormat("HH:mm");
					try {
						TimePeriod tp = new TimePeriod(
								DayOfWeek.valueOf(startDay.getSelectedItem().toString().toUpperCase()),
								df.format((Date)startTime.getValue()).toString(),
								DayOfWeek.valueOf(stopDay.getSelectedItem().toString().toUpperCase()),
								df.format((Date)stopTime.getValue()).toString());
						schedule.addPeriod(tp);
					}
					catch (IllegalArgumentException e) {
						JOptionPane.showMessageDialog(frame,
							    "Invalid time period: " + e,
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					workList.setModel(new MyListModel(schedule.asStringList()));
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
		add(addSched, "5, 59, left, default");
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				submitPressed();
			}
		});
		add(btnSubmit, "11, 61, center, center");
	}

	protected abstract void submitPressed();
	
	protected ArrayList<String> qualificationNames(){
		ArrayList<String> rtn = new ArrayList<String>();
		for (Qualification q : qualifications){
			rtn.add(q.getName());
		}
		return rtn;
	}
}
