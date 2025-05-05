package DAL;

import DTO.EmployeeDTO;
import java.sql.*;
import java.util.Vector;

public class EmployeeDAL {
    public Vector<EmployeeDTO> getAllEmployees(){
        Vector<EmployeeDTO> arr = new Vector<EmployeeDTO>();
        Connection con = DBConnection.openConnect();
        try{
            String sql = "SELECT * FROM employees";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                EmployeeDTO em = new EmployeeDTO();
                em.setEmployeeID(rs.getInt("EmployeeID"));
                em.setPassword(rs.getString("Password"));
                em.setUsername(rs.getString("Username"));
                em.setPhone(rs.getString("Phone"));
                em.setEmail(rs.getString("Email"));
                em.setJoinDate(rs.getDate("JoinDate"));
                em.setAddress(rs.getString("Address"));
                arr.add(em);
            }
        }catch(SQLException ex){
            System.out.println("Lỗi cơ sở dữ liệu "+ex.getMessage());
        }finally{
            DBConnection.closeConnect(con);
        }
        return arr;
    }

    public boolean register(EmployeeDTO empl){
        boolean result = false;
        Connection con = DBConnection.openConnect();
        try{
            String sql = "INSERT INTO employees(Username, Password, Phone) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, empl.getUsername());
            ps.setString(2,empl.getPassword());
            ps.setString(3, empl.getPhone());
            if(ps.executeUpdate()>=1){
                result = true;
            }
        }catch(SQLException ex){
            System.out.println("Lỗi cơ sở dữ liệu "+ex.getMessage());
        }finally{
            DBConnection.closeConnect(con);
        }
        return result;
    }

    public boolean login(String username, String password){
        boolean result = false;
        Connection con = DBConnection.openConnect();
        try{
            String sql = "SELECT Username, Password FROM employees WHERE Username = ? AND Password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                result = true;
            }
        }catch(SQLException ex){
            System.out.println("Lỗi cơ sở dữ liệu "+ex.getMessage());
        }finally{
            DBConnection.closeConnect(con);
        }
        return result;
    }

    public boolean isUsernameExist(String username){
        boolean result = false;
        Connection con = DBConnection.openConnect();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT Username FROM employees WHERE Username = ?");
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            result = true;
        }catch(SQLException ex){
            System.out.println("Lỗi cơ sở dữ liệu "+ex.getMessage());
        }finally{
            DBConnection.closeConnect(con);
        }
        return result;
    }

    public boolean addEmployee(EmployeeDTO e){
        boolean result = false;
        Connection con = DBConnection.openConnect();
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO employees(Username, Password, Phone, Email, Address)" +
            " VALUES(?,?,?,?,?) ");
            ps.setString(1, e.getUsername());
            ps.setString(2,e.getPassword());
            ps.setString(3,e.getPhone());
            ps.setString(4,e.getEmail());
            ps.setString(5,e.getAddress());
            if(ps.executeUpdate()>0){
                result = true; 
            }
        }catch(SQLException ex){
            System.out.println("Lỗi cơ sở dữ liệu "+ex.getMessage());
        }finally{
            DBConnection.closeConnect(con);
        }
        return result;
    }

    public boolean updateEmployee(EmployeeDTO e){
        boolean result = false;
        Connection con = DBConnection.openConnect();
        try{
            String sql = "UPDATE employees SET Username = ?, Password = ?, Phone = ?, Email = ?, Address = ? WHERE EmployeeID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, e.getUsername());
            ps.setString(2,e.getPassword());
            ps.setString(3,e.getPhone());
            ps.setString(4, e.getEmail());
            ps.setString(5, e.getAddress());
            ps.setInt(6, e.getEmployeeID());
            if(ps.executeUpdate()>=1){
                result = true;
            }
        }catch(SQLException ex){
            System.out.println("Lỗi cơ sở dữ liệu "+ex.getMessage());
        }finally{
            DBConnection.closeConnect(con);
        }
        return result;
    }

    public boolean deleteEmployee(String username){
        boolean result = false;
        Connection con = DBConnection.openConnect();
        try{
            String sql = "DELETE FROM employees WHERE Username = "+username;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            if(ps.executeUpdate()>=1){
                result = true;
            }
        }catch(SQLException ex){
            System.out.println("Lỗi cơ sở dữ liệu "+ex.getMessage());
        }finally{
            DBConnection.closeConnect(con);
        }
        return result;
    }
}
