package kakuro.views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import kakuro.models.ChronoModel;

/**
 * Timer view class called Chrono, which acts as our view interface
 * 
 * @author Audrey St-Louis
 * @author Hoang Thuan Pham
 * Date written: January 29th, 2020
 */
public class ChronoView {
   
    private JLabel timerLabel; // the label for our timer in the view
    
    /**
     * ChronoView constructor
     */
    public ChronoView() {
        timerLabel = new JLabel("0:0:0:0", SwingConstants.CENTER); 
        timerLabel.setFont(new Font(timerLabel.getFont().getFontName(), Font.PLAIN, 20));
        timerLabel.setVisible(false);
        timerLabel.setBorder(new EmptyBorder(10,0,10,0));//top,left,bottom,right
    }
    
    /**
     * Sets the timer to the label
     * 
     * @param label
     */
    public void setTimerLabel(String label) {
        timerLabel.setText(label);
    }
    
    /**
     * Accesses the timer from the label
     * 
     * @return JLabel object
     */
    public JLabel getTimerLabel() {
        return timerLabel;
    }
}
