package Service;

import DB.DbController;
import org.junit.jupiter.api.Test;

import java.sql.Date;

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
    }
}