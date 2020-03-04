//@author Brian Gamboc-Javiniar and Nolan Mckay

package kakuro.utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
    private final String INSERT_PRECONFIGURED_GAME_DATA_QUERY = "INSERT INTO game(cells) VALUES(?)";
    
    public void connect() {
        
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
                insertPreconfiguredGames();
            }
            else {
         
                connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_FILE_PATH);
                System.out.println("Success! Connected to SQLite database");
            }
            

        } catch (Exception e) {
            System.err.println("Failed to connect to SQLite database");
        }
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
    
    public Connection getConnection() {
        return this.connection;
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
    
    private void insertPreconfiguredGames() throws SQLException {
        String preconfiguredGame1 = "[[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"FILLED10\",\"value1\":37,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"FILLED01\",\"value1\":-1,\"value2\":43},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":3},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":4},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":5},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":6},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":7},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":8},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":9},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":2},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":3},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":4},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":5},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":6},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":7},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"FILLED01\",\"value1\":-1,\"value2\":39},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":9},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":8},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":7},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":5},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":4},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":3},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":2},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}]]"; 
        String preconfiguredGame2 = "[[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"FILLED10\",\"value1\":24,\"value2\":-1},{\"type\":\"FILLED10\",\"value1\":23,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":5},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":6},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":5},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":6},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":5},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":6},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":5},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":6},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"FILLED10\",\"value1\":8,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":5},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":6},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":2},{\"type\":\"FILLED10\",\"value1\":70,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":5},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":7},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":3},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":5},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":9},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":8},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":7},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":5},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"FILLED01\",\"value1\":-1,\"value2\":90},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}]]"; 
        String preconfiguredGame3 = "[[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"FILLED10\",\"value1\":50,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"FILLED10\",\"value1\":5,\"value2\":-1},{\"type\":\"FILLED10\",\"value1\":10,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"FILLED01\",\"value1\":-1,\"value2\":33},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":2},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":3},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":4},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":5},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":6},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":7},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":8},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":9},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":3},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":8},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":9},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":4},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"FILLED11\",\"value1\":3,\"value2\":12},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":5},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":6},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":7},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":5},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":4},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":5},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":6},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":7},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":8},{\"type\":\"FILLED10\",\"value1\":8,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":6},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":8},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":9},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":7},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":8},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":8},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":7},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":5},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":4},{\"type\":\"INPUT\",\"value1\":-1,\"value2\":3},{\"type\":\"FILLED11\",\"value1\":21,\"value2\":12},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}],[{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1},{\"type\":\"EMPTY\",\"value1\":-1,\"value2\":-1}]]"; 
        
        String[] listOfPreconfiguredGames = new String[] {preconfiguredGame1, preconfiguredGame2, preconfiguredGame3};

        PreparedStatement pstmt = connection.prepareStatement(INSERT_PRECONFIGURED_GAME_DATA_QUERY);
        
        for(int i = 0; i < listOfPreconfiguredGames.length; i++) {
            pstmt.setString(1, listOfPreconfiguredGames[i]);
            pstmt.addBatch();            
        }
        
        pstmt.executeBatch();
    }
}
