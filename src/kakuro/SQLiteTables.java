package kakuro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteTables {
    private static Connection con;
    private static boolean hasData = false;

//gets connection
    public void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:SQLiteKakuro.db");
        initialise();
    }

    public void initialise() throws SQLException {
        if(!hasData) {
            hasData = true;
            Statement state = con.createStatement();
            Statement statePro = con.createStatement();
            Statement statePla = con.createStatement();
            
            //get the sets from the tables
            ResultSet res =  state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='game'");
            ResultSet res2 = statePro.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='gameProgress'");
            ResultSet res3 = statePla.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='player'");
            
            //If there are no tables, create them
            if(!res.next()) {   
                //need to build the table
                Statement state2 = con.createStatement();
                state2.execute("CREATE TABLE game(gameID integer primary key," 
                + "username varchar(60)," + "time integer," + "cells blob);");
                System.out.println("Game table has been created");
            }

            if(!res2.next()) {  
                //need to build the table
                Statement state2 = con.createStatement();
                state2.execute("CREATE TABLE gameProgress(gameProgressID integer primary key," 
                + "username varchar(60)," + "time integer," + "cells blob," + "gameID integer," 
                        + "FOREIGN KEY(gameID) REFERENCES game (gameID)"
                        + ");");
                System.out.println("GameProgress table been created");
            }

            if(!res3.next()) {  
                //need to build the table
                Statement state2 = con.createStatement();
                state2.execute("CREATE TABLE player(playerID integer," + "username varchar(60) primary key unique,"
                + "password varchar(60)"
                + ");");
                System.out.println("Player table been created");
            }
        }
    }
}
