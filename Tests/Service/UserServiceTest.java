package Service;

import DB.DbController;
import Domain.Referee;
import Domain.Team;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private static DbController dbController;
    private static UserService userService;
//    UserService userService = new UserService();
//    DbController dbController = new DbController();

    @org.junit.jupiter.api.BeforeAll
    static void beforeUnitTests(){
        userService = new UserService();
        dbController = new DbController();
        /**/
        dbController.createTables();
        /*TODO:
            Maybe it's better to add this user to DB instead of doing it here*/
        userService.addReferee(20, "tomer weitzman", "054-5872007",
                new Date(1920,2,20), "bgu4ever", "bgu2022");
        userService.addReferee(2, "Weitzman Tomer Weitzman", "050-8873928",
                new Date(1994,6,26), "Tomer", "tomer123");
        userService.addReferee(15, "Man Omri Man", "052-5216644",
                new Date(1993,12,15), "Omri", "tomer555");
        userService.addReferee(31, "Weitzman Dylan Weitzman", "050-8873928",
                new Date(2000,5,31), "Dylan", "dylan123");
        userService.addReferee(4, "ref4", "050-8873928",
                new Date(1994,6,26), "ref4", "tomer123");
        userService.addReferee(5, "ref5", "050-8873928",
                new Date(1994,6,26), "ref5", "tomer123");

        //Teams
        userService.addTeam(1, "Hodisans", "sami-ofer");
        userService.addTeam(2, "Maimons", "nokia");
        userService.addTeam(3, "team3", "kunhiya");
        userService.addTeam(4, "team4", "Doha");
        userService.addTeam(5, "team5", "Bloomfield");

    }


    /*add Referee tests*/
    @Test
    void addReferee() {
        assertTrue(userService.addReferee(12345678, "Yaniv Gadol", "054-1234567",
                new Date(1994,1,1), "tomer", "tomer123"));
    }

    @Test
    void addRefereeSameId() {
        userService.addReferee(777, "tomer weitzman", "050-8873928",
                new Date(1994,6,26), "kingtomer", "tomer123");
        /*same id*/
        assertFalse(userService.addReferee(777, "tomer weitzman", "050-8873928",
                new Date(1994,6,26), "kingtomer2", "tomer123"));
    }

    @Test
    void addRefereeSameUserName() {
        /*same username*/
        userService.addReferee(555, "tomer weitzman", "050-8873928",
                new Date(1994,6,26), "CEOtomer", "tomer123");
        assertFalse(userService.addReferee(222, "tomer weitzman", "050-8873928",
                new Date(1994,6,26), "CEOtomer", "tomer123"));
    }

    @Test
    void addRefereeMissingId() {
        /*missing id*/
        assertFalse(userService.addReferee(0, "Yaniv Gadol", "054-1234567",
                new Date(1994,1,1), "tomer", "tomer123"));
    }

    @Test
    void addRefereeInvalidBirthDay() {
        /*1.1.2023 its an invalid date of birth*/
        assertFalse(userService.addReferee(222, "Big Yaniv", "054-5872007",
                new Date(2023,1,1), "Yaniv Katan", "yaniv20"));
    }

    /*End of add Referee tests*/



    /*Login tests */
    @Test
    void login() {
        assertTrue(userService.Login("bgu4ever", "bgu2022"));
    }

    @Test
    void loginWrongPassword() {
        /*2022 is not the password of the user "bgu4ever"*/
        assertFalse(userService.Login("bgu4ever", "2022"));
    }

    @Test
    void loginIncorrectUsername() {
        /*There is no user with username "bguNot4ever"*/
        assertFalse(userService.Login("bguNot4ever", "bgu2022"));
    }

    @Test
    void loginAlreadyLogged() {
        /*need to run login before it */
        assertFalse(userService.Login("bgu4ever", "bgu2022"));
//        userService.addReferee(999, "Ben Gurion", "054-5872007",
//                new Date(1886,10,16), "KingDavid", "bgu1886");
//        userService.Login("KingDavid", "bgu1886");
//        assertFalse(userService.Login("KingDavid", "bgu1886"));
    }

    @Test
    void loginNullPassword() {
        /*password:null*/
        assertFalse(userService.Login("Dylan", null));
    }

    /*End of login tests */





    /*Game Placement tests */
    @Test
    void gamePlacement() {
        //refs
        ArrayList<Referee> threereferees = new ArrayList<>();
        threereferees.add(dbController.getRefereeById(2));
        threereferees.add(dbController.getRefereeById(15));
        threereferees.add(dbController.getRefereeById(31));
        //Teams
        Team team1 = dbController.getTeamById(1);
        Team team2 = dbController.getTeamById(2);

        assertTrue(userService.gamePlacement(1,team1,team2, threereferees, new Date(2022,8,1), team1.getField()));

        /*assertTrue(userService.addTeam(3, "team3", "kunhiya"));*/

        //same team
        assertFalse(userService.gamePlacement(1,team1,team1, threereferees, new Date(2022,8,1), team1.getField()));
        //field doesn't belong to either team
        assertFalse(userService.gamePlacement(1,team1,team2, threereferees, new Date(2022,8,1), "WonderLand"));
    }

    @Test
    void gamePlacementRefereeNotAvailable() {
        /*Referee 2 (tomer) not available at 1/8/22 because he works in team1.getField() (game_id:1) */
        ArrayList<Referee> threereferees = new ArrayList<>();
        threereferees.add(dbController.getRefereeById(20));
        threereferees.add(dbController.getRefereeById(2));//not available at 1/8/22 because he works in team1.getField()
        threereferees.add(dbController.getRefereeById(4));
        //Teams
        Team team1 = dbController.getTeamById(4);
        Team team2 = dbController.getTeamById(5);

        assertFalse(userService.gamePlacement(11,team1,team2, threereferees, new Date(2022,8,1), team1.getField()));

    }

    @Test
    void gamePlacementTeamNotAvailable() {
        /*Team 1 (Hodisans) is not available because they supposed to lose to team "Maimons" in "Sami-Ofer" on this date */
        ArrayList<Referee> threereferees = new ArrayList<>();
        threereferees.add(dbController.getRefereeById(20));
        threereferees.add(dbController.getRefereeById(5));
        threereferees.add(dbController.getRefereeById(4));
        //Teams
        Team team1 = dbController.getTeamById(1);
        Team team3 = dbController.getTeamById(3);

        assertFalse(userService.gamePlacement(12,team1,team3, threereferees, new Date(2022,8,1), team1.getField()));
    }

    @Test
    void gamePlacementSameTeam() {
        //refs
        ArrayList<Referee> threereferees = new ArrayList<>();
        threereferees.add(dbController.getRefereeById(20));
        threereferees.add(dbController.getRefereeById(5));
        threereferees.add(dbController.getRefereeById(4));
        //Teams
        Team team1 = dbController.getTeamById(1);

        //same team
        assertFalse(userService.gamePlacement(1,team1,team1, threereferees, new Date(2022,8,1), team1.getField()));
    }

    @Test
    void gamePlacementFieldForeign() {
        //refs
        ArrayList<Referee> threereferees = new ArrayList<>();
        threereferees.add(dbController.getRefereeById(20));
        threereferees.add(dbController.getRefereeById(5));
        threereferees.add(dbController.getRefereeById(4));
        //Teams
        Team team1 = dbController.getTeamById(4);
        Team team2 = dbController.getTeamById(5);

        //field doesn't belong to either team
        assertFalse(userService.gamePlacement(1,team1,team2, threereferees, new Date(2022,8,1), "WonderLand"));
    }

    /*End of Game Placement tests*/
}