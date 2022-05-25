package Domain;

public class Team {
    private int team_id;
    private String name;
    private String field;
    private String league;

    public Team(int team_id, String name, String field, String league) {
        this.team_id = team_id;
        this.name = name;
        this.field = field;
        this.league = league;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Team))
            return false;
        Team other = (Team) o;
        return this.getTeam_id() == other.getTeam_id();
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

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }
}
