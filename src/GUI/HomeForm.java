package GUI;

import javax.swing.*;

public class HomeForm extends JFrame {
    public HomeForm(String username) {
        setTitle("Home");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel label = new JLabel("Hello, " + username);
        label.setBounds(80, 50, 200, 30);
        label.setFont(label.getFont().deriveFont(16f));
        add(label);

        JButton btnLogout = new JButton("Đăng xuất");
        btnLogout.setBounds(90, 100, 120, 30);
        add(btnLogout);

        btnLogout.addActionListener(e -> {
            dispose();
            new LoginForm().setVisible(true);
        });
    }
}