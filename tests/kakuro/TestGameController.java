// @author Brian Gamboc-Javiniar
// @author Nalveer Moocheet
// @author Vsevolod Ivanov
// @author Chang Liu
// @brief Test for the GameController class

package kakuro;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import kakuro.game.dao.GameDao;
import kakuro.game.dao.GameDaoImpl;
import kakuro.gameprogress.dao.GameProgressDaoImpl;
import kakuro.models.GameModel;
import kakuro.controllers.GameController;
import kakuro.core.BoardCell;
import kakuro.core.DatabaseConnection;

public class TestGameController {
    Boolean GUI = false;
    Boolean solved = false; 
    
    @Test
    public void testGameController() {
        //Arrange
        int rows = 3;
        int columns = 5;
        //Act
        GameController gameController = new GameController(columns,rows, false); 
        //Set GUI to false
        //Assert
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
        //Arrange
        DatabaseConnection db = new DatabaseConnection();
        db.connect();
        //Act
        Connection conn = db.getConnection(); 
        //Assert
        assertEquals(conn!=null, true);
        db.disconnect();
    }

    @Test
    public void testDisconnectDatabase() {
        //Arrange
        DatabaseConnection db = new DatabaseConnection();
        db.connect();       
        db.disconnect();
        //Act
        Connection conn = db.getConnection();
        //Assert
        try {
            assertEquals(conn.isClosed(), true);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      
    }

    @Test
 // @brief Test Valid Board
    public void testBoardSolveValidBoard() {
        //Arrange
        int columns = 10;
        int rows = 10;
        //Act
        GameModel model = new GameModel(columns, rows);
        //Assert
        assertEquals(model.columns, columns);
        assertEquals(model.rows, rows);        
        //Arrange
        GameController controller1 = new GameController(10,10, GUI);
        GameModel model1 = new GameModel(10,10); 
        model1.initBoard();  //initialize board
        model1.board[0][1] = new BoardCell(BoardCell.CellType.FILLED10, 5);
        model1.board[1][0] = new BoardCell(BoardCell.CellType.FILLED01,-1,4);
        model1.board[1][1] = new BoardCell(BoardCell.CellType.INPUT,3,-1);
        model1.board[2][1] = new BoardCell(BoardCell.CellType.INPUT,2,-1);
        model1.board[1][2] = new BoardCell(BoardCell.CellType.INPUT,1,-1);
        controller1.model = model1;
        //Act
        solved = controller1.solveBoard();
        //Assert
        assertEquals(solved, true);
    }
    
    @Test
 // @brief Test Invalid Board with one wrong vertical sum
    public void testBoardSolveInvalidBoardWithOneWrongVerticalSum() {       
        //Arrange
        int columns = 10;
        int rows = 10;
        //Act
        GameModel model = new GameModel(columns, rows);
        //Assert
        assertEquals(model.columns, columns);
        assertEquals(model.rows, rows);
        GameController controller2 = new GameController(10,10, GUI);
        GameModel model2 = new GameModel(10,10); 
        model2.initBoard();
        model2.board[0][1] = new BoardCell(BoardCell.CellType.FILLED10, 5);
        model2.board[1][0] = new BoardCell(BoardCell.CellType.FILLED01,-1,4);
        model2.board[1][1] = new BoardCell(BoardCell.CellType.INPUT,3,-1);
        model2.board[2][1] = new BoardCell(BoardCell.CellType.INPUT,6,-1);
        model2.board[1][2] = new BoardCell(BoardCell.CellType.INPUT,1,-1);
        controller2.model = model2;
        //Act
        solved = controller2.solveBoard();
        //Assert
        assertEquals(solved, false);
    }
    
    @Test
 // @brief Test Invalid Board with one wrong horizontal sum
    public void testBoardSolveInvalidBoardWithOneWrongHorizontalSum() {       
        //Arrange
        int columns = 10;
        int rows = 10;
        //Act
        GameModel model = new GameModel(columns, rows);
        //Assert
        assertEquals(model.columns, columns);
        assertEquals(model.rows, rows);
        GameController controller2 = new GameController(10,10, GUI);
        GameModel model2 = new GameModel(10,10); 
        model2.initBoard();
        model2.board[0][1] = new BoardCell(BoardCell.CellType.FILLED10, 5);
        model2.board[1][0] = new BoardCell(BoardCell.CellType.FILLED01,-1,8);
        model2.board[1][1] = new BoardCell(BoardCell.CellType.INPUT,3,-1);
        model2.board[2][1] = new BoardCell(BoardCell.CellType.INPUT,2,-1);
        model2.board[1][2] = new BoardCell(BoardCell.CellType.INPUT,1,-1);
        controller2.model = model2;
        //Act
        solved = controller2.solveBoard();
        //Assert
        assertEquals(solved, false);
    }
    
    @Test
 // @brief Test Invalid Board with correct sum but duplicate entries
    public void testBoardSolveInvalidBoardWithDuplicateEntries() {        
        //Arrange
        int columns = 10;
        int rows = 10;
        //Act
        GameModel model = new GameModel(columns, rows);
        //Assert
        assertEquals(model.columns, columns);
        assertEquals(model.rows, rows);   
        //Arrange
        GameController controller3 = new GameController(10,10, GUI);
        GameModel model3 = new GameModel(10,10); 
        model3.initBoard();
        model3.board[0][1] = new BoardCell(BoardCell.CellType.FILLED10, 5);
        model3.board[1][0] = new BoardCell(BoardCell.CellType.FILLED01,-1,4);
        model3.board[1][1] = new BoardCell(BoardCell.CellType.INPUT,2,-1);
        model3.board[2][1] = new BoardCell(BoardCell.CellType.INPUT,3,-1);
        model3.board[1][2] = new BoardCell(BoardCell.CellType.INPUT,2,-1);
        controller3.model = model3;            
        //Act
        solved = controller3.solveBoard();
        //Assert
        assertEquals(solved, false);
    }
}

