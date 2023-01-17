package application.books;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void testUserConstructor() {
        User user = new User(1, "username", "password");
        assertEquals(1, user.getUser_id());
        assertEquals("username", user.getUser());
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testGetUser_id() {
        User user = new User(1, "username", "password");
        assertEquals(1, user.getUser_id());
    }

    @Test
    public void testSetUser_id() {
        User user = new User(1, "username", "password");
        user.setUser_id(2);
        assertEquals(2, user.getUser_id());
    }

    @Test
    public void testGetUser() {
        User user = new User(1, "username", "password");
        assertEquals("username", user.getUser());
    }

    @Test
    public void testSetUser() {
        User user = new User(1, "username", "password");
        user.setUser("catalina");
        assertEquals("catalina", user.getUser());
    }

    @Test
    public void testGetPassword() {
        User user = new User(1, "username", "password");
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testSetPassword() {
        User user = new User(1, "username", "password");
        user.setPassword("alabala");
        assertEquals("alabala", user.getPassword());
    }

    @Test
    public void testStaticFields() {
        User.username = "staticUsername";
        User.userID = 100;
        assertEquals("staticUsername", User.username);
        assertEquals(100, User.userID);
    }
}