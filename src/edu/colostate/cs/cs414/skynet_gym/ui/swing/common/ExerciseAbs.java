package edu.colostate.cs.cs414.skynet_gym.ui.swing.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Duration;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

import edu.colostate.cs.cs414.skynet_gym.domain.data.objects.Equipment;
import edu.colostate.cs.cs414.skynet_gym.ui.swing.trainer.SelectEquipment;

/**
 * This abstract class is intended to be derived for creation and modification
 * of an Exercise
 * 
 * @author Mike Allan
 *
 */
public abstract class ExerciseAbs extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7596873601460780054L;
	protected JTextField name;
	protected JSpinner dMinutes;
	protected JSpinner sSets;
	protected JSpinner dSeconds;
	protected JSpinner sReps;
	protected ButtonGroup exerciseButtonGroup;
	protected JRadioButton useSetBased;
	protected JRadioButton useTimeBased;
	protected JPanel selectEquipmentPanel;
	protected JTextField selectedEquipmentName;
	protected Equipment selectedEquipment;
	protected JButton btnSelectEquipment;
	protected final int initalSetValue = 1;
	protected final int initalTimeValue = 0;
	
	
	/**
	 * Create the panel.
	 */
	public ExerciseAbs(
			final JTabbedPane frame,
			final String paneLabel,
			boolean closeTabBtn) {
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
		
		exerciseButtonGroup = new ButtonGroup();
		
		JLabel lblCreateManagerAccount = new JLabel(paneLabel);
		add(lblCreateManagerAccount, "3, 1, 7, 1, center, bottom");
		
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
		
		selectEquipmentPanel = new SelectEquipment(null, this);
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
				submitPressed();
			}
		});
		add(btnSubmit, "9, 27, left, center");
		
		exerciseButtonGroup.add(useTimeBased);
		exerciseButtonGroup.add(useSetBased);
	}
	
	/**
	 * @param selectedEquipment the selectedEquipment to set
	 */
	public void setSelectedEquipment(Equipment selectedEquipment) {
		this.selectedEquipment = selectedEquipment;
		if (selectedEquipment != null) {
			selectedEquipmentName.setText(selectedEquipment.getName());
			toggleEquipment();
		}
		else {
			selectedEquipmentName.setText("None");
		}
	}
	
	protected abstract void submitPressed();
	
	protected void commitEdits() {
		try {
			dMinutes.commitEdit();
			dSeconds.commitEdit();
			sSets.commitEdit();
			sReps.commitEdit();
		} catch ( java.text.ParseException e ) {}
	}
	
	protected Duration getDuration() {
		Integer minutes = Integer.parseInt(dMinutes.getValue().toString());
		Integer seconds = Integer.parseInt(dSeconds.getValue().toString());
		return Duration.ofSeconds((seconds+(minutes*60)));
	}
	
	protected int getSets() {
		return Integer.class.cast(sSets.getValue());
	}
	
	protected int getReps() {
		return Integer.class.cast(sReps.getValue());
	}
	
	protected void toggleType(Boolean setBased) {

		useSetBased.setSelected(setBased);
		useTimeBased.setSelected(!setBased);

		dMinutes.setEnabled(!setBased);
		dSeconds.setEnabled(!setBased);
		sSets.setEnabled(setBased);
		sReps.setEnabled(setBased);
		
	}
	
	/**
	 * @return the selectedEquipment
	 */
	protected Equipment getSelectedEquipment() {
		return selectedEquipment;
	}
	
	private void toggleEquipment() {
		if (selectEquipmentPanel.isVisible()) {
			selectEquipmentPanel.setVisible(false);
			btnSelectEquipment.setText("Select");
		} else {
			selectEquipmentPanel.setVisible(true);
			btnSelectEquipment.setText("Clear");
		}
	}


}
