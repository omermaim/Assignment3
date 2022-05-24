package Domain;

import java.sql.Date;
import java.util.ArrayList;

public class System {
    private static System system =null;
    private User current_user;
    private ArrayList<Referee> referees;


    public static System getinstance(){
        if(system==null){
            system = new System();
        }
        return system;

    }

    public boolean Login(String login_name, String login_password) {
        return false;
    }

    public boolean addReferee(String name, int id, Date birthdate, int phone) {
        return false;
    }

    public boolean gamePlacement(int hometeam_id, int guestteam_id, ArrayList<Referee> threereferees) {
        return false;
    }
}
