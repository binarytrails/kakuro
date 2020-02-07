// @author Isabelle Charette
// @author Audrey-Laure St-Louis

package kakuro;
import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
public class TestInputValid {

    @Test
    public void testValidInput1() {
        GameView gameView = new GameView(new GameController(10,10,false), false/*GUI*/);
        int value = 1;
        assertTrue(value<=gameView.getMaxNumberValid() && 
                value>=gameView.getMinNumberValid());      
    }
    
    @Test
    public void testValidInput9() {
        GameView gameView = new GameView(new GameController(10,10,false), false/*GUI*/);
        int value = 9;
        assertTrue(value<=gameView.getMaxNumberValid() && 
                value>=gameView.getMinNumberValid());      
    }
    
    @Test
    public void testInvalidInput() {
        GameView gameView = new GameView(new GameController(10,10,false), false/*GUI*/);
        int value = 12;
        assertFalse(value<=gameView.getMaxNumberValid() && 
                value>=gameView.getMinNumberValid());      
    }
    
    @Test
    public void testInvalidNegativeInput() {
        GameView gameView = new GameView(new GameController(10,10,false), false/*GUI*/);
        int value = -12;
        assertFalse(value<=gameView.getMaxNumberValid() && 
                value>=gameView.getMinNumberValid());      
    }
    
    @Test
    public void testInvalidInput0() {
        GameView gameView = new GameView(new GameController(10,10,false), false/*GUI*/);
        int value = 0;
        assertFalse(value<=gameView.getMaxNumberValid() && 
                value>=gameView.getMinNumberValid());      
    }
    
    @Test
    public void testStringTypeInput() {
        GameView gameView = new GameView(new GameController(10,10,false), false/*GUI*/);
        String value = "yo";
        assertFalse(gameView.getNumberFormatterClassType().equals(value.getClass())); 
    }
}
