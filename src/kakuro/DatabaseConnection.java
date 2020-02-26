package kakuro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private Connection connection = null;
    private final static String DATABASE_FILE_PATH = "resources/SQLiteKakuro.db";
    
    public Connection connect() {
        String createGameTableQuery = "CREATE TABLE IF NOT EXISTS game"
                                        + "(gameID INTEGER PRIMARY KEY AUTOINCREMENT," 
                                        + "username VARCHAR(60)," 
                                        + "time INTEGER," + 
                                        "cells BLOB);";
        
        String createGameProgressQuery = "CREATE TABLE IF NOT EXISTS gameProgress"
                                            + "(gameProgressID INTEGER PRIMARY KEY AUTOINCREMENT," 
                                            + "time INTEGER,"
                                            + "cells BLOB,"
                                            + "gameID INTEGER,"
                                            + "username VARCHAR(60),"
                                            + "FOREIGN KEY(gameID) REFERENCES game (gameID),"
                                            + "FOREIGN KEY(username) REFERENCES player (username)"
                                            + ");";
        
        String createPlayerQuery = "CREATE TABLE IF NOT EXISTS player"
                                    + "(username VARCHAR(60) PRIMARY KEY,"
                                    + "password VARCHAR(60)"
                                    + ");";
        
        try {
            
            connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_FILE_PATH);
            System.out.println("Success! Connected to SQLite database");

            Statement gameTableStmt = connection.createStatement();
            Statement gameProgressStmt = connection.createStatement();
            Statement playerStmt = connection.createStatement();
            
            gameTableStmt.execute(createGameTableQuery);
            gameProgressStmt.execute(createGameProgressQuery);
            playerStmt.execute(createPlayerQuery);
            
            
        } catch (Exception e) {
            System.err.println("Failed to connect to SQLite database");
        }
        
        return connection;
    }
    
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();

                System.out.println("Success! Disconnected from SQLite database");
            }
        } catch (Exception e) {
            System.err.println("Failed to disconnect from SQLite database");
        }
       
    }
    
    protected Boolean login(String uid, String pwd) {
           String query = "SELECT * FROM player where username=? AND password=? ";
           
           try {
               connection = this.connect();
               PreparedStatement psmt = connection.prepareStatement(query);
   
               psmt.setString(1, uid);
               psmt.setString(2, pwd);
               
               ResultSet rs = psmt.executeQuery();
               
               int count = 0;
               
               while(rs.next()) {
                   count++;
               }
               
               if (count == 1) {
                   return true;
               }
               
           } catch (SQLException e ) {
               System.err.println("Failed to login");
           }
           
           
           return false;
        
    }
    
    protected Boolean register(String uid, String pwd) {
        String checkIfUserExistQuery = "SELECT * FROM player where username=? ";
        String registerQuery = "INSERT INTO player (username, password) VALUES (?, ?)";
          
        try {
            connection = this.connect();
            
            PreparedStatement checkUserPsmt = connection.prepareStatement(checkIfUserExistQuery);
            checkUserPsmt.setString(1, uid);
            
            ResultSet rs = checkUserPsmt.executeQuery();
            
            int count = 0;
            
            while(rs.next()) {
                count++;
            }
            
            if(count > 0) {
                System.err.println("User already exist");
                return false;
            } else {
                PreparedStatement registerPsmt = connection.prepareStatement(registerQuery);
                registerPsmt.setString(1, uid);
                registerPsmt.setString(2, pwd);
                
                registerPsmt.executeUpdate();
                System.out.println("Succesfully registered");
                return true;
            }
            
            
        } catch (SQLException e) {
            System.err.println("Failed to register");
        }
        
        return false;
    }
    
    
    
    // save 
    // take in object / array 
    // insert to db (serializing)
    //talk :d
    // load 
    // select the username
    // de-serialize back to an object
    // return data (which format)
}
