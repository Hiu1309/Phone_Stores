package GUI;

import BLL.EmployeeBLL;
import DTO.EmployeeDTO;

import javax.swing.*;
import java.awt.event.*;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginForm() {
        setTitle("Đăng nhập");
        setSize(300, 230);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 80, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 30, 130, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 70, 80, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 130, 25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(30, 120, 100, 30);
        add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(150, 120, 100, 30);
        add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    EmployeeBLL bll = new EmployeeBLL();
                    EmployeeDTO emp = bll.login(usernameField.getText(), new String(passwordField.getPassword()));
                    if (emp != null) {
                        JOptionPane.showMessageDialog(null, "Xin chào " + emp.getFullName() + " (" + emp.getRole() + ")");
                        dispose();
                        new HomeForm().setVisible(true);
                        // Mở HomeForm tùy theo quyền nếu cần
                    } else {
                        JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegisterForm();
                dispose();
            }
        });

        setVisible(true);
    }

}