package GUI;

import javax.swing.*;

import DTO.EmployeeDTO;

import java.awt.*;
import java.awt.event.*;

public class HomeForm extends JFrame {
    private JPanel contentPanel;  
    private EmployeeDTO currEmployee;

    public HomeForm(EmployeeDTO emp) {
        this.currEmployee=emp;
        initComponents();
    }

    private void initComponents(){
        
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
        JButton employeeBtn = new JButton("Nhân Viên");
        employeeBtn.setBounds(10, 300, 120, 50);
        JButton statsBtn = new JButton("Thống kê");
        statsBtn.setBounds(10, 400, 120, 50);
        JButton customerBtn = new JButton("Khách hàng");
        customerBtn.setBounds(10, 500, 120, 50);
        JButton invoiceBtn = new JButton("Hóa Đơn");
        invoiceBtn.setBounds(10,30,120,50);

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
        menuPanel.add(employeeBtn);
        menuPanel.add(statsBtn);
        menuPanel.add(customerBtn);
        menuPanel.add(exitBtn);
        menuPanel.add(invoiceBtn);

        // Panel nội dung trung tâm với CardLayout
        contentPanel = new JPanel(new CardLayout());
        contentPanel.add(new StorePanel(currEmployee), "store");
        contentPanel.add(new JLabel("Giao diện Kho"), "warehouse");
        contentPanel.add(new EmployeePanel(), "employee");
        contentPanel.add(new JLabel("Giao diện Thống kê"), "stats");
        contentPanel.add(new CustomerPanel(), "customer");
        contentPanel.add(new InvoicePanel(), "invoice");

        // Sự kiện nút chuyển panel
        storeBtn.addActionListener(e -> switchPanel("store"));
        warehouseBtn.addActionListener(e -> switchPanel("warehouse"));
        employeeBtn.addActionListener(e -> switchPanel("employee"));
        statsBtn.addActionListener(e -> switchPanel("stats"));
        customerBtn.addActionListener(e -> switchPanel("customer"));
        invoiceBtn.addActionListener(e -> switchPanel("invoice"));

        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    private void switchPanel(String name) {
        CardLayout cl = (CardLayout) (contentPanel.getLayout());
        cl.show(contentPanel, name);
    }
}
