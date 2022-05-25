package Domain;

import java.sql.Date;
import java.util.ArrayList;

import DB.DbAdapter;
import DB.DbController;

public class system {
    private static system system =null;
    private ArrayList<User> LoggedInUsers;
    private DbAdapter db;

    private system() {
        db = new DbAdapter();
        LoggedInUsers = new ArrayList<>();
    }


    public static system getinstance(){
        if(system==null){
            system = new system();
        }
        return system;

    }

    public boolean Login(String username, String password) {
        if(username == null || password == null){
            System.out.println("Login Failed - Invalid Input");
            return false;
        }
        User user = db.getUser(username);
        if(user == null || user.getPassword() != password) {
            System.out.println("Login Failed - Invalid Username or Password");
            return false;
        } else{
            this.LoggedInUsers.add(user);
            return true;
        }

    }

    public boolean addReferee(int ID, String name, String phoneNumber, Date birthday, String username, String password) {
        /*check arguments*/
        if(ID <= 0 || name == null || phoneNumber == null ||  birthday == null || username == null || password == null){
            System.out.println("Referee Registration Failed - Invalid Input");
            return false;
        }
        /*check if user or referee ID exists*/
        else if (db.refereeExists(ID, username)) {
            System.out.println("Referee Registration Failed - Username or ID already exists");
            return false;
        }
        /* register referee*/
        else {
            if (db.addReferee(ID, name, phoneNumber, birthday, username, password)){
                System.out.println("Add Referee successfully");
                return true;
            } else {
                System.out.println("Referee Registration Cancelled - Failed insert Referee to DB");
                return false;
            }
        }

    }


    public boolean gamePlacement(int game_id, Team home_team, Team guest_team, ArrayList<Referee> threereferees, Date date) {
        /*check arguments*/
        if(db.checkGameID(game_id) && db.checkTeams(home_team, guest_team, date) && db.checkThreeReferres(threereferees)){
            System.out.println("Game Placement Failed - Invalid Input");
            return false;
        }
        else if(db.placeGame(game_id, home_team, guest_team, threereferees, date)){
            return true;
        }
        else{
            return false;
        }

    }


}
