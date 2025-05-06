package BLL;

import DAL.InvoiceViewDAL;
import DTO.InvoiceViewDTO;
import java.util.Vector;

public class InvoiceViewBLL {
    private InvoiceViewDAL invoiceViewDAL;

    public InvoiceViewBLL() {
        invoiceViewDAL = new InvoiceViewDAL();
    }

    public Vector<InvoiceViewDTO> getAllInvoiceViews() {
        return invoiceViewDAL.getAllInvoiceViews();
    }

    public Vector<InvoiceViewDTO> getInvoiceViewsByCustomerName(String customerName) {
        Vector<InvoiceViewDTO> result = new Vector<>();
        for (InvoiceViewDTO iv : getAllInvoiceViews()) {
            if (iv.getCustomerName().toLowerCase().contains(customerName.toLowerCase())) {
                result.add(iv);
            }
        }
        return result;
    }

    public Vector<InvoiceViewDTO> getInvoiceViewsByEmployeeName(String employeeName) {
        Vector<InvoiceViewDTO> result = new Vector<>();
        for (InvoiceViewDTO iv : getAllInvoiceViews()) {
            if (iv.getEmployeeName().toLowerCase().contains(employeeName.toLowerCase())) {
                result.add(iv);
            }
        }
        return result;
    }

    public Vector<InvoiceViewDTO> getInvoiceViewsByDate(java.util.Date date) {
        Vector<InvoiceViewDTO> result = new Vector<>();
        for (InvoiceViewDTO iv : getAllInvoiceViews()) {
            if (iv.getSaleDate().equals(date)) {
                result.add(iv);
            }
        }
        return result;
    }
}
