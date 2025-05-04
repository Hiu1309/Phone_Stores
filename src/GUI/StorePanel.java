package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector;
import java.math.BigDecimal;

import BLL.*;
import DTO.*;

public class StorePanel extends JPanel{
    ProductsBLL productsBLL = new ProductsBLL();
    DefaultTableModel modelTT;
    JTextField searchTf, phoneTf, tongTf, nhanTf, thoiTf;
    JButton searchBtn, thanhToanBtn, inBillBtn;
    JComboBox filterBox;
    JLabel nameLabel;
    JTable spTable;

    public StorePanel(){
        initComponents();
        loadProductList();
       
    }

    public void initComponents(){
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
        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                searchAndFilterAction(e);
            }
        });

        //Tạo thanh nhập tìm kiếm  
        searchTf = new JTextField();
        searchTf.setBounds(130,45,470,22);

        //Tạo mục checkbox để lọc sản phẩm
        String cb[] = {"Tất cả", "Iphone", "Samsung", "Xiaomi", "Realme", "Huawei", "Vinsmart"};
        filterBox = new JComboBox(cb);
        filterBox.setBounds(610,45,80,22);
        filterBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                searchAndFilterAction(e);
            }
        });

        //Taọ bảng sản phẩm
        spTable = new JTable();
        JScrollPane sp = new JScrollPane(spTable);
        sp.setBounds(10,80,720,570);

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
        inBillBtn = new JButton("In bill");
        
        nameLabel.setBounds(50,400,150,20);

        phoneLabel.setBounds(50,430,80,20);
        phoneTf.setBounds(125,432,100,20);
        tongLabel.setBounds(50,460,80,20);
        tongTf.setBounds(125,462,100,20);
        nhanLabel.setBounds(50,490,80,20);
        nhanTf.setBounds(125,492,100,20);
        thoiLabel.setBounds(50,520,80,20);
        thoiTf.setBounds(125,522,100,20);

        thanhToanBtn.setBounds(40,570,105,30);
        inBillBtn.setBounds(175,570,105,30);

        //Thêm giao diện vào
        spPanel.add(titlelb);spPanel.add(searchBtn);spPanel.add(searchTf);spPanel.add(filterBox);spPanel.add(sp);
        payPanel.add(payTitle);payPanel.add(nameLabel);payPanel.add(payScrollPane);payPanel.add(phoneLabel);payPanel.add(phoneTf);
        payPanel.add(tongLabel);payPanel.add(tongTf);payPanel.add(nhanLabel);payPanel.add(nhanTf);payPanel.add(thoiLabel);payPanel.add(thoiTf);
        payPanel.add(thanhToanBtn);payPanel.add(inBillBtn);
        add(spPanel);add(payPanel);
    }

    public void loadProductList(){
         //Tạo modelSP cho table chứa sản phẩm
         DefaultTableModel modelSP = new DefaultTableModel();;
         modelSP.addColumn("Mã điện thoại");
         modelSP.addColumn("Tên điện thoại");
         modelSP.addColumn("Hãng");
         modelSP.addColumn("Giá");
         modelSP.addColumn("Số lượng");
         spTable.setModel(modelSP);
 
         Vector<ProductsDTO> arr = new Vector<ProductsDTO>();
         arr = productsBLL.getAllProducts();
         for(int i=0;i<arr.size();i++){
             ProductsDTO p = arr.get(i);
             int ma = p.getProductID();
             String ten = p.getProductName();
             String hang = p.getBrand();
             BigDecimal gia = p.getPrices();
             int soLuong = p.getStock();
             Object[] row = {ma, ten, hang, gia, soLuong};
             modelSP.addRow(row);
        }
    }

    public void searchAndFilterAction(ActionEvent e){
        String searchStr = searchTf.getText().trim().toLowerCase();
        String filterStr = filterBox.getSelectedItem().toString();
        Vector<ProductsDTO> arr = new Vector<ProductsDTO>();
        arr = productsBLL.getAllProducts();

        DefaultTableModel modelSp = new DefaultTableModel();
        modelSp.addColumn("Mã điện thoại");
        modelSp.addColumn("Tên điện thoại");
        modelSp.addColumn("Hãng");
        modelSp.addColumn("Giá");
        modelSp.addColumn("Số lượng");

        for(int i=0;i<arr.size();i++){
            ProductsDTO p = arr.get(i);
            String productName = p.getProductName().toLowerCase();
            String productBrand = p.getBrand();
            boolean matchSearch = false;
            boolean matchFilter = false;

            if(searchStr.equals("")||productName.contains(searchStr)){
                matchSearch = true;
            }
            if(filterStr.equals("Tất cả")||productBrand.equals(filterStr)){
                matchFilter = true;
            }
            if(matchSearch&&matchFilter){
                int ma = p.getProductID();
                String ten = p.getProductName();
                String hang = p.getBrand();
                BigDecimal gia = p.getPrices();
                int soLuong = p.getStock();
                Object[] row = {ma, ten, hang, gia, soLuong};
                modelSp.addRow(row);
            }
        }
        spTable.setModel(modelSp);
    }

}
