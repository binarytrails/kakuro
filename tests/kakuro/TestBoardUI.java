// @author Isabelle Charette
// @author Audrey-Laure St-Louis
// @brief Test for generating the board UI

package kakuro;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import kakuro.controllers.GameController;

public class TestBoardUI {
    //The testBoardUIGeneration will test if the board will be created base of the rows and columns defined. If this works, then a bigger board can be produced.
    @Test
    public void testBoardUIGeneration(){
        //Arrange
        int rows = 3;
        int columns = 5;
        //Act
        GameController gameController = new GameController(columns,rows, false);
        //Assert
        assertEquals(columns, gameController.model.getColumns());
        assertEquals(rows, gameController.model.getRows());
    }
}
