package BLL;

import DAL.CustomerDAL;
import DTO.CustomerDTO;
import java.util.Vector;

public class CustomerBLL {
    CustomerDAL customerDAL = new CustomerDAL();

    public Vector<CustomerDTO> getAllCustomers() {
        return customerDAL.getAllCustomers();
    }

    public String addCustomer(CustomerDTO c) {
        if (customerDAL.addCustomer(c)) {
            return "Thêm khách hàng thành công";
        }
        return "Thêm khách hàng thất bại";
    }

    public String updateCustomer(CustomerDTO c) {
        if (customerDAL.updateCustomer(c)) {
            return "Cập nhật khách hàng thành công";
        }
        return "Cập nhật khách hàng thất bại";
    }

    public String deleteCustomer(int id) {
        if (customerDAL.deleteCustomer(id)) {
            return "Xóa khách hàng thành công";
        }
        return "Xóa khách hàng thất bại";
    }

    public CustomerDTO getCustomerByPhone(String phone){
        Vector<CustomerDTO> customerArr = customerDAL.getAllCustomers();
        for(int i=0;i<customerArr.size();i++){
            CustomerDTO c = customerArr.get(i);
            if(c.getPhone().equals(phone)){
                return c;
            }
        }
        return null;
    }
}
