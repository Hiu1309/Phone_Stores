package GUI;

import BLL.InvoiceViewBLL;
import DTO.InvoiceViewDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class InvoicePanel extends JPanel {
    private JTable invoiceTable;
    private DefaultTableModel model;
    private JTextField txtSearch;
    private JButton btnAll, btnSearchByCustomer, btnSearchByEmployee;
    private final InvoiceViewBLL bll = new InvoiceViewBLL();

    public InvoicePanel() {
        initComponents();
        loadData(bll.getAllInvoiceViews());
    }

    private void initComponents(){
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("QUẢN LÝ HÓA ĐƠN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        
        String[] columnNames = {"Mã hóa đơn", "Nhân viên", "Khách hàng", "Sản phẩm", "Ngày bán", "Đơn giá", "Số lượng", "Tổng tiền"};
        model = new DefaultTableModel(columnNames, 0);
        invoiceTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(invoiceTable);
        add(scrollPane, BorderLayout.CENTER);

        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtSearch = new JTextField(15);
        btnAll = new JButton("Tất cả");
        btnSearchByCustomer = new JButton("Lọc theo KH");
        btnSearchByEmployee = new JButton("Lọc theo NV");

        topPanel.add(new JLabel("Tìm kiếm:"));
        topPanel.add(txtSearch);
        topPanel.add(btnSearchByCustomer);
        topPanel.add(btnSearchByEmployee);
        topPanel.add(btnAll);
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(lblTitle, BorderLayout.NORTH);
        headerPanel.add(topPanel, BorderLayout.SOUTH);
        add(headerPanel, BorderLayout.NORTH);
      
        btnAll.addActionListener(e -> loadData(bll.getAllInvoiceViews()));

        btnSearchByCustomer.addActionListener(e -> {
            String keyword = txtSearch.getText().trim();
            if (!keyword.isEmpty()) {
                loadData(bll.getInvoicesByCustomerName(keyword));
            }
        });

        btnSearchByEmployee.addActionListener(e -> {
            String keyword = txtSearch.getText().trim();
            if (!keyword.isEmpty()) {
                loadData(bll.getInvoicesByEmployeeName(keyword));
            }
        });
    }

    private void loadData(Vector<InvoiceViewDTO> list) {
        model.setRowCount(0);
        for (InvoiceViewDTO dto : list) {
            model.addRow(new Object[]{
                dto.getInvoiceID(),
                dto.getEmployeeName(),
                dto.getCustomerName(),
                dto.getProductName(),
                dto.getSaleDate(),
                dto.getUnitPrice(),
                dto.getQuantity(),
                dto.getTotalPrice()
            });
        }
    }
}
