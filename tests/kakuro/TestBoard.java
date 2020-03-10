// @author Vsevolod Ivanov
// @author Nalveer Moocheet

/**
   	 *@author Vsevolod Ivanov
   	 *@author Nalveer Moocheet
 */
package kakuro;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestBoard
{
    private Boolean GUI = false;
    private Boolean solved = false;

    /**
	 *testSize method is used to initialize columns and rows as 10 and check if this method  in class Board works
	 */
    @Test
    public void testSize()
    {
        // Arrange
        int columns = 10;
        int rows = 10;
        // Act
        GameModel model = new GameModel(columns, rows);
        // Assert
        assertEquals(model.columns, columns);
        assertEquals(model.rows, rows);
    }
    

    /**
   	 *testValidBoard method is to check if the board structure is good and what have been asked
   	 */
    @Test
    public void testValidBoard()
    {
        // Arrange
        GameController controller = new GameController(10,10, GUI);
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
    
    /**
   	 *testNotValidBoardOneSumIsWrong is a method 
   	 */
    @Test 
    public void testNotValidBoardOneSumIsWrong()
    {
         // Arrange
         GameController controller = new GameController(10,10, GUI);
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
    public void testNotValidBoardCorrectSumDuplicateEntries()
    {
        // Arrange
        GameController controller = new GameController(10,10, GUI);
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
