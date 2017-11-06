package edu.colostate.cs.cs414.skynet_gym.ui.swing.manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

import edu.colostate.cs.cs414.skynet_gym.domain.control.EquipmentCtrl;

/**
 * This panel is shown to create equipment for the system.
 * 
 * @author Mike Allan
 *
 */
public class CreateEquipment extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2485263844163272000L;
	private JTextField name;
	private JSpinner quantity;
	private File picture;
	private JTextField selectedPictureName;
	
	/**
	 * Create the panel.
	 */
	public CreateEquipment(final JTabbedPane frame) {
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("34px:grow"),}));
		
		JLabel lblCreateManagerAccount = new JLabel("Create Equipment");
		add(lblCreateManagerAccount, "1, 1, 13, 1, center, bottom");
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		add(separator, "1, 3, 13, 1");
		
		JLabel lblSub = new JLabel("General:");
		lblSub.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblSub, "3, 5, left, center");
		
		JLabel lblFirstName = new JLabel("Name");
		add(lblFirstName, "3, 7, right, default");
		
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
		            picture = fc.getSelectedFile();
		            //This is where a real application would open the file.
		            System.out.println("Selected: " + picture.getName());
		            selectedPictureName.setText(picture.getName());
		        } else {
		        	System.out.println("Open command cancelled by user.");
		        }
			}
		});
		add(btnPicture, "11, 7, left, default");
		
		JLabel lblDriversLicense = new JLabel("Quantity");
		add(lblDriversLicense, "3, 9, right, default");
		
		quantity = new JSpinner();
		quantity.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		add(quantity, "5, 9, left, default");
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				//setPanel(new sub2(frame));
				try {
					EquipmentCtrl.createEquipment(
							name.getText(),
							Integer.parseInt(quantity.getValue().toString()),
							picture);
					JOptionPane.showMessageDialog(null,
						    "Equipment Created Successfully",
						    "Success",
						    JOptionPane.INFORMATION_MESSAGE);
					clearAll();
					//frame.setSelectedIndex(0);
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
		
		JLabel lblNewLabel = new JLabel("Selected");
		add(lblNewLabel, "9, 9, right, default");
		
		selectedPictureName = new JTextField();
		selectedPictureName.setEditable(false);
		add(selectedPictureName, "11, 9, fill, default");
		selectedPictureName.setColumns(10);
		selectedPictureName.setText("None");
		
		add(btnSubmit, "11, 15, center, center");
		
		//frame.setBounds(getLayout().preferredLayoutSize(this));
	}
	
	public void clearAll(){
		name.setText("");
		quantity.setValue(0);
		picture = null;
		selectedPictureName.setText("None");
	}

}
