package DTO;

import java.sql.Date;

public class CustomerDTO {
    private int customerID;
    private String fullName;
    private String phone;
    private String email;
    private Date createDate;
    private String gender;

    public int getCustomerID() { return customerID; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Date getCreateDate() { return createDate; }
    public void setCreateDate(Date createDate) { this.createDate = createDate; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}
