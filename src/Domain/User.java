package Domain;


public class User {
    private String username;
    private String password;
    private Boolean connected;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    public User(String username, String password, Boolean connected) {
        this.username = username;
        this.password = password;
        this.connected = connected;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getConnected() {
        return connected;
    }
}
