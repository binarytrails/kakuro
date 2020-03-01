//@author Brian Gamboc-Javiniar and Nolan Mckay

package kakuro.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private Connection connection = null;
    private final static String DATABASE_FILE_PATH = "resources/SQLiteKakuro.db";
    private final String CREATE_GAME_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS game"
            + "(gameID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "username VARCHAR(60)," + "time INTEGER," + "cells BLOB);";
    
    private final String CREATE_GAME_PROGRESS_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS game_progress"
            + "(gameProgressID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "time INTEGER," + "cells BLOB," + "gameID INTEGER,"
            + "username VARCHAR(60),"
            + "FOREIGN KEY(gameID) REFERENCES game (gameID),"
            + "FOREIGN KEY(username) REFERENCES player (username)" + ");";
    
    private final String CREATE_PLAYER_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS player"
            + "(username VARCHAR(60) PRIMARY KEY," + "password VARCHAR(60)"
            + ");";

    public Connection connect() {

        try {

            connection = DriverManager
                    .getConnection("jdbc:sqlite:" + DATABASE_FILE_PATH);
            System.out.println("Success! Connected to SQLite database");

            createGameTable();
            createGameProgressTable();
            createPlayerTable();

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
}
