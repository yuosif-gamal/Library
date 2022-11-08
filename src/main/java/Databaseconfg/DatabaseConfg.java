package Databaseconfg;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfg {

    private Connection connect;

    public DatabaseConfg() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "pass");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public Connection getConnect() {
        return connect;
    }

}