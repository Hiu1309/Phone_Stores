package GUI;

import BLL.UserBLL;

import javax.swing.*;

public class LoginForm extends JFrame {
    private JTextField txtUser;
    private JPasswordField txtPass;
    private UserBLL bll = new UserBLL();

    public LoginForm() {
        setTitle("Đăng nhập");
        setSize(300, 250);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lbTitle = new JLabel("ĐĂNG NHẬP");
        lbTitle.setBounds(90, 10, 150, 30);
        add(lbTitle);

        txtUser = new JTextField();
        txtUser.setBounds(100, 60, 120, 20);
        add(new JLabel("Username:")).setBounds(20, 60, 80, 20);
        add(txtUser);

        txtPass = new JPasswordField();
        txtPass.setBounds(100, 100, 120, 20);
        add(new JLabel("Password:")).setBounds(20, 100, 80, 20);
        add(txtPass);

        JButton btnLogin = new JButton("Đăng nhập");
        btnLogin.setBounds(80, 140, 120, 30);
        add(btnLogin);

        JButton btnToRegister = new JButton("Đăng ký");
        btnToRegister.setBounds(80, 180, 120, 25);
        add(btnToRegister);

        btnLogin.addActionListener(e -> {
            String user = txtUser.getText().trim();
            String pass = new String(txtPass.getPassword());
            if (bll.validateLogin(user, pass)) {
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
                dispose();
                new HomeForm(user).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu");
            }
        });

        btnToRegister.addActionListener(e -> {
            dispose();
            new RegisterForm().setVisible(true);
        });
    }
}
