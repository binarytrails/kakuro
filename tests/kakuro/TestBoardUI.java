// @author Isabelle Charette
// @author Audrey-Laure St-Louis

package kakuro;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestBoardUI {
    @Test
    public void testBoardUIGeneration()
    {
        int rows = 3;
        int columns = 5;
        GameController gameController = new GameController(columns,rows, false);
        assertEquals(columns, gameController.model.columns);
        assertEquals(rows, gameController.model.rows);
    }
}
