package DTO;

import java.math.BigDecimal;

public class SalesInvoiceDetailDTO {
    private int detailID;
    private int invoiceID;
    private int productID;
    private int quantity;
    private BigDecimal price;
    private BigDecimal totalPrices;

    public int getDetailID() {
        return detailID;
    }

    public void setDetailID(int detailID) {
        this.detailID = detailID;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal Price) {
        this.price = Price;
    }

    public BigDecimal getTotalPrices() {
        return totalPrices;
    }
    
    public void setTotalPrices(BigDecimal totalPrices) {
        this.totalPrices = totalPrices;
    }
}
