package GUI;

import BLL.InvoiceViewBLL;
import DTO.InvoiceViewDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class InvoicePanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JComboBox<String> cbFilter;

    private InvoiceViewBLL invoiceBLL = new InvoiceViewBLL();

    public InvoicePanel() {
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("DANH SÁCH HÓA ĐƠN BÁN", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitle, BorderLayout.NORTH);

        // Bảng dữ liệu
        model = new DefaultTableModel(new String[]{"Mã HĐ", "Khách hàng", "Nhân viên", "Ngày bán", "Tổng tiền"}, 0);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        // Panel lọc
        JPanel searchPanel = new JPanel();
        cbFilter = new JComboBox<>(new String[]{"Tất cả", "Theo tên KH", "Theo tên NV", "Theo ngày"});
        txtSearch = new JTextField(20);
        btnSearch = new JButton("Lọc");
        searchPanel.add(cbFilter);
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        add(searchPanel, BorderLayout.SOUTH);

        loadTable(invoiceBLL.getAllInvoiceViews());

        btnSearch.addActionListener(e -> {
            String selected = cbFilter.getSelectedItem().toString();
            String keyword = txtSearch.getText().trim();

            switch (selected) {
                case "Tất cả":
                    loadTable(invoiceBLL.getAllInvoiceViews());
                    break;
                case "Theo tên KH":
                    loadTable(invoiceBLL.getInvoiceViewsByCustomerName(keyword));
                    break;
                case "Theo tên NV":
                    loadTable(invoiceBLL.getInvoiceViewsByEmployeeName(keyword));
                    break;
                case "Theo ngày":
                    try {
                        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(keyword);
                        loadTable(invoiceBLL.getInvoiceViewsByDate(date));
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Ngày không hợp lệ (định dạng yyyy-MM-dd)");
                    }
                    break;
            }
        });
    }

    private void loadTable(Vector<InvoiceViewDTO> list) {
        model.setRowCount(0);
        for (InvoiceViewDTO iv : list) {
            model.addRow(new Object[]{
                iv.getInvoiceID(),
                iv.getCustomerName(),
                iv.getEmployeeName(),
                new SimpleDateFormat("yyyy-MM-dd").format(iv.getSaleDate()),
                iv.getTotalPrice()
            });
        }
    }
}
