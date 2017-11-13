package edu.colostate.cs.cs414.skynet_gym.ui.swing.trainer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import edu.colostate.cs.cs414.skynet_gym.domain.control.RoutineCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Exercise;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Routine;

/**
 * This panel is shown to modify a routine for the system.
 * 
 * @author Mike Allan
 *
 */
public class ModifyRoutine extends RoutineAbs {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6086793370635305053L;
	private final Routine routineOrig;

	/**
	 * Create the panel.
	 */
	public ModifyRoutine(final JTabbedPane frame, final Routine routineOrig) {
		super(frame);
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
		
		this.routineOrig = routineOrig; 
		
		JLabel lblCreateRoutine = new JLabel("Modify Routine");
		add(lblCreateRoutine, "3, 1, 7, 1, center, bottom");
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		add(separator, "1, 3, 11, 1");
		
		JButton btnCloseTab = new JButton("Close Tab");
		btnCloseTab.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				frame.remove(frame.getSelectedIndex());
			}
		});
		add(btnCloseTab, "11, 1, center, bottom");
		
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
		
		add(selectedList, "5, 15, 1, 11, fill, fill");
		
		add(unselectedList, "7, 15, 1, 11, fill, fill");
		
		add(btnRemoveEx, "5, 27, center, default");
		
		add(btnAddEx, "7, 27, center, top");
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					RoutineCtrl.replaceRoutine(
							name.getText(),
							selectedExercises,
							routineOrig);
					JOptionPane.showMessageDialog(null,
						    "Routine Modifed Successfully",
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
		
		loadRoutineInfo();
	}
	
	private void loadRoutineInfo() {
		name.setText(routineOrig.getName());
		for (Exercise ex : (ArrayList<Exercise>) routineOrig.getExercises().clone()) {
			addSelected(ex);
		}
		
	}

}
