package Domain;


public class User {
    private String username;
    private String password;
    private String UserID;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public User(String username, String password, String UserID) {
        this.username = username;
        this.password = password;
        this.UserID = UserID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserID() {
        return UserID;
    }
}
