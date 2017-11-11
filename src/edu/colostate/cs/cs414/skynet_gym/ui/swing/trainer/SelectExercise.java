package edu.colostate.cs.cs414.skynet_gym.ui.swing.trainer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Duration;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
import edu.colostate.cs.cs414.skynet_gym.domain.control.ExerciseCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.data.equipment.Equipment;
import edu.colostate.cs.cs414.skynet_gym.domain.data.exercise.Exercise;
import edu.colostate.cs.cs414.skynet_gym.domain.data.exercise.ExerciseType;
import edu.colostate.cs.cs414.skynet_gym.domain.people.customer.Customer;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.MyListModel;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.manager.ModifyCustomer;

/**
 * This panel is shown to select an exercise for the system.
 * 
 * @author Mike Allan
 *
 */
public class SelectExercise extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5078190783895167499L;
	private JTextField name;
	private JList matchingExercises;
	private ArrayList<Exercise> exerciseList;
	
	
	/**
	 * Create the panel.
	 */
	public SelectExercise(final JTabbedPane frame) {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(12dlu;default):grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("max(72dlu;pref)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("center:max(91dlu;default)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("max(12dlu;default):grow"),},
			new RowSpec[] {
				RowSpec.decode("max(20dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(10dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.MIN_ROWSPEC,
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
		
		exerciseList = new ArrayList<Exercise>();
		
		JLabel lblCreateManagerAccount = new JLabel("Select Exercise");
		add(lblCreateManagerAccount, "1, 1, 7, 1, center, bottom");
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		add(separator, "1, 3, 7, 1");
		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblSearch, "3, 5");
		
		JLabel lblFirstName = new JLabel("Name");
		add(lblFirstName, "3, 7, right, default");
		
		name = new JTextField();
		add(name, "5, 7, fill, default");
		name.setColumns(20);
		
		JButton button = new JButton("Search");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				matchingExercises.setModel(new MyListModel(matchingExercises()));
			}
		});
		add(button, "5, 9, right, default");
		
		JLabel lblExercise = new JLabel("Exercises:");
		lblExercise.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblExercise, "3, 11");
		
		matchingExercises = new JList();
		matchingExercises.setModel(new MyListModel(matchingExercises()));
		matchingExercises.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		matchingExercises.setValueIsAdjusting(true);
		add(matchingExercises, "3, 13, 3, 1, fill, fill");
		
		JButton btnModify = new JButton("Modify");
		btnModify.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int index = matchingExercises.getSelectedIndex();
				
				if (index >= 0 &&
						index <= exerciseList.size()) {
					Exercise modE = exerciseList.get(index);
					JPanel modExercise = new ModifyExercise(frame, modE);
					frame.addTab("Modify Exercise: " + modE.getName(),
							null,
							modExercise,
							null);
					frame.setSelectedIndex(frame.getTabCount()-1);
				}
			}
		});
		add(btnModify, "5, 15, left, center");
		
	}
	
	public ArrayList<String> matchingExercises(){
		ArrayList<String> rtn = new ArrayList<String>();
		ArrayList<Exercise> exercises = ExerciseCtrl.searchExercises(
				this.name.getText());
		this.exerciseList = new ArrayList<Exercise>();
		for (Exercise e : exercises){
			rtn.add(exerciseToString(e));
			this.exerciseList.add(e);
		}
		return rtn;
	}
	
	public String exerciseToString(Exercise e) {
		String rtn = "";
		
		System.out.println(e);
		rtn += e.getName() + " ";
		rtn += String.valueOf(e.getExerciseInfo().getType());
		
		return rtn;
	}
}
	

