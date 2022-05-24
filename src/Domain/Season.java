package Domain;

import java.sql.Date;
import java.util.ArrayList;

public class Season {
    private String season_name;
    private Date start_date;
    private Date end_date;
    private ArrayList<League> leagues;

    public Season(String season_name, Date start_date, Date end_date) {
        this.season_name = season_name;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String getSeason_name() {
        return season_name;
    }

    public void setSeason_name(String season_name) {
        this.season_name = season_name;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public ArrayList<League> getLeagues() {
        return leagues;
    }

    public void AddLeague(League league) {
        this.leagues.add(league);
    }
}
