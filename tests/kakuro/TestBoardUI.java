package kakuro;

import static org.junit.Assert.*;

import java.util.Enumeration;

import javax.swing.tree.TreeNode;

import org.junit.BeforeClass;
import org.junit.Test;


public class TestBoardUI {
    @Test
    public void testBoardUIGeneration()
    {
        int rows = 3;
        int columns = 5;
        // Arrange
        GameController gameController = new GameController(columns,rows);

        // Act

        // Assert
        //        assertEquals(columns, gameController.model.board.length);
        //        assertEquals(rows, gameController.model.board[0].length);

        assertEquals(columns, gameController.model.columns);
        assertEquals(rows, gameController.model.rows);
    }
}
