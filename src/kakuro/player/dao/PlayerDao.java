//@author Brian Gamboc-Javiniar

package kakuro.player.dao;

import java.sql.SQLException;
import java.sql.Connection;


public interface PlayerDao {

    /**
     * Logins when player is found in the database
     * @param conn
     *          - the database connection
     * @param uid
     *          - the username of the player
     * @param pwd
     *          - the password of the player 
     * @return true if player if found and false if player is not found
     */
    boolean login(Connection conn, String uid, String pwd) throws SQLException;
    
    /**
     * Registers the player and inserts them to the database
     * @param conn
     *          - the database connection
     * @param uid
     *          - the username of the player
     * @param pwd
     *          - the password of the player 
     * @return true if successfully registering and false if failing to register
     */
    boolean register(Connection conn, String uid, String pwd) throws SQLException;
}
