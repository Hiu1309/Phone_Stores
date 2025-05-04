package BLL;

import DAL.ProductsDAL;
import DTO.ProductsDTO;
import java.util.ArrayList;

public class ProductsBLL {
    private ProductsDAL dal;

    public ProductsBLL() {
        dal = new ProductsDAL();
    }
    public String deleteProduct(int productID) {
        boolean success = dal.deleteProduct(productID);
        return success ? "Xóa sản phẩm thành công!" : "Xóa sản phẩm thất bại!";
    }
    
    public String updateProduct(ProductsDTO p) {
        boolean success = dal.updateProduct(p);
        return success ? "Cập nhật sản phẩm thành công!" : "Cập nhật sản phẩm thất bại!";
    }
    

    public ArrayList<ProductsDTO> getAllProducts() {
        return dal.getAllProducts();
    }

    public String addProduct(ProductsDTO p) {
        if (dal.isProductIDExists(p.getProductID())) {
            return "Sản phẩm đã tồn tại!";
        }

        boolean success = dal.insertProduct(p);
        return success ? "Thêm sản phẩm thành công!" : "Thêm sản phẩm thất bại!";
    }
}
