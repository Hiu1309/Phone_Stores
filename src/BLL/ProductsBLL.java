package BLL;

import DAL.ProductsDAL;
import DTO.ProductsDTO;
import java.util.ArrayList;

public class ProductsBLL {
    private ProductsDAL dal;

    public ProductsBLL() {
        dal = new ProductsDAL();
    }

    public ArrayList<ProductsDTO> getAllProducts() {
        return dal.getAllProducts();
    }

    public String addProduct(ProductsDTO p) {
        if (dal.isProductIDExists(p.getProductID())) {
            return "Mã sản phẩm đã tồn tại!";
        }

        boolean success = dal.insertProduct(p);
        return success ? "Thêm sản phẩm thành công!" : "Thêm sản phẩm thất bại!";
    }
}
