package edu.colostate.cs.cs414.skynet_gym.ui.swing.trainer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import edu.colostate.cs.cs414.skynet_gym.ui.swing.start.Launcher;

/**
 * This is the screen shown to a logged in trainer
 * 
 * @author Mike Allan
 *
 */
public class TrainerScreen extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1033745957719195444L;

	/**
	 * Create the panel.
	 */
	public TrainerScreen(final Launcher frame) {

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
		
		JLabel lblYouAreLogged = new JLabel("You are logged in as a trainer!");
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
		
		JPanel createExercise = new CreateExercise(tabbedPane);
		tabbedPane.addTab("Create Exercise", null, createExercise, null);
		
		JPanel selectExercise = new SelectExercise(tabbedPane);
		tabbedPane.addTab("Modify Exercise", null, selectExercise, null);
		
		JPanel createRoutine = new CreateRoutine(tabbedPane);
		tabbedPane.addTab("Create Routine", null, createRoutine, null);
		
		JPanel selectRoutine = new SelectRoutine(tabbedPane);
		tabbedPane.addTab("Modify Routine", null, selectRoutine, null);
		
		JPanel assignRoutines = new SelectCustomer(tabbedPane);
		tabbedPane.addTab("Assign Routine", null, assignRoutines, null);
		
		setLayout(groupLayout);
		
		frame.setBounds(getLayout().preferredLayoutSize(this));
	}
}
