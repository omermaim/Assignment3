package DB;
import Domain.Game;
import Domain.Referee;
import Domain.Team;
import Domain.User;
import java.sql.*;
import java.util.ArrayList;


public class DbController {

    static final String url =  "jdbc:sqlite:resources/DataBase.db";

//    public DbController(){
//
//    }

    private Connection getConnection(){
        try{
            return DriverManager.getConnection(url);
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
                    "SELECT ref_id, name, phonenumber, birthday, username, password " +
                        "FROM (Referee INNER JOIN Users ON Referee.ref_id == Users.UserID);");

            ArrayList<Referee> res = new ArrayList<>();
            ArrayList<Game> games = this.getAllGames();
            while (rs.next()) {
                Referee referee = new Referee(
                        rs.getInt("ref_id"),
                        rs.getString("name"),
                        rs.getString("phonenumber"),
                        rs.getDate("birthday"),
                        rs.getString("username"),
                        rs.getString("password"));
                referee.setGames(this.getRefereeGames(referee, games));
                res.add(referee);
            }
            return res;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    private ArrayList<Game> getRefereeGames(Referee ref, ArrayList<Game> games) {
        games.removeIf(game -> {
            ArrayList<Referee> gameRefs = game.getReferees();
            return gameRefs.contains(ref);
        });
        return games;
    }

    public boolean insertReferee(int id, String name, String PhoneNumber, Date birthday){
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO referee(ref_id, name, PhoneNumber, birthday)\n" +
                    "VALUES ( ? , ? , ? , ? );");
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setString(3, PhoneNumber);
            stmt.setDate(4, birthday);

            stmt.execute();
            return true;
        } catch (SQLException e ) {
            System.out.println(e);
        }
        return false;
    }

    public Referee getRefereeById(int ref_id) {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("select * from Referee WHERE Referee.ref_id == ? ");
            stmt.setInt(1, ref_id);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Game> games = this.getAllGames();
            Referee referee = new Referee(
                    rs.getInt("ref_id"),
                    rs.getString("name"),
                    rs.getString("phonenumber"),
                    rs.getDate("birthday"),
                    rs.getString("username"),
                    rs.getString("password"));

            referee.setGames(this.getRefereeGames(referee, games));
            return referee;
        } catch (SQLException e ) {
            System.out.println(e);
        }
        return null;
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

    public boolean insertUser(String username, String password, int UserID){
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Users(username, password, UserID)\n" +
                            "VALUES ( ? , ? , ? );");

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setInt(3, UserID);

            stmt.execute();
            return true;
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
            ArrayList<Game> games = this.getAllGames();
            while (rs.next()) {
                Team team  = new Team(
                        rs.getInt("team_id"),
                        rs.getString("name"),
                        rs.getString("field"));

                team.setGames(this.getTeamGames(team.getTeam_id(), games));
                res.add(team);
            }
            return res;
        } catch (SQLException e ) {
            System.out.println(e);
        }
        return null;
    }

    private ArrayList<Game> getTeamGames(int id, ArrayList<Game> games) {
        games.removeIf(game -> game.getGuest_team_id() != id && game.getHome_team_id() != id);
        return games;
    }

    public boolean insertTeam(int team_id, String name, String field){
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Team(team_id, name, field)\n" +
                            "VALUES ( ? , ? , ? );");

            stmt.setInt(1, team_id);
            stmt.setString(2, name);
            stmt.setString(3, field);

            stmt.execute();
            return true;
        } catch (SQLException e ) {
            System.out.println(e);
        }
        return false;
    }

    public Team getTeamById(int team_id) {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("select * from Team WHERE Team.team_id == ? ");
            stmt.setInt(1, team_id);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Game> games = this.getAllGames();
            Team team  = new Team(
                    rs.getInt("team_id"),
                    rs.getString("name"),
                    rs.getString("field"));

            team.setGames(this.getTeamGames(team.getTeam_id(), games));
            return team;
        } catch (SQLException e ) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Game> getAllGames(){
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Game");
            ArrayList<Game> res = new ArrayList<>();
            while (rs.next()) {
                Game game  = new Game(
                        rs.getInt("game_id"),
                        rs.getInt("home_team_id"),
                        rs.getInt("guest_team_id"),
                        rs.getInt("ref1"),
                        rs.getInt("ref2"),
                        rs.getInt("ref3"),
                        rs.getDate("game_date"),
                        rs.getString("field"));
                res.add(game);
            }
            return res;
        } catch (SQLException e ) {
            System.out.println(e);
        }
        return null;
    }

    public boolean insertGame(int game_id, int home_team_id, int guest_team_id, int ref1, int ref2, int ref3, Date game_date, String field){
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Game(game_id, home_team, guest_team, game_date)\n" +
                            "VALUES ( ? , ? , ? , ? , ? , ? , ? , ? );");

            stmt.setInt(1, game_id);
            stmt.setInt(2, home_team_id);
            stmt.setInt(3, guest_team_id);
            stmt.setInt(4, ref1);
            stmt.setInt(5, ref2);
            stmt.setInt(6, ref3);
            stmt.setDate(7, game_date);
            stmt.setString(8, field);

            stmt.execute();
            return true;
        } catch (SQLException e ) {
            System.out.println(e);
        }
        return false;
    }

    public Game getGameById(int game_id) {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("select * from Game WHERE Game.game_id == ? ");
            stmt.setInt(1, game_id);
            ResultSet rs = stmt.executeQuery();
            return new Game(
                    rs.getInt("game_id"),
                    rs.getInt("home_team_id"),
                    rs.getInt("guest_team_id"),
                    rs.getInt("ref1"),
                    rs.getInt("ref2"),
                    rs.getInt("ref3"),
                    rs.getDate("game_date"),
                    rs.getString("field"));

        } catch (SQLException e ) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Game> getAllGamesByDate(Date game_date) {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("select * from Game WHERE Game.game_date == ? ");
            stmt.setDate(1, game_date);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Game> res = new ArrayList<>();
            while (rs.next()) {
                Game game  = new Game(
                        rs.getInt("game_id"),
                        rs.getInt("home_team_id"),
                        rs.getInt("guest_team_id"),
                        rs.getInt("ref1"),
                        rs.getInt("ref2"),
                        rs.getInt("ref3"),
                        rs.getDate("game_date"),
                        rs.getString("field"));
                res.add(game);
            }
            return res;
        } catch (SQLException e ) {
            System.out.println(e);
        }
        return null;
    }


    public boolean createTables(){
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("Drop Table If EXISTS Users;");
            stmt.execute("Drop Table If EXISTS Referee;");
            stmt.execute("Drop Table If EXISTS Team;");
            stmt.execute("Drop Table If EXISTS Game;");
            stmt.execute("CREATE TABLE Referee (\n" +
                    "    ref_id int NOT NULL PRIMARY KEY,\n" +
                    "    name varchar(255) NOT NULL,\n" +
                    "    PhoneNumber varchar(15),\n" +
                    "    birthday DATE\n" +
                    ");");
            stmt.execute("CREATE TABLE Users (\n" +
                    "    username varchar(16) NOT NULL PRIMARY KEY,\n" +
                    "    password varchar(16) NOT NULL,\n" +
                    "    UserID int NOT NULL REFERENCES Referee(ref_id)\n" +
                    ");");
            stmt.execute("CREATE TABLE Team (\n" +
                    "    team_id int NOT NULL PRIMARY KEY,\n" +
                    "    name varchar(255) NOT NULL,\n" +
                    "    field varchar(255) NOT NULL\n" +
                    ");");
            stmt.execute("CREATE TABLE Game (\n" +
                    "    game_id int NOT NULL PRIMARY KEY,\n" +
                    "    home_team int NOT NULL REFERENCES Team(team_id),\n" +
                    "    guest_team int NOT NULL REFERENCES Team(team_id),\n" +
                    "    ref1 int NOT NULL REFERENCES Referee(ref_id),\n" +
                    "    ref2 int NOT NULL REFERENCES Referee(ref_id),\n" +
                    "    ref3 int NOT NULL REFERENCES Referee(ref_id),\n" +
                    "    game_date DATE NOT NULL,\n" +
                    "    field varchar(255) NOT NULL\n" +
                    ");");

            return true;

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
            return false;
        }
    }



}
