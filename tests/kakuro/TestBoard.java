// @author Vsevolod Ivanov
// @author ...

package kakuro;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestBoard
{
    
	
	public GameController c1 = new GameController(10,10);
	public GameController c2 = new GameController(10,10);
	public GameController c3 = new GameController(10,10);
	
	@Before
	public void initializeBoards() {
		
	  	GameModel m1 = new GameModel(10,10);  //valid board
	  	
		m1.initBoard();
		m1.board[0][1] = new BoardCell(BoardCell.CellType.FILLED10, 5);
		m1.board[1][0] = new BoardCell(BoardCell.CellType.FILLED01,-1,4);
		m1.board[1][1] = new BoardCell(BoardCell.CellType.INPUT,3,-1);
		m1.board[2][1] = new BoardCell(BoardCell.CellType.INPUT,2,-1);
		m1.board[1][2] = new BoardCell(BoardCell.CellType.INPUT,1,-1);
	    c1.model = m1;
	    
	    GameModel m2 = new GameModel(10,10);  //Invalid board, one of the sums is wrong
		m2.initBoard();
		m2.board[0][1] = new BoardCell(BoardCell.CellType.FILLED10, 5);
		m2.board[1][0] = new BoardCell(BoardCell.CellType.FILLED01,-1,4);
		m2.board[1][1] = new BoardCell(BoardCell.CellType.INPUT,3,-1);
		m2.board[2][1] = new BoardCell(BoardCell.CellType.INPUT,2,-1);
		m2.board[1][2] = new BoardCell(BoardCell.CellType.INPUT,5,-1);
	    c2.model = m2;
	    
	    GameModel m3 = new GameModel(10,10);  //Invalid board, correct sum but duplicate entries
	  	m3.initBoard();
	  	m3.board[0][1] = new BoardCell(BoardCell.CellType.FILLED10, 5);
	  	m3.board[1][0] = new BoardCell(BoardCell.CellType.FILLED01,-1,4);
	  	m3.board[1][1] = new BoardCell(BoardCell.CellType.INPUT,2,-1);
	  	m3.board[2][1] = new BoardCell(BoardCell.CellType.INPUT,3,-1);
	  	m3.board[1][2] = new BoardCell(BoardCell.CellType.INPUT,2,-1);
	  	c3.model = m3;
		
	}
	
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

	
	@Test
	public void testBoardsSolution(){
		
		assertEquals(c1.solveBoard(),true);
	
		assertEquals(c2.solveBoard(),false);
		
		assertEquals(c3.solveBoard(),false);
	}
	
	

}
