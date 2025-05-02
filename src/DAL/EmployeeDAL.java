package DAL;

import DTO.EmployeeDTO;
import java.sql.*;

public class EmployeeDAL {
    private Connection con;

    public EmployeeDAL(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phone_store", "root", "");
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public EmployeeDTO login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM employees WHERE Username = ? AND Password = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            EmployeeDTO emp = new EmployeeDTO();
            emp.setEmployeeID(rs.getInt("EmployeeID"));
            emp.setUsername(rs.getString("Username"));
            emp.setFullName(rs.getString("FullName"));
            emp.setPhone(rs.getString("Phone"));
            emp.setRole(rs.getString("Role"));
            return emp;
        }
        return null;
    }

    public boolean isUsernameExist(String username) throws SQLException {
        String sql = "SELECT * FROM employees WHERE Username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public void register(EmployeeDTO emp) throws SQLException {
        String sql = "INSERT INTO employees (Username, Password, FullName, Phone, Role) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, emp.getUsername());
        ps.setString(2, emp.getPassword());
        ps.setString(3, emp.getFullName());
        ps.setString(4, emp.getPhone());
        ps.setString(5, emp.getRole());
        ps.executeUpdate();
    }
}
