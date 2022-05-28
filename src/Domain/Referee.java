package Domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;

public class Referee extends User{
    private int ref_id;
    private String name;
    private String PhoneNumber;
    private Date birthday;
    private ArrayList<Game> games;

    public Referee(int ref_id, String name, String phoneNumber, Date birthday, String username, String password) {
        super(username, password, ref_id);
        this.name = name;
        this.ref_id = ref_id;
        this.PhoneNumber = phoneNumber;
        this.birthday = birthday;
        this.games = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Referee))
            return false;
        Referee other = (Referee) o;
        return this.getRef_id() == other.getRef_id();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ref_id);
    }

    public int getRef_id() {
        return ref_id;
    }

    public void setRef_id(int ref_id) {
        this.ref_id = ref_id;
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

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public Game getGame(int id) {
        for (int i = 0; i < this.games.size(); i++) {
            if(this.games.get(i).getGame_id() == id){
                return this.games.get(i);
            }
        }
        return null;
    }

    public boolean addGame(Game game) {
        if(this.getGame(game.getGame_id()) != null) {
            System.out.println("Game Already Exists in for Referee");
            return false;
        }
        else{
            this.games.add(game);
            return true;
        }
    }
}
