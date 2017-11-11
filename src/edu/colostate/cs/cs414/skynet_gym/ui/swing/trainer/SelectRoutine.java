package edu.colostate.cs.cs414.skynet_gym.ui.swing.trainer;

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

import edu.colostate.cs.cs414.skynet_gym.domain.control.RoutineCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.data.routine.Routine;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.MyListModel;

/**
 * This panel is shown to select a routine for the system.
 * 
 * @author Mike Allan
 *
 */
public class SelectRoutine extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4181457799076680945L;
	private JTextField name;
	private JList matchingRoutines;
	private ArrayList<Routine> routineList;
	
	/**
	 * Create the panel.
	 */
	public SelectRoutine(final JTabbedPane frame) {
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
		
		routineList = new ArrayList<Routine>();
		
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
				matchingRoutines.setModel(new MyListModel(getMatchingExercises()));
			}
		});
		add(button, "5, 9, right, default");
		
		JLabel lblExercise = new JLabel("Exercises:");
		lblExercise.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblExercise, "3, 11");
		
		matchingRoutines = new JList();
		matchingRoutines.setModel(new MyListModel(getMatchingExercises()));
		matchingRoutines.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		matchingRoutines.setValueIsAdjusting(true);
		add(matchingRoutines, "3, 13, 3, 1, fill, fill");
		
		JButton btnModify = new JButton("Modify");
		btnModify.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int index = matchingRoutines.getSelectedIndex();
				
				if (index >= 0 &&
						index <= routineList.size()) {
					Routine modR = routineList.get(index);
					JPanel modExercise = new ModifyRoutine(frame, modR);
					frame.addTab("Modify Routine: " + modR.getName(),
							null,
							modExercise,
							null);
					frame.setSelectedIndex(frame.getTabCount()-1);
				}
			}
		});
		add(btnModify, "5, 15, left, center");
		
	}
	
	private ArrayList<String> getMatchingExercises(){
		ArrayList<String> rtn = new ArrayList<String>();
		ArrayList<Routine> routines = RoutineCtrl.searchRoutines(
				this.name.getText());
		this.routineList = new ArrayList<Routine>();
		for (Routine r : routines){
			rtn.add(routineToString(r));
			this.routineList.add(r);
		}
		return rtn;
	}
	
	private String routineToString(Routine r) {
		String rtn = "";
		
		rtn += r.getName();
		
		return rtn;
	}
}
	

