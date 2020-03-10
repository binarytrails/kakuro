//@author Brian Gamboc-Javiniar
/**
   	 *@author  Brian Gamboc-Javiniar
   	 
 */
package kakuro.game.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import kakuro.BoardCell;

public interface GameDao {
 
    /**
     * 
     * Deserialize the JSON object and returns all pre-configured BoardCell object
     * @param conn
     *          - the database connection
     * @return BoardCell object
     */
    ArrayList<BoardCell[][]> loadAllPreconfiguredGames(Connection conn) throws SQLException;
}
