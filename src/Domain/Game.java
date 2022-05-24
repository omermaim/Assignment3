package Domain;

import java.sql.Date;

public class Game {
    private Team guest_team;
    private Team home_team;
    private Date date;
    private int game_id;

    public Game(Team guest_team, Team home_team, Date date, int game_id) {
        this.guest_team = guest_team;
        this.home_team = home_team;
        this.date = date;
        this.game_id = game_id;
    }

    public Team getGuest_team() {
        return guest_team;
    }

    public void setGuest_team(Team guest_team) {
        this.guest_team = guest_team;
    }

    public Team getHome_team() {
        return home_team;
    }

    public void setHome_team(Team home_team) {
        this.home_team = home_team;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }
}
