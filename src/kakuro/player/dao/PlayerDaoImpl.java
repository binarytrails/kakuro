package kakuro.player.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Player implementation class that implements the abstract class for operations in the database
 * TODO: continue in iteration 3
 *
 * @author Brian Gamboc-Javiniar
 * Date written: March 1st, 2020
 */
public class PlayerDaoImpl implements PlayerDao {
    
    private final String SELECT_A_PLAYER_QUERY = "SELECT * FROM player WHERE username=? AND password=? "; // SELECT query for the login mechanism
    private final String VERIFY_IF_PLAYER_EXIST_QUERY = "SELECT * FROM player WHERE username=? "; // SELECT query for the register mechanism
    private final String INSERT_PLAYER_QUERY = "INSERT INTO player(username, password) VALUES(?, ?)"; // INSERT query if user does not exist
    
    /**
     * Logins when player is found in the database
     * 
     * @param conn
     *          - the database connection
     * @param uid
     *          - the username of the player
     * @param pwd
     *          - the password of the player 
     * @return true if player if found and false if player is not found
     */
    @Override
    public boolean login(Connection conn, String uid, String pwd) throws SQLException {
        
        PreparedStatement psmt = conn.prepareStatement(SELECT_A_PLAYER_QUERY);

        psmt.setString(1, uid);
        psmt.setString(2, pwd);

        ResultSet rs = psmt.executeQuery();

        int count = 0;

        while (rs.next()) {
            count++;
        }

        if (count == 1) {
            return true;
        }
        
        return false;
    }

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
    @Override
    public boolean register(Connection conn, String uid, String pwd) throws SQLException {
        
    
        PreparedStatement checkUserPsmt = conn.prepareStatement(VERIFY_IF_PLAYER_EXIST_QUERY);
        
        checkUserPsmt.setString(1, uid);

        ResultSet rs = checkUserPsmt.executeQuery();

        int count = 0;

        while (rs.next()) {
            count++;
        }

        if (count > 0) {
            System.err.println("User already exist");
            return false;
            
        } else {
            PreparedStatement registerPsmt = conn.prepareStatement(INSERT_PLAYER_QUERY);
            
            registerPsmt.setString(1, uid);
            registerPsmt.setString(2, pwd);
            
            registerPsmt.executeUpdate();
            
            System.out.println("Succesfully registered");
            return true;
        }

    }
}
