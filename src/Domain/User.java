package Domain;


public class User {
    private String username;
    private String password;
    private int UserID;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public User(String username, String password, int UserID) {
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

    public int getUserID() {
        return UserID;
    }
}
