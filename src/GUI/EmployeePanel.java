package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.*;
import DTO.*;

public class EmployeePanel extends JPanel{
    private EmployeeBLL emplBLL = new EmployeeBLL();
    private DefaultTableModel empModelTable;
    private JTable tb;
    private JButton nvButton, khButton;
    private JTextField idTf, nameTf, passTf, phoneTf, emailTf, addressTf;
    private JButton themBtn, suaBtn, xoaBtn, kiemBtn, rsBtn;

    public EmployeePanel(){
        initComponents();
        loadEmployee();
    }

    public void initComponents(){
        setLayout(new BorderLayout());
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setPreferredSize(new Dimension(1050,60));
        topPanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));

        JLabel titleLabel = new JLabel("Danh Sách Nhân Viên");
        titleLabel.setFont(new Font("Arial",Font.BOLD,25));
        titleLabel.setBounds(360,5,290,50);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);

        tb = new JTable();
        JScrollPane sp = new JScrollPane(tb);
        sp.setBounds(20,20,998, 450);

        tb.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                mouseClickedAction(e);
            }
        });

        //Các label
        JLabel inputLb = new JLabel("Điền Thông Tin:");
        inputLb.setFont(new Font("Arial", Font.BOLD, 18));
        inputLb.setBounds(22, 480, 200, 40);

        JLabel nameLb = new JLabel("Tên Nhân Viên:");
        nameLb.setBounds(22,520,100,30);
        JLabel passLb = new JLabel("Mật Khẩu:");
        passLb.setBounds(255,520,100,30);
        JLabel phoneLb = new JLabel("Số Điện Thoại:");
        phoneLb.setBounds(457,520,100,30);
        JLabel emailLb = new JLabel("Email:");
        emailLb.setBounds(687,520,100,30);
        JLabel addressLb = new JLabel("Địa Chỉ:");
        addressLb.setBounds(868,520,100,30);

        //Các textfield điền vào
        idTf = new JTextField("Mã Nhân Viên");
        idTf.setEnabled(false);
        idTf.setBounds(172, 492,80,20);
        nameTf = new JTextField();
        nameTf.setBounds(115,527,100,20);
        passTf = new JTextField();
        passTf.setBounds(318,527,100,20);
        phoneTf = new JTextField();
        phoneTf.setBounds(547,527,100,20);
        emailTf = new JTextField();
        emailTf.setBounds(727,527,100,20);
        addressTf = new JTextField();
        addressTf.setBounds(918,527,100,20);

        //Các nút
        themBtn = new JButton("Thêm");
        suaBtn = new JButton("Sửa");
        xoaBtn = new JButton("Xóa");
        kiemBtn = new JButton("Tìm kiếm");
        rsBtn = new JButton("Làm mới");

        themBtn.setBounds(120,565,100,20);
        suaBtn.setBounds(270,565,100,20);
        xoaBtn.setBounds(420,565,100,20);
        kiemBtn.setBounds(570,565,100,20);
        rsBtn.setBounds(720,565,100,20);

        topPanel.add(titleLabel);

        //ScrollPane
        contentPanel.add(sp);
        //Các label
        contentPanel.add(inputLb);contentPanel.add(nameLb);contentPanel.add(passLb);contentPanel.add(phoneLb);contentPanel.add(emailLb);
        contentPanel.add(addressLb);
        //Các textfield
        contentPanel.add(nameTf);contentPanel.add(passTf);contentPanel.add(phoneTf);contentPanel.add(emailTf);contentPanel.add(addressTf);
        contentPanel.add(idTf);
        //Các button
        contentPanel.add(themBtn);contentPanel.add(suaBtn);contentPanel.add(xoaBtn);contentPanel.add(rsBtn);contentPanel.add(kiemBtn);

        add(topPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        
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
            if(result.equals("Thêm thành công")){
                loadEmployee();
                clearInputField();
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this,"Thông tin không hợp lệ");
        }
    }

    private void updateEmployeeAction(ActionEvent e){
        try{
            int id = Integer.parseInt(idTf.getText().trim());
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
            emp.setEmployeeID(id);
            emp.setUsername(name);
            emp.setPassword(password);
            emp.setPhone(phone);
            emp.setEmail(email);
            emp.setAddress(address);
            String result = emplBLL.updateEmployee(emp);
            JOptionPane.showMessageDialog(this, result);
            if(result.equals("Cập nhật thông tin nhân viên thành công")){
                loadEmployee();
                clearInputField();
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
        }
    }

    private void deleteEmployeeBtnAction(ActionEvent e){
            int id = Integer.parseInt(idTf.getText().trim());
            String result = emplBLL.deleteEmployee(id);
            JOptionPane.showMessageDialog(this, result);
            if(result.equals("Xoá nhân viên thành công")){            
                loadEmployee();
                clearInputField();
            }
    }

    private void mouseClickedAction(MouseEvent e){
        int i = tb.getSelectedRow();
        if(i>=0){
            Object emailObj = empModelTable.getValueAt(i, 3);
            Object addressObj = empModelTable.getValueAt(i,5);
            if(emailObj!=null){
                emailTf.setText(emailObj.toString());
            }
            else{
                emailTf.setText("");
            }
            if(addressObj!=null){
                addressTf.setText(addressObj.toString());
            }
            else{
                addressTf.setText("");
            }
            idTf.setText(empModelTable.getValueAt(i, 0).toString());
            nameTf.setText(empModelTable.getValueAt(i, 1).toString());
            phoneTf.setText(empModelTable.getValueAt(i, 2).toString());            
        }
    }

    private void clearInputField(){
        idTf.setText("MãNv");
        nameTf.setText("");
        passTf.setText("");
        phoneTf.setText("");
        emailTf.setText("");
        addressTf.setText("");
    }
}
