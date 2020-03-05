package kakuro;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import kakuro.game.dao.GameDao;
import kakuro.game.dao.GameDaoImpl;
import kakuro.gameprogress.dao.GameProgressDaoImpl;
import kakuro.utils.DatabaseConnection;

public class TestGameController {

    @Test
    public void testGameController() {
        
        int rows = 3;
        int columns = 5;
        GameController gameController = new GameController(columns,rows, true);
        assertEquals(columns, gameController.model.columns);
        assertEquals(rows, gameController.model.rows);
        
    }
    
    
    @Test
    public void testGetDatabaseConnection() {
        
        DatabaseConnection db = new DatabaseConnection();
        
        db.getConnection();
        
    }
    @Test
    public void testConnectDatabase() {
        
        DatabaseConnection db = new DatabaseConnection();
        
        db.connect();
        
        Connection conn = db.getConnection(); 
        
        assertEquals(conn!=null, true);
        
        db.disconnect();
    }

    @Test
    public void testDisconnectDatabase() {
        
        DatabaseConnection db = new DatabaseConnection();
        
        db.connect();
        
        db.disconnect();
        
        Connection conn = db.getConnection();
        
        try {
          assertEquals(conn.isClosed(), true);
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
         
    }

    @Test
    public void testSaveGame() {
        DatabaseConnection db = new DatabaseConnection();
        int rows = 10;
        int columns = 10;
        GameModel model = new GameModel(columns, rows);
        
        String uid = "TestPlayer";
        
        GameProgressDaoImpl gp = new GameProgressDaoImpl();
        try {
            db.connect();
            gp.save(db.getConnection(), uid, model.board);
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    /*@Test
    public void testLoadGame() {
        DatabaseConnection db = new DatabaseConnection();
        int rows = 10;
        int columns = 10;
        GameModel model = new GameModel(columns, rows);
        String uid = "TestPlayer";
        GameProgressDaoImpl gp = new GameProgressDaoImpl();
       
        try {            
            db.connect();
            BoardCell[][] boardCell = gp.load(db.getConnection(), uid);
            
            if(boardCell != null) {
                model.board = boardCell;         
                System.out.println("Successfully loaded game progress");
            }
       } catch(SQLException e) {
            System.err.println("Failed to load game");
        }
    }

    @Test
    public void testLoadPreconfiguredGame() {
        int gameLevel=3;
        DatabaseConnection db = new DatabaseConnection();
        int rows = 10;
        int columns = 10;
        GameModel model = new GameModel(columns, rows);
        GameDaoImpl game = new GameDaoImpl();
        
        try {
            
            db.connect();
            ArrayList<BoardCell[][]> boardCells = game.loadAllPreconfiguredGames(db.getConnection());
            model.board = boardCells.get(gameLevel-1);
          
        } catch(SQLException e) {
            System.err.println("Failed to load preconfigred game");
        }
    }*/
    

    @Test
    public void testSolveBoard() {
         Boolean GUI = false;
         Boolean solved = false;

        
            // Arrange
            int columns = 10;
            int rows = 10;
            // Act
            GameModel model = new GameModel(columns, rows);
            // Assert
            assertEquals(model.columns, columns);
            assertEquals(model.rows, rows);
        
            // Arrange
            GameController controller1 = new GameController(10,10, GUI);
            GameModel model1 = new GameModel(10,10); 
            model1.initBoard();  //initialize board
            model1.board[0][1] = new BoardCell(BoardCell.CellType.FILLED10, 5);
            model1.board[1][0] = new BoardCell(BoardCell.CellType.FILLED01,-1,4);
            model1.board[1][1] = new BoardCell(BoardCell.CellType.INPUT,3,-1);
            model1.board[2][1] = new BoardCell(BoardCell.CellType.INPUT,2,-1);
            model1.board[1][2] = new BoardCell(BoardCell.CellType.INPUT,1,-1);
            controller1.model = model1;
            // Act
            solved = controller1.solveBoard();
            // Assert
            assertEquals(solved, true);
     
             // Arrange
             GameController controller2 = new GameController(10,10, GUI);
             GameModel model2 = new GameModel(10,10); 
             model2.initBoard();
             model2.board[0][1] = new BoardCell(BoardCell.CellType.FILLED10, 5);
             model2.board[1][0] = new BoardCell(BoardCell.CellType.FILLED01,-1,4);
             model2.board[1][1] = new BoardCell(BoardCell.CellType.INPUT,3,-1);
             model2.board[2][1] = new BoardCell(BoardCell.CellType.INPUT,2,-1);
             model2.board[1][2] = new BoardCell(BoardCell.CellType.INPUT,5,-1);
             controller2.model = model2;
             // Act
             solved = controller2.solveBoard();
             // Assert
             assertEquals(solved, false);
        
             // Arrange
            GameController controller3 = new GameController(10,10, GUI);
            GameModel model3 = new GameModel(10,10); 
            model3.initBoard();
            model3.board[0][1] = new BoardCell(BoardCell.CellType.FILLED10, 5);
            model3.board[1][0] = new BoardCell(BoardCell.CellType.FILLED11,-1,4);
            model3.board[1][1] = new BoardCell(BoardCell.CellType.INPUT,2,-1);
            model3.board[2][1] = new BoardCell(BoardCell.CellType.INPUT,3,-1);
            model3.board[1][2] = new BoardCell(BoardCell.CellType.INPUT,2,-1);
            controller3.model = model3;
            // Act
            solved = controller3.solveBoard();
            // Assert
            assertEquals(solved, false);
    }

}
