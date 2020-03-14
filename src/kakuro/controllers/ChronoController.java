package kakuro.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.Timer;

import kakuro.models.ChronoModel;
import kakuro.views.ChronoView;

/**
 * Timer controller class called Chrono, which acts as an orchestrator
 * between the model and the view  
 * 
 * @author Audrey St-Louis
 * @author Hoang Thuan Pham
 * Date written: January 29th, 2020
 */
public class ChronoController {
    
    
    private ChronoModel chronoModel; // A ChronoModel object reference
    private ChronoView chronoView; // A ChronoView object reference
    private ActionListener timerListener; // An event action listener for our timer
    private Timer time; // our time for the game application
    private boolean isVisible; // shows timer 
    
    /**
     * ChronoController constructor
     */
    public ChronoController() {
        chronoModel = new ChronoModel();
        chronoView = new ChronoView();
        
        isVisible=false;
        timerSetUp();
    }
    
    /**
     * timerSetUp method sets an action listener and sets up the time
     */
    public void timerSetUp() {
        timerListener= new ActionListener()
        {
            public void actionPerformed(ActionEvent e1)
            {   
                chronoView.setTimerLabel(chronoModel.updateTime());
            }
        };

        chronoModel.getDelay();
        time = new Timer(chronoModel.getDelay(), timerListener);
    }
    
    /**
     * chronoPause method stops the timer from continuing
     */
    public void chronoPause() {
        time.stop();
    }
    
    /**
     * chronoStart method stops the timer from continuing
     */
    public void chronoStart() {
        time.start();
    }
    
    /**
     * show method that shows the timer from the view
     */
    public void show() {
        chronoView.getTimerLabel().setVisible(true);
    }
    
    /**
     * hide method that hides the timer from the view
     */
    public void hide() {
        chronoView.getTimerLabel().setVisible(false);
    }
    
    /**
     * Accesses the timer from the view
     * @return JLabel object
     */
    public JLabel getTimerLabel() {
        return chronoView.getTimerLabel();
    }
    
    /**
     * toggleTimerDisplay method that toggles the timer from the view
     */
    public void toggleTimerDisplay() {
        chronoView.getTimerLabel().setVisible(!isVisible);
    }
    
    /**
     * resetTimer method that that resets the time back to 0:0:0:0
     */
    public void resetTimer() {
        chronoModel.resetTimer();
        chronoView.getTimerLabel().setText("0:0:0:0");
    }
    
    /**
     * Accesses the time and returns the current hour of the game
     * @return An integer
     */
    public int getHours() {
        return chronoModel.getHours();
    }

    /**
     * Accesses the time and returns the current minutes of the game
     * @return An integer
     */
    public int getMinutes() {
        return chronoModel.getMinutes();
    }
    
    /**
     * Accesses the time and returns the current hour of the game
     * @return An integer
     */
    public int getSeconds() {
        return chronoModel.getSeconds();
    }
       
    /**
     * Accesses the time and returns the current hour of the game
     * @return JComponent object
     */
    public JComponent getView() {
        return chronoView.getTimerLabel();
    }
}
