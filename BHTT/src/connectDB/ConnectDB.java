/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Huy 
public class ConnectDB {
    public static Connection opConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl = "jdbc:sqlserver://localhost;database=BHTT"; 
        String user = "sa";
        String pass = "123456";
        Connection con = DriverManager.getConnection(connectionUrl,user,pass);
        return con;
    }
}
