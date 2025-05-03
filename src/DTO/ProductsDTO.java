package DTO;

import java.math.BigDecimal;
import java.sql.Date;

public class ProductsDTO {
    private int productID;
    private String productName;
    private String type;
    private String brand;
    private int stock;
    private BigDecimal prices;
    private String status;
    private Date date;
    private String images;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPrices() {
        return prices;
    }

    public void setPrices(BigDecimal prices) {
        this.prices = prices;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Date getDate(){
        return date;
    }
    public void setDate(Date date){
        this.date =date;
    }
    public String getImages(){
        return images;
    }
    public void setImages(String images){
        this.images = images;
    }
}
