package kakuro.game.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import kakuro.core.Cell;

/**
 * Game interface that defines its abstract API to the database
 *
 * @author Brian Gamboc-Javiniar
 * Date written: March 3rd, 2020
 */
public interface GameDao {
 
    /**
     * Deserialize the JSON object and returns all pre-configured BoardCell object
     * 
     * @param conn
     *          - the database connection
     * @return BoardCell object
     */
    ArrayList<Cell[][]> loadAllPreconfiguredGames(Connection conn) throws SQLException;
}
