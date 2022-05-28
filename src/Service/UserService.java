package Service;
import Domain.*;
import Domain.system;

import java.sql.Date;
import java.util.ArrayList;

public class UserService {
    system Service_controller;

    public UserService() {
        Service_controller = system.getinstance();
    }
    public boolean Login(String login_name,String login_password){
        return Service_controller.Login(login_name,login_password);
    }
    public boolean addReferee(int ref_id, String name, String phoneNumber, Date birthday, String username, String password){
        return Service_controller.addReferee(ref_id, name, phoneNumber, birthday, username, password);
    }
    public boolean gamePlacement(int game_id, Team home_team, Team guest_team, ArrayList<Referee> threereferees, Date date, String field){
        return Service_controller.gamePlacement(game_id, home_team, guest_team, threereferees, date, field);
    }
}
