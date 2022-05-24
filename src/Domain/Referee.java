package Domain;

import java.sql.Date;
import java.util.ArrayList;

public class Referee extends User{
    private String name;
    private String ID;
    private int PhoneNumber;
    private Date birthday;
    private ArrayList<League> leagues;

    public Referee(String username, String password, Boolean connected, String name, String ID, int phoneNumber, Date birthday) {
        super(username, password, connected);
        this.name = name;
        this.ID = ID;
        this.PhoneNumber = phoneNumber;
        this.birthday = birthday;
        this.leagues = new ArrayList<League>();
    }

    public ArrayList<League> getLeagues() {
        return leagues;
    }

    public void AddLeague(League league) {
        this.leagues.add(league);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
