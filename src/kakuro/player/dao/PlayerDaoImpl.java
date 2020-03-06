//@author Brian Gamboc-Javiniar

package kakuro.player.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDaoImpl implements PlayerDao {
    
    private final String SELECT_A_PLAYER_QUERY = "SELECT * FROM player WHERE username=? AND password=? ";
    private final String VERIFY_IF_PLAYER_EXIST_QUERY = "SELECT * FROM player WHERE username=? ";
    private final String INSERT_PLAYER_QUERY = "INSERT INTO player(username, password) VALUES(?, ?)";

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
