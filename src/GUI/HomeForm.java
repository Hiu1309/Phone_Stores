package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import GUI.StorePanel;
import GUI.WareHousePanel;

public class HomeForm extends JFrame {
    private JPanel contentPanel;  

    public HomeForm() {
        ImageIcon favicon = new ImageIcon("img/other/newera-logo.png");
        setIconImage(favicon.getImage());
        setTitle("Trang chính");
        setSize(1240, 790);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
       
        // Panel menu trái
        JPanel menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(150, 700));
        menuPanel.setLayout(null);
        menuPanel.setBackground(new Color(58, 83, 96));

        menuPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        JButton storeBtn = new JButton("Cửa hàng");
        storeBtn.setBounds(10, 150, 120, 50);
        storeBtn.setFocusable(false);
        storeBtn.setBackground(new Color(84, 106, 117));
        storeBtn.setForeground(Color.white);
        JButton warehouseBtn = new JButton("Kho");
        warehouseBtn.setBounds(10, 250, 120, 50);
        warehouseBtn.setFocusable(false);
        warehouseBtn.setBackground(new Color(84, 106, 117));
        warehouseBtn.setForeground(Color.white);
        JButton accountBtn = new JButton("Tài khoản");
        accountBtn.setBounds(10, 350, 120, 50);
        accountBtn.setFocusable(false);
        accountBtn.setBackground(new Color(84, 106, 117));
        accountBtn.setForeground(Color.white);
        JButton statsBtn = new JButton("Thống kê");
        statsBtn.setBounds(10, 450, 120, 50);
        statsBtn.setFocusable(false);
        statsBtn.setBackground(new Color(84, 106, 117));
        statsBtn.setForeground(Color.white);


        menuPanel.add(storeBtn);
        menuPanel.add(warehouseBtn);
        menuPanel.add(accountBtn);
        menuPanel.add(statsBtn);

        // Panel nội dung trung tâm với CardLayout
        contentPanel = new JPanel(new CardLayout());
        contentPanel.add(new StorePanel(), "store");
        contentPanel.add(new WareHousePanel(), "warehouse");
        contentPanel.add(new JLabel("Giao diện Tài khoản"), "account");
        contentPanel.add(new JLabel("Giao diện Thống kê"), "stats");

        // Sự kiện nút chuyển panel
        storeBtn.addActionListener(e -> switchPanel("store"));
        warehouseBtn.addActionListener(e -> switchPanel("warehouse"));
        accountBtn.addActionListener(e -> switchPanel("account"));
        statsBtn.addActionListener(e -> switchPanel("stats"));

        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        JLabel infoLabel = new JLabel("Di chuột để xem tọa độ và kích thước...");
infoLabel.setPreferredSize(new Dimension(1240, 20));
infoLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
add(infoLabel, BorderLayout.SOUTH);

// Bắt sự kiện rê chuột trên contentPanel
contentPanel.addMouseMotionListener(new MouseMotionAdapter() {
    @Override
    public void mouseMoved(MouseEvent e) {
        Component source = e.getComponent(); // là contentPanel
        Point point = e.getPoint(); // vị trí chuột trong contentPanel
        Rectangle bounds = source.getBounds(); // kích thước contentPanel

        infoLabel.setText(String.format(
            "Tọa độ chuột: (%d, %d) | contentPanel (x=%d, y=%d, w=%d, h=%d)",
            point.x, point.y, bounds.x, bounds.y, bounds.width, bounds.height
        ));
    }
});

        setVisible(true);
    }
    

    private void switchPanel(String name) {
        CardLayout cl = (CardLayout) (contentPanel.getLayout());
        cl.show(contentPanel, name);
    }
}
