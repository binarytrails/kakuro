//@author Brian Gamboc-Javiniar

package kakuro.gameprogress.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.google.gson.*;

import kakuro.BoardCell;

public class GameProgressDaoImpl implements GameProgressDao {
    // TODO: to verify with team -multiple or single saved games - iteration 2
    private final String SAVE_GAME_PROGRESS = "UPDATE game_progress SET cells=? WHERE username=?";
    private final String LOAD_GAME_PROGRESS = "SELECT cells FROM game_progress WHERE username=?";

    @Override
    public void save(Connection conn, String uid, BoardCell[][] board) throws SQLException {
        Gson gson = new Gson();
        
        String boardCellJSON = gson.toJson(board);
        
        PreparedStatement pstmt = conn.prepareStatement(SAVE_GAME_PROGRESS);
        
        pstmt.setString(1, boardCellJSON);
        pstmt.setString(2, uid);    
        pstmt.executeUpdate();
    }

    @Override
    public BoardCell[][] load(Connection conn, String uid) throws SQLException {
        Gson gson = new Gson();
        
        PreparedStatement pstmt = conn.prepareStatement(LOAD_GAME_PROGRESS);
        
        pstmt.setString(1, uid);
        ResultSet rs = pstmt.executeQuery();
       

        if(rs.next()) {
            String cells = rs.getString("cells");
            return gson.fromJson(cells, BoardCell[][].class);
        }
        
        return null;
    }

}
