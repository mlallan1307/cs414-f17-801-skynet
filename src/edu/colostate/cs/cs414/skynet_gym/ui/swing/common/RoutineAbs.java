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
import javax.swing.border.LineBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import edu.colostate.cs.cs414.skynet_gym.domain.control.ExerciseCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Exercise;

/**
 * This abstract class is intended to be derived for creation and modification
 * of a Routine
 * 
 * @author Mike Allan
 *
 */
public abstract class RoutineAbs extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8691189655985186143L;
	protected JTextField name;
	protected ArrayList<Exercise> selectedExercises;
	protected ArrayList<Exercise> unselectedExercises;
	protected JList selectedList;
	protected JList unselectedList;
	protected JButton btnRemoveEx;
	protected JButton btnAddEx;
	
	
	/**
	 * Create the panel.
	 */
	public RoutineAbs(
			final JTabbedPane frame,
			final String paneLabel,
			boolean closeTabBtn) {
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
		
		JLabel lblCreateRoutine = new JLabel(paneLabel);
		add(lblCreateRoutine, "1, 1, 11, 1, center, bottom");
		
		if (closeTabBtn) {
			JButton btnCloseTab = new JButton("Close Tab");
			btnCloseTab.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					frame.remove(frame.getSelectedIndex());
				}
			});
			add(btnCloseTab, "11, 1, center, bottom");
		}
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		add(separator, "1, 3, 11, 1");
		
		JLabel lblName = new JLabel("Name");
		add(lblName, "3, 7, right, default");
		
		name = new JTextField();
		add(name, "5, 7, fill, default");
		name.setColumns(10);
		
		JLabel lblExercises = new JLabel("Exercises");
		lblExercises.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblExercises, "5, 11, 3, 1, center, default");
		
		JLabel lblSelectedExercises = new JLabel("Selected");
		add(lblSelectedExercises, "5, 13, center, default");
		
		JLabel lblUnselectedExercises = new JLabel("Unselected");
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
		
		resetSelectedLists();
		
		btnRemoveEx = new JButton("Remove");
		btnRemoveEx.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int index = selectedList.getSelectedIndex();
				if (index >= 0 && index < selectedExercises.size()) {
					removeSelected((Exercise) selectedExercises.get(index));
				}
			}
		});
		add(btnRemoveEx, "5, 27, center, default");
		
		btnAddEx = new JButton("Add");
		btnAddEx.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int index = unselectedList.getSelectedIndex();
				if (index >= 0 && index < unselectedExercises.size()) {
					addSelected((Exercise) unselectedExercises.get(index));
				}
			}
		});
		add(btnAddEx, "7, 27, center, top");
		
		JButton btnNewButton = new JButton("Reset Exercises");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				resetSelectedLists();
			}
		});
		add(btnNewButton, "5, 31, center, default");
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				submitPressed();
			}
		});
		add(btnSubmit, "9, 31, center, center");
		
	}
	
	protected abstract void submitPressed();
	
	/**
	 * 
	 * @param e is the exercise to move from unselected to selected
	 */
	protected void addSelected(Exercise e) {
		if (e == null) {
			return;
		}
		if (!selectedExercises.contains(e)
				&& unselectedExercises.contains(e)) {
			selectedExercises.add(e);
			unselectedExercises.remove(e);
			updateLists();
		}
	}
	
	/**
	 * Clears the selected list and adds all exercises to the unselected list
	 */
	protected void resetSelectedLists() {
		selectedExercises = new ArrayList<Exercise>();
		unselectedExercises = (ArrayList<Exercise>) ExerciseCtrl.getExercises().clone();
		updateLists();
	}
	
	/**
	 * Updates selectedList and unselectedList
	 */
	private void updateLists() {
		selectedList.setModel(new MyListModel(getSelectedExercisesAsStrings()));
		unselectedList.setModel(new MyListModel(getUnselectedExercisesAsStrings()));
	}
	
	/**
	 * 
	 * @param e is the exercise to move from selected to unselected
	 */
	private void removeSelected(Exercise e) {
		if (e == null) {
			return;
		}
		if (selectedExercises.contains(e)
				&& !unselectedExercises.contains(e)) {
			selectedExercises.remove(e);
			unselectedExercises.add(e);
			updateLists();
		}
	}

	/**
	 * @return the selectedExercises as a string list
	 */
	private ArrayList<String> getSelectedExercisesAsStrings() {
		ArrayList<String> rtn = new ArrayList<String>();
		for (Exercise e : selectedExercises){
			rtn.add(e.toStringShort());
		}
		return rtn;
	}
	
	/**
	 * @return the unselectedExercises as a string list
	 */
	private ArrayList<String> getUnselectedExercisesAsStrings() {
		ArrayList<String> rtn = new ArrayList<String>();
		for (Exercise e : unselectedExercises){
			rtn.add(e.toStringShort());
		}
		return rtn;
	}

}
