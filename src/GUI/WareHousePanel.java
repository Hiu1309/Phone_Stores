package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class WareHousePanel extends JPanel {

    private JTable table;
    private JTextField tfProductID, tfProductName, tfStock, tfPrice;
    private JComboBox<String> cbType, cbBrand, cbStatus;
    private JLabel imageLabel;

    public WareHousePanel() {
        setLayout(null);
        setBackground(new Color(60, 63, 65)); // JavaFX-like dark background

        // Table
        String[] columns = {"Product ID", "Product Name", "Type", "Brand", "Stock", "Prices", "Status", "Date"};
        table = new JTable(new DefaultTableModel(columns, 0));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15, 15, 1040, 350);
        add(scrollPane);

        // Separator
        JSeparator separator = new JSeparator();
        separator.setBounds(15, 384, 1040, 1);
        add(separator);

        // Labels & Fields
        addLabel("Product ID", 56, 414);
        tfProductID = addTextField(155, 412);

        addLabel("Product Name:", 30, 477);
        tfProductName = addTextField(155, 475);

        addLabel("Type:", 92, 538);
        cbType = addComboBox(new String[]{"Ios", "Android"}, 155, 536);

        addLabel("Brand:", 94, 594);
        cbBrand = addComboBox(new String[]{"Iphone", "Samsung", "Xiaomi", "Realme", "Huawei", "Vinsmart"}, 155, 592);

        addLabel("Stock:", 528, 414);
        tfStock = addTextField(586, 412);
        tfStock.setText("0");

        addLabel("Prices:", 528, 477);
        tfPrice = addTextField(586, 475);
        tfPrice.setText("0đ");

        addLabel("Status:", 526, 538);
        cbStatus = addComboBox(new String[]{"Available", "Out of stock"}, 586, 536);

        // Image Panel
        JPanel imagePanel = new JPanel();
        imagePanel.setBounds(868, 429, 158, 212);
        imagePanel.setBackground(Color.WHITE);
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(158, 212));
        imagePanel.add(imageLabel);
        add(imagePanel);

        // Buttons
        String[] buttonLabels = {"Import", "Add", "Update", "Clear", "Delete"};
        int[] xPositions = {896, 133, 287, 445, 599};
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton btn = new JButton(buttonLabels[i]);
            btn.setBounds(xPositions[i], 685, 122, 50);
            styleButton(btn);
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

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(75, 110, 175));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
    }

    private void clearFields() {
        tfProductID.setText("");
        tfProductName.setText("");
        tfStock.setText("0");
        tfPrice.setText("0đ");
        cbType.setSelectedIndex(0);
        cbBrand.setSelectedIndex(0);
        cbStatus.setSelectedIndex(0);
        imageLabel.setIcon(null);
    }

    // Optional: method to set image
    public void setProductImage(ImageIcon imageIcon) {
        imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(158, 212, Image.SCALE_SMOOTH)));
    }

    // Optional: method to add data to table
    public void addRowToTable(Object[] rowData) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(rowData);
    }

    // Optional: method to get selected row data
    public Object[] getSelectedRowData() {
        int row = table.getSelectedRow();
        if (row == -1) return null;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] data = new Object[model.getColumnCount()];
        for (int i = 0; i < data.length; i++) {
            data[i] = model.getValueAt(row, i);
        }
        return data;
    }
}
