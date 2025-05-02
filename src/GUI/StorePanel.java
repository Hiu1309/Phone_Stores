package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class StorePanel extends JPanel{
    DefaultTableModel modelSP, modelTT;
    JTextField searchTf, phoneTf, tongTf, nhanTf, thoiTf;
    JButton searchBtn, thanhToanBtn, InBillBtn;
    JComboBox filterBox;
    JLabel nameLabel;

    public StorePanel(){
        setLayout(null);

        //Panel mục hiện sản phẩm
        JPanel spPanel = new JPanel();
        spPanel.setLayout(null);
        spPanel.setBounds(0,0,740,700);
        spPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //Thanh tiêu đề giao diện bán hàng
        JLabel titlelb = new JLabel("Danh Sách Sản Phẩm");
        titlelb.setFont(new Font("Arial", Font.BOLD, 20));
        titlelb.setBounds(280, 0, 250, 50);

        //Tạo nút tìm kiếm
        ImageIcon originalIcon = new ImageIcon("img/search.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        searchBtn = new JButton(resizedIcon);
        searchBtn.setBounds(108,45,22,22);

        //Tạo thanh nhập tìm kiếm  
        searchTf = new JTextField();
        searchTf.setBounds(130,45,470,22);

        //Tạo mục checkbox để lọc sản phẩm
        String cb[] = {"Iphone", "Samsung", "Xiaomi", "Realme", "Huawei", "Vinsmart"};
        filterBox = new JComboBox(cb);
        filterBox.setBounds(610,45,80,22);

        //Tạo modelSP cho table chứa sản phẩm
        modelSP = new DefaultTableModel();
        JTable table = new JTable();
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(10,80,720,570);
        table.setModel(modelSP);
        modelSP.addColumn("STT");
        modelSP.addColumn("Mã điện thoại");
        modelSP.addColumn("Tên điện thoại");
        modelSP.addColumn("Hãng");
        modelSP.addColumn("Giá");
        modelSP.addColumn("Số lượng");

        //Panel thanh toán
        JPanel payPanel = new JPanel();
        payPanel.setLayout(null);
        payPanel.setBounds(740,0,380,700);
        payPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //Title thanh toán
        JLabel payTitle = new JLabel("Thanh Toán");
        payTitle.setBounds(95,35,200,50);
        payTitle.setFont(new Font("Arial", Font.BOLD, 20));

        //Table hiện thị sản phẩm trong mục thanh toán
        modelTT = new DefaultTableModel();
        JTable payTable = new JTable();
        JScrollPane payScrollPane = new JScrollPane(payTable);
        payScrollPane.setBounds(10,80,277,300);
        payTable.setModel(modelTT);
        modelTT.addColumn("Tên điện thoại");
        modelTT.addColumn("Giá");
        modelTT.addColumn("Số lượng");

        //Thông tin thanh toán
        nameLabel = new JLabel("Khách hàng: ");
        JLabel phoneLabel = new JLabel("Nhập sđt:");
        JLabel tongLabel = new JLabel("Tổng tiền:");
        JLabel nhanLabel = new JLabel("Tiền nhận:");
        JLabel thoiLabel = new JLabel("Tiền thối:");

        phoneTf = new JTextField();
        tongTf = new JTextField();
        nhanTf = new JTextField();
        thoiTf = new JTextField();
        thoiTf.setEditable(false);

        thanhToanBtn = new JButton("Thanh Toán");
        InBillBtn = new JButton("In bill");
        
        phoneLabel.setBounds(20,400,150,20);
        phoneTf.setBounds(85,402,150,20);
        nameLabel.setBounds(70,430,150,20);



        //Thêm giao diện vào
        spPanel.add(titlelb);spPanel.add(searchBtn);spPanel.add(searchTf);spPanel.add(filterBox);spPanel.add(sp);
        payPanel.add(payTitle);payPanel.add(nameLabel);payPanel.add(payScrollPane);payPanel.add(phoneLabel);payPanel.add(phoneTf);
        add(spPanel);add(payPanel);
    }
}
