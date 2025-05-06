package DTO;

import java.math.BigDecimal;
import java.util.Date;

public class InvoiceViewDTO {
    private int invoiceID;
    private String employeeName;
    private String customerName;
    private String productName;
    private Date saleDate;
    private BigDecimal unitPrice;
    private int quantity;
    private BigDecimal totalPrice;

    public InvoiceViewDTO() {
    }

    public InvoiceViewDTO(int invoiceID, String employeeName, String customerName, String productName,
                          Date saleDate, BigDecimal unitPrice, int quantity, BigDecimal totalPrice) {
        this.invoiceID = invoiceID;
        this.employeeName = employeeName;
        this.customerName = customerName;
        this.productName = productName;
        this.saleDate = saleDate;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
