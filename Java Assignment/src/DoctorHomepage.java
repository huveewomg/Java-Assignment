
import java.awt.Color;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import javax.swing.table.DefaultTableModel;

public class DoctorHomepage extends JFrame {
	
	private String username;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchField;
	private JTable PatientTable;
	private JTable AppointmentTable;

	
	DefaultTableModel PatientModel;
	DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorHomepage frame = new DoctorHomepage(username);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DoctorHomepage(String username) {
		this.username = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton SettingBtn = new JButton("Setting");
		SettingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingForm();
			}
		});
		SettingBtn.setBounds(605, 65, 146, 23);
		contentPane.add(SettingBtn);
		
		JLabel lblNewLabel = new JLabel("Welcome Back: " + username);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel.setBounds(97, 31, 457, 54);
		contentPane.add(lblNewLabel);
		
		JButton LogOutBtn = new JButton("Logout");
		LogOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logout();
			}
		});
		LogOutBtn.setBounds(605, 31, 146, 23);
		contentPane.add(LogOutBtn);
		
		JButton btnAddItemTo = new JButton("Schedule");
		btnAddItemTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddItem();
			}
		});
		btnAddItemTo.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnAddItemTo.setBounds(10, 157, 335, 70);
		contentPane.add(btnAddItemTo);
		
		JButton AppointmentBtn = new JButton("Appointment");
		AppointmentBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		AppointmentBtn.setBounds(10, 253, 335, 70);
		contentPane.add(AppointmentBtn);
		
		JButton btnCheckPatientRecord = new JButton("Patient Record");
		btnCheckPatientRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String PatientName = searchField.getText();
				if (PatientName.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Enter Patient Name");
				}
				else
					MedicalRecord(PatientName);
			}
		});
		btnCheckPatientRecord.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnCheckPatientRecord.setBounds(10, 357, 335, 70);
		contentPane.add(btnCheckPatientRecord);
		
		searchField = new JTextField();
		searchField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		searchField.setColumns(10);
		searchField.setBounds(505, 493, 181, 39);
		contentPane.add(searchField);
		
		JButton ConsultationBtn = new JButton("New Record");
		ConsultationBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String PatientName = searchField.getText();
				if (PatientName.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Enter Patient Name");
				}
				// need to actual remove as well.
				else {
					RecordForm(PatientName);
					if (AppointmentTable.getSelectedRow() != -1) {
						model.removeRow(AppointmentTable.getSelectedRow());
					}
					else if (PatientTable.getSelectedRow() != -1) {
						PatientModel.removeRow(PatientTable.getSelectedRow());
					}
				}
			}
		});
		ConsultationBtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		ConsultationBtn.setBounds(10, 453, 335, 70);
		contentPane.add(ConsultationBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(401, 157, 373, 166);
		contentPane.add(scrollPane);
		
		PatientTable = new JTable();
		PatientTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = PatientTable.getSelectedRow();
				searchField.setText(PatientModel.getValueAt(i, 0).toString());
			}
		});
		PatientTable.setBackground(Color.GRAY);
		PatientModel = new DefaultTableModel();
		Object[] PatientColumn = {"Username", "Severity"};
		PatientModel.setColumnIdentifiers(PatientColumn);
		PatientTable.setModel(PatientModel);
		scrollPane.setViewportView(PatientTable);
		
		JLabel lblNewLabel_1 = new JLabel("Patient Queue");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(516, 126, 140, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Appointment List");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(505, 344, 169, 23);
		contentPane.add(lblNewLabel_1_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(401, 378, 373, 104);
		contentPane.add(scrollPane_1);
		
		AppointmentTable = new JTable();
		AppointmentTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i  = AppointmentTable.getSelectedRow();
				searchField.setText(model.getValueAt(i, 0).toString());
			}
		});
		AppointmentTable.setBackground(Color.WHITE);
		model= new DefaultTableModel();
		Object[] column = {"Username", "Date"};
		model.setColumnIdentifiers(column);
		AppointmentTable.setModel(model);
		scrollPane_1.setViewportView(AppointmentTable);

		queueList();
		appointmentList();
	}
	
		public void Logout() {
			LoginForm loginForm = new LoginForm();
			loginForm.setVisible(true);
			dispose();
			JOptionPane.showMessageDialog(null, "Logout Successfully Thank You For using The System!");
		}
		
		public void SettingForm() {
			SettingPage SettingPage = new SettingPage(username);
			SettingPage.setVisible(true);
			dispose();
		}
//		remove this later
		public void AddItem() {
			ItemPage ItemPage = new ItemPage(username);
			ItemPage.setVisible(true);
			dispose();
		}
		
		public void MedicalRecord(String PatientName) {
			MedicalRecord MedicalRecord = new MedicalRecord(PatientName);
			MedicalRecord.setVisible(true);
		}
		
		public void RecordForm(String PatientName) {
			RecordForm RecordForm = new RecordForm(PatientName);
			RecordForm.setVisible(true);
		}
		
		public void queueList() {
			try {
				String filePath = "PatientQueue.txt";
				BufferedReader reader = new BufferedReader(new FileReader(filePath));
				String line;
				List<String> data = new ArrayList<>();
				String[] currentRow = new String[2];
				while((line = reader.readLine()) != null) {
					if (line.startsWith("Username")) {
						currentRow[0] = line.substring(line.indexOf(":") + 1).trim();
					} else if (line.startsWith("Severity")) {
						currentRow[1] = line.substring(line.indexOf(":") + 1).trim();
						data.add(currentRow[0]);
						data.add(currentRow[1]);
						currentRow = new String[2]; // Reset currentRow
					}
				}
				// Populate the table
				for (int j = 0; j < data.size(); j += 2) {
					PatientModel.addRow(new Object[] {data.get(j), data.get(j + 1)});
				}
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		public void appointmentList(){
			try {
				String filePath = "Appointment.txt";
				BufferedReader reader = new BufferedReader(new FileReader(filePath));
				String line;
				List<String> data = new ArrayList<>();
				String[] currentRow = new String[2];
				while((line = reader.readLine()) != null) {
					if (line.startsWith("Username")) {
						currentRow[0] = line.substring(line.indexOf(":") + 1).trim();
					} else if (line.startsWith("Date")) {
						currentRow[1] = line.substring(line.indexOf(":") + 1).trim();
						data.add(currentRow[0]);
						data.add(currentRow[1]);
						currentRow = new String[2]; // Reset currentRow
					}
				}
				// Populate the table
				for (int j = 0; j < data.size(); j += 2) {
					model.addRow(new Object[] {data.get(j), data.get(j + 1)});
				}
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
}

		// Remove listing from the queue

