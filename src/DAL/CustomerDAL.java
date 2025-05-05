package DAL;

import DTO.CustomerDTO;
import java.sql.*;
import java.util.Vector;

public class CustomerDAL {
    public Vector<CustomerDTO> getAllCustomers() {
        Vector<CustomerDTO> customers = new Vector<>();
        Connection con = DBConnection.openConnect();
        try {
            String sql = "SELECT * FROM customers";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                CustomerDTO c = new CustomerDTO();
                c.setCustomerID(rs.getInt("CustomerID"));
                c.setFullName(rs.getString("FullName"));
                c.setPhone(rs.getString("Phone"));
                c.setEmail(rs.getString("Email"));
                c.setGender(rs.getString("Gender"));                
                c.setCreateDate(rs.getDate("CreateDate"));
                customers.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi khi lấy danh sách khách hàng: " + ex.getMessage());
        } finally {
            DBConnection.closeConnect(con);
        }
        return customers;
    }

    public boolean addCustomer(CustomerDTO c) {
        boolean result = false;
        Connection con = DBConnection.openConnect();
        try {
            String sql = "INSERT INTO customers(FullName, Phone, Email, Gender) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getFullName());
            ps.setString(2, c.getPhone());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getGender());
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi khi thêm khách hàng: " + ex.getMessage());
        } finally {
            DBConnection.closeConnect(con);
        }
        return result;
    }

    public boolean updateCustomer(CustomerDTO c) {
        boolean result = false;
        Connection con = DBConnection.openConnect();
        try {
            String sql = "UPDATE customers SET FullName = ?, Phone = ?, Email = ?, Gender = ? WHERE CustomerID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getFullName());
            ps.setString(2, c.getPhone());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getGender());            
            ps.setInt(5, c.getCustomerID());
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi khi cập nhật khách hàng: " + ex.getMessage());
        } finally {
            DBConnection.closeConnect(con);
        }
        return result;
    }

    public boolean deleteCustomer(int id) {
        boolean result = false;
        Connection con = DBConnection.openConnect();
        try {
            String sql = "DELETE FROM customers WHERE CustomerID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi khi xóa khách hàng: " + ex.getMessage());
        } finally {
            DBConnection.closeConnect(con);
        }
        return result;
    }
}
