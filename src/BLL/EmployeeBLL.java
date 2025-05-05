package BLL;

import DAL.EmployeeDAL;
import DTO.EmployeeDTO;
import java.util.Vector;

public class EmployeeBLL {
    EmployeeDAL emplDAL = new EmployeeDAL();
    public Vector<EmployeeDTO> getAllEmployees(){
        return emplDAL.getAllEmployees();
    }

    public String addEmployee(EmployeeDTO empl){
        if(emplDAL.isUsernameExist(empl.getUsername())){
            return "Tài khoản này đã tồn tại";
        }
        if(emplDAL.addEmployee(empl)){
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }
    
    public String updateEmployee(EmployeeDTO empl){
        if(emplDAL.isUsernameExist(empl.getUsername())){
            return "Tên tài khoản này đã tồn tại không thể cập nhật lại giống tên tài khoản khác";
        }
        if(emplDAL.updateEmployee(empl)){
            return "Cập nhật thông tin nhân viên thành công";
        }
        return "Cập nhật thông tin nhân viên thất bại";
    }

    public String deleteEmployee(int id){
        if(emplDAL.deleteEmployee(id)){
            return "Xoá nhân viên thành công";
        }
        return "Xóa nhân viên thất bại";
    }

    public EmployeeDTO getEmployeeAfterLogin(String username, String password) {
        return emplDAL.loginAndGetEmployee(username, password);
    }
    
}