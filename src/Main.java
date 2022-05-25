import DB.DbController;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        DbController dbController = new DbController();

        dbController.createTables();

//        dbController.InsertReferee(11111, "tomer wei", "050-8873928", "20/12/90");
//        System.out.println(dbController.getAllReferees());
//
//        dbController.InsertUser("omri","tomer111", 11122);
//        System.out.println(dbController.getAllUsers());


    }


}
