package DB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbControllerTest {
    DbController dbController = new DbController();

    @Test
    void createTables() {
        assertTrue(dbController.createTables());
        assertTrue(dbController.getAllReferees().size() == 0);
        assertTrue(dbController.getAllUsers().size() == 0);
        assertTrue(dbController.getAllTeams().size() == 0);
        assertTrue(dbController.getAllGames().size() == 0);
    }
}