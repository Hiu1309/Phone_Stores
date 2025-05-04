package GUI;

import BLL.EmployeeBLL;
import DTO.EmployeeDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm extends JFrame {
    private JTextField usernameField, fullNameField, phoneField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegisterForm() {
        ImageIcon favicon = new ImageIcon("img/other/newera-logo.png");
        setIconImage(favicon.getImage());
        setTitle("Sign up to start shopping");
        setSize(700, 450);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
       

        // Left panel (màu xanh lá)
        JPanel leftPanel = new JPanel(null);
        leftPanel.setBackground(new Color(59, 228, 119));
        leftPanel.setPreferredSize(new Dimension(180, 550));
        add(leftPanel, BorderLayout.WEST);

        ImageIcon barcode = new ImageIcon("img/other/barcode.png");
        Image barcode1 = barcode.getImage().getScaledInstance(200, 900, Image.SCALE_SMOOTH); // điều chỉnh kích thước ở đây
        ImageIcon barcode2 = new ImageIcon(barcode1);
        JLabel leftImage = new JLabel(barcode2);
        leftImage.setBounds(-80, -200, 200, 900); // cập nhật theo kích thước mới
        leftPanel.add(leftImage);

        // Right panel (form đăng ký)
        JPanel rightPanel = new JPanel(null);
        rightPanel.setBackground(new Color(18, 18, 18));
        add(rightPanel, BorderLayout.CENTER);

        JLabel title = new JLabel("Sign up to start shopping");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(100, 30, 400, 30);
        rightPanel.add(title);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(60, 100, 100, 25);
        rightPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(180, 100, 250, 30);
        rightPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(60, 220, 100, 25);
        rightPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 220, 250, 30);
        rightPanel.add(passwordField);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(60, 160, 100, 25);
        rightPanel.add(nameLabel);

        fullNameField = new JTextField();
        fullNameField.setBounds(180, 160, 250, 30);
        rightPanel.add(fullNameField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setForeground(Color.WHITE);
        phoneLabel.setBounds(60, 280, 100, 25);
        rightPanel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(180, 280, 250, 30);
        rightPanel.add(phoneField);

        registerButton = new JButton("Confirm");
        registerButton.setBounds(310, 330, 120, 35);
        registerButton.setBackground(new Color(30, 215, 96));
        registerButton.setForeground(Color.black);
        registerButton.setFocusable(false);
        rightPanel.add(registerButton);

        JLabel question = new JLabel("Already have an account?");
        question.setForeground(new Color(188, 186, 186));
        question.setBounds(130, 380, 180, 25);
        rightPanel.add(question);

        JButton loginLink = new JButton("Log in here");
        loginLink.setBounds(260, 378, 120, 28);
        loginLink.setBorderPainted(false);
        loginLink.setForeground(new Color(35, 169, 242));
        loginLink.setContentAreaFilled(false);
        loginLink.setFocusPainted(false);
        rightPanel.add(loginLink);

        // Xử lý sự kiện
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = usernameField.getText().trim();
                    String password = new String(passwordField.getPassword()).trim();
                    String fullName = fullNameField.getText().trim();
                    String phone = phoneField.getText().trim();

                    if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() || phone.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                        return;
                    }

                    if (username.contains(" ")) {
                        JOptionPane.showMessageDialog(null, "Username không được chứa khoảng trắng!");
                        return;
                    }

                    if (password.length() < 6) {
                        JOptionPane.showMessageDialog(null, "Password phải có ít nhất 6 ký tự!");
                        return;
                    }

                    EmployeeBLL bll = new EmployeeBLL();
                    if (bll.isUsernameExist(username)) {
                        JOptionPane.showMessageDialog(null, "Username đã tồn tại!");
                        return;
                    }

                    EmployeeDTO emp = new EmployeeDTO();
                    emp.setUsername(username);
                    emp.setPassword(password);
                    emp.setFullName(fullName);
                    emp.setPhone(phone);
                    emp.setRole("staff");

                    bll.register(emp);
                    JOptionPane.showMessageDialog(null, "Đăng ký thành công!");
                    new LoginForm();
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi đăng ký");
                }
            }
        });

        loginLink.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginForm();
                dispose();
            }
        });

        setVisible(true);
    }
}
