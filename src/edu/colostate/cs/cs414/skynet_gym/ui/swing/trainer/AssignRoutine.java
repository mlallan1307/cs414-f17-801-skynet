package edu.colostate.cs.cs414.skynet_gym.ui.swing.trainer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import edu.colostate.cs.cs414.skynet_gym.domain.control.CustomerCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.control.RoutineCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.data.routine.Routine;
import edu.colostate.cs.cs414.skynet_gym.domain.people.customer.Customer;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.MyListModel;

/**
 * This panel is shown to create a routine for the system.
 * 
 * @author Mike Allan
 *
 */
public class AssignRoutine extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3426588801205179071L;
	private final Customer customerOrig;
	protected ArrayList<Routine> selectedRoutines;
	protected ArrayList<Routine> unselectedRoutines;
	private JList selectedList;
	private JList unselectedList;
	private JButton btnRemoveEx;
	private JButton btnAddEx;

	/**
	 * Create the panel.
	 */
	public AssignRoutine(final JTabbedPane frame, final Customer customerOrig) {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(12dlu;default):grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("max(72dlu;pref)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default):grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default):grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("max(82dlu;min)"),
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
				RowSpec.decode("max(10dlu;default)"),
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
				RowSpec.decode("34px:grow"),}));
		
		this.customerOrig = customerOrig;
		
		JLabel lblCreateRoutine = new JLabel("Assign Routines");
		add(lblCreateRoutine, "3, 1, 7, 1, center, bottom");
		
		JButton btnCloseTab = new JButton("Close Tab");
		btnCloseTab.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				frame.remove(frame.getSelectedIndex());
			}
		});
		add(btnCloseTab, "11, 1, center, bottom");
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		add(separator, "1, 3, 11, 1");
		
		JLabel lblExercises = new JLabel("Routines");
		lblExercises.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblExercises, "5, 11, 3, 1, center, default");
		
		JLabel lblSelectedExercises = new JLabel("Assigned");
		add(lblSelectedExercises, "5, 13, center, default");
		
		JLabel lblUnselectedExercises = new JLabel("Unassigned");
		add(lblUnselectedExercises, "7, 13, center, default");
		
		selectedList = new JList();
		selectedList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		selectedList.setValueIsAdjusting(true);
		selectedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(selectedList, "5, 15, 1, 11, fill, fill");
		
		unselectedList = new JList();
		unselectedList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		unselectedList.setValueIsAdjusting(true);
		unselectedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(unselectedList, "7, 15, 1, 11, fill, fill");
		
		btnRemoveEx = new JButton("Remove");
		btnRemoveEx.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int index = selectedList.getSelectedIndex();
				if (index >= 0 && index < selectedRoutines.size()) {
					removeSelected((Routine) selectedRoutines.get(index));
				}
			}
		});
		add(btnRemoveEx, "5, 27, center, default");
		
		btnAddEx = new JButton("Add");
		btnAddEx.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int index = unselectedList.getSelectedIndex();
				if (index >= 0 && index < unselectedRoutines.size()) {
					addSelected((Routine) unselectedRoutines.get(index));
				}
			}
		});
		add(btnAddEx, "7, 27, center, top");
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					CustomerCtrl.assignRoutines(
							customerOrig,
							getSelectedRoutines());
					JOptionPane.showMessageDialog(null,
						    "Customer Routines Updated",
						    "Success",
						    JOptionPane.INFORMATION_MESSAGE);
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
		
		add(btnSubmit, "9, 31, center, center");
		
		resetSelectedLists();
		
	}
	
	/**
	 * Updates selectedList and unselectedList
	 */
	private void updateLists() {
		selectedList.setModel(new MyListModel(getSelectedRoutinesAsStrings()));
		unselectedList.setModel(new MyListModel(getUnselectedRoutinesAsStrings()));
	}
	
	/**
	 * 
	 * @param r is the routine to move from unselected to selected
	 */
	private void addSelected(Routine r) {
		if (r == null) {
			return;
		}
		if (!selectedRoutines.contains(r)
				&& unselectedRoutines.contains(r)) {
			selectedRoutines.add(r);
			unselectedRoutines.remove(r);
			updateLists();
		}
	}
	
	/**
	 * 
	 * @param r is the routine to move from selected to unselected
	 */
	private void removeSelected(Routine r) {
		if (r == null) {
			return;
		}
		if (selectedRoutines.contains(r)
				&& !unselectedRoutines.contains(r)) {
			selectedRoutines.remove(r);
			unselectedRoutines.add(r);
			updateLists();
		}
	}
	
	/**
	 * @return the selectedRoutines
	 */
	private ArrayList<Routine> getSelectedRoutines() {
		return selectedRoutines;
	}
	
	/**
	 * @return the unselectedRoutines
	 */
	private ArrayList<Routine> getUnselectedRoutines() {
		return unselectedRoutines;
	}
	
	/**
	 * Rebuild selected and unselected lists based on customer
	 */
	private void resetSelectedLists() {
		selectedRoutines = new ArrayList<Routine>();
		unselectedRoutines = (ArrayList<Routine>) RoutineCtrl.getRoutines().clone();
		for (Routine r : customerOrig.getRoutines()) {
			addSelected(r);
		}
		updateLists();
	}

	/**
	 * @return the selectedRoutines as a string list
	 */
	private ArrayList<String> getSelectedRoutinesAsStrings() {
		ArrayList<String> rtn = new ArrayList<String>();
		for (Routine e : selectedRoutines){
			rtn.add(routineToString(e));
		}
		return rtn;
	}
	
	/**
	 * @return the unselectedRoutines as a string list
	 */
	private ArrayList<String> getUnselectedRoutinesAsStrings() {
		ArrayList<String> rtn = new ArrayList<String>();
		for (Routine e : unselectedRoutines){
			rtn.add(routineToString(e));
		}
		return rtn;
	}
	
	/**
	 * 
	 * @param r the routine to convert to string 
	 * @return the given routine as a shortened string
	 */
	private String routineToString(Routine r) {
		String rtn = "";
		
		rtn += r.getName();
		
		return rtn;
	}

}
