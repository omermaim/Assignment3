package Domain;

import java.sql.Date;
import java.util.ArrayList;
import DB.DbController;

public class system {
    private static system system =null;
    private User current_user;
    private ArrayList<User> users;
    private ArrayList<Referee> referees;
    private ArrayList<Game> games;
    private ArrayList<Team> teams;
    private DbController db;

    private system() {
        db = new DbController();
        users = db.getAllUsers();
        referees = db.getAllReferees();

//        games = db.getAllGames();
//        teams = db.getAllTeams();

    }


    public static system getinstance(){
        if(system==null){
            system = new system();
        }
        return system;

    }

    /*Getters and setters */

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Referee> getReferees() {
        return referees;
    }

    public void setReferees(ArrayList<Referee> referees) {
        this.referees = referees;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public DbController getDb() {
        return db;
    }

    public void setDb(DbController db) {
        this.db = db;
    }

    /*end of getter and setters*/


    public boolean Login(String login_name, String login_password) {
//        if ()
        return false;
    }

    public boolean addReferee(String name, String id,String password, String birthdate, String phone) {
        if (current_user==null) { return false;}
        /*check if user exists*/
        if (!this.UserExists(name,id)){
//            add user
//            InsertUser
            if (!db.InsertUser(name,password,id)) {
                System.out.println("Failed insert user to DB");
                return false;
            }
        } else {
            /*check valid password*/
            System.out.println("User already exists in DB ! check that the password is valid");
            if (!CheckValidPasswordUser(name,id,password)) {
                System.out.println("Wrong password ! ");
                return false;
            }
        }
        if (!this.RefereeExists(name,id)){
            if (db.InsertReferee(id,name,phone,birthdate)){
                System.out.println("Add Referee successfully");
            } else {
                System.out.println("Failed insert Referee to DB");
            }
        } else {
            System.out.println("Referee already exists.");
            return false;
        }
        return true;
    }


    /*checker of objects in DB*/
    public boolean gamePlacement(int hometeam_id, int guestteam_id, ArrayList<Referee> threereferees) {
        return false;
    }
    private boolean UserExists(String name,String id) {
        /*checks if there is a user with this name and ID in DB*/
        for (User user: this.getUsers())
        {
            if (user.getUsername().equals(name) && user.getUserID().equals(id)){
                return true;
            }
        }
        return false;
    }

    private boolean CheckValidPasswordUser(String name,String id,String pass) {
        /*checks if the password is the same password of the user with this name and ID that exists in DB*/
        for (User user: this.getUsers())
        {
            if (user.getUsername().equals(name) && user.getUserID().equals(id)){
                if (user.getPassword().equals(pass)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    private boolean RefereeExists(String name,String id) {
        /*checks if there is a referee with this name and ID in DB*/
        for (Referee referee: this.getReferees())
        {
            if (referee.getName().equals(name) && referee.getID().equals(id)){
                return true;
            }
        }
        return false;
    }


    /*End of checker of objects in DB*/


}
