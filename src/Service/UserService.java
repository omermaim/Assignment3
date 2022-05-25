package Service;
import Domain.*;
import Domain.system;

import java.sql.Date;
import java.util.ArrayList;

public class UserService {
    system Service_controller;

    public UserService(System service_controller) {
        Service_controller = system.getinstance();
    }
    public boolean Login(String login_name,String login_password){
        return Service_controller.Login(login_name,login_password);
    }
    public boolean addReferee(String name, String id, String password,String birthdate,String phone){
        return Service_controller.addReferee(name,id,password,birthdate,phone);
    }
    public boolean gamePlacement(int hometeam_id, int guestteam_id, ArrayList<Referee> threereferees, Date date){
        return Service_controller.gamePlacement(hometeam_id,guestteam_id,threereferees, date);
    }
}
