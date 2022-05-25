//package Domain;
//
//import java.util.ArrayList;
//
//public class League {
//    private String season;
//    private int league_id;
//    private String League_name;
//    private int number_of_teams;
//    private ArrayList<Referee> referees;
//    private ArrayList<Game> games;
//
//    public League(String season, int league_id, String league_name, int number_of_teams) {
//        this.season = season;
//        this.league_id = league_id;
//        League_name = league_name;
//        this.number_of_teams = number_of_teams;
//        this.referees=new ArrayList<Referee>();
//        this.games=new ArrayList<Game>();
//
//    }
//
//    public int getLeague_id() {
//        return league_id;
//    }
//
//    public void setLeague_id(int league_id) {
//        this.league_id = league_id;
//    }
//
//    public String getLeague_name() {
//        return League_name;
//    }
//
//    public void setLeague_name(String league_name) {
//        League_name = league_name;
//    }
//
//    public int getNumber_of_teams() {
//        return number_of_teams;
//    }
//
//    public void setNumber_of_teams(int number_of_teams) {
//        this.number_of_teams = number_of_teams;
//    }
//
//
//    public void AddReferee(Referee ref){
//        this.referees.add(ref);
//    }
//    public void AddGame(Game game){
//        this.games.add(game);
//    }
//}
