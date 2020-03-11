// @author Isabelle Charette
// @author Audrey-Laure St-Louis
// @author Vsevolod Ivanov
// @brief Test for the Chrono class

package kakuro;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestChrono
{
    private int faultToleranceMs = 200;
    
    @Test
    public void testRunStopChronoAfter3Seconds() throws InterruptedException
    {
        // Arrange
        int waitSeconds = 3;
        GameController gameController = new GameController(10, 10, false);
        GameView gameView = new GameView(gameController, false/*GUI*/);
        gameView.setButtonMenu(new ButtonMenu(null, 0, 0, null));
        // Act
        gameView.settingUpMenu(); // chrono starts
        Thread.sleep(waitSeconds * 1000/*ms*/ + faultToleranceMs);
        gameView.getButtonMenu().getChrono().chronoPause();
        
        int hours = gameView.getButtonMenu().getChrono().getHours();
        int minutes = gameView.getButtonMenu().getChrono().getMinutes();
        int seconds = gameView.getButtonMenu().getChrono().getSeconds();
        // Assert
        System.out.println("Chrono was run for " + seconds + " seconds");
        assertTrue(waitSeconds == seconds);
    }
}
