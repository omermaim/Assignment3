package Domain;

import java.sql.Date;
import java.util.ArrayList;

public class Game {
    private int game_id;
    private int home_team_id;
    private int guest_team_id;
    private int ref1;
    private int ref3;
    private int ref2;
    private Date game_date;
    private String field;
    private ArrayList<Referee> referees;

    public Game(int game_id, int home_team, int guest_team, int ref1, int ref2, int ref3, Date game_date, String field) {
        this.home_team_id = home_team;
        this.guest_team_id = guest_team;
        this.ref1 = ref1;
        this.ref2 = ref2;
        this.ref3 = ref3;
        this.game_date = game_date;
        this.game_id = game_id;
        this.field = field;
        this.referees = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Game))
            return false;
        Game other = (Game) o;
        return this.getGame_id() == other.getGame_id();
    }



    public int getGuest_team_id() {
        return guest_team_id;
    }

    public void setGuest_team_id(int guest_team_id) {
        this.guest_team_id = this.guest_team_id;
    }

    public int getHome_team_id() {
        return home_team_id;
    }

    public void setHome_team_id(int home_team_id) {
        this.home_team_id = home_team_id;
    }

    public int getRef1() {
        return ref1;
    }

    public void setRef1(int ref1) {
        this.ref1 = ref1;
    }

    public int getRef3() {
        return ref3;
    }

    public void setRef3(int ref3) {
        this.ref3 = ref3;
    }

    public int getRef2() {
        return ref2;
    }

    public void setRef2(int ref2) {
        this.ref2 = ref2;
    }

    public Date getDate() {
        return game_date;
    }

    public void setDate(Date game_date) {
        this.game_date = game_date;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public ArrayList<Referee> getReferees() {
        return referees;
    }

    public void setReferees(ArrayList<Referee> referees) {
        this.referees = referees;
    }
}