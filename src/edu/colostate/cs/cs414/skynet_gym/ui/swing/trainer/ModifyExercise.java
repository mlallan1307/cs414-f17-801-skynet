package edu.colostate.cs.cs414.skynet_gym.ui.swing.trainer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import edu.colostate.cs.cs414.skynet_gym.domain.control.ExerciseCtrl;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Exercise;
import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.ExerciseType;

/**
 * This panel is shown to modify an exercise for the system.
 * 
 * @author Mike Allan
 *
 */
public class ModifyExercise extends ExerciseAbs {

	/**
	 * 
	 */
	private static final long serialVersionUID = -814407781198347031L;
	private final Exercise exerciseOrig;
	
	
	/**
	 * Create the panel.
	 */
	public ModifyExercise(final JTabbedPane frame, final Exercise exerciseOrig) {
		super(frame);
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(54dlu;default):grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("max(72dlu;pref)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("center:max(91dlu;default)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("132px"),
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("34px:grow"),}));
		
		this.exerciseOrig = exerciseOrig;
		
		JLabel lblCreateManagerAccount = new JLabel("Modify Exercise");
		add(lblCreateManagerAccount, "3, 1, 7, 1, center, bottom");
		
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
		
		JLabel lblFirstName = new JLabel("Name");
		add(lblFirstName, "3, 7, right, default");
		
		name = new JTextField();
		add(name, "5, 7, 3, 1, fill, default");
		name.setColumns(20);
		
		JLabel lblExerciseType = new JLabel("Exercise Info:");
		lblExerciseType.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblExerciseType, "3, 11");
		
		
		useTimeBased = new JRadioButton("Time Based");
		useTimeBased.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (!useTimeBased.isSelected()) {
					toggleType(false);
				}
			}
		});
		add(useTimeBased, "5, 13, left, default");
		
		JLabel lblNewLabel = new JLabel("or");
		add(lblNewLabel, "7, 13, left, center");
		
		useSetBased = new JRadioButton("Set Based");
		useSetBased.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (!useSetBased.isSelected()) {
					toggleType(true);
				}
			}
		});
		add(useSetBased, "9, 13, left, default");
		
		JLabel lblDuration = new JLabel("Duration (Minutes)");
		add(lblDuration, "3, 15, right, default");
		
		dMinutes = new JSpinner();
		dMinutes.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		add(dMinutes, "5, 15, left, default");
		
		JLabel lblNumberOfSets = new JLabel("Number of Sets");
		add(lblNumberOfSets, "7, 15, right, default");
		
		sSets = new JSpinner();
		sSets.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		add(sSets, "9, 15, left, default");
		
		JLabel lblDurationseconds = new JLabel("Duration (Seconds)");
		add(lblDurationseconds, "3, 17, right, default");
		
		dSeconds = new JSpinner();
		dSeconds.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		add(dSeconds, "5, 17, left, default");
		
		JLabel lblRepititionsPerSet = new JLabel("Repititions Per Set");
		add(lblRepititionsPerSet, "7, 17, right, default");
		
		sReps = new JSpinner();
		sReps.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		add(sReps, "9, 17, left, default");
		
		JLabel lblSelectEquipment = new JLabel("Select Equipment:");
		lblSelectEquipment.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblSelectEquipment, "3, 21");
		
		JLabel lblNewLabel_1 = new JLabel("Selection:");
		add(lblNewLabel_1, "3, 23, right, default");
		
		selectedEquipmentName = new JTextField();
		selectedEquipmentName.setEditable(false);
		selectedEquipmentName.setText("None");
		add(selectedEquipmentName, "5, 23, fill, default");
		selectedEquipmentName.setColumns(10);
		
		selectEquipmentPanel = new SelectEquipmentExercise(null, this);
		selectEquipmentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		selectEquipmentPanel.setVisible(false);
		add(selectEquipmentPanel, "3, 25, 7, 1, fill, fill");
		
		btnSelectEquipment = new JButton("Select");
		btnSelectEquipment.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (btnSelectEquipment.getText().equals("Clear")) {
					setSelectedEquipment(null);
				}
				else {
					toggleEquipment();
				}
			}
		});
		add(btnSelectEquipment, "7, 23, left, default");
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				//setPanel(new sub2(frame));
				try {
					ExerciseType exerciseType;
					if (useTimeBased.isSelected()) {
						exerciseType = ExerciseType.TimeBased;
					} else if (useSetBased.isSelected()) {
						exerciseType = ExerciseType.SetBased;
					} else {
						throw new IllegalArgumentException(
								"Invalid: Exercise type unknown");
					}
					commitEdits();
					ExerciseCtrl.replaceExercise(
							name.getText(),
							exerciseType,
							getSelectedEquipment(),
							getDuration(),
							getSets(),
							getReps(),
							exerciseOrig);
					JOptionPane.showMessageDialog(null,
						    "Exercise Updated Successfully",
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
		add(btnSubmit, "9, 27, left, center");
		
		exerciseButtonGroup.add(useTimeBased);
		exerciseButtonGroup.add(useSetBased);
		
		loadExerciseInfo();
	}
	
	public void loadExerciseInfo() {
		name.setText(exerciseOrig.getName());
		if (exerciseOrig.getExerciseInfo().getType().equals(ExerciseType.TimeBased)) {
			dMinutes.setValue(exerciseOrig.getExerciseInfo().getDuration().getSeconds()/60);
			dSeconds.setValue(exerciseOrig.getExerciseInfo().getDuration().getSeconds()%60);
			toggleType(false);
		} else if (exerciseOrig.getExerciseInfo().getType().equals(ExerciseType.SetBased)) {
			sSets.setValue(exerciseOrig.getExerciseInfo().getNumberOfSets());
			sReps.setValue(exerciseOrig.getExerciseInfo().getNumberOfRepetitions());
			toggleType(true);
		} else {
			throw new IllegalArgumentException(
					"Invalid: Exercise type unknown");
		}
		selectEquipmentPanel.setVisible(false);
		btnSelectEquipment.setText("Select");
		setSelectedEquipment(exerciseOrig.getEquipment());
	}


}
