// @author Isabelle Charette
// @author Audrey-Laure St-Louis

package kakuro;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class testChrono {

    @Test
    public void testStop() throws InterruptedException{
        Chrono chrono = new Chrono();
        chrono.timerSetUp();
        chrono.chronoStart();
        Thread.sleep(3000);
        chrono.chronoPause();
        int seconds = chrono.getSeconds();
        assertEquals(3, (seconds+1)); // 1 extra second because of the delay in the constructor of the Timer object
    }

}
