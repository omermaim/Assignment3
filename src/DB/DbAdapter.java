package DB;
import Domain.Game;
import Domain.Referee;
import Domain.Team;
import Domain.User;

import java.sql.Date;
import java.util.ArrayList;

public class DbAdapter {
    DbController dbController;

    public DbAdapter(){
        this.dbController = new DbController();
    }

    public User getUser(String username){
        ArrayList<User> users = dbController.getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getUsername() == username){
                return users.get(i);
            }
        }
        return null;
    }

    public boolean refereeExists(int ID, String username){
        ArrayList<Referee> referees = dbController.getAllReferees();
        for (int i = 0; i < referees.size(); i++) {
            if(referees.get(i).getUsername() == username || referees.get(i).getUserID() == ID){
                return false;
            }
        }
        return true;
    }

    public boolean addReferee(int ID, String name, String phoneNumber, Date birthday, String username, String password) {
        if(dbController.insertReferee(ID, name, phoneNumber, birthday) && dbController.insertUser(username, password, ID)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkGameID(int game_id){
        if(game_id <= 0){
            System.out.println("Game Placement Failed - Invalid Input");
            return false;
        }
        else{
            ArrayList<Game> games = dbController.getAllGames();
            for (int i = 0; i < games.size(); i++) {
                if(games.get(i).getGame_id() == game_id){
                    System.out.println("Game Placement Failed - Game ID Already Exists");
                    return false;
                }
            }
        }
        return false;
    }




    public boolean checkTeams(Team home_team, Team guest_team, Date date) {
        if( home_team == null || guest_team == null ||  home_team.equals(guest_team)  || date == null){
            System.out.println("Game Placement Failed - Invalid Input");
            return false;
        }
        return false;
    }

    public boolean checkThreeReferres(ArrayList<Referee> threereferees) {
        if( threereferees == null || threereferees.size() != 3 ){
            System.out.println("Game Placement Failed - Invalid Input");
            return false;
        }
        return false;
    }

    public boolean placeGame(int game_id, Team home_team, Team guest_team, ArrayList<Referee> threereferees, Date date) {
        return false;
    }
}
