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
        assertTrue(userService.addReferee(11111, "tomer weitzman", "050-8873928",
                new Date(1994,6,26), "tomer", "tomer123"));
        assertFalse(userService.addReferee(11111, "tomer weitzman", "050-8873928",
                new Date(1994,6,26), "tomer", "tomer123"));

    }

    @Test
    void login() {
        dbController.createTables();
    }

    @Test
    void gamePlacement() {
        dbController.createTables();
    }
}