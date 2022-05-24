package Domain;

public class Team {
    private String name;
    private int team_id;
    private String field;
    private League league;

    public Team(String name, int team_id, String field, League league) {
        this.name = name;
        this.team_id = team_id;
        this.field = field;
        this.league = league;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }
}
