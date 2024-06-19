package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnect {
    private static final String URL = "jdbc:mysql://localhost:3306/miximoi";
    private static final String USER = "root";
    private static final String PASSWORD = "admin12345";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
    }
        public static void main(String[] args) throws SQLException
    {
        System.out.println(DBConnect.getConnection() );
    }
}
