// @author Isabelle Charette
// @author Audrey-Laure St-Louis

package kakuro;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import kakuro.controllers.AppController;

public class TestBoardUI {
    @Test
    public void testBoardUIGeneration()
    {
        int rows = 3;
        int columns = 5;
        AppController gameController = new AppController(columns,rows, false);
        assertEquals(columns, gameController.model.columns);
        assertEquals(rows, gameController.model.rows);
    }
}
