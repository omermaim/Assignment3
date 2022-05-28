package DB;
import Domain.Game;
import Domain.Referee;
import Domain.Team;
import Domain.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;

public class DbAdapter {
    DbController dbController;

    public DbAdapter(){
        this.dbController = new DbController();
    }

    public User getUser(String username){
        ArrayList<User> users = dbController.getAllUsers();
        if(users != null){
            for (int i = 0; i < users.size(); i++) {
                if(users.get(i).getUsername() == username){
                    return users.get(i);
                }
            }
        }
        return null;
    }

    public boolean refereeExists(int ref_id, String username){
        ArrayList<Referee> referees = dbController.getAllReferees();
        if(referees != null){
            for (int i = 0; i < referees.size(); i++) {
                if(referees.get(i).getUsername() == username || referees.get(i).getUserID() == ref_id){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addReferee(int ID, String name, String phoneNumber, Date birthday, String username, String password) {
        if(dbController.insertReferee(ID, name, phoneNumber, birthday) && dbController.insertUser(username, password, ID)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean placeGame(int game_id, Team home_team, Team guest_team, ArrayList<Referee> threereferees, Date date, String field) {
        //check game exists
        if(dbController.getGameById(game_id) != null){
            System.out.println("Game Placement Failed - Game ID already exists");
            return false;
        }
        else if(home_team.getField() != field && guest_team.getField() != field){
            System.out.println("Game Placement Failed - Field doesn't belong to any of the teams");
            return false;
        }
        ArrayList<Game> gamesInDate = dbController.getAllGamesByDate(date);
        for (Game game : gamesInDate){
            for (Referee ref : threereferees) {
                if(ref.getRef_id() == game.getRef1() || ref.getRef_id() == game.getRef2() || ref.getRef_id() == game.getRef3()){
                    System.out.println("Game Placement Failed - Referee already has a game that day");
                    return false;
                }
            }
            if(game.getHome_team_id() == home_team.getTeam_id() || game.getHome_team_id() == guest_team.getTeam_id() ||
                    game.getGuest_team_id() == home_team.getTeam_id() || game.getGuest_team_id() == guest_team.getTeam_id()){
                System.out.println("Game Placement Failed - Team already has a game that day");
                return false;
            }
        }
        return dbController.InsertGame(game_id, home_team.getTeam_id(), guest_team.getTeam_id(),
                threereferees.get(0).getRef_id(), threereferees.get(1).getRef_id(), threereferees.get(2).getRef_id(), date, field);
    }


}
