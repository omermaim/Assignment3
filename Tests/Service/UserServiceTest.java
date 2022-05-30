package Service;
import DB.DbController;
import Domain.Referee;
import Domain.Team;
import org.junit.jupiter.api.*;
import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {
    private static DbController dbController;
    private static UserService userService;

    @BeforeAll
    static void beforeUnitTests(){
        System.out.println("Tests Initialization");
        userService = new UserService();
        dbController = new DbController();

        dbController.createTables();

        //Referees
        userService.addReferee(1, "tomer weitzman", "054-5872007",
                new Date(1920 - 1900,2,20), "bgu4ever", "bgu2022");
        userService.addReferee(2, "Weitzman Tomer Weitzman", "050-8873928",
                new Date(1994 - 1900,6,26), "Tomer", "tomer123");
        userService.addReferee(3, "Man Omri Man", "052-5216644",
                new Date(1993 - 1900,12,15), "Omri", "tomer555");
        userService.addReferee(4, "Weitzman Dylan Weitzman", "050-8873928",
                new Date(2000 - 1900,5,31), "Dylan", "dylan123");
        userService.addReferee(5, "ref4", "050-8873928",
                new Date(1994 - 1900,6,26), "ref4", "tomer123");
        userService.addReferee(6, "ref5", "050-8873928",
                new Date(1994 - 1900,6,26), "ref5", "tomer123");

        //Teams
        userService.addTeam(1, "Hodisans", "sami-ofer");
        userService.addTeam(2, "Maimons", "nokia");
        userService.addTeam(3, "team3", "kunhiya");
        userService.addTeam(4, "team4", "Doha");
        System.out.println("Initialization Finished\n");
    }

    @BeforeEach
    void beforeEachUnitTests(){
        System.out.println("Starting new Test");
    }

    @AfterEach
    void afterEachUnitTests(){
        System.out.println("Test Finished\n");
    }


    /*add Referee tests*/
    @Test
    @Order(1)
    void addReferee() {
        assertTrue(userService.addReferee(12345678, "Yaniv Gadol", "054-1234567",
                new Date(1994 - 1900,1,1), "tomer", "tomer123"));
    }

    @Test
    @Order(2)
    void addRefereeInvalidId() {
        /*Same id*/
        assertFalse(userService.addReferee(12345678, "tomer weitzman", "050-8873928",
                new Date(1994 - 1900,6,26), "Yaniv Katan", "tomer123"));
        /*Invalid id*/
        assertFalse(userService.addReferee(0, "Yaniv Gadol", "054-1234567",
                new Date(1994 - 1900,1,1), "Yaniv Katan", "tomer123"));
    }

    @Test
    @Order(3)
    void addRefereeInvalidUserName() {
        /*same username*/
        assertFalse(userService.addReferee(11111, "tomer weitzman", "050-8873928",
                new Date(1994 - 1900,6,26), "tomer", "tomer123"));
        /*null username*/
        assertFalse(userService.addReferee(11111, "tomer weitzman", "050-8873928",
                new Date(1994 - 1900,6,26), null, "tomer123"));
        /*empty username*/
        assertFalse(userService.addReferee(11111, "tomer weitzman", "050-8873928",
                new Date(1994 - 1900,6,26), " ", "tomer123"));
    }

    @Test
    @Order(4)
    void addRefereeInvalidArgs() {
        assertFalse(userService.addReferee(11111, null, "050-8873928",
                new Date(1994 - 1900,6,26), "tomer", "tomer123"));
        assertFalse(userService.addReferee(11111, "", "050-8873928",
                new Date(1994 - 1900,6,26), "tomer", "tomer123"));
        assertFalse(userService.addReferee(11111, "tomer weitzman", null,
                new Date(1994 - 1900,6,26), "tomer", "tomer123"));
        assertFalse(userService.addReferee(11111, "tomer weitzman", " ",
                new Date(1994 - 1900,6,26), "tomer", "tomer123"));
        assertFalse(userService.addReferee(11111, "tomer weitzman", "050-8873928",
                new Date(1994 - 1900,6,26), "tomer", null));
        assertFalse(userService.addReferee(11111, "tomer weitzman", "050-8873928",
                new Date(1994 - 1900,6,26), "tomer",  "    "));
    }

    @Test
    @Order(5)
    void addRefereeInvalidBirthDay() {
        /*1.1.2023 its an invalid date of birth*/
        assertFalse(userService.addReferee(11111, "Big Yaniv", "054-5872007",
                new Date(2023 - 1900,1,1), "Yaniv Katan", "yaniv20"));
        /*null Date*/
        assertFalse(userService.addReferee(11111, "Big Yaniv", "054-5872007",
                null, "Yaniv Katan", "yaniv20"));
    }

    /*End of add Referee tests*/

    /*Login tests */
    @Test
    @Order(6)
    void login() {
        assertTrue(userService.Login("bgu4ever", "bgu2022"));
    }

    @Test
    @Order(7)
    void loginIncorrectUsername() {
        /*There is no user with username "WrongTomer"*/
        assertFalse(userService.Login("WrongTomer", "tomer123"));
    }

    @Test
    @Order(8)
    void loginWrongPassword() {
        /*2022 is not the password of the user "Wrongtomer123"*/
        assertFalse(userService.Login("Tomer", "Wrongtomer123"));
    }

    @Test
    @Order(9)
    void loginNullUsername() {
        /*password:null*/
        assertFalse(userService.Login(null, "tomer123"));
    }

    @Test
    @Order(10)
    void loginNullPassword() {
        /*password:null*/
        assertFalse(userService.Login("Tomer", null));
    }

    @Test
    @Order(11)
    void loginAlreadyLogged() {
        /*need to run login before it */
        assertFalse(userService.Login("bgu4ever", "bgu2022"));
    }

    /*End of login tests */


    /*Game Placement tests */
    @Test
    @Order(12)
    void gamePlacement() {
        //refs
        ArrayList<Referee> threereferees = new ArrayList<>();
        threereferees.add(dbController.getRefereeById(1));
        threereferees.add(dbController.getRefereeById(2));
        threereferees.add(dbController.getRefereeById(3));
        //Teams
        Team team1 = dbController.getTeamById(1);
        Team team2 = dbController.getTeamById(2);

        assertTrue(userService.gamePlacement(1,team1,team2, threereferees, new Date(2022 - 1900,8,1), team1.getField()));
    }

    @Test
    @Order(13)
    void gamePlacementSameId() {
        //refs
        ArrayList<Referee> threereferees = new ArrayList<>();
        threereferees.add(dbController.getRefereeById(4));
        threereferees.add(dbController.getRefereeById(5));
        threereferees.add(dbController.getRefereeById(6));

        //Teams
        Team team3 = dbController.getTeamById(3);
        Team team4 = dbController.getTeamById(4);

        //same id
        assertFalse(userService.gamePlacement(1,team3,team4, threereferees, new Date(2022 - 1900,8,1), team3.getField()));
    }

    @Test
    @Order(14)
    void gamePlacementRefereeNotAvailable() {
        /*Referee 2 (tomer) not available at 1/8/22 because he works in team1.getField() (game_id:1) */
        ArrayList<Referee> threereferees = new ArrayList<>();
        threereferees.add(dbController.getRefereeById(3)); //not available at 1/8/22 because he works in team1.getField()
        threereferees.add(dbController.getRefereeById(4));
        threereferees.add(dbController.getRefereeById(5));
        //Teams
        Team team3 = dbController.getTeamById(3);
        Team team4 = dbController.getTeamById(4);

        assertFalse(userService.gamePlacement(2,team3,team4, threereferees, new Date(2022 - 1900,8,1), team3.getField()));

    }

    @Test
    @Order(15)
    void gamePlacementTeamNotAvailable() {
        /*Team 1 (Hodisans) is not available because they supposed to lose to team "Maimons" in "Sami-Ofer" on this date */
        ArrayList<Referee> threereferees = new ArrayList<>();
        threereferees.add(dbController.getRefereeById(4));
        threereferees.add(dbController.getRefereeById(5));
        threereferees.add(dbController.getRefereeById(6));
        //Teams
        Team team1 = dbController.getTeamById(1); //not available at 1/8/22 because he works in team1.getField()
        Team team3 = dbController.getTeamById(3);

        assertFalse(userService.gamePlacement(3,team1,team3, threereferees, new Date(2022 - 1900,8,1), team1.getField()));
    }

    @Test
    @Order(16)
    void gamePlacementSameTeam() {
        //refs
        ArrayList<Referee> threereferees = new ArrayList<>();
        threereferees.add(dbController.getRefereeById(4));
        threereferees.add(dbController.getRefereeById(5));
        threereferees.add(dbController.getRefereeById(6));
        //Teams
        Team team4 = dbController.getTeamById(4);

        //same team
        assertFalse(userService.gamePlacement(4,team4,team4, threereferees, new Date(2022 - 1900,8,1), team4.getField()));
    }

    @Test
    @Order(17)
    void gamePlacementFieldForeign() {
        //refs
        ArrayList<Referee> threereferees = new ArrayList<>();
        threereferees.add(dbController.getRefereeById(4));
        threereferees.add(dbController.getRefereeById(5));
        threereferees.add(dbController.getRefereeById(6));
        //Teams
        Team team3 = dbController.getTeamById(3);
        Team team4 = dbController.getTeamById(4);

        //field doesn't belong to either team
        assertFalse(userService.gamePlacement(5,team3,team4, threereferees, new Date(2022 - 1900,8,1), "WonderLand"));
    }

    /*End of Game Placement tests*/
}