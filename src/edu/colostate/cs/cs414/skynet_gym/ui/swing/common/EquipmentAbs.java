package edu.colostate.cs.cs414.skynet_gym.ui.swing.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * This abstract class is intended to be derived for creation and modification
 * of an Equipment entry
 * 
 * @author Mike Allan
 *
 */
public abstract class EquipmentAbs extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8667400300093255795L;
	protected JTextField name;
	protected JSpinner quantity;
	protected File picture;
	protected JTextField selectedPictureName;
	
	public EquipmentAbs(
			final JTabbedPane frame,
			final String paneLabel,
			boolean closeTabBtn) {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(27dlu;default):grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("center:max(100dlu;min)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("40px:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("max(49dlu;min)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(100dlu;min)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("max(20dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(10dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.MIN_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.MIN_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(10dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("34px:grow"),}));
		
		JLabel lblPane = new JLabel(paneLabel);
		add(lblPane, "3, 1, 9, 1, center, bottom");
		
		if (closeTabBtn) {
			JButton btnCloseTab = new JButton("Close Tab");
			btnCloseTab.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					frame.remove(frame.getSelectedIndex());
				}
			});
			add(btnCloseTab, "13, 1, center, bottom");
		}
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		add(separator, "1, 3, 13, 1");
		
		JLabel lblSub = new JLabel("General:");
		lblSub.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblSub, "3, 5, left, center");
		
		JLabel lblName = new JLabel("Name");
		add(lblName, "3, 7, right, default");
		
		name = new JTextField();
		add(name, "5, 7, fill, default");
		name.setColumns(20);
		
		JLabel lblLastName = new JLabel("Choose Picture");
		add(lblLastName, "9, 7, right, default");
		
		JButton btnPicture = new JButton("Choose");
		btnPicture.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg files", "jpg", "jpeg");
				JFileChooser fc = new JFileChooser();
				fc.setAcceptAllFileFilterUsed(false);
				fc.setFileFilter(filter);
				int returnVal = fc.showOpenDialog(null);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		        	// Picture selected
		            picture = fc.getSelectedFile();
		            selectedPictureName.setText(picture.getName());
		        } else {
		        	// Cancelled
		        }
			}
		});
		add(btnPicture, "11, 7, left, default");
		
		JLabel lblQuantity = new JLabel("Quantity");
		add(lblQuantity, "3, 9, right, top");
		
		quantity = new JSpinner();
		quantity.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		add(quantity, "5, 9, left, default");
		
		JLabel lblSelected = new JLabel("Selected");
		add(lblSelected, "9, 9, right, default");
		
		selectedPictureName = new JTextField();
		selectedPictureName.setEditable(false);
		add(selectedPictureName, "11, 9, fill, default");
		selectedPictureName.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				submitPressed();
			}
		});
		add(btnSubmit, "11, 13, center, center");
	}
	
	protected abstract void submitPressed();

}
