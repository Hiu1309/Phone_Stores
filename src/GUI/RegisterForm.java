package GUI;

import BLL.EmployeeBLL;
import DTO.EmployeeDTO;

import javax.swing.*;
import java.awt.event.*;

public class RegisterForm extends JFrame {
    private JTextField usernameField, fullNameField, phoneField;
    private JPasswordField passwordField;
    private JButton registerButton, backButton;

    public RegisterForm() {
        setTitle("Đăng ký nhân viên");
        setSize(350, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 80, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 30, 180, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 70, 80, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 180, 25);
        add(passwordField);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(30, 110, 80, 25);
        add(nameLabel);

        fullNameField = new JTextField();
        fullNameField.setBounds(120, 110, 180, 25);
        add(fullNameField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(30, 150, 80, 25);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(120, 150, 180, 25);
        add(phoneField);

        registerButton = new JButton("Register");
        registerButton.setBounds(60, 210, 100, 30);
        add(registerButton);

        backButton = new JButton("Back");
        backButton.setBounds(180, 210, 100, 30);
        add(backButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = usernameField.getText().trim();
                    String password = new String(passwordField.getPassword());

                    if (username.contains(" ")) {
                        JOptionPane.showMessageDialog(null, "Username không được chứa khoảng trắng!");
                        return;
                    }

                    if (password.length() < 6) {
                        JOptionPane.showMessageDialog(null, "Password phải có ít nhất 6 ký tự!");
                        return;
                    }

                    EmployeeBLL bll = new EmployeeBLL();
                    if (bll.isUsernameExist(username)) {
                        JOptionPane.showMessageDialog(null, "Username đã tồn tại!");
                        return;
                    }

                    EmployeeDTO emp = new EmployeeDTO();
                    emp.setUsername(username);
                    emp.setPassword(password);
                    emp.setFullName(fullNameField.getText());
                    emp.setPhone(phoneField.getText());
                    emp.setRole("staff");
                    bll.register(emp);

                    JOptionPane.showMessageDialog(null, "Đăng ký thành công!");
                    new LoginForm();
                    dispose();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi đăng ký");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginForm();
                dispose();
            }
        });

        setVisible(true);
    }
}