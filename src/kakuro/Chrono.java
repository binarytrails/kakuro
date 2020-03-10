package kakuro;
//@author Audrey St-Louis
//@brief Timer class 

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.*;

public class Chrono
{
    private  int hours=0;
    private int minutes=0;
    private int seconds=0;
    private int delay=1000;
    private boolean isVisible=false;
    JLabel timerLabel;
    ActionListener timerListener;
    Timer time;

    /* Constructor : Creates all the UI components and add them on the panel. */
    public Chrono()
    {
        timerLabel = new JLabel("0:"+hours+":"+minutes+":"+seconds, SwingConstants.CENTER); 
        timerLabel.setFont(new Font(timerLabel.getFont().getFontName(), Font.PLAIN, 20));
        timerLabel.setVisible(false);
        timerLabel.setBorder(new EmptyBorder(10,0,10,0));//top,left,bottom,right
    }

    public JLabel getTimerLabel() {

        return timerLabel;
    }
    public void validation() {

    }

    /* Checks if the user have clicked on a button. If he clicks on pause, the timer will stop. If he clicks on start, the timer will start. If 
     * he clicks on new game, the timer will restart and a new grid will appear on the screen. 
     * The timer is set to start automatically when the application is launched since a game will already be ready to play.
     * Need to implement : Save feature
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

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    
    public void toggleTimerDisplay() {
        timerLabel.setVisible(!isVisible);
    }
}

