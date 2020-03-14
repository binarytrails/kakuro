// @author Brian Gamboc-Javiniar
// @author Nalveer Moocheet
// @author Vsevolod Ivanov
// @author Chang Liu
// @brief Test for the GameController class

package kakuro;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import kakuro.models.GameModel;
import kakuro.controllers.GameController;
import kakuro.core.Cell;
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
        assertEquals(columns, gameController.model.getColumns());
        assertEquals(rows, gameController.model.getRows());
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
        GameController controller = new GameController(10,10, GUI);
        GameModel model = controller.model;
        model.initBoard();  //initialize board
        model.board[0][1] = new Cell(Cell.CellType.FILLED10, 5);
        model.board[1][0] = new Cell(Cell.CellType.FILLED01,-1,4);
        model.board[1][1] = new Cell(Cell.CellType.INPUT,3,-1);
        model.board[2][1] = new Cell(Cell.CellType.INPUT,2,-1);
        model.board[1][2] = new Cell(Cell.CellType.INPUT,1,-1);
        //Act
        solved = controller.solveBoard();
        //Assert
        assertEquals(solved, true);
    }

    @Test
 // @brief Test Invalid Board with one wrong vertical sum
    public void testBoardSolveInvalidBoardWithOneWrongVerticalSum() {
        //Arrange
        GameController controller = new GameController(10,10, GUI);
        GameModel model = controller.model;
        model.initBoard();  //initialize board
        model.board[0][1] = new Cell(Cell.CellType.FILLED10, 5);
        model.board[1][0] = new Cell(Cell.CellType.FILLED01,-1,4);
        model.board[1][1] = new Cell(Cell.CellType.INPUT,3,-1);
        model.board[2][1] = new Cell(Cell.CellType.INPUT,6,-1);
        model.board[1][2] = new Cell(Cell.CellType.INPUT,1,-1);
        //Act
        solved = controller.solveBoard();
        //Assert
        assertEquals(solved, false);
    }

    @Test
 // @brief Test Invalid Board with one wrong horizontal sum
    public void testBoardSolveInvalidBoardWithOneWrongHorizontalSum() {
        //Arrange
        GameController controller = new GameController(10,10, GUI);
        GameModel model = controller.model;
        model.initBoard();  //initialize board
        model.board[0][1] = new Cell(Cell.CellType.FILLED10, 5);
        model.board[1][0] = new Cell(Cell.CellType.FILLED01,-1,8);
        model.board[1][1] = new Cell(Cell.CellType.INPUT,3,-1);
        model.board[2][1] = new Cell(Cell.CellType.INPUT,2,-1);
        model.board[1][2] = new Cell(Cell.CellType.INPUT,1,-1);
        //Act
        solved = controller.solveBoard();
        //Assert
        assertEquals(solved, false);
    }

    @Test
 // @brief Test Invalid Board with correct sum but duplicate entries
    public void testBoardSolveInvalidBoardWithDuplicateEntries() {
        //Arrange
        GameController controller = new GameController(10,10, GUI);
        GameModel model = controller.model;
        model.initBoard();  //initialize board
        model.board[0][1] = new Cell(Cell.CellType.FILLED10, 5);
        model.board[1][0] = new Cell(Cell.CellType.FILLED01,-1,4);
        model.board[1][1] = new Cell(Cell.CellType.INPUT,2,-1);
        model.board[2][1] = new Cell(Cell.CellType.INPUT,3,-1);
        model.board[1][2] = new Cell(Cell.CellType.INPUT,2,-1);
        //Act
        solved = controller.solveBoard();
        //Assert
        assertEquals(solved, false);
    }
}

