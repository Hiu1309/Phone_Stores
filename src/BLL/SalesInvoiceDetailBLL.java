package BLL;

import DAL.SalesInvoiceDetailDAL;
import DTO.SalesInvoiceDetailDTO;
import java.util.Vector;

public class SalesInvoiceDetailBLL {
    SalesInvoiceDetailDAL detailDAL = new SalesInvoiceDetailDAL();

    public Vector<SalesInvoiceDetailDTO> getDetailsByInvoiceID(int invoiceID) {
        return detailDAL.getDetailsByInvoiceID(invoiceID);
    }

    public boolean addInvoiceDetail(SalesInvoiceDetailDTO detail) {
        if (detailDAL.addInvoiceDetail(detail)) {
            return true;
        }
        return false;
    }

    public String addMultipleInvoiceDetails(Vector<SalesInvoiceDetailDTO> detailList) {
        boolean allSuccess = true;
        for (SalesInvoiceDetailDTO detail : detailList) {
            if (!detailDAL.addInvoiceDetail(detail)) {
                allSuccess = false;
            }
        }
        return allSuccess ? "Thêm tất cả chi tiết hóa đơn thành công." : "Có lỗi khi thêm chi tiết hóa đơn.";
    }
}
