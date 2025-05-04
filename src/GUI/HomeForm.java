package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import GUI.*;

public class HomeForm extends JFrame {
    private JPanel contentPanel;  

    public HomeForm() {
        setTitle("Trang chính");
        setSize(1200, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel menu trái
        JPanel menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(150, 700));
        menuPanel.setLayout(null);

        menuPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        JButton storeBtn = new JButton("Cửa hàng");
        storeBtn.setBounds(10, 100, 120, 50);
        JButton warehouseBtn = new JButton("Kho");
        warehouseBtn.setBounds(10, 200, 120, 50);
        JButton accountBtn = new JButton("Tài khoản");
        accountBtn.setBounds(10, 300, 120, 50);
        JButton statsBtn = new JButton("Thống kê");
        statsBtn.setBounds(10, 400, 120, 50);

        JButton exitBtn = new JButton("Thoát");
        exitBtn.setBounds(60,600,70,30);
        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(HomeForm.this, "Thoát về màn hình đăng nhập");
                dispose();
                new LoginForm().setVisible(true);
            }
        });

        menuPanel.add(storeBtn);
        menuPanel.add(warehouseBtn);
        menuPanel.add(accountBtn);
        menuPanel.add(statsBtn);
        menuPanel.add(exitBtn);

        // Panel nội dung trung tâm với CardLayout
        contentPanel = new JPanel(new CardLayout());
        contentPanel.add(new StorePanel(), "store");
        contentPanel.add(new JLabel("Giao diện Kho"), "warehouse");
        contentPanel.add(new AccountForm(), "account");
        contentPanel.add(new JLabel("Giao diện Thống kê"), "stats");

        // Sự kiện nút chuyển panel
        storeBtn.addActionListener(e -> switchPanel("store"));
        warehouseBtn.addActionListener(e -> switchPanel("warehouse"));
        accountBtn.addActionListener(e -> switchPanel("account"));
        statsBtn.addActionListener(e -> switchPanel("stats"));

        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void switchPanel(String name) {
        CardLayout cl = (CardLayout) (contentPanel.getLayout());
        cl.show(contentPanel, name);
    }
}
