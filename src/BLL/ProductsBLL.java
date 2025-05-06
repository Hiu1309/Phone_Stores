package BLL;

import DAL.ProductsDAL;
import DTO.EmployeeDTO;
import DTO.ProductsDTO;
import java.util.Vector;

public class ProductsBLL {
    ProductsDAL productDAL = new ProductsDAL();

    public String deleteProduct(int productID) {
        if(productDAL.deleteProduct(productID))
        return "Xóa sản phẩm thành công!"; 
        return "Xóa sản phẩm thất bại!";
    }
    
    public String updateProduct(ProductsDTO p) {
        if(productDAL.updateProduct(p))
        return "Cập nhật sản phẩm thành công!"; 
        return "Cập nhật sản phẩm thất bại!";
    }
    

    public Vector<ProductsDTO> getAllProducts() {
        return productDAL.getAllProducts();
    }

    public String addProduct(ProductsDTO p) {
        if (productDAL.isProductIDExists(p.getProductID())) {
            return "Sản phẩm đã tồn tại!";
        }

        if(productDAL.insertProduct(p)){
            return "Thêm sản phẩm thành công";
        }
        return "Thêm sản phẩm thất bại";
    }

    public ProductsDTO getProductsByName(String name){
        return productDAL.getProductsByName(name);
    }

    public void updateStockAfterSale(int productID, int quantitySold) {
        ProductsDTO product = productDAL.getProductByID(productID);
        if (product != null) {
            int newStock = product.getStock() - quantitySold;
            if (newStock < 0) newStock = 0;
            productDAL.updateStock(productID, newStock);
        }
    }
    
}
