package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    public static Connection con = null;
    private static ConnectDB instance = new ConnectDB();

    public static ConnectDB getInstance() {
        return instance;
    }

    public void connect() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databasename=BHTT";
        String user = "bac";
        String password = "123";
        con = DriverManager.getConnection(url, user, password);
    }

    public void disconnect() {
        if (con != null)
	try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return con;
    }
    
    
    // Thư xài cái này
    public static Connection opConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl = "jdbc:sqlserver://localhost;database=BHTT"; 
        String user = "bac";
        String pass = "123";
        Connection con = DriverManager.getConnection(connectionUrl,user,pass);
        return con;
    }
}
