package BLL;

import DAL.InvoiceViewDAL;
import DTO.InvoiceViewDTO;
import java.sql.Date;
import java.util.Vector;

public class InvoiceViewBLL {
    private final InvoiceViewDAL invoiceDAL = new InvoiceViewDAL();

    public Vector<InvoiceViewDTO> getAllInvoiceViews() {
        return invoiceDAL.getAllInvoiceViews();
    }

    public Vector<InvoiceViewDTO> getInvoicesByCustomerName(String name) {
        return invoiceDAL.getInvoicesByCustomerName(name);
    }

    public Vector<InvoiceViewDTO> getInvoicesByEmployeeName(String name) {
        return invoiceDAL.getInvoicesByEmployeeName(name);
    }

    public Vector<InvoiceViewDTO> getInvoicesByDate(Date date) {
        return invoiceDAL.getInvoicesByDate(date);
    }
}
