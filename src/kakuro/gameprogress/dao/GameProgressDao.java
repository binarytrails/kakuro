package kakuro.gameprogress.dao;

import java.sql.Connection;
import java.sql.SQLException;

import kakuro.core.Cell;

/**
 * Game Progress interface that defines its abstract API to the database
 *
 * @author Brian Gamboc-Javiniar
 * Date written: March 1st, 2020
 */
public interface GameProgressDao {
    /**
     * 
     * Serialize the BoardCell object to JSON and stores a JSON object to the gameprogress table in the database
     * @param conn
     *          - the database connection
     * @param uid
     *          - the username of the player
     */
    void save(Connection conn, String uid, Cell[][] board) throws SQLException;
    
    /**
     * 
     * Deserialize the JSON object and returns a BoardCell object
     * @param conn
     *          - the database connection
     * @param uid
     *          - the username of the player
     * @return BoardCell object
     */
    Cell[][] load(Connection conn, String uid) throws SQLException;
}
