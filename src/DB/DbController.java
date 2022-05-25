package DB;
import Domain.Game;
import Domain.Referee;
import Domain.Team;
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
                        rs.getInt("ID"),
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

    public boolean InsertReferee(int id, String name, String PhoneNumber, String birthday){
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO referee(ID, name, PhoneNumber, birthday)\n" +
                    "VALUES ( ? , ? , ? , ? );");
            stmt.setInt(1, id);
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
                        rs.getInt("UserID"));
                res.add(user);
            }
            return res;
        } catch (SQLException e ) {
            System.out.println(e);
        }
        return null;
    }

    public boolean InsertUser(String username, String password, int UserID){
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Users(username, password, UserID)\n" +
                            "VALUES ( ? , ? , ? );");

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setInt(3, UserID);

            return stmt.execute();
        } catch (SQLException e ) {
            System.out.println(e);
        }
        return false;
    }

    public ArrayList<Team> getAllTeams(){
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Team");
            ArrayList<Team> res = new ArrayList<>();
            while (rs.next()) {
                Team user  = new Team(
                        rs.getInt("team_id"),
                        rs.getString("name"),
                        rs.getString("field"),
                        rs.getString("league"));
                res.add(user);
            }
            return res;
        } catch (SQLException e ) {
            System.out.println(e);
        }
        return null;
    }

    public boolean InsertTeam(int team_id, String name, String field, String league){
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Team(team_id, name, field, league)\n" +
                            "VALUES ( ? , ? , ? , ?);");

            stmt.setInt(1, team_id);
            stmt.setString(2, name);
            stmt.setString(3, field);
            stmt.setString(4, league);

            return stmt.execute();
        } catch (SQLException e ) {
            System.out.println(e);
        }
        return false;
    }

    public ArrayList<Game> getAllGames(){
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Game");
            ArrayList<Game> res = new ArrayList<>();
            while (rs.next()) {
                Game user  = new Game(
                        rs.getInt("game_id"),
                        rs.getInt("home_team_id"),
                        rs.getInt("guest_team_id"),
                        rs.getDate("game_date"),
                        rs.getString("field"));
                res.add(user);
            }
            return res;
        } catch (SQLException e ) {
            System.out.println(e);
        }
        return null;
    }

    public boolean InsertGame(int game_id, int home_team_id, int guest_team_id, Date game_date, String field){
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Game(game_id, home_team, guest_team, game_date)\n" +
                            "VALUES ( ? , ? , ? , ? , ?);");

            stmt.setInt(1, game_id);
            stmt.setInt(2, home_team_id);
            stmt.setInt(3, guest_team_id);
            stmt.setDate(4, game_date);
            stmt.setString(5, field);

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
            stmt.execute(
            "Drop Table If EXISTS Users;\n" +
                "Drop Table If EXISTS Referee;\n" +
                "Drop Table If EXISTS Team;\n" +
                "Drop Table If EXISTS Game;\n" +
                "\n" +
                "CREATE TABLE Referee (\n" +
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
                "    name varchar(255) NOT NULL,\n" +
                "    field varchar(255) NOT NULL,\n" +
                "    league varchar(255) NOT NULL\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Game (\n" +
                "    game_id int NOT NULL PRIMARY KEY,\n" +
                "    home_team int NOT NULL REFERENCES Team(team_id),\n" +
                "    guest_team int NOT NULL REFERENCES Team(team_id),\n" +
                "    game_date DATE NOT NULL,\n" +
                "    field varchar(255) NOT NULL\n" +
                ");");

        } catch (SQLException e ) {
            System.out.println(e);
        }
    }



}
