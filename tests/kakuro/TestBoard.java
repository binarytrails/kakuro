// @author Vsevolod Ivanov
// @author Nalveer Moocheet
// @brief Test for validating the board

package kakuro;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import kakuro.controllers.AppController;
import kakuro.core.BoardCell;
import kakuro.models.GameModel;

public class TestBoard{
    private Boolean GUI = false; //disable GUI
    private Boolean solved = false;
  
    @Test
    public void testSize(){
        // Arrange
        int columns = 10;
        int rows = 10;
        // Act
        GameModel model = new GameModel(columns, rows);
        // Assert
        assertEquals(model.columns, columns);
        assertEquals(model.rows, rows);
    }

    @Test
    public void testValidBoard(){
        // Arrange
        AppController controller = new AppController(10,10, GUI);
        GameModel model = new GameModel(10,10); 
        model.initBoard();  //initialize board
        model.board[0][1] = new BoardCell(BoardCell.CellType.FILLED10, 5);
        model.board[1][0] = new BoardCell(BoardCell.CellType.FILLED01,-1,4);
        model.board[1][1] = new BoardCell(BoardCell.CellType.INPUT,3,-1);
        model.board[2][1] = new BoardCell(BoardCell.CellType.INPUT,2,-1);
        model.board[1][2] = new BoardCell(BoardCell.CellType.INPUT,1,-1);
        controller.model = model;
        // Act
        solved = controller.solveBoard();
        // Assert
        assertEquals(solved, true);
    }
    
    @Test 
    public void testNotValidBoardOneSumIsWrong(){
         // Arrange
         AppController controller = new AppController(10,10, GUI);
         GameModel model = new GameModel(10,10); 
         model.initBoard();
         model.board[0][1] = new BoardCell(BoardCell.CellType.FILLED10, 5);
         model.board[1][0] = new BoardCell(BoardCell.CellType.FILLED01,-1,4);
         model.board[1][1] = new BoardCell(BoardCell.CellType.INPUT,3,-1);
         model.board[2][1] = new BoardCell(BoardCell.CellType.INPUT,2,-1);
         model.board[1][2] = new BoardCell(BoardCell.CellType.INPUT,5,-1);
         controller.model = model;
         // Act
         solved = controller.solveBoard();
         // Assert
         assertEquals(solved, false);
    }

    @Test
    public void testNotValidBoardCorrectSumDuplicateEntries(){
        // Arrange
        AppController controller = new AppController(10,10, GUI);
        GameModel model = new GameModel(10,10); 
        model.initBoard();
        model.board[0][1] = new BoardCell(BoardCell.CellType.FILLED10, 5);
        model.board[1][0] = new BoardCell(BoardCell.CellType.FILLED01,-1,4);
        model.board[1][1] = new BoardCell(BoardCell.CellType.INPUT,2,-1);
        model.board[2][1] = new BoardCell(BoardCell.CellType.INPUT,3,-1);
        model.board[1][2] = new BoardCell(BoardCell.CellType.INPUT,2,-1);
        controller.model = model;
        // Act
        solved = controller.solveBoard();
        // Assert
        assertEquals(solved, false);
    }
}
