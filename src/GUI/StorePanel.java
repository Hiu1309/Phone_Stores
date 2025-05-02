package GUI;

import java.awt.Font;
import java.awt.Image;

import javax.swing.*;
import javax.swing.table.*;

public class StorePanel extends JPanel{
    DefaultTableModel model;
    JTextField searchTf;
    JButton searchBtn;
    public StorePanel(){
        setLayout(null);
        //Thanh tiêu đề giao diện bán hàng
        JLabel titlelb = new JLabel("Danh Sách Sản Phẩm");
        titlelb.setFont(new Font("Arial", Font.BOLD, 20));
        titlelb.setBounds(280, 0, 250, 50);

        //Tạo nút tìm kiếm
        ImageIcon originalIcon = new ImageIcon("img/search.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        searchBtn = new JButton(resizedIcon);
        searchBtn.setBounds(100,30,20,20);

        //Tạo model cho table chứa sản phẩm
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
        sp.setBounds(10,70,750,400);
        add(titlelb);add(searchBtn);add(sp);
    }
}
