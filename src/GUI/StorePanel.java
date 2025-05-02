package GUI;

import javax.swing.*;
import javax.swing.table.*;

public class StorePanel extends JPanel{
    DefaultTableModel model;
    public StorePanel(){
        setLayout(null);
        model = new DefaultTableModel();
        JTable table = new JTable();
        table.setModel(model);
        model.addColumn("STT");
        model.addColumn("Mã sản phẩm");
        model.addColumn("Tên sản phẩm");
        
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(10,10,400,400);
        add(sp);
    }
}
