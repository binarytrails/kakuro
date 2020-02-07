package kakuro;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestPauseButton {
    
    @Test
    public void testStop() throws InterruptedException{
        GameView gameView = new GameView(new GameController(10,10,false));
        
        gameView.settingUpMenu(); // chrono starts
        Thread.sleep(3000);
        gameView.getButtonMenu().getChrono().chronoPause();
        
        int hours = gameView.getButtonMenu().getChrono().getHours();
        int minutes = gameView.getButtonMenu().getChrono().getMinutes();
        int seconds = gameView.getButtonMenu().getChrono().getSeconds();
        assertEquals(5, (seconds+1)); // 1 extra second because of the delay in the constructor of the chrono object
    }

}
