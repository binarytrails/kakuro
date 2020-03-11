// @author Isabelle Charette
// @author Audrey-Laure St-Louis
// @author Vsevolod Ivanov
// @brief Test for the input of GameView

package kakuro;
import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestGameViewInput
{

    private static GameController gameController;
    private static GameView gameView;

    @BeforeClass
    public static void onlyOnce()
    {
        gameController = new GameController(10, 10, false);
        gameView = new GameView(gameController, false/*GUI*/);
    }

    private boolean hasValidRange(final int value)
    {
        return (value <= gameView.getMaxNumberValid() && value >= gameView.getMinNumberValid());
    }

    @Test
    public void testValidInputNumber1()
    {
        // Arrange
        int value = 1;
        Boolean isValid = false;
        // Act
        isValid = hasValidRange(value);
        // Assert
        assertTrue(isValid);
    }
    
    @Test
    public void testValidInputNumber9()
    {
        // Arrange
        int value = 9;
        Boolean isValid = false;
        // Act
        isValid = hasValidRange(value);
        // Assert
        assertTrue(isValid);
    }
    
    @Test
    public void testInvalidInputNumber12()
    {
        // Arrange
        int value = 12;
        Boolean isValid = false;
        // Act
        isValid = hasValidRange(value);
        // Assert
        assertFalse(isValid);
    }
    
    @Test
    public void testInvalidNegativeInputMinus12()
    {
        // Arrange
        int value = -12;
        Boolean isValid = false;
        // Act
        isValid = hasValidRange(value);
        // Assert
        assertFalse(isValid);
    }
    
    @Test
    public void testInvalidInputNumber0()
    {
        // Arrange
        int value = 0;
        Boolean isValid = false;
        // Act
        isValid = hasValidRange(value);
        // Assert
        assertFalse(isValid);
    }
    
    @Test
    public void testInvalidInputString()
    {
        // Arrange
        String value = "yo";
        Boolean isValid = false;
        // Act
        isValid = gameView.getNumberFormatterClassType().equals(value.getClass());
        // Assert
        assertFalse(isValid);
    }
}
