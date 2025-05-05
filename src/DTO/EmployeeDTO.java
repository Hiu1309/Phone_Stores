package DTO;

import java.sql.Date;

public class EmployeeDTO {
    private int employeeID;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Date joinDate;
    private String address;

    public int getEmployeeID() { 
        return employeeID; 
    }
    
    public void setEmployeeID(int employeeID) { 
        this.employeeID = employeeID; 
    }

    public String getUsername() { 
        return username; 
    }
    public void setUsername(String username) {
         this.username = username; 
    }

    public String getPassword() {
         return password; 
    }

    public void setPassword(String password) {
         this.password = password; 
    }

    public String getPhone() {
         return phone; 
    }

    public void setPhone(String phone) { 
        this.phone = phone; 
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Date getJoinDate(){
        return joinDate;
    }

    public void setJoinDate(Date joiDate){
        this.joinDate = joiDate;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }
} 