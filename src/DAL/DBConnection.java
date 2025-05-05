package DAL;

import java.sql.*;

public class DBConnection {
    public static Connection openConnect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = "jdbc:mysql://localhost:3306/phone_store";
            String name = "root";
            String password = "";
            return DriverManager.getConnection(dbUrl, name, password);  
        }catch(SQLException ex){
            System.out.println("Lỗi kết nối cơ sở dữ liệu "+ex.getMessage());
            return null;
        }catch(ClassNotFoundException ex){
            System.out.println("Lỗi không tìm thấy class jdbc "+ex.getMessage());
            return null;
        }
    }

    public static void closeConnect(Connection con){
        try{
            if(con!=null){
                con.close();
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
}
