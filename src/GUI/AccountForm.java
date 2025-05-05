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
    JTextField nameTf, passTf, phoneTf, emailTf, addressTf;
    JButton themBtn, suaBtn, xoaBtn, rsBtn;

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

        //Các label
        JLabel inputLb = new JLabel("Điền Thông Tin:");
        inputLb.setFont(new Font("Arial", Font.BOLD, 17));
        inputLb.setBounds(22, 430, 200, 40);

        JLabel nameLb = new JLabel("Tên nhân viên");
        nameLb.setBounds(30,488,100,30);
        JLabel passLb = new JLabel("Mật khẩu");
        passLb.setBounds(220,488,100,30);
        JLabel phoneLb = new JLabel("Số điện thoại");
        phoneLb.setBounds(378,488,100,30);
        JLabel emailLb = new JLabel("Email");
        emailLb.setBounds(595,488,100,30);
        JLabel addressLb = new JLabel("Địa chỉ");
        addressLb.setBounds(780,488,100,30);

        //Các textfield điền vào
        nameTf = new JTextField();
        nameTf.setBounds(22,520,100,20);
        passTf = new JTextField();
        passTf.setBounds(195,520,100,20);
        phoneTf = new JTextField();
        phoneTf.setBounds(367,520,100,20);
        emailTf = new JTextField();
        emailTf.setBounds(560,520,100,20);
        addressTf = new JTextField();
        addressTf.setBounds(750,520,100,20);

        //Các nút
        themBtn = new JButton("Thêm");
        suaBtn = new JButton("Sửa");
        xoaBtn = new JButton("Xóa");
        rsBtn = new JButton("Làm mới");

        themBtn.setBounds(915,452,100,20);
        suaBtn.setBounds(915,482,100,20);
        xoaBtn.setBounds(915,512,100,20);
        rsBtn.setBounds(915,542,100,20);
        

        themBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                addEmployeeAction(e);
            }
        });

        suaBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                updateEmployeeAction(e);
            }
        });

        xoaBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                deleteEmployeeBtnAction(e);
            }
        });

        rsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                clearInputField();
            }
        });


        topPanel.add(titleLabel);topPanel.add(nvButton);topPanel.add(khButton);


        contentPanel.add(sp);//ScrollPane
        contentPanel.add(inputLb);contentPanel.add(nameLb);contentPanel.add(passLb);contentPanel.add(phoneLb);contentPanel.add(emailLb);
        contentPanel.add(addressLb);//Các label
        contentPanel.add(nameTf);contentPanel.add(passTf);contentPanel.add(phoneTf);contentPanel.add(emailTf);contentPanel.add(addressTf);//Các textfield
        contentPanel.add(themBtn);contentPanel.add(suaBtn);contentPanel.add(xoaBtn);contentPanel.add(rsBtn);//Các button

        add(topPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }
    
    public void loadEmployee(){
        Vector<EmployeeDTO> arr = new Vector<EmployeeDTO>();
        empModelTable = new DefaultTableModel();
        empModelTable.addColumn("Mã nhân viên"); 
        empModelTable.addColumn("Tên nhân viên");
        empModelTable.addColumn("Số điện thoại");
        empModelTable.addColumn("Email"); 
        empModelTable.addColumn("Ngày vào làm"); 
        empModelTable.addColumn("Địa chỉ nhà");
        tb.setModel(empModelTable);

        arr = emplBLL.getAllEmployees();
        for(int i=0;i<arr.size();i++){
            EmployeeDTO e = arr.get(i);
            int id = e.getEmployeeID();
            String name = e.getUsername();
            String phone = e.getPhone();
            String email = e.getEmail();
            Date jdate = e.getJoinDate();
            String address = e.getAddress();
            Object[] row = {id, name, phone, email, jdate, address};
            empModelTable.addRow(row);
        }
    }

    private void addEmployeeAction(ActionEvent e){
        try{
            String name = nameTf.getText().trim();
            String password = passTf.getText().trim();
            String phone = phoneTf.getText().trim();
            String email = emailTf.getText().trim();
            String address = addressTf.getText().trim();
            if(name.equals("")||password.equals("")||phone.equals("")||email.equals("")||address.equals("")){
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin");
                return;
            }

            if(password.length()<8){
                JOptionPane.showMessageDialog(this, "Mật khẩu phải từ 8 ký tự trở lên");
                return;
            }

            if(!phone.matches("\\d{10}")){
                JOptionPane.showMessageDialog(this, "Số điện thoại phải đủ 10 số và không chứa chữ hay ký tự đặc biệt");
                return;
            }

            if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(this, "Email không hợp lệ");
                return;
            }

            EmployeeDTO emp = new EmployeeDTO();
            emp.setPassword(password);
            emp.setUsername(name);
            emp.setPhone(phone);
            emp.setEmail(email);
            emp.setAddress(address);
            String result = emplBLL.addEmployee(emp);
            JOptionPane.showMessageDialog(this, result);
            loadEmployee();
            clearInputField();
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this,"Thông tin không hợp lệ");
        }
    }

    private void updateEmployeeAction(ActionEvent e){
        try{
            String name = nameTf.getText().trim();
            String password = passTf.getText().trim();
            String phone = phoneTf.getText().trim();
            String email = emailTf.getText().trim();
            String address = addressTf.getText().trim();

            if(password.length()<8){
                JOptionPane.showMessageDialog(this, "Mật khẩu phải từ 8 ký tự trở lên");
                return;
            }

            if(!phone.matches("\\d{10}")){
                JOptionPane.showMessageDialog(this, "Số điện thoại phải đủ 10 số và không chứa chữ hay ký tự đặc biệt");
                return;
            }

            if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(this, "Email không hợp lệ");
                return;
            }

            EmployeeDTO emp = new EmployeeDTO();
            emp.setUsername(name);
            emp.setPassword(password);
            emp.setPhone(phone);
            emp.setEmail(email);
            emp.setAddress(address);
            String result = emplBLL.updateEmployee(emp);
            JOptionPane.showMessageDialog(this, result);
            loadEmployee();
            clearInputField();
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
        }
    }

    private void deleteEmployeeBtnAction(ActionEvent e){
            String name = nameTf.getText().trim();
            if(name.equals("")){
                JOptionPane.showMessageDialog(this, "không có tên nhân viên để xóa");
            }
            String result = emplBLL.deleteEmployee(name);
            JOptionPane.showMessageDialog(this, result);
            loadEmployee();
            clearInputField();
    }

    private void mouseClickedAction(MouseEvent e){
        int i = tb.getSelectedRow();
        if(i>=0){
            nameTf.setText(empModelTable.getValueAt(i, 1).toString());
            phoneTf.setText(empModelTable.getValueAt(i, 2).toString());
            emailTf.setText(empModelTable.getValueAt(i, 3).toString());
            addressTf.setText(empModelTable.getValueAt(i, 5).toString());
        }
    }

    private void clearInputField(){
        nameTf.setText("");
        passTf.setText("");
        phoneTf.setText("");
        emailTf.setText("");
        addressTf.setText("");
    }
}
