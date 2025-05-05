package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.CustomerBLL;
import DTO.CustomerDTO;

public class CustomerPanel extends JPanel {
    private CustomerBLL customerBLL = new CustomerBLL();
    private DefaultTableModel customerTableModel;
    private JTable table;

    private JTextField idTf, nameTf, phoneTf, emailTf;
    private JComboBox<String> genderCb;
    private JButton addBtn, updateBtn, deleteBtn, searchBtn, resetBtn;

    public CustomerPanel() {
        initComponents();
        loadCustomerData();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Quản Lý Khách Hàng", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // Table
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                onRowClick();
            }
        });

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        idTf = new JTextField("ID");
        idTf.setEnabled(false);
        nameTf = new JTextField();
        phoneTf = new JTextField();
        emailTf = new JTextField();
        genderCb = new JComboBox<>(new String[]{"Nam", "Nữ", "Khác"});

        formPanel.add(new JLabel("Họ tên:"));
        formPanel.add(nameTf);
        formPanel.add(new JLabel("Số điện thoại:"));
        formPanel.add(phoneTf);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailTf);
        formPanel.add(new JLabel("Giới tính:"));
        formPanel.add(genderCb);
        formPanel.add(new JLabel("Mã khách hàng:"));
        formPanel.add(idTf);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        addBtn = new JButton("Thêm");
        updateBtn = new JButton("Sửa");
        deleteBtn = new JButton("Xoá");
        searchBtn = new JButton("Tìm kiếm");
        resetBtn = new JButton("Làm mới");

        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(resetBtn);

        // Gộp formPanel và buttonPanel lại
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(formPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        // Actions
        addBtn.addActionListener(e -> addCustomerAction());
        updateBtn.addActionListener(e -> updateCustomerAction());
        deleteBtn.addActionListener(e -> deleteCustomerAction());
        resetBtn.addActionListener(e -> clearFields());
    }

    private void loadCustomerData() {
        Vector<CustomerDTO> customers = customerBLL.getAllCustomers();
        customerTableModel = new DefaultTableModel();
        customerTableModel.addColumn("ID");
        customerTableModel.addColumn("Họ tên");
        customerTableModel.addColumn("SĐT");
        customerTableModel.addColumn("Email");
        customerTableModel.addColumn("Ngày tạo");
        customerTableModel.addColumn("Giới tính");

        for (CustomerDTO c : customers) {
            customerTableModel.addRow(new Object[]{
                c.getCustomerID(), c.getFullName(), c.getPhone(), c.getEmail(),
                c.getCreateDate(), c.getGender()
            });
        }

        table.setModel(customerTableModel);
    }

    private void addCustomerAction() {
        String name = nameTf.getText().trim();
        String phone = phoneTf.getText().trim();
        String email = emailTf.getText().trim();
        String gender = (String) genderCb.getSelectedItem();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin");
            return;
        }

        if (!phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "SĐT phải gồm 10 chữ số");
            return;
        }

        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ");
            return;
        }

        CustomerDTO c = new CustomerDTO();
        c.setFullName(name);
        c.setPhone(phone);
        c.setEmail(email);
        c.setGender(gender);

        String result = customerBLL.addCustomer(c);
        JOptionPane.showMessageDialog(this, result);
        if (result.contains("thành công")) {
            loadCustomerData();
            clearFields();
        }
    }

    private void updateCustomerAction() {
        try {
            int id = Integer.parseInt(idTf.getText().trim());
            String name = nameTf.getText().trim();
            String phone = phoneTf.getText().trim();
            String email = emailTf.getText().trim();
            String gender = (String) genderCb.getSelectedItem();

            if (!phone.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(this, "SĐT phải gồm 10 chữ số");
                return;
            }

            if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(this, "Email không hợp lệ");
                return;
            }

            CustomerDTO c = new CustomerDTO();
            c.setCustomerID(id);
            c.setFullName(name);
            c.setPhone(phone);
            c.setEmail(email);
            c.setGender(gender);

            String result = customerBLL.updateCustomer(c);
            JOptionPane.showMessageDialog(this, result);
            if (result.contains("thành công")) {
                loadCustomerData();
                clearFields();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Mã khách hàng không hợp lệ");
        }
    }

    private void deleteCustomerAction() {
        try {
            int id = Integer.parseInt(idTf.getText().trim());
            String result = customerBLL.deleteCustomer(id);
            JOptionPane.showMessageDialog(this, result);
            if (result.contains("thành công")) {
                loadCustomerData();
                clearFields();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID không hợp lệ");
        }
    }

    private void onRowClick() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            idTf.setText(customerTableModel.getValueAt(row, 0).toString());
            nameTf.setText((String) customerTableModel.getValueAt(row, 1));
            phoneTf.setText((String) customerTableModel.getValueAt(row, 2));
            emailTf.setText((String) customerTableModel.getValueAt(row, 3));
            genderCb.setSelectedItem(customerTableModel.getValueAt(row, 5));
        }
    }

    private void clearFields() {
        idTf.setText("ID");
        nameTf.setText("");
        phoneTf.setText("");
        emailTf.setText("");
        genderCb.setSelectedIndex(0);
    }
}
