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
    private ProductsBLL productsBLL = new ProductsBLL();
    private EmployeeDTO currentEmployee;
    private DefaultTableModel modelTT;
    private JTextField searchTf, phoneTf, tongTf, nhanTf, thoiTf;
    private JButton searchBtn, addBtn, deleteBtn, resetBtn, payBtn, printBtn;
    private JComboBox filterBox;
    private JLabel nameLabel;
    private JTable spTable;

    public StorePanel(EmployeeDTO emp){
        this.currentEmployee=emp;
        initComponents();
        loadProductList();
       
    }

    public void initComponents(){
        setLayout(null);

        //Panel mục hiện sản phẩm
        JPanel spPanel = new JPanel();
        spPanel.setLayout(null);
        spPanel.setBounds(0,0,740,700);
        spPanel.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.black));

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
        String cb[] = {"Tất cả", "Apple", "Samsung", "Xiaomi", "Realme", "Huawei", "Vinsmart"};
        filterBox = new JComboBox(cb);
        filterBox.setBounds(610,45,80,22);

        //Taọ bảng sản phẩm
        spTable = new JTable();
        JScrollPane sp = new JScrollPane(spTable);
        sp.setBounds(10,80,720,570);

        //Panel thanh toán
        JPanel payPanel = new JPanel();
        payPanel.setLayout(null);
        payPanel.setBounds(740,0,380,700);

        //Title thanh toán
        JLabel payTitle = new JLabel("Thanh Toán");
        payTitle.setBounds(95,35,200,50);
        payTitle.setFont(new Font("Arial", Font.BOLD, 20));

        //Table hiện thị sản phẩm trong mục thanh toán
        modelTT = new DefaultTableModel();
        JTable payTable = new JTable();
        JScrollPane payScrollPane = new JScrollPane(payTable);
        payScrollPane.setBounds(10,80,277,300);
        modelTT.addColumn("Tên điện thoại");
        modelTT.addColumn("Giá");
        modelTT.addColumn("Số lượng");
        payTable.setModel(modelTT);

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

        addBtn = new JButton("Thêm");
        deleteBtn = new JButton("Xóa");
        resetBtn = new JButton("Làm mới");
        payBtn = new JButton("Thanh Toán");
        printBtn = new JButton("In bill");

        addBtn.setBounds(15,400,80,22);
        deleteBtn.setBounds(105,400,80,22);
        resetBtn.setBounds(195,400,85,22);
        
        nameLabel.setBounds(50,450,150,20);

        phoneLabel.setBounds(50,475,80,20);
        phoneTf.setBounds(125,477,100,20);
        tongLabel.setBounds(50,500,80,20);
        tongTf.setBounds(125,502,100,20);
        nhanLabel.setBounds(50,525,80,20);
        nhanTf.setBounds(125,527,100,20);
        thoiLabel.setBounds(50,550,80,20);
        thoiTf.setBounds(125,552,100,20);

        payBtn.setBounds(30,600,105,30);
        printBtn.setBounds(165,600,105,30);

        //Thêm giao diện vào
        spPanel.add(titlelb);spPanel.add(searchBtn);spPanel.add(searchTf);spPanel.add(filterBox);spPanel.add(sp);
        payPanel.add(payTitle);payPanel.add(nameLabel);payPanel.add(payScrollPane);payPanel.add(phoneLabel);payPanel.add(phoneTf);
        payPanel.add(tongLabel);payPanel.add(tongTf);payPanel.add(nhanLabel);payPanel.add(nhanTf);payPanel.add(thoiLabel);payPanel.add(thoiTf);
        payPanel.add(payBtn);payPanel.add(printBtn);payPanel.add(addBtn);payPanel.add(deleteBtn);payPanel.add(resetBtn);
        add(spPanel);add(payPanel);

        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                searchAndFilterAction(e);
            }
        });

        filterBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                searchAndFilterAction(e);
            }
        });

        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                addBtnProduct(e);
            }
        });

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

    private void addBtnProduct(ActionEvent e){
        int rowSl = spTable.getSelectedRow();
        if(rowSl>=0){
            String input = JOptionPane.showInputDialog(this, "Nhập số lượng:");
            if(input!=null&&input.matches("\\d+")){                
                int stockSp = Integer.parseInt(spTable.getValueAt(rowSl, 4).toString());
                int sl = Integer.parseInt(input);
                if(sl>stockSp){
                    JOptionPane.showMessageDialog(this, "Vượt quá số lượng có thể mua");
                    return;
                }                    
                String tenSp = spTable.getValueAt(rowSl, 1).toString();
                BigDecimal giaSp = new BigDecimal(spTable.getValueAt(rowSl,3).toString());
                BigDecimal sumGiaSp = giaSp.multiply(BigDecimal.valueOf(sl));
                modelTT.addRow(new Object[]{tenSp, sumGiaSp, sl});
                JOptionPane.showMessageDialog(this, "Thêm thành công");                
            }
        }
    }
}
