package DB;
import Domain.Referee;
import Domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DbController {

    static final String url =  "jdbc:sqlite:resources/DataBase.db";

//    public DbController(){
//
//    }

    private Connection getConnection(){
        try{
            Connection conn = DriverManager.getConnection(url);
            return conn;
        }
        catch (SQLException e ) {
            throw new Error("Error connecting to Data Base", e);
        }
    }

    public ArrayList<Referee> getAllReferees(){
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT ID, name, phonenumber, birthday, username, password " +
                        "FROM (Referee INNER JOIN Users ON Referee.ID == Users.UserID);");

            ArrayList<Referee> res = new ArrayList<>();
            while (rs.next()) {
                Referee referee = new Referee(
                        rs.getString("ID"),
                        rs.getString("name"),
                        rs.getString("phonenumber"),
                        rs.getDate("birthday"),
                        rs.getString("username"),
                        rs.getString("password"));
                res.add(referee);
            }
            return res;
        } catch (SQLException e ) {
            System.out.println(e);
        }
        return null;
    }

    public boolean InsertReferee(String id, String name, String PhoneNumber, String birthday){
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO referee(ID, name, PhoneNumber, birthday)\n" +
                    "VALUES ( ? , ? , ? , ? );");
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, PhoneNumber);
            stmt.setString(4, birthday);

            return stmt.execute();
        } catch (SQLException e ) {
            System.out.println(e);
        }
        return false;
    }

    public ArrayList<User> getAllUsers(){
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Users");
            ArrayList<User> res = new ArrayList<>();
            while (rs.next()) {
                User user  = new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("UserID"));
                res.add(user);
            }
            return res;
        } catch (SQLException e ) {
            System.out.println(e);
        }
        return null;
    }

    public boolean InsertUser(String username, String password, String UserID){
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Users(username, password, UserID)\n" +
                            "VALUES ( ? , ? , ? );");

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, UserID);

            return stmt.execute();
        } catch (SQLException e ) {
            System.out.println(e);
        }
        return false;
    }

    public void createTables(){
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("CREATE TABLE Referee (\n" +
                    "    ID int NOT NULL PRIMARY KEY,\n" +
                    "    name varchar(255) NOT NULL,\n" +
                    "    PhoneNumber varchar(15),\n" +
                    "    birthday DATE\n" +
                    ");\n" +
                    "\n" +
                    "CREATE TABLE Users (\n" +
                    "    username varchar(16) NOT NULL PRIMARY KEY,\n" +
                    "    password varchar(16) NOT NULL,\n" +
                    "\tUserID int NOT NULL,\n" +
                    "\tFOREIGN KEY(UserID) REFERENCES Referee(ID)\n" +
                    ");\n" +
                    "\n" +
                    "CREATE TABLE Team (\n" +
                    "    team_id int NOT NULL PRIMARY KEY,\n" +
                    "    name varchar(255) NOT NULL\n" +
                    ");\n" +
                    "\n" +
                    "CREATE TABLE Game (\n" +
                    "    ID int NOT NULL PRIMARY KEY,\n" +
                    "    home_team NOT NULL REFERENCES Team(name),\n" +
                    "    guest_team NOT NULL REFERENCES Team(name),\n" +
                    "    game_date DATE NOT NULL\n" +
                    ");");

        } catch (SQLException e ) {
            System.out.println(e);
        }
    }



}
