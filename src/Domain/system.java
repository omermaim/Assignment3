package Domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;

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
        if(username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()){
            System.out.println("Login Failed - Invalid Input");
            return false;
        }
        User user = db.getUser(username);
        if(user == null || !user.getPassword().equals(password)) {
            System.out.println("Login Failed - Invalid Username or Password");
            return false;
        }
        else if(LoggedInUsers.stream().anyMatch(u -> u.getUsername().equals(username))){
            System.out.println("Login Failed - User already logged in");
            return false;
        }
        else{
            this.LoggedInUsers.add(user);
            return true;
        }

    }

    public boolean addReferee(int ref_id, String name, String phoneNumber, Date birthday, String username, String password) {
        /*check arguments*/
        if(ref_id <= 0 || name == null || name.trim().isEmpty() || phoneNumber == null || phoneNumber.trim().isEmpty() ||
                birthday == null || birthday.after(new Date(System.currentTimeMillis())) ||
                username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()){
            System.out.println("Referee Registration Failed - Invalid Input");
            return false;
        }
        /*check if user or referee ref_id exists*/
        else if (db.refereeExists(ref_id, username)) {
            System.out.println("Referee Registration Failed - Username or ID already exists");
            return false;
        }
        /* register referee*/
        else {
            if (db.addReferee(ref_id, name, phoneNumber, birthday, username, password)){
                System.out.println("Added Referee successfully");
                return true;
            } else {
                System.out.println("Referee Registration Cancelled - Failed insert Referee to DB");
                return false;
            }
        }
    }

    public boolean gamePlacement(int game_id, Team home_team, Team guest_team, ArrayList<Referee> threereferees, Date date, String field) {
        /*check arguments*/
        if(game_id <= 0 || home_team == null || guest_team == null || home_team.equals(guest_team) ||
                date == null || date.before(new Date(System.currentTimeMillis())) || threereferees == null ||
                threereferees.size() != 3 || new HashSet<>(threereferees).size() != 3 || field == null){
            System.out.println("Game Placement Failed - Invalid Input");
            return false;
        }
        else if(db.placeGame(game_id, home_team, guest_team, threereferees, date, field)){
            System.out.println("Placed Game successfully");
            return true;
        }
        else{
            System.out.println("Game Placement Failed - Game ID already exists");
            return false;
        }

    }


    public boolean addTeam(int team_id, String name, String field) {
        /*check arguments*/
        if(team_id <= 0 || name == null || name.trim().isEmpty() || field == null || field.trim().isEmpty()){
            System.out.println("Referee Registration Failed - Invalid Input");
            return false;
        }
        /* register team*/
        else {
            if (db.addTeam(team_id, name, field)){
                System.out.println("Added Team successfully");
                return true;
            } else {
                System.out.println("Team Registration Cancelled - Failed insert Team to DB");
                return false;
            }
        }
    }



}
