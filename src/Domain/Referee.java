package Domain;

import java.sql.Date;
import java.util.ArrayList;

public class Referee extends User{
    private int ID;
    private String name;
    private String PhoneNumber;
    private Date birthday;
    private ArrayList<String> leagues;

    public Referee(int ID, String name, String phoneNumber, Date birthday, String username, String password) {
        super(username, password, ID);
        this.name = name;
        this.ID = ID;
        this.PhoneNumber = phoneNumber;
        this.birthday = birthday;
        this.leagues = new ArrayList<String>();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Referee))
            return false;
        Referee other = (Referee) o;
        return this.getID() == other.getID();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
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

    public ArrayList<String> getLeagues() {
        return leagues;
    }

    public void setLeagues(ArrayList<String> leagues) {
        this.leagues = leagues;
    }

    public boolean addLeague(String league) {
        if(this.leagues.contains(league)){
            return false;
        }
        else{
            this.leagues.add(league);
            return true;
        }
    }
}
