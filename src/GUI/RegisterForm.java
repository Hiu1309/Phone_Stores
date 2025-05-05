package GUI;

import BLL.EmployeeBLL;
import DTO.EmployeeDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class RegisterForm extends JFrame {
    private JTextField usernameField, phoneField;
    private JPasswordField passwordField;
    private JButton registerButton, loginButton;

    public RegisterForm() {
        setTitle("Đăng ký");
        setSize(350, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
    
        JLabel titleLabel = new JLabel("ĐĂNG KÝ");
        JLabel usernameLabel = new JLabel("Tên tài khoản:");
        JLabel passwordLabel = new JLabel("Mật khẩu:");
        JLabel phoneLabel = new JLabel("Số điện thoại:");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(125,10,200,30);

        usernameLabel.setBounds(40,70,100,30);
        passwordLabel.setBounds(40,115,100,30);
        phoneLabel.setBounds(40,160,100,30);

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        phoneField = new JTextField();

        usernameField.setBounds(140,72,150,25);
        passwordField.setBounds(140,117,150,25);
        phoneField.setBounds(140,162,150,25);

        registerButton = new JButton("Đăng ký");
        loginButton = new JButton("Đăng nhập");

        registerButton.setBounds(50,215,100,30);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                registerAction(e);
            }
        });

        loginButton.setBounds(180,215,100,30);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                dispose();
                new LoginForm().setVisible(true);
            }
        });
       
        add(titleLabel);add(usernameLabel);add(passwordLabel);add(phoneLabel);
        add(usernameField);add(passwordField);add(phoneField);
        add(registerButton);add(loginButton);
    }

    private void registerAction(ActionEvent e){
        try{
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String phone = phoneField.getText().trim();

            if(username.equals("") ||password.trim().equals("")||phone.equals("")){
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin");   
                return;                     
            }
            
            if(password.length()<8){
                JOptionPane.showMessageDialog(this, "Mật khẩu phải từ 8 ký tự trở lên");
                return;
            }

            if(!phone.matches("\\d{10}")){
                JOptionPane.showMessageDialog(this, "Số điện thoại phải đủ 10 số và không chứa chữ hay ký tự đặc biệt");
                return;
            }

            EmployeeDTO empl = new EmployeeDTO();            
            empl.setUsername(username);
            empl.setPassword(password);
            empl.setPhone(phone);

            EmployeeBLL empBLL = new EmployeeBLL();
            String result = empBLL.register(empl);
            JOptionPane.showMessageDialog(this, result );
            if(result.equals("Đăng ký thành công")){
                dispose();
                new LoginForm().setVisible(true);            
            }

        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this,"Thông tin không hợp lệ");
        }
    }
}
