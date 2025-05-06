package DAL;

import DTO.SalesInvoiceDetailDTO;
import java.sql.*;
import java.util.Vector;

public class SalesInvoiceDetailDAL {
    public Vector<SalesInvoiceDetailDTO> getDetailsByInvoiceID(int invoiceID) {
        Vector<SalesInvoiceDetailDTO> list = new Vector<>();
        Connection con = DBConnection.openConnect();
        try {
            String sql = "SELECT * FROM salesinvoicedetails WHERE InvoiceID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, invoiceID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SalesInvoiceDetailDTO detail = new SalesInvoiceDetailDTO();
                detail.setDetailID(rs.getInt("DetailID"));
                detail.setInvoiceID(rs.getInt("InvoiceID"));
                detail.setProductID(rs.getInt("ProductID"));
                detail.setQuantity(rs.getInt("Quantity"));
                detail.setPrice(rs.getBigDecimal("Price"));
                detail.setTotalPrices(rs.getBigDecimal("TotalPrices"));
                list.add(detail);
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi cơ sở dữ liệu: " + ex.getMessage());
        } finally {
            DBConnection.closeConnect(con);
        }
        return list;
    }

    public boolean addInvoiceDetail(SalesInvoiceDetailDTO detail) {
        boolean result = false;
        Connection con = DBConnection.openConnect();
        try {
            String sql = "INSERT INTO salesinvoicedetails (InvoiceID, ProductID, Quantity, Prices, TotalPrices) VALUES (?, ?, ?, ?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, detail.getInvoiceID());
            ps.setInt(2, detail.getProductID());
            ps.setInt(3, detail.getQuantity());
            ps.setBigDecimal(4, detail.getPrice());
            ps.setBigDecimal(5, detail.getTotalPrices());
            result = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Lỗi cơ sở dữ liệu: " + ex.getMessage());
        } finally {
            DBConnection.closeConnect(con);
        }
        return result;
    }
}
