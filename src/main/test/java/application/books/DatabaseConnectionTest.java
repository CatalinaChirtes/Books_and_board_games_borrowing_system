package application.books;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionTest {

    @Test
    public void testGetDBConnection() {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getDBConnection();
        assertNotNull(connection);
    }
}