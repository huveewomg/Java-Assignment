import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditUser extends JFrame {

    private static String username;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField UsernameTXT;
    private JPasswordField PasswordTXT;
    private JComboBox<String> RolesBox;
    private JTextField NameTXT;
    DefaultTableModel model;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EditUser frame = new EditUser(username);
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
    public EditUser(String username) {
        this.username = username;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Edit User");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 41));
        lblNewLabel.setBounds(288, -1, 229, 107);
        contentPane.add(lblNewLabel);

        JButton HomepageBtn = new JButton("Return To Homepage");
        HomepageBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Homepage();
            }
        });
        HomepageBtn.setBounds(572, 53, 189, 23);
        contentPane.add(HomepageBtn);

        JLabel lblNewLabel_1 = new JLabel("Role           :");
        lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(10, 140, 112, 23);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Username  :");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(10, 201, 112, 32);
        contentPane.add(lblNewLabel_1_1);

        UsernameTXT = new JTextField();
        UsernameTXT.setBounds(142, 201, 201, 20);
        contentPane.add(UsernameTXT);
        UsernameTXT.setColumns(10);

        JLabel lblNewLabel_1_1_1 = new JLabel("Password   :");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1_1.setBounds(10, 314, 127, 32);
        contentPane.add(lblNewLabel_1_1_1);

        PasswordTXT = new JPasswordField();
        PasswordTXT.setBounds(142, 334, 201, 20);
        contentPane.add(PasswordTXT);

        JLabel lblNewLabel_2 = new JLabel("Exp: DrRamesh");
        lblNewLabel_2.setBounds(142, 226, 201, 14);
        contentPane.add(lblNewLabel_2);

        JButton AddUserBtn = new JButton("Add User");
        AddUserBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddUser();
            }
        });
        AddUserBtn.setFont(new Font("Tahoma", Font.PLAIN, 25));
        AddUserBtn.setBounds(10, 493, 241, 45);
        contentPane.add(AddUserBtn);

        JButton DeleteUserBtn = new JButton("Delete User");
        DeleteUserBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteUser();
            }
        });
        DeleteUserBtn.setFont(new Font("Tahoma", Font.PLAIN, 25));
        DeleteUserBtn.setBounds(10, 385, 241, 45);
        contentPane.add(DeleteUserBtn);

        RolesBox = new JComboBox<>();
        RolesBox.setModel(new DefaultComboBoxModel(new String[] { "Doctor", "Patient" }));
        RolesBox.setBounds(142, 144, 201, 22);
        contentPane.add(RolesBox);

        NameTXT = new JTextField();
        NameTXT.setColumns(10);
        NameTXT.setBounds(142, 277, 201, 20);
        contentPane.add(NameTXT);

        JLabel lblNewLabel_1_1_2 = new JLabel("Name        :");
        lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1_2.setBounds(10, 263, 112, 32);
        contentPane.add(lblNewLabel_1_1_2);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(361, 132, 400, 406);
        contentPane.add(scrollPane);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = table.getSelectedRow();
                RolesBox.setSelectedItem(model.getValueAt(i, 3).toString());
                UsernameTXT.setText(model.getValueAt(i, 0).toString());
                NameTXT.setText(model.getValueAt(i, 1).toString());
                PasswordTXT.setText(model.getValueAt(i, 2).toString());
            }
        });
        table.setBackground(Color.PINK);
        model = new DefaultTableModel();
        Object[] column = { "Username", "Name", "Password", "Role" };
        model.setColumnIdentifiers(column);
        table.setModel(model);
        scrollPane.setViewportView(table);

        JButton btnUpdateUser = new JButton("Update User");
        btnUpdateUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UpdateUser();
            }
        });
        btnUpdateUser.setFont(new Font("Tahoma", Font.PLAIN, 25));
        btnUpdateUser.setBounds(10, 441, 241, 45);
        contentPane.add(btnUpdateUser);

        JButton ClearBtn = new JButton("Clear");
        ClearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UsernameTXT.setText("");
                NameTXT.setText("");
                PasswordTXT.setText("");
            }
        });
        ClearBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ClearBtn.setBounds(261, 432, 89, 74);
        contentPane.add(ClearBtn);

        JButton ImportPatientBtn = new JButton("Patient List");
        ImportPatientBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ImportData("Patient");
            }
        });
        ImportPatientBtn.setBounds(390, 105, 112, 23);
        contentPane.add(ImportPatientBtn);

        JButton ImportDoctorBtn = new JButton("Doctor List");
        ImportDoctorBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ImportData("Doctor");
            }
        });
        ImportDoctorBtn.setBounds(612, 105, 112, 23);
        contentPane.add(ImportDoctorBtn);
    }

    public void Homepage() {
        AdminHomepage AdminHomepage = new AdminHomepage(username);
        AdminHomepage.setVisible(true);
        dispose();
    }

    // need to rename this method to DeleteUser
    public void DeleteUser() {
        int i = table.getSelectedRow();
        if (i >= 0) { // make sure a row is actually selected
            String username = (String) model.getValueAt(i, 0); // get the username from the selected row
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                model.removeRow(i);
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("credentials.txt"));
                    boolean usernameFound = false;
                    StringBuilder fileContent = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] data = line.split(":");
                        if (data[0].trim().equals("Username") && data[1].trim().equals(username)) {
                            for (int j = 0; j < 3; j++) {
                                reader.readLine();
                            }
                            usernameFound = true;
                        } else {
                            fileContent.append(line).append("\n");
                        }
                    }
                    reader.close();
                    if (usernameFound) {
                        // write the new content to the file
                        FileWriter writer = new FileWriter("credentials.txt");
                        JOptionPane.showMessageDialog(null, "Account deleted successfully!");
                        writer.write(fileContent.toString());
                        writer.close();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No row is selected");
        }
    }

    // based on the username and update the other fields in the file
    public void UpdateUser() {
        try {
            
            BufferedReader reader = new BufferedReader(new FileReader("credentials.txt"));
            StringBuilder fileContent = new StringBuilder();
            String line;
            int i = table.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "No row is selected");
                return;
            }
            String username = (String) model.getValueAt(i, 0);
            while ((line = reader.readLine()) != null) {
                if (line.contains("Username: " + username)) {
                    fileContent.append("Username: ").append(UsernameTXT.getText()).append("\n");
                    fileContent.append("Name: ").append(NameTXT.getText()).append("\n");
                    fileContent.append("Password: ").append(PasswordTXT.getText()).append("\n");
                    fileContent.append("Role: ").append(RolesBox.getSelectedItem()).append("\n");
                    fileContent.append("\n");
                    reader.readLine();
                    reader.readLine();
                    reader.readLine();
                    reader.readLine();
                } else {
                    fileContent.append(line).append("\n");
                }
            }
            reader.close();

            // CheckUsername();

            FileWriter writer = new FileWriter("credentials.txt");
            writer.write(fileContent.toString());
            writer.close();
            model.setValueAt(UsernameTXT.getText(), i, 0);
            model.setValueAt(NameTXT.getText(), i, 1);
            model.setValueAt(PasswordTXT.getText(), i, 2);
            model.setValueAt(RolesBox.getSelectedItem(), i, 3);
            JOptionPane.showMessageDialog(null, "Account updated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AddUser() {
        try {
            String role = (String) RolesBox.getSelectedItem();
            String username = UsernameTXT.getText();
            String name = NameTXT.getText();
            String password = new String(PasswordTXT.getPassword());

            if (username.equals("") | password.equals("") | (name.equals(""))) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                return;
            }

            if (username.contains("admin")) {
                JOptionPane.showMessageDialog(null, "Invalid username! Please choose another username.");
                return;
            }

            if (CheckUsername()) {
                JOptionPane.showMessageDialog(null, "Username already exists. Please type again.");
                return;
            }

            FileWriter writer = new FileWriter("credentials.txt", true);
            writer.write("Username: " + username + "\n");
            writer.write("Name: " + name + "\n");
            writer.write("Password: " + password + "\n");
            writer.write("Role: " + role + "\n");
            writer.write("\n");

            writer.close();
            JOptionPane.showMessageDialog(null, "Account created successfully!");
            Object[] row = new Object[4];
            row[0] = username;
            row[1] = name;
            row[2] = password;
            row[3] = role;
            model.addRow(row);

            UsernameTXT.setText("");
            NameTXT.setText("");
            PasswordTXT.setText("");
            // Homepage();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ImportData(String role) {
        try (BufferedReader reader = new BufferedReader(new FileReader("credentials.txt"))) {
            String line;
            List<String[]> data = new ArrayList<>();
            Deque<String> previousLines = new ArrayDeque<>();

            while ((line = reader.readLine()) != null) {
                // Store the line into the deque
                previousLines.addLast(line);
                // If the deque contains more than 4 lines, remove the oldest line
                if (previousLines.size() > 4) {
                    previousLines.pollFirst();
                }

                if (line.startsWith("Role: " + role)) {
                    // Once the specified role is found, retrieve the previous 3 lines in reverse
                    // order
                    String[] currentRow = new String[4];
                    for (int i = 0; i < 4; i++) {
                        currentRow[i] = previousLines.pollFirst().split(": ")[1];
                    }
                    data.add(currentRow);
                }
            }

            // Populate table
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear existing data
            for (String[] row : data) {
                model.addRow(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean CheckUsername() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("credentials.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Username: " + UsernameTXT.getText())) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
