package kakuro;
//@author Audrey St-Louis
//@brief Timer class 

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;

public class Chrono
{
    private  int hours=0;
    private int minutes=0;
    private int seconds=0;
    private int delay=1000;
    JLabel timerLabel;
    ActionListener timerListener;
    Timer time;

    /* Constructor : Creates all the UI components and add them on the panel. */
    public Chrono()
    {
        timerLabel = new JLabel("0:"+hours+":"+minutes+":"+seconds); 
    }

    public JLabel getTimerLabel() {

        return timerLabel;
    }
    public void validation() {

    }

    /* Checks if the user have clicked on a button. If he clicks on pause, the timer will stop. If he clicks on start, the timer will start. If 
     * he clicks on new game, the timer will restart and a new grid will appear on the screen. 
     * The timer is set to start automatically when the application is launched since a game will already be ready to play.
     * Need to implement : Save button
     */

    public void checkTime() {
        seconds++;
        if(seconds==60)
        {
            seconds=0;
            minutes++;
        }
        if(minutes==60)
        {
            minutes=0;
            hours++;
        }
        timerLabel.setText("0:"+hours+":"+minutes+":"+seconds);
    }


    public int getDelay() {
        return this.delay;
    }

    public void timerSetUp() {
        timerListener= new ActionListener()
        {
            public void actionPerformed(ActionEvent e1)
            {   
               checkTime();
            }
        };

        time = new Timer(delay,timerListener);
        time.start(); 
    }

    public void chronoPause() {
        time.stop();
    }
    

    public void chronoStart() {
        time.start();
    }

    public void resetTimer() {
       hours=0;
       minutes=0;
       seconds=0;
       timerLabel.setText("0:"+hours+":"+minutes+":"+seconds);
    }
}

