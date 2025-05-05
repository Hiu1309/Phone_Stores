package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.*;
import DTO.*;

public class AccountForm extends JPanel{
    EmployeeBLL emplBLL = new EmployeeBLL();
    DefaultTableModel empModelTable;
    JTable tb;
    JButton nvButton, khButton;
    JLabel nameLb, lb2, phoneLb;
    JTextField nameTf, tf2, phoneTf;
    JButton themBtn, suaBtn, xoaBtn;

    public AccountForm(){
        initComponents();
        loadEmployee();
    }

    public void initComponents(){
        setLayout(new BorderLayout());
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setPreferredSize(new Dimension(1050,80));
        topPanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));

        JLabel titleLabel = new JLabel("Danh Sách Tài Khoản");
        titleLabel.setFont(new Font("Arial",Font.BOLD,25));
        titleLabel.setBounds(360,5,290,50);
        nvButton = new JButton("Nhân viên");
        khButton = new JButton("Khách hàng");
        nvButton.setBounds(375,50,102,23);
        khButton.setBounds(495,50,102,23);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);

        tb = new JTable();
        JScrollPane sp = new JScrollPane(tb);
        sp.setBounds(20,20,998, 400);

        tb.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                mouseClickedAction(e);
            }
        });

        nameLb = new JLabel("Username");
        lb2 = new JLabel("Password");
        phoneLb = new JLabel("Phone");

        nameLb.setBounds(95,455,150,30);
        lb2.setBounds(375,455,150,30);
        phoneLb.setBounds(655,455,150,30);

        nameTf = new JTextField();
        tf2 = new JTextField();
        phoneTf = new JTextField();

        nameTf.setBounds(44,500,150,20);
        tf2.setBounds(325,500,150,20);
        phoneTf.setBounds(602,500,150,20);

        themBtn = new JButton("Thêm");
        suaBtn = new JButton("Sửa");
        xoaBtn = new JButton("Xóa");

        themBtn.setBounds(882,442,100,20);
        suaBtn.setBounds(882,482,100,20);
        xoaBtn.setBounds(882,522,100,20);

        topPanel.add(titleLabel);topPanel.add(nvButton);topPanel.add(khButton);


        contentPanel.add(sp);contentPanel.add(nameLb);contentPanel.add(lb2);contentPanel.add(phoneLb);
        contentPanel.add(nameTf);contentPanel.add(tf2);contentPanel.add(phoneTf);contentPanel.add(themBtn);
        contentPanel.add(suaBtn);contentPanel.add(xoaBtn);

        add(topPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }
    
    public void loadEmployee(){
        Vector<EmployeeDTO> arr = new Vector<EmployeeDTO>();
        empModelTable = new DefaultTableModel();
        empModelTable.addColumn("EmployeeID"); 
        empModelTable.addColumn("Username"); 
        empModelTable.addColumn("Password"); 
        empModelTable.addColumn("Phone"); 
        tb.setModel(empModelTable);

        arr = emplBLL.getAllEmployees();
        for(int i=0;i<arr.size();i++){
            EmployeeDTO e = arr.get(i);
            int id = e.getEmployeeID();
            String name = e.getUsername();
            String pass = e.getPassword();
            String phone = e.getPhone();
            Object[] row = {id, name, pass, phone};
            empModelTable.addRow(row);
        }
    }

    private void mouseClickedAction(MouseEvent e){
        int i = tb.getSelectedRow();
        if(i>=0){
            nameTf.setText(empModelTable.getValueAt(i, 1).toString());
            tf2.setText(empModelTable.getValueAt(i, 2).toString());
            phoneTf.setText(empModelTable.getValueAt(i, 3).toString());
        }
    }
}
