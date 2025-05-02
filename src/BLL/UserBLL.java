package BLL;

import DAL.UserDAL;
import DTO.User;

public class UserBLL {
    private UserDAL dal = new UserDAL();

    public boolean validateLogin(String username, String password) {
        return dal.login(new User(username, password));
    }

    public String validateRegister(String username, String password) {
        if (username.contains(" ")) return "Username không được chứa khoảng trắng";
        if (password.length() < 6) return "Password phải ít nhất 6 ký tự";
        if (dal.usernameExists(username)) return "Username đã tồn tại";
        boolean success = dal.register(new User(username, password));
        return success ? "OK" : "Đăng ký thất bại";
    }
}