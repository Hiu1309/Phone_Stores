package DTO;

import java.math.BigDecimal;
import java.util.Date;

public class SalesInvoiceDTO {
    private int invoiceID;
    private int customerID;
    private int employeeID;
    private Date saleDate;
    private BigDecimal totalAmount;

    public SalesInvoiceDTO() {}

    public SalesInvoiceDTO(int invoiceID, int customerID, int employeeID, Date saleDate) {
        this.invoiceID = invoiceID;
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.saleDate = saleDate;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
