package BLL;

import DAL.SalesInvoiceDAL;
import DTO.SalesInvoiceDTO;
import java.util.Vector;

public class SalesInvoiceBLL {
    SalesInvoiceDAL invoiceDAL = new SalesInvoiceDAL();

    public Vector<SalesInvoiceDTO> getAllSalesInvoices() {
        return invoiceDAL.getAllSalesInvoices();
    }

    public String addSalesInvoice(SalesInvoiceDTO invoice) {
        if (invoiceDAL.addSalesInvoice(invoice)) {
            return "Thêm hóa đơn thành công. Mã hóa đơn: " + invoice.getInvoiceID();
        }
        return "Thêm hóa đơn thất bại.";
    }

    public int addSalesInvoiceAndGetID(SalesInvoiceDTO invoice) {
        if (invoiceDAL.addSalesInvoice(invoice)) {
            return invoice.getInvoiceID();
        }
        return -1;
    }
}
