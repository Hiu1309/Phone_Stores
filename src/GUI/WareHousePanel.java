package GUI;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import BLL.ProductsBLL;
import DTO.ProductsDTO;
public class WareHousePanel extends JPanel {

    private JTable table;
    private JTextField tfProductID, tfProductName, tfStock, tfPrice;
    private JComboBox<String> cbType, cbBrand, cbStatus;
    private JLabel imageLabel;


    public WareHousePanel() {
        setLayout(null);
        setBackground(new Color(60, 63, 65)); 

        // Table
        String[] columns = {"ProductID", "Product Name", "Type", "Brand", "Stock", "Prices", "Status", "Date"};
        table = new JTable(new DefaultTableModel(columns, 0));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15, 15, 1040, 350);
        add(scrollPane);

        // Separator
        JSeparator separator = new JSeparator();
        separator.setBounds(15, 384, 1040, 1);
        add(separator);

        // Labels & Fields
        addLabel("ID sản Phẩm:", 30, 414);
        tfProductID = addTextField(155, 412);

        addLabel("Tên Sản Phẩm:", 30, 477);
        tfProductName = addTextField(155, 475);

        addLabel("Hệ Điều Hành:", 30, 538);
        cbType = addComboBox(new String[]{"Ios", "Android"}, 155, 536);

        addLabel("Hãng:", 94, 594);
        cbBrand = addComboBox(new String[]{"Iphone", "Samsung", "Xiaomi", "Realme", "Huawei", "Vinsmart"}, 155, 592);

        addLabel("Số Lượng:", 500, 414);
        tfStock = addTextField(586, 412);
        tfStock.setText("0");

        addLabel("Giá:", 540, 477);
        tfPrice = addTextField(586, 475);
        tfPrice.setText("0");

        addLabel("Trạng Thái:", 500, 538);
        cbStatus = addComboBox(new String[]{"Còn Hàng", "Hết Hàng"}, 586, 536);

        // Image Panel
        JPanel imagePanel = new JPanel();
        imagePanel.setBounds(866, 410, 158, 212);
        imagePanel.setBackground(Color.WHITE);
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(158, 212));
        imagePanel.add(imageLabel);
        add(imagePanel);

        // Buttons
        String[] buttonLabels = {"Nhập Ảnh", "Thêm", "Cập Nhật", "Reset", "Xóa"};
        int[] xPositions = {885, 133, 287, 445, 599};
        int[] yPositions = {643, 685, 685, 685, 685};
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton btn = new JButton(buttonLabels[i]);
            btn.setBounds(xPositions[i], yPositions[i], 122, 50);
            btn.setFocusPainted(false);
            btn.setBackground(new Color(75, 110, 175));
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("SansSerif", Font.BOLD, 14));
            add(btn);

            // Simple actions for demo
            if (buttonLabels[i].equals("Clear")) {
                btn.addActionListener(e -> clearFields());
            }     
        }

        setPreferredSize(new Dimension(1080, 800));
    }

    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 120, 20);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        add(label);
    }

    private JTextField addTextField(int x, int y) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, 200, 26);
        tf.setFont(new Font("SansSerif", Font.PLAIN, 14));
        add(tf);
        return tf;
    }

    private JComboBox<String> addComboBox(String[] items, int x, int y) {
        JComboBox<String> cb = new JComboBox<>(items);
        cb.setBounds(x, y, 211, 26);
        cb.setFont(new Font("SansSerif", Font.PLAIN, 14));
        add(cb);
        return cb;
    }

    private void clearFields() {
        tfProductID.setText("");
        tfProductName.setText("");
        tfStock.setText("0");
        tfPrice.setText("0");
        cbType.setSelectedIndex(0);
        cbBrand.setSelectedIndex(0);
        cbStatus.setSelectedIndex(0);
        imageLabel.setIcon(null);
    } 
}
