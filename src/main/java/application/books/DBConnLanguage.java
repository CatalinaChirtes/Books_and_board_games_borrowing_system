package application.books;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnLanguage {
    public Connection connection;
    public Connection getDBLanguageConnection() {
        String dbName = "languages";
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
