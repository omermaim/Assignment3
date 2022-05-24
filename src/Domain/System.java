package Domain;

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

}
