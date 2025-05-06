package DAL;

import DTO.ProductsDTO;
import java.sql.*;
import java.util.Vector;

public class ProductsDAL {

    public Vector<ProductsDTO> getAllProducts() {
        Vector<ProductsDTO> list = new Vector<ProductsDTO>();
        Connection con = DBConnection.openConnect();
        String sql = "SELECT * FROM products";
        try{
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
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
                p.setImages(rs.getString("Image"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi cơ sở dữ liệu"+e.getMessage());
        }finally{
            DBConnection.closeConnect(con);
        }
        return list;
    }

    public boolean insertProduct(ProductsDTO p) {
        Connection con = DBConnection.openConnect();
        String sql = "INSERT INTO products (ProductName, Type, Brand, Stock, Prices, Status, Date, Image) "+
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        boolean result = false;
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getProductName());
            ps.setString(2, p.getType());
            ps.setString(3, p.getBrand());
            ps.setInt(4, p.getStock());
            ps.setBigDecimal(5, p.getPrices());
            ps.setString(6, p.getStatus());
            ps.setDate(7, p.getDate());
            ps.setString(8, p.getImages());
            if(ps.executeUpdate()>=1){
                result = true;
            }
        }catch (SQLException e) {
            System.out.println("Lỗi cơ sở dữ liệu "+e.getMessage());
        }finally{
            DBConnection.closeConnect(con);
        }
        return result;
    }

    public boolean isProductIDExists(int productID) {
        Connection con = DBConnection.openConnect();
        String sql = "SELECT ProductID FROM products WHERE ProductID = ?";
        boolean result = false;
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi cơ sở dữ liệu "+e.getMessage());
        }finally{
            DBConnection.closeConnect(con);
        }
        return result;
    }
    
    public boolean updateProduct(ProductsDTO p) {
        Connection con = DBConnection.openConnect();
        boolean result = false;
        String sql = "UPDATE products SET ProductName = ?, Type = ?, Brand = ?, Stock = ?, "+
                     "Prices = ?, Status = ?, Date = ?, Image = ? WHERE ProductID = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getProductName());
            ps.setString(2, p.getType());
            ps.setString(3, p.getBrand());
            ps.setInt(4, p.getStock());
            ps.setBigDecimal(5, p.getPrices());
            ps.setString(6, p.getStatus());
            ps.setDate(7, p.getDate());
            ps.setString(8, p.getImages());
            ps.setInt(9, p.getProductID());
            
            if(ps.executeUpdate()>=1){
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi cơ sở dữ liệu "+e.getMessage());
        }finally{
            DBConnection.closeConnect(con);
        }
        return result;
    }
    
    
    public boolean deleteProduct(int productID) {
        Connection con = DBConnection.openConnect();
        boolean result = false;
        String sql = "DELETE FROM products WHERE ProductID = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, productID);
            if(ps.executeUpdate()>=1){
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi cơ sở dữ liệu "+e.getMessage());
        }finally{
            DBConnection.closeConnect(con);
        }
        return result;
    }
    
    public ProductsDTO getProductsByName(String productName){
        Connection con = DBConnection.openConnect();
        ProductsDTO p = null;
        try{
            String sql = "SELECT * FROM products WHERE ProductName = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, productName);
            ResultSet rs = ps.executeQuery();            
            if(rs.next()){
                p = new ProductsDTO();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setType(rs.getString("Type"));
                p.setBrand(rs.getString("Brand"));
                p.setStock(rs.getInt("Stock"));
                p.setPrices(rs.getBigDecimal("Prices"));
                p.setStatus(rs.getString("Status"));
                p.setDate(rs.getDate("Date"));
                p.setImages(rs.getString("Image"));
            }            
        } catch(SQLException ex){
            System.out.println("Lỗi cơ sở dữ liệu " + ex.getMessage());
        } finally {
            DBConnection.closeConnect(con);
        }
        return p;
    }
    

    public ProductsDTO getProductByID(int productID) {
        Connection con = DBConnection.openConnect();
        ProductsDTO p = null;
        try {
            String sql = "SELECT * FROM products WHERE ProductID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new ProductsDTO();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setType(rs.getString("Type"));
                p.setBrand(rs.getString("Brand"));
                p.setStock(rs.getInt("Stock"));
                p.setPrices(rs.getBigDecimal("Prices"));
                p.setStatus(rs.getString("Status"));
                p.setDate(rs.getDate("Date"));
                p.setImages(rs.getString("Image"));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi cơ sở dữ liệu " + e.getMessage());
        } finally {
            DBConnection.closeConnect(con);
        }
        return p;
    }
    
    public boolean updateStock(int productID, int newStock) {
        Connection con = DBConnection.openConnect();
        boolean result = false;
        String sql = "UPDATE products SET Stock = ? WHERE ProductID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, newStock);
            ps.setInt(2, productID);
            if (ps.executeUpdate() >= 1) {
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi cơ sở dữ liệu " + e.getMessage());
        } finally {
            DBConnection.closeConnect(con);
        }
        return result;
    }
    
}
