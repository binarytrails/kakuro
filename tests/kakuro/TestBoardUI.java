// @author Isabelle Charette
// @author Audrey-Laure St-Louis
// @brief Test for generating the board UI

package kakuro;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestBoardUI {
    @Test
    public void testBoardUIGeneration(){
        //Arrange
        int rows = 3;
        int columns = 5;
        //Act
        GameController gameController = new GameController(columns,rows, false);
        //Assert
        assertEquals(columns, gameController.model.columns);
        assertEquals(rows, gameController.model.rows);
    }
}
