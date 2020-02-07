package kakuro;
import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
public class TestInputValid {

    @Test
    public void testIfInputValid() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter input:");
        GameView gameView = new GameView(new GameController(10,10,false));
        int value = scanner.nextInt();
        assertTrue(value<=gameView.getMaxNumberValid() && 
                value>=gameView.getMinNumberValid());      
    }
}
