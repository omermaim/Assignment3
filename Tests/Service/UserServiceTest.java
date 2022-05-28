package Service;

import DB.DbController;
import Domain.Referee;
import Domain.Team;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserService userService = new UserService();
    DbController dbController = new DbController();

    @Test
    void addReferee() {
        assertTrue(dbController.createTables());
        assertTrue(userService.addReferee(1, "tomer weitzman", "050-8873928",
                new Date(1994,6,26), "tomer", "tomer123"));
        //same id
        assertFalse(userService.addReferee(1, "tomer weitzman", "050-8873928",
                new Date(1994,6,26), "tomer2", "tomer123"));
        //same username
        assertFalse(userService.addReferee(2, "tomer weitzman", "050-8873928",
                new Date(1994,6,26), "tomer", "tomer123"));
        assertTrue(userService.addReferee(2, "tomer weitzman", "050-8873928",
                new Date(1994,6,26), "tomer2", "tomer123"));


    }

    @Test
    void login() {
        dbController.createTables();
        assertTrue(userService.addReferee(1, "tomer weitzman", "050-8873928",
                new Date(1994,6,26), "tomer1", "tomer123"));
        assertTrue(userService.addReferee(2, "tomer weitzman", "050-8873928",
                new Date(1994,6,26), "tomer2", "tomer123"));
        //Invalid username
        assertFalse(userService.Login("tomer3", "tomer123"));
        //Invalid password
        assertFalse(userService.Login("tomer1", "tomer321"));
        //valid login1
        assertTrue(userService.Login("tomer1", "tomer123"));
        //valid login2
        assertTrue(userService.Login("tomer2", "tomer123"));
        //Invalid login - already logged
        assertFalse(userService.Login("tomer1", "tomer123"));
    }

    @Test
    void gamePlacement() {
        dbController.createTables();
        //refs
        assertTrue(userService.addReferee(1, "ref1", "050-8873928",
                new Date(1994,6,26), "ref1", "tomer123"));
        assertTrue(userService.addReferee(2, "ref2", "050-8873928",
                new Date(1994,6,26), "ref2", "tomer123"));
        assertTrue(userService.addReferee(3, "ref3", "050-8873928",
                new Date(1994,6,26), "ref3", "tomer123"));
        ArrayList<Referee> threereferees = dbController.getAllReferees();
        assertTrue(userService.addReferee(4, "ref4", "050-8873928",
                new Date(1994,6,26), "ref4", "tomer123"));

        //Teams
        assertTrue(userService.addTeam(1, "team1", "sami-ofer"));
        Team team1 = dbController.getTeamById(1);
        assertTrue(userService.addTeam(2, "team2", "nokia"));
        Team team2 = dbController.getTeamById(2);
        assertTrue(userService.addTeam(3, "team3", "kunhiya"));

        //same team
        assertFalse(userService.gamePlacement(1,team1,team1, threereferees, new Date(2022,8,1), team1.getField()));



    }
}