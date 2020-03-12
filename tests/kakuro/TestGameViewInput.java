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

public class TestGameViewInput{
    
    private static GameController gameController;
    private static GameView gameView;

    @BeforeClass
    public static void onlyOnce(){
        gameController = new GameController(10, 10, false);
        gameView = new GameView(gameController, false/*GUI*/);
    }

    // hasValidRange method is to check if a number is in the range set from the start
    private boolean hasValidRange(final int value){
        return (value <= gameView.getMaxNumberValid() && value >= gameView.getMinNumberValid());
    }

    // testValidInputNumber1 will test the method with the value 1.
    @Test
    public void testValidInputNumber1(){
        // Arrange
        int value = 1;
        Boolean isValid = false;
        // Act
        isValid = hasValidRange(value);
        // Assert
        assertTrue(isValid);
    }
    
    //testValidInputNumber9 method where 9 will be used to test the method. 9 is a valid value to be used in the game
    @Test
    public void testValidInputNumber9(){
        // Arrange
        int value = 9;
        Boolean isValid = false;
        // Act
        isValid = hasValidRange(value);
        // Assert
        assertTrue(isValid);
    }
    
    //testInvalidInputNumber12 method will check this method by having 12 as a tetsing case and where it wont be validated
    @Test
    public void testInvalidInputNumber12(){
        // Arrange
        int value = 12;
        Boolean isValid = false;
        // Act
        isValid = hasValidRange(value);
        // Assert
        assertFalse(isValid);
    }
    
    //testInvalidNegativeInputMinus12 will verify with a dummy value 12 to check if this method works or not
    @Test
    public void testInvalidNegativeInputMinus12(){
        // Arrange
        int value = -12;
        Boolean isValid = false;
        // Act
        isValid = hasValidRange(value);
        // Assert
        assertFalse(isValid);
    }
    
    //testInvalidInputNumber0 method produce an error if the number 0 is entered.
    @Test
    public void testInvalidInputNumber0(){
        // Arrange
        int value = 0;
        Boolean isValid = false;
        // Act
        isValid = hasValidRange(value);
        // Assert
        assertFalse(isValid);
    }
    
    //testInvalidInputString method will not accept a string in one of the cell and the player will have to change before playing further
    @Test
    public void testInvalidInputString(){
        // Arrange
        String value = "yo";
        Boolean isValid = false;
        // Act
        isValid = gameView.getNumberFormatterClassType().equals(value.getClass());
        // Assert
        assertFalse(isValid);
    }
}
