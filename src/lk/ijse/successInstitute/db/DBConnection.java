package lk.ijse.successInstitute.db;

import java.sql.Connection;
import java.sql.DriverAction;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver"); //load driver ram
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/successinstitute","root","1234");

    }
    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        return (dbConnection==null)? dbConnection = new DBConnection() : dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
