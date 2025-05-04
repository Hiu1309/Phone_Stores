package GUI;

import BLL.EmployeeBLL;
import DTO.EmployeeDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginForm() {
        setTitle("Đăng nhập - NewEra");
        setSize(1043, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(new Color(29, 29, 29));

        ImageIcon favicon = new ImageIcon("img/other/newera-logo.png");
        setIconImage(favicon.getImage());

        // Left-side image
        ImageIcon icon = new ImageIcon("img/other/newera-icon.png");
        Image icon1 = icon.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH); 
        ImageIcon icon2 = new ImageIcon(icon1);
        JLabel leftImage = new JLabel(icon2);
        leftImage.setBounds(100, 80, 300, 320); 
        add(leftImage);


        // Right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBounds(496, 0, 532, 885);
        rightPanel.setBackground(new Color(18, 18, 18));
        add(rightPanel);

        // Logo
        ImageIcon logo = new ImageIcon("img/other/newera-logo.png");
        Image logo1 = logo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon logo2 = new ImageIcon(logo1);
        JLabel logoLabel = new JLabel(logo2);
        logoLabel.setBounds(219, 54, 94, 50); 
        rightPanel.add(logoLabel);


        // Heading
        JLabel heading = new JLabel("Log in to NewEra");
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        heading.setBounds(195, 115, 200, 27);
        rightPanel.add(heading);

        // Username field
        usernameField = new JTextField();
        usernameField.setBounds(125, 189, 282, 31);
        rightPanel.add(usernameField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(50, 189, 100, 25);
        rightPanel.add(usernameLabel);

        // Password field
        passwordField = new JPasswordField();
        passwordField.setBounds(125, 256, 282, 31);
        rightPanel.add(passwordField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(50, 256, 100, 25);
        rightPanel.add(passwordLabel);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(125, 332, 282, 27);
        loginButton.setBackground(new Color(59, 228, 119));
        loginButton.setFont(new Font("Arial", Font.BOLD, 13));
        loginButton.setFocusable(false);
        rightPanel.add(loginButton);

        // Register prompt
        JLabel promptLabel = new JLabel("Don't have an account?");
        promptLabel.setForeground(new Color(188, 186, 186));
        promptLabel.setBounds(180, 387, 150, 20);
        rightPanel.add(promptLabel);

        //hyperlink style
        registerButton = new JButton("Sign up");
        registerButton.setBounds(300, 384, 80, 25);
        registerButton.setForeground(new Color(35, 169, 242));
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setFocusPainted(false);
        rightPanel.add(registerButton);

        // Action listeners
        loginButton.addActionListener(e -> handleLogin());
        registerButton.addActionListener(e -> {
            new RegisterForm();
            dispose();
        });

        setVisible(true);
    }

    private void handleLogin() {
        try {
            EmployeeBLL bll = new EmployeeBLL();
            EmployeeDTO emp = bll.login(usernameField.getText(), new String(passwordField.getPassword()));
            if (emp != null) {
                JOptionPane.showMessageDialog(this, "Xin chào " + emp.getFullName() + " (" + emp.getRole() + ")");
                dispose();
                new HomeForm().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu");
        }
    }
}
