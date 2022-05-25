package Domain;

import java.sql.Date;
import java.util.ArrayList;

public class Referee extends User{
    private String ID;
    private String name;
    private String PhoneNumber;
    private Date birthday;
    private ArrayList<League> leagues;

    public Referee(String ID, String name, String phoneNumber, Date birthday, String username, String password) {
        super(username, password, ID);
        this.name = name;
        this.ID = ID;
        this.PhoneNumber = phoneNumber;
        this.birthday = birthday;
        this.leagues = new ArrayList<League>();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Referee))
            return false;
        Referee other = (Referee) o;
        return (this.getID() == null && other.getID() == null)
                || (this.getID() != null && this.getID().equals(other.getID()));
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public ArrayList<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(ArrayList<League> leagues) {
        this.leagues = leagues;
    }
}
