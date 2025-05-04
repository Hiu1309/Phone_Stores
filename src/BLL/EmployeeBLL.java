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

    public String addEmployee(EmployeeDTO empl){
        if(emplDAL.isUsernameExist(empl.getUsername())){
            return "Tài khoản này đã tồn tại";
        }
        if(emplDAL.addEmployee(empl)){
            return "Đăng ký thành công";
        }
        return "Thêm thất bại";
    }
}