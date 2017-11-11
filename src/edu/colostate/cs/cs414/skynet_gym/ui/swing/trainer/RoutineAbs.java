package edu.colostate.cs.cs414.skynet_gym.ui.swing.trainer;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import edu.colostate.cs.cs414.skynet_gym.domain.control.ExerciseCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.data.exercise.Exercise;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.MyListModel;

/**
 * This is an abstract class that implements common functionality related to routines.
 * 
 * @author Mike Allan
 *
 */
public class RoutineAbs extends JPanel {

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
	public RoutineAbs(final JTabbedPane frame) {
		selectedList = new JList();
		selectedList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		selectedList.setValueIsAdjusting(true);
		selectedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		unselectedList = new JList();
		unselectedList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		unselectedList.setValueIsAdjusting(true);
		unselectedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
	}
	
	/**
	 * Updates selectedList and unselectedList
	 */
	protected void updateLists() {
		selectedList.setModel(new MyListModel(getSelectedExercisesAsStrings()));
		unselectedList.setModel(new MyListModel(getUnselectedExercisesAsStrings()));
	}
	
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
	 * 
	 * @param e is the exercise to move from selected to unselected
	 */
	protected void removeSelected(Exercise e) {
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
	 * @return the selectedExercises
	 */
	protected ArrayList<Exercise> getSelectedExercises() {
		return selectedExercises;
	}
	
	/**
	 * @return the unselectedExercises
	 */
	protected ArrayList<Exercise> getUnselectedExercises() {
		return unselectedExercises;
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
	 * @return the selectedExercises as a string list
	 */
	protected ArrayList<String> getSelectedExercisesAsStrings() {
		ArrayList<String> rtn = new ArrayList<String>();
		for (Exercise e : selectedExercises){
			rtn.add(exerciseToString(e));
		}
		return rtn;
	}
	
	/**
	 * @return the unselectedExercises as a string list
	 */
	protected ArrayList<String> getUnselectedExercisesAsStrings() {
		ArrayList<String> rtn = new ArrayList<String>();
		for (Exercise e : unselectedExercises){
			rtn.add(exerciseToString(e));
		}
		return rtn;
	}
	
	/**
	 * 
	 * @param e the exercise to convert to stringm 
	 * @return the given exercise as a shortened string
	 */
	protected String exerciseToString(Exercise e) {
		String rtn = "";
		
		rtn += e.getName();
		
		return rtn;
	}

}
