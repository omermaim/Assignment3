package Domain;

import java.sql.Date;

public class Game {
    private int home_team_id;
    private int guest_team_id;
    private Date game_date;
    private int game_id;
    private String field;

    public Game(int game_id, int home_team_id, int guest_team_id, Date game_date, String field) {
        this.home_team_id = home_team_id;
        this.guest_team_id = guest_team_id;
        this.game_date = game_date;
        this.game_id = game_id;
        this.field = field;
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



    public int getGuest_team() {
        return guest_team_id;
    }

    public void setGuest_team(int guest_team_id) {
        this.guest_team_id = guest_team_id;
    }

    public int getHome_team() {
        return home_team_id;
    }

    public void setHome_team(int home_team_id) {
        this.home_team_id = home_team_id;
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
}
