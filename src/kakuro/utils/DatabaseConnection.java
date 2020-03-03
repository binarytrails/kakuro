//@author Brian Gamboc-Javiniar and Nolan Mckay

package kakuro.utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private Connection connection = null;
    private final static String DATABASE_FILE_PATH = "resources/SQLiteKakuro.db";
    private final String CREATE_GAME_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS game"
            + "(gameID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "username VARCHAR(60)," + "time INTEGER," + "cells TEXT);";
    
    private final String CREATE_GAME_PROGRESS_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS game_progress"
            + "(gameProgressID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "time INTEGER," + "cells TEXT," + "gameID INTEGER,"
            + "username VARCHAR(60),"
            + "FOREIGN KEY(gameID) REFERENCES game (gameID),"
            + "FOREIGN KEY(username) REFERENCES player (username)" + ");";
    
    private final String CREATE_PLAYER_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS player"
            + "(username VARCHAR(60) PRIMARY KEY," + "password VARCHAR(60)"
            + ");";
    
    //TODO: remove hardcoded player in iteration 3
    private final String INSERT_PLAYER_QUERY = "INSERT INTO player(username,password) VALUES(?,?)";
    //TODO: remove this in iteration 3
    private final String INSERT_PLAYER_DATA_QUERY = "INSERT INTO game_progress(username) VALUES(?)";
    
    public DatabaseConnection() {
        this.connect();
        
        /** TODO: to discuss with team - iteration 2
         * To discuss if we should disconnect every time for memory leaks or not. 
         * I would not because we will have a player playing one at a time in there desktop
         * If we agree to not disconnecting for memory leaks due to the argument above,
         * we can listen to the event of closing the application window and close the database.
         * But I think it closes the database anyhow when closing the window.
         */
//        this.disconnect();
    }
    
    public Connection connect() {
        
        try {
            File file = new File(System.getProperty("user.dir") + "/" + DATABASE_FILE_PATH);
            
            if(!file.exists()) {
                
                connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_FILE_PATH);
                System.out.println("Success! Connected to SQLite database");
                
                createGameTable();
                createGameProgressTable();
                createPlayerTable();
                //TODO: remove hardcoded player in iteration 3
                insertMainPlayer();      
              //TODO: remove hardcoded player in iteration 3
                insertPlayerData();
            }
            else {
         
                connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_FILE_PATH);
                System.out.println("Success! Connected to SQLite database");
            }

        } catch (Exception e) {
            System.err.println("Failed to connect to SQLite database");
        }

        return connection;
    }

    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();

                System.out
                        .println("Success! Disconnected from SQLite database");
            }
        } catch (Exception e) {
            System.err.println("Failed to disconnect from SQLite database");
        }

    }
    
    private void createGameTable() throws SQLException {
        Statement stmt = connection.createStatement();  
        stmt.execute(CREATE_GAME_TABLE_QUERY);
    }
    
    private void createGameProgressTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(CREATE_GAME_PROGRESS_TABLE_QUERY);
    }
    
    private void createPlayerTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(CREATE_PLAYER_TABLE_QUERY);
    }
    
    //TODO: remove hardcoded player in iteration 3
    private void insertMainPlayer() throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(INSERT_PLAYER_QUERY);
        
        pstmt.setString(1, "TestPlayer");
        pstmt.setString(2, "123");
        
        pstmt.executeUpdate();
    }
    
    //TODO: remove in iteration 3
    private void insertPlayerData() throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(INSERT_PLAYER_DATA_QUERY);
        
        pstmt.setString(1, "TestPlayer");
        
        pstmt.executeUpdate();
    }
    
    public Connection getConnection() {
    	
    	return connection;
    	
    }
    
    
}
