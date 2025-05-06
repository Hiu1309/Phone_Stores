package DAL;

import DTO.SalesInvoiceDTO;
import java.sql.*;
import java.util.Vector;

public class SalesInvoiceDAL {
    public Vector<SalesInvoiceDTO> getAllSalesInvoices() {
        Vector<SalesInvoiceDTO> list = new Vector<SalesInvoiceDTO>();
        Connection con = DBConnection.openConnect();
        try {
            String sql = "SELECT * FROM salesinvoices";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                SalesInvoiceDTO invoice = new SalesInvoiceDTO();
                invoice.setInvoiceID(rs.getInt("InvoiceID"));
                invoice.setCustomerID(rs.getInt("CustomerID"));
                invoice.setEmployeeID(rs.getInt("EmployeeID"));
                invoice.setSaleDate(rs.getTimestamp("SaleDate"));
                invoice.setTotalAmount(rs.getBigDecimal("TotalAmount"));
                list.add(invoice);
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi cơ sở dữ liệu: " + ex.getMessage());
        } finally {
            DBConnection.closeConnect(con);
        }
        return list;
    }

    public boolean addSalesInvoice(SalesInvoiceDTO invoice) {
        boolean result = false;
        Connection con = DBConnection.openConnect();
        try {
            String sql = "INSERT INTO salesinvoices (CustomerID, EmployeeID, TotalAmount) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, invoice.getCustomerID());
            ps.setInt(2, invoice.getEmployeeID());
            ps.setBigDecimal(3, invoice.getTotalAmount()); 
    
            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    invoice.setInvoiceID(rs.getInt(1));
                }
                result = true;
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi cơ sở dữ liệu: " + ex.getMessage());
        } finally {
            DBConnection.closeConnect(con);
        }
        return result;
    }
    
}
