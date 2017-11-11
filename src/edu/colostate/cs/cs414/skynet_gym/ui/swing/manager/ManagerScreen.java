package edu.colostate.cs.cs414.skynet_gym.ui.swing.manager;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import edu.colostate.cs.cs414.skynet_gym.ui.swing.Launcher;

/**
 * This is the screen shown to a logged in manager
 * 
 * @author Mike Allan
 *
 */
public class ManagerScreen extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3211885657911804369L;

	/**
	 * Create the panel.
	 */
	public ManagerScreen(final Launcher frame) {

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1021, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
		);
		
		JPanel Welcome = new JPanel();
		tabbedPane.addTab("Welcome", null, Welcome, null);
		
		JLabel lblYouAreLogged = new JLabel("You are logged in as the Manager!");
		lblYouAreLogged.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout groupLayout_1 = new GroupLayout(Welcome);
		groupLayout_1.setHorizontalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout_1.createSequentialGroup()
					.addGap(429)
					.addComponent(lblYouAreLogged, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
					.addGap(422))
		);
		groupLayout_1.setVerticalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_1.createSequentialGroup()
					.addGap(290)
					.addComponent(lblYouAreLogged, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(315))
		);
		Welcome.setLayout(groupLayout_1);
		
		JPanel createTrainer = new CreateTrainer(tabbedPane);
		tabbedPane.addTab("Create Trainer", null, createTrainer, null);
		
		JPanel selectTrainer = new SelectTrainer(tabbedPane);
		tabbedPane.addTab("Modify Trainer", null, selectTrainer, null);
		
		JPanel createCustomer = new CreateCustomer(tabbedPane);
		tabbedPane.addTab("Create Customer", null, createCustomer, null);
		
		JPanel selectCustomer = new SelectCustomer(tabbedPane);
		tabbedPane.addTab("Modify Customer", null, selectCustomer, null);
		
		JPanel createEquipment = new CreateEquipment(tabbedPane);
		tabbedPane.addTab("Create Equipment", null, createEquipment, null);
		
		JPanel selectEquipment = new SelectEquipmentModify(tabbedPane);
		tabbedPane.addTab("Modify Equipment", null, selectEquipment, null);
		
		setLayout(groupLayout);
		
		frame.setBounds(getLayout().preferredLayoutSize(this));
	}
}
