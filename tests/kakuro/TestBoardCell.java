// @author Vsevolod Ivanov
// @brief Test for the BoardCell class

package kakuro;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import kakuro.core.Cell;

public class TestBoardCell
{
    @Test
    public void testNonInputCell(){
        // Arrange
        Cell.CellType cellType = Cell.CellType.EMPTY;
        // Act
        Cell cell = new Cell(cellType);
        // Assert
        assertEquals(cell.getType(), Cell.CellType.EMPTY);
    }

    @Test
    public void testInputCell(){
        // Arrange
        int inputNumber = 1;
        Cell.CellType cellType = Cell.CellType.EMPTY;
        // Act
        Cell cell = new Cell(cellType, inputNumber);
        // Assert
        assertEquals(cell.getFirstValue(), inputNumber);
        assertEquals(cell.getType(), Cell.CellType.EMPTY);
    }

    @Test
    public void testInputAnswerCell(){
        // Arrange
        int inputNumber = 1;
        int answerNumber = 3;
        Cell.CellType cellType = Cell.CellType.EMPTY;
        // Act
        Cell cell = new Cell(cellType, inputNumber, answerNumber);
        // Assert
        assertEquals(cell.getFirstValue(), inputNumber);
        assertEquals(cell.getSecondValue(), answerNumber);
        assertEquals(cell.getType(), Cell.CellType.EMPTY);
    }
}
