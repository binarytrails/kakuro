// @author Isabelle Charette
// @author Audrey-Laure St-Louis
// @author Vsevolod Ivanov
// @brief Test for the Chrono class

package kakuro;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import kakuro.controllers.GameController;
import kakuro.controllers.MenuBarController;
import kakuro.controllers.ChronoController;
import kakuro.views.MenuBarView;
import kakuro.views.GameView;


public class TestChrono{
    private int faultToleranceMs = 200;
    
    @Test
    public void testRunStopChronoAfter3Seconds() throws InterruptedException{
        // Arrange
        int waitSeconds = 3;
        ChronoController chronoController = new ChronoController();
       
        // Act
        chronoController.chronoStart(); 
        Thread.sleep(waitSeconds * 1000/*ms*/ + faultToleranceMs);
        chronoController.chronoPause();
        
        int hours = chronoController.getHours();
        int minutes = chronoController.getMinutes();
        int seconds = chronoController.getSeconds();
        // Assert
        System.out.println("Chrono was run for " + seconds + " seconds");
        assertTrue(waitSeconds == seconds);
    }
}
