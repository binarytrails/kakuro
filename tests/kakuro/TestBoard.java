// @author Vsevolod Ivanov
// @author Nalveer Moocheet
// @brief Test for validating the board

package kakuro;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import kakuro.controllers.GameController;
import kakuro.core.Cell;
import kakuro.models.GameModel;

public class TestBoard{
    private Boolean GUI = false; //disable GUI
    private Boolean solved = false;    				
    //testSize method is used to initialize columns and rows as 10 and use those values to test if this method works or not
    @Test
    public void testSize(){
        // Arrange
        int columns = 10;
        int rows = 10;
        // Act
        GameModel model = new GameModel(columns, rows);
        // Assert
        assertEquals(model.getColumns(), columns);
        assertEquals(model.getRows(), rows);
    }

    //testValidBoard is to verify if the values of the board are what is asked and expected
    @Test
    public void testValidBoard(){
        // Arrange
        GameController controller = new GameController(10,10, GUI);
        GameModel model = new GameModel(10,10); 
        model.initBoard();  //initialize board
        model.board[0][1] = new Cell(Cell.CellType.FILLED10, 5);
        model.board[1][0] = new Cell(Cell.CellType.FILLED01,-1,4);
        model.board[1][1] = new Cell(Cell.CellType.INPUT,3,-1);
        model.board[2][1] = new Cell(Cell.CellType.INPUT,2,-1);
        model.board[1][2] = new Cell(Cell.CellType.INPUT,1,-1);
        controller.model = model;
        // Act
        solved = controller.solveBoard();
        // Assert
        assertEquals(solved, true);
    }
    
    //testNotValidBoardOneSumIsWrong method will verify the sum. If it's not good then it won't be accepted or even validated.
    @Test 
    public void testNotValidBoardOneSumIsWrong(){
         // Arrange
         GameController controller = new GameController(10,10, GUI);
         GameModel model = new GameModel(10,10); 
         model.initBoard();
         model.board[0][1] = new Cell(Cell.CellType.FILLED10, 5);
         model.board[1][0] = new Cell(Cell.CellType.FILLED01,-1,4);
         model.board[1][1] = new Cell(Cell.CellType.INPUT,3,-1);
         model.board[2][1] = new Cell(Cell.CellType.INPUT,2,-1);
         model.board[1][2] = new Cell(Cell.CellType.INPUT,5,-1);
         controller.model = model;
         // Act
         solved = controller.solveBoard();
         // Assert
         assertEquals(solved, false);
    }

  //testNotValidBoardCorrectSumDuplicateEntries method where the Board is not correct but some sum are correctly inputted
    @Test
    public void testNotValidBoardCorrectSumDuplicateEntries(){
        // Arrange
        GameController controller = new GameController(10,10, GUI);
        GameModel model = new GameModel(10,10); 
        model.initBoard();
        model.board[0][1] = new Cell(Cell.CellType.FILLED10, 5);
        model.board[1][0] = new Cell(Cell.CellType.FILLED01,-1,4);
        model.board[1][1] = new Cell(Cell.CellType.INPUT,2,-1);
        model.board[2][1] = new Cell(Cell.CellType.INPUT,3,-1);
        model.board[1][2] = new Cell(Cell.CellType.INPUT,2,-1);
        controller.model = model;
        // Act
        solved = controller.solveBoard();
        // Assert
        assertEquals(solved, false);
    }
}
