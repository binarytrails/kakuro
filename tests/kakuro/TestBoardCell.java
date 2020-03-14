// @author Vsevolod Ivanov
// @brief Test for the BoardCell class

package kakuro;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestBoardCell{
	
	/*The TestBoardCell class contain different methods to test if the BoardCell class is implemented in the right way. 
	  The user will enter a value which will be compared and checked if its correct or not. 
	  This test class is needed to ensure the BoardCell class is error proof.
	*/
	
    @Test
    public void testNonInputCell(){
        // Arrange
        BoardCell.CellType cellType = BoardCell.CellType.EMPTY;
        // Act
        BoardCell cell = new BoardCell(cellType);
        // Assert
        assertEquals(cell.getType(), BoardCell.CellType.EMPTY);
    }

    @Test
    public void testInputCell(){
        // Arrange
        int inputNumber = 1;
        BoardCell.CellType cellType = BoardCell.CellType.EMPTY;
        // Act
        BoardCell cell = new BoardCell(cellType, inputNumber);
        // Assert
        assertEquals(cell.getFirstValue(), inputNumber);
        assertEquals(cell.getType(), BoardCell.CellType.EMPTY);
    }
    
    
    @Test
    public void testInputAnswerCell(){
        // Arrange
        int inputNumber = 1;
        int answerNumber = 3;
        BoardCell.CellType cellType = BoardCell.CellType.EMPTY;
        // Act
        BoardCell cell = new BoardCell(cellType, inputNumber, answerNumber);
        // Assert
        assertEquals(cell.getFirstValue(), inputNumber);
        assertEquals(cell.getSecondValue(), answerNumber);
        assertEquals(cell.getType(), BoardCell.CellType.EMPTY);
    }
}
