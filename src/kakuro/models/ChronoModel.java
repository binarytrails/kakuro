package kakuro.models;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.*;

/**
 * Timer model class called Chrono, which acts as our representation data for the object
 * 
 * @author Audrey St-Louis
 * @author Hoang Thuan Pham
 * Date written: January 29th, 2020
 */
public class ChronoModel
{
    private int hours; // the hours in time
    private int minutes; // the minutes in time
    private int seconds; // the seconds in time
    private int delay; // the delay of the timer
    
    /**
     * ChronoModel constructor
     */
    public ChronoModel()
    {
        hours=0;
        minutes=0;
        seconds=0;
        delay=1000;
    }
   
    /** 
     * updateTime method that checks if the user have clicked on a button. If he clicks on pause, the timer will stop. If he clicks on start, the timer will start. If 
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

    /**
     * Accesses the delay of the timer
     * 
     * @return An integer
     */
    public int getDelay() {
        return this.delay;
    }
    
    /**
     * Accesses the time and returns the current hour of the game
     * 
     * @return An integer
     */
    public int getHours() {
        return hours;
    }
    
    /**
     * Sets the hours of the timer
     * 
     * @param hours
     *  - the hour to be used in the timer
     */
    public void setHours(int hours) {
        this.hours = hours;
    }

    /**
     * Accesses the time and returns the current minutes of the game
     * 
     * @return An integer
     */
    public int getMinutes() {
        return minutes;
    }
    
    /**
     * Sets the minutes of the timer
     * 
     * @param minutes
     *  - the minutes to be used in the timer
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    
    /**
     * Accesses the time and returns the current seconds of the game
     * 
     * @return An integer
     */
    public int getSeconds() {
        return seconds;
    }
    
    /**
     * Sets the seconds of the timer
     * 
     * @param seconds
     *  - the seconds to be used in the timer
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    
    /**
     * resetTimer method that resets the timer back to 0 for hours, minutes and seconds
     */
    public void resetTimer() {
        hours=0;
        minutes=0;
        seconds=0;
     }  
}

