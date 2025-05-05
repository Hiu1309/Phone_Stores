package BLL;

import DAL.EmployeeDAL;
import DTO.EmployeeDTO;
import java.util.Vector;

public class EmployeeBLL {
    EmployeeDAL emplDAL = new EmployeeDAL();
    public Vector<EmployeeDTO> getAllEmployees(){
        return emplDAL.getAllEmployees();
    }

    public String login(String username, String password){
        if(emplDAL.login(username, password))
        return "Đăng nhập thành công!";
        return "Sai tên tài khoản hoặc mật khẩu";
    }

    public String register(EmployeeDTO empl){
        if(emplDAL.isUsernameExist(empl.getUsername())){
            return "Tài khoản này đã tồn tại";
        }
        if(emplDAL.register(empl)){
            return "Đăng ký thành công";
        }
        return "Đăng ký thất bại";
    }

    public String addEmployee(EmployeeDTO empl){
        if(emplDAL.isUsernameExist(empl.getUsername())){
            return "Tài khoản này đã tồn tại";
        }
        if(emplDAL.addEmployee(empl)){
            return "Thêm nhân viên thành công";
        }
        return "Thêm nhân viên thất bại";
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

    public String deleteEmployee(String username){
        if(emplDAL.deleteEmployee(username)){
            return "Xoá nhân viên thành công";
        }
        return "Xóa nhân viên thất bại";
    }
}