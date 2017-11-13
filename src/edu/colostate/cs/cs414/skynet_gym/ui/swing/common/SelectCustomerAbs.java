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

import edu.colostate.cs.cs414.skynet_gym.domain.control.CustomerCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.people.other.Customer;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.common.MyListModel;

/**
 * This panel is shown to select a customer for the system.
 * 
 * @author Mike Allan
 *
 */
public class SelectCustomerAbs extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4265670066705486028L;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField phone;
	private JTextField email;
	private JList matchingCustomers;
	private ArrayList<Customer> customerList;

	/**
	 * Create the panel.
	 * @param tabbedPane 
	 */
	public SelectCustomerAbs(final JTabbedPane frame) {
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
		
		customerList = new ArrayList<Customer>();
		
		JLabel lblCreateManagerAccount = new JLabel("Select Customer");
		add(lblCreateManagerAccount, "1, 1, 7, 1, center, bottom");
		
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
		
		JButton submitBtn = new JButton("Search");
		submitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				matchingCustomers.setModel(new MyListModel(getMatchingCustomers()));
			}
		});
		add(submitBtn, "5, 15, right, center");
		
		JLabel lblTrainers = new JLabel("Customers:");
		lblTrainers.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblTrainers, "3, 17");
		
		matchingCustomers = new JList();
		matchingCustomers.setModel(new MyListModel(getMatchingCustomers()));
		matchingCustomers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		matchingCustomers.setValueIsAdjusting(true);
		add(matchingCustomers, "3, 19, 3, 7, fill, fill");
		
	}
	
	public final JList getSelectionList(){
		return matchingCustomers;
	}
	
	public final ArrayList<Customer> getCustomerList(){
		return customerList;
	}
	
	private ArrayList<String> getMatchingCustomers(){
		ArrayList<String> rtn = new ArrayList<String>();
		ArrayList<Customer> customers = CustomerCtrl.searchCustomers(
				this.firstName.getText(),
				this.lastName.getText(),
				this.phone.getText(),
				this.email.getText());
		this.customerList = new ArrayList<Customer>();
		for (Customer c : customers){
			rtn.add(customerToString(c));
			this.customerList.add(c);
		}
		return rtn;
	}
	
	private String customerToString(Customer c) {
		String rtn = "";
		
		System.out.println(c);
		rtn += c.getPersonInfo().getFirstName() + " ";
		rtn += c.getPersonInfo().getLastName() + " ";
		rtn += c.getPersonInfo().getPhone() + " ";
		rtn += c.getPersonInfo().getEmail();
		
		return rtn;
	}

}
