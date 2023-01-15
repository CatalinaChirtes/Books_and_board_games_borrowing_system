package application.books;

public class User {
    Integer user_id;
    String user;

    static String username;
    static Integer userID;
    String password;

    public User(Integer user_id, String user, String password) {
        this.user_id = user_id;
        this.user = user;
        this.password = password;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
