package Domain;


import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(UserID, user.UserID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, UserID);
    }
}

