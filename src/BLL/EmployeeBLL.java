package BLL;

import DAL.EmployeeDAL;
import DTO.EmployeeDTO;

public class EmployeeBLL {
    private EmployeeDAL dal;

    public EmployeeBLL() throws Exception {
        dal = new EmployeeDAL();
    }

    public EmployeeDTO login(String username, String password) throws Exception {
        return dal.login(username, password);
    }

    public boolean isUsernameExist(String username) throws Exception {
        return dal.isUsernameExist(username);
    }

    public void register(EmployeeDTO emp) throws Exception {
        dal.register(emp);
    }
}