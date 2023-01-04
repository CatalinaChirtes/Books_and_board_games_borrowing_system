package application.books;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseConnection {

    public Connection connection;
    public Connection getDBConnection() {
        String dbName = "bookdatabase";
        String userName = "root";
        String passWord = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,passWord);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
