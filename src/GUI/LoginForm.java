package GUI;

import BLL.EmployeeBLL;
import DTO.EmployeeDTO;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;
    private EmployeeBLL empl = new EmployeeBLL();

    public LoginForm() {
        initComponents();
    }

    private void initComponents(){
        setTitle("Đăng nhập");
        setSize(300, 230);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("ĐĂNG NHẬP");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(80,10,200,30);
        add(titleLabel);

        JLabel userLabel = new JLabel("Tên tài khoản:");
        userLabel.setBounds(30, 60, 100, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 60, 130, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Mật khẩu:");
        passLabel.setBounds(30, 100, 80, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 100, 130, 25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(30, 150, 100, 30);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                loginAction(e);
            }
        });
        add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(150, 150, 100, 30);
        add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RegisterForm().setVisible(true);
            }
        });
    }

    private void loginAction(ActionEvent e){
        try{
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
    
            if(username.equals("") || password.equals("")){
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin");
            } else {
                EmployeeDTO emp = empl.getEmployeeAfterLogin(username, password);
                if(emp != null){
                    JOptionPane.showMessageDialog(this, "Đăng nhập thành công!\nChào nhân viên: " + emp.getUsername());
                    dispose();
                    new HomeForm(emp).setVisible(true); 
                } else {
                    JOptionPane.showMessageDialog(this, "Sai tên tài khoản hoặc mật khẩu");
                }
            }
        } catch(Exception ex){
            System.out.println("Lỗi khi đăng nhập: " + ex.getMessage());
        }
    }
    

}