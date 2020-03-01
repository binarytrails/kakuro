//@author Brian Gamboc-Javiniar

package kakuro.gameprogress.dao;

import java.sql.Connection;
import java.sql.SQLException;
import kakuro.BoardCell;

public interface GameProgressDao {
    /**
     * TODO: confirm the datatype
     * 
     * Serialize the BoardCell object to JSON and stores a JSON object to the gameprogress table in the database
     * @param conn
     *          - the database connection
     * @param uid
     *          - the username of the player
     */
    void save(Connection conn, String uid, BoardCell[][] board) throws SQLException;
    
    /**
     * TODO: confirm the datatype
     * 
     * Deserialize the JSON object and returns a BoardCell object
     * @param conn
     *          - the database connection
     * @param uid
     *          - the username of the player
     * @return BoardCell object
     */
    BoardCell[][] load(Connection conn, String uid) throws SQLException;
}
