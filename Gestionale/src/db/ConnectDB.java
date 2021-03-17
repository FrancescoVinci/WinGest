package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

public class ConnectDB {

    public static Connection getConnection() {
        try {
            String url = "jdbc:h2:~/gestionale";
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(url, "root", "123");
        }catch (ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }
}
