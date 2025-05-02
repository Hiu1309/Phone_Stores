package DTO;

public class EmployeeDTO {
    private int employeeID;
    private String username;
    private String password;
    private String fullName;
    private String phone;
    private String role;

    // Getters and Setters
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

    public String getFullName() {
         return fullName; 
    }

    public void setFullName(String fullName) {
         this.fullName = fullName; 
    }

    public String getPhone() {
         return phone; 
    }

    public void setPhone(String phone) { 
        this.phone = phone; 
    }

    public String getRole() { 
        return role; 
    }

    public void setRole(String role) {
         this.role = role; 
    }
} 