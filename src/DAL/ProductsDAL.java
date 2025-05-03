package DAL;

import DTO.ProductsDTO;
import java.sql.*;
import java.util.ArrayList;

public class ProductsDAL {
    private Connection con;

    public ProductsDAL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phone_store", "root", "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<ProductsDTO> getAllProducts() {
        ArrayList<ProductsDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ProductsDTO p = new ProductsDTO();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setType(rs.getString("Type"));
                p.setBrand(rs.getString("Brand"));
                p.setStock(rs.getInt("Stock"));
                p.setPrices(rs.getBigDecimal("Prices"));
                p.setStatus(rs.getString("Status"));
                p.setDate(rs.getDate("Date"));
                p.setImages(rs.getString("Images"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertProduct(ProductsDTO p) {
        String sql = "INSERT INTO products (ProductID, ProductName, Type, Brand, Stock, Prices, Status, Date, Images) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getProductID());
            ps.setString(2, p.getProductName());
            ps.setString(3, p.getType());
            ps.setString(4, p.getBrand());
            ps.setInt(5, p.getStock());
            ps.setBigDecimal(6, p.getPrices());
            ps.setString(7, p.getStatus());
            ps.setDate(8, p.getDate());
            ps.setString(9, p.getImages());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isProductIDExists(int productID) {
        String sql = "SELECT ProductID FROM products WHERE ProductID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
