package kakuro.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.Timer;

import kakuro.models.ChronoModel;
import kakuro.views.ChronoView;

public class ChronoController {
    private ChronoModel chronoModel;
    private ChronoView chronoView;
    private ActionListener timerListener;
    private Timer time;
    private boolean isVisible;
    
    public ChronoController() {
        chronoModel = new ChronoModel();
        chronoView = new ChronoView();
        
        isVisible=false;
        timerSetUp();
    }
    
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
    
    public void chronoPause() {
        time.stop();
    }

    public void chronoStart() {
        time.start();
    }
    
    public void show() {
        chronoView.getTimerLabel().setVisible(true);
    }
    
    public void hide() {
        chronoView.getTimerLabel().setVisible(false);
    }
    
    public JLabel getTimerLabel() {
        return chronoView.getTimerLabel();
    }
    
    public void toggleTimerDisplay() {
        chronoView.getTimerLabel().setVisible(!isVisible);
    }

    public void resetTimer() {
        chronoModel.resetTimer();
        chronoView.getTimerLabel().setText("0:0:0:0");
    }
    
    public int getHours() {
        return chronoModel.getHours();
    }

    public int getMinutes() {
        return chronoModel.getMinutes();
    }

    public int getSeconds() {
        return chronoModel.getSeconds();
    }
    
    public JComponent getView() {
        return chronoView.getTimerLabel();
    }
}
