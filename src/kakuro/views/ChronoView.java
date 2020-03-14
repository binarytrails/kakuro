package kakuro.views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import kakuro.models.ChronoModel;

public class ChronoView {
   
    private JLabel timerLabel;
    
    public ChronoView() {
        timerLabel = new JLabel("0:0:0:0", SwingConstants.CENTER); 
        timerLabel.setFont(new Font(timerLabel.getFont().getFontName(), Font.PLAIN, 20));
        timerLabel.setVisible(false);
        timerLabel.setBorder(new EmptyBorder(10,0,10,0));//top,left,bottom,right
    }
    
    public void setTimerLabel(String label) {
        timerLabel.setText(label);
    }
    
    public JLabel getTimerLabel() {
        return timerLabel;
    }
}
