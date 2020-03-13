//@author Brian Gamboc-Javiniar

package kakuro.game.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import kakuro.core.Cell;

public class GameDaoImpl implements GameDao {
    private final String LOAD_ALL_PRECONFIGURED_GAMES = "SELECT cells FROM game";
    
    @Override
    public ArrayList<Cell[][]> loadAllPreconfiguredGames(Connection conn) throws SQLException {
        Gson gson = new Gson();
        ArrayList<Cell[][]> boardCells = new ArrayList<Cell[][]>();
        
        PreparedStatement pstmt = conn.prepareStatement(LOAD_ALL_PRECONFIGURED_GAMES);
        
        ResultSet rs = pstmt.executeQuery();
       
        
        while(rs.next()) {
            String cells = rs.getString("cells");
            boardCells.add(gson.fromJson(cells, Cell[][].class));
        }
        
        return boardCells;
    }

}
