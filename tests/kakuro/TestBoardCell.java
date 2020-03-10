
/**
   	 *@author Vsevolod Ivanov
 */

package kakuro;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestBoardCell
{

	/**
	 *testInputCell is to check the BoardCell class and to check if those spaces where nothing are outputted are not causing an issue
	 */
	@Test
	public void testNonInputCell()
	{
		// Arrange
		BoardCell.CellType cellType = BoardCell.CellType.EMPTY;
		// Act
		BoardCell cell = new BoardCell(cellType);
		// Assert
		assertEquals(cell.getType(), BoardCell.CellType.EMPTY);
	}

	/**
	 *testInputCell is to check if what is entered is a valid number and if its not empty
	 */
	@Test
	public void testInputCell()
	{
		// Arrange
		int inputNumber = 1;
		BoardCell.CellType cellType = BoardCell.CellType.EMPTY;
		// Act
		BoardCell cell = new BoardCell(cellType, inputNumber);
		// Assert
		assertEquals(cell.getFirstValue(), inputNumber);
		assertEquals(cell.getType(), BoardCell.CellType.EMPTY);
	}

	/**
	 *testInputAnswerCell is to check if what is entered is the number searching for to solve the board
	 */
	@Test
	public void testInputAnswerCell()
	{
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
