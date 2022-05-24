package Service;
import Domain.*;
import Domain.System;

import java.sql.Date;
import java.util.ArrayList;

public class UserService {
    System Service_controller;

    public UserService(System service_controller) {
        Service_controller = System.getinstance();
    }
    public boolean Login(String login_name,String login_password){
        return Service_controller.Login(login_name,login_password);
    }
    public boolean addReferee(String name, int id, Date birthdate,int phone){
        return Service_controller.addReferee(name,id,birthdate,phone);
    }
    public boolean gamePlacement(int hometeam_id, int guestteam_id, ArrayList<Referee> threereferees,Date date){
        return Service_controller.gamePlacement(hometeam_id,guestteam_id,threereferees);
    }
}
