package kakuro.gameprogress.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.google.gson.*;

import kakuro.core.Cell;

/**
 * Game Progress implementation class that implements the abstract class for operations in the database
 *
 * @author Brian Gamboc-Javiniar
 * Date written: March 1st, 2020
 */
public class GameProgressDaoImpl implements GameProgressDao {
    private final String SAVE_GAME_PROGRESS = "UPDATE game_progress SET cells=? WHERE username=?"; // Update query on user's game progress
    private final String LOAD_GAME_PROGRESS = "SELECT cells FROM game_progress WHERE username=?"; // Select query for a user's game progress

    @Override
    public void save(Connection conn, String uid, Cell[][] board) throws SQLException {
        Gson gson = new Gson();
        
        String boardCellJSON = gson.toJson(board);
        PreparedStatement pstmt = conn.prepareStatement(SAVE_GAME_PROGRESS);
        
        pstmt.setString(1, boardCellJSON);
        pstmt.setString(2, uid);    
        pstmt.executeUpdate();
    }

    @Override
    public Cell[][] load(Connection conn, String uid) throws SQLException {
        Gson gson = new Gson();
        
        PreparedStatement pstmt = conn.prepareStatement(LOAD_GAME_PROGRESS);
        
        pstmt.setString(1, uid);
        ResultSet rs = pstmt.executeQuery();
       

        if(rs.next()) {
            String cells = rs.getString("cells");
            return gson.fromJson(cells, Cell[][].class);
        }
        
        return null;
    }

}
