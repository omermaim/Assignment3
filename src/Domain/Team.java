package Domain;

import java.util.ArrayList;
import java.util.Objects;

public class Team {
    private int team_id;
    private String name;
    private String field;
    private ArrayList<Game> games;

    public Team(int team_id, String name, String field) {
        this.team_id = team_id;
        this.name = name;
        this.field = field;
        this.games = new ArrayList<>();
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

    @Override
    public int hashCode() {
        return Objects.hash(team_id);
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
            System.out.println("Game Already Exists in for Team");
            return false;
        }
        else{
            this.games.add(game);
            return true;
        }
    }

}
