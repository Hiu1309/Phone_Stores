package GUI;

import BLL.UserBLL;

import javax.swing.*;

public class RegisterForm extends JFrame {
    private JTextField txtUser;
    private JPasswordField txtPass;
    private UserBLL bll = new UserBLL();

    public RegisterForm() {
        setTitle("Đăng ký");
        setSize(300, 250);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new JLabel("Username:")).setBounds(20, 60, 80, 20);
        txtUser = new JTextField();
        txtUser.setBounds(100, 60, 120, 20);
        add(txtUser);

        add(new JLabel("Password:")).setBounds(20, 100, 80, 20);
        txtPass = new JPasswordField();
        txtPass.setBounds(100, 100, 120, 20);
        add(txtPass);

        JButton btnRegister = new JButton("Đăng ký");
        btnRegister.setBounds(80, 140, 120, 30);
        add(btnRegister);

        JButton btnBack = new JButton("← Quay lại");
        btnBack.setBounds(80, 180, 120, 25);
        add(btnBack);

        btnRegister.addActionListener(e -> {
            String user = txtUser.getText().trim();
            String pass = new String(txtPass.getPassword());
            String result = bll.validateRegister(user, pass);
            if (result.equals("OK")) {
                JOptionPane.showMessageDialog(this, "Đăng ký thành công!");
                dispose();
                new LoginForm().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, result);
            }
        });

        btnBack.addActionListener(e -> {
            dispose();
            new LoginForm().setVisible(true);
        });
    }
}