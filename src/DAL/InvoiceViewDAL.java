package DAL;

import DTO.InvoiceViewDTO;
import java.sql.*;
import java.util.Vector;
import java.math.BigDecimal;

public class InvoiceViewDAL {

    private InvoiceViewDTO extractInvoice(ResultSet rs) throws SQLException {
        InvoiceViewDTO dto = new InvoiceViewDTO();
        dto.setInvoiceID(rs.getInt("InvoiceID"));
        dto.setEmployeeName(rs.getString("EmployeeName"));
        dto.setCustomerName(rs.getString("CustomerName"));
        dto.setProductName(rs.getString("ProductName"));
        dto.setSaleDate(rs.getTimestamp("SaleDate"));
        dto.setUnitPrice(rs.getBigDecimal("Prices"));
        dto.setQuantity(rs.getInt("Quantity"));
        dto.setTotalPrice(rs.getBigDecimal("TotalPrices"));
        return dto;
    }

    public Vector<InvoiceViewDTO> getAllInvoiceViews() {
        Vector<InvoiceViewDTO> invoices = new Vector<>();
        Connection con = DBConnection.openConnect();
        try {
            String sql = """
                SELECT si.InvoiceID, e.Username AS EmployeeName, c.FullName AS CustomerName, 
                       p.ProductName, si.SaleDate, sid.Prices, sid.Quantity, sid.TotalPrices
                FROM salesinvoices si
                JOIN employees e ON si.EmployeeID = e.EmployeeID
                JOIN customers c ON si.CustomerID = c.CustomerID
                JOIN salesinvoicedetails sid ON si.InvoiceID = sid.InvoiceID
                JOIN products p ON sid.ProductID = p.ProductID
                ORDER BY si.InvoiceID DESC
            """;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                invoices.add(extractInvoice(rs));
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi khi lấy danh sách hóa đơn: " + ex.getMessage());
        } finally {
            DBConnection.closeConnect(con);
        }
        return invoices;
    }

    public Vector<InvoiceViewDTO> getInvoicesByCustomerName(String name) {
        Vector<InvoiceViewDTO> list = new Vector<>();
        Connection con = DBConnection.openConnect();
        try {
            String sql = """
                SELECT si.InvoiceID, e.Username AS EmployeeName, c.FullName AS CustomerName,
                       p.ProductName, si.SaleDate, sid.Prices, sid.Quantity, sid.TotalPrices
                FROM salesinvoices si
                JOIN employees e ON si.EmployeeID = e.EmployeeID
                JOIN customers c ON si.CustomerID = c.CustomerID
                JOIN salesinvoicedetails sid ON si.InvoiceID = sid.InvoiceID
                JOIN products p ON sid.ProductID = p.ProductID
                WHERE c.FullName LIKE ?
                ORDER BY si.InvoiceID DESC
            """;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(extractInvoice(rs));
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi khi lọc theo tên khách hàng: " + ex.getMessage());
        } finally {
            DBConnection.closeConnect(con);
        }
        return list;
    }

    public Vector<InvoiceViewDTO> getInvoicesByEmployeeName(String name) {
        Vector<InvoiceViewDTO> list = new Vector<>();
        Connection con = DBConnection.openConnect();
        try {
            String sql = """
                SELECT si.InvoiceID, e.Username AS EmployeeName, c.FullName AS CustomerName,
                       p.ProductName, si.SaleDate, sid.Prices, sid.Quantity, sid.TotalPrices
                FROM salesinvoices si
                JOIN employees e ON si.EmployeeID = e.EmployeeID
                JOIN customers c ON si.CustomerID = c.CustomerID
                JOIN salesinvoicedetails sid ON si.InvoiceID = sid.InvoiceID
                JOIN products p ON sid.ProductID = p.ProductID
                WHERE e.Username LIKE ?
                ORDER BY si.InvoiceID DESC
            """;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(extractInvoice(rs));
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi khi lọc theo tên nhân viên: " + ex.getMessage());
        } finally {
            DBConnection.closeConnect(con);
        }
        return list;
    }

    public Vector<InvoiceViewDTO> getInvoicesByDate(Date date) {
        Vector<InvoiceViewDTO> list = new Vector<>();
        Connection con = DBConnection.openConnect();
        try {
            String sql = """
                SELECT si.InvoiceID, e.Username AS EmployeeName, c.FullName AS CustomerName,
                       p.ProductName, si.SaleDate, sid.Prices, sid.Quantity, sid.TotalPrices
                FROM salesinvoices si
                JOIN employees e ON si.EmployeeID = e.EmployeeID
                JOIN customers c ON si.CustomerID = c.CustomerID
                JOIN salesinvoicedetails sid ON si.InvoiceID = sid.InvoiceID
                JOIN products p ON sid.ProductID = p.ProductID
                WHERE DATE(si.SaleDate) = ?
                ORDER BY si.InvoiceID DESC
            """;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(extractInvoice(rs));
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi khi lọc theo ngày bán: " + ex.getMessage());
        } finally {
            DBConnection.closeConnect(con);
        }
        return list;
    }
}
