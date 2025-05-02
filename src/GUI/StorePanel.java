package GUI;

import javax.swing.*;
import javax.swing.table.*;

public class StorePanel extends JPanel{
    DefaultTableModel model;
    public StorePanel(){
        setLayout(null);
        JLabel titlelb = new JLabel("Danh Sách Sản Phẩm");
        titlelb.setBounds(50, 0, 150, 150);
        model = new DefaultTableModel();
        JTable table = new JTable();
        table.setModel(model);
        model.addColumn("STT");
        model.addColumn("Mã sản phẩm");
        model.addColumn("Tên sản phẩm");
        model.addColumn("Hãng");
        model.addColumn("Giá");
        model.addColumn("Số lượng");
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(10,40,750,400);
        add(titlelb);add(sp);
    }
}
