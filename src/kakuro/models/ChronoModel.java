package kakuro.models;
//@author Audrey St-Louis
//@brief Timer class 

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.*;

public class ChronoModel
{
    private int hours;
    private int minutes;
    private int seconds;
    private int delay;
    

    /* Constructor : Creates all the UI components and add them on the panel. */
    public ChronoModel()
    {
        hours=0;
        minutes=0;
        seconds=0;
        delay=1000;
    }
   
    /* Checks if the user have clicked on a button. If he clicks on pause, the timer will stop. If he clicks on start, the timer will start. If 
     * he clicks on new game, the timer will restart and a new grid will appear on the screen. 
     * The timer is set to start automatically when the application is launched since a game will already be ready to play.
     * Need to implement : Save feature
     */
    public String updateTime() {
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
        
        return "0:"+hours+":"+minutes+":"+seconds;
    }

    //Getters and setters
    public int getDelay() {
        return this.delay;
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

    public void resetTimer() {
        hours=0;
        minutes=0;
        seconds=0;
     }  
}

