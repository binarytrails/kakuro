package kakuro;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ButtonMenu {
    JButton pause_button;
    JButton play_button;
    JButton submit_button;
    JButton newGame_button;
    JButton save_button;
    JButton restart_button;
    JPanel mainPanel;
    Chrono chrono;
    public Chrono getChrono() {
        return chrono;
    }

    public void setChrono(Chrono chrono) {
        this.chrono = chrono;
    }

    GameController gameController;


    public ButtonMenu(JFrame appFrame, int x, int y, GameController gameController) {
        this.gameController = gameController;
        pause_button = new JButton("Pause");
        play_button = new JButton("Play");
        submit_button = new JButton("Submit");
        newGame_button = new JButton("New Game");
        save_button = new JButton("Save");
        restart_button = new JButton("Restart");
        mainPanel = new JPanel();
        chrono = new Chrono();

        // Set up
        mainPanel.add(play_button);
        mainPanel.add(pause_button);
        mainPanel.add(submit_button);
        mainPanel.add(restart_button);
        
        if (appFrame != null)
        {
            appFrame.getContentPane().add(chrono.getTimerLabel(), BorderLayout.BEFORE_FIRST_LINE);
            appFrame.getContentPane().add(mainPanel, BorderLayout.AFTER_LAST_LINE);
            appFrame.pack();
            appFrame.setSize(x,y);
            appFrame.setVisible(true);
        }
    }

    public void chronoSetUp() {
        chrono.timerSetUp();
    }

    public void buttonsSetUp() {

        /* With the use of an Action Listener to know if the user has clicked on the button, this part of the method will stop the timer. */    
        pause_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                chrono.chronoPause();
            }
        });
        
        /* With the use of an Action Listener to know if the user has clicked on the button, this part of the method will start the timer. */
        play_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                chrono.chronoStart();
            }
        });

        /* With the use of an Action Listener to know if the user has clicked on the button, this part of the loop method will restart the timer. */
        newGame_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                chrono.resetTimer();
                chrono.chronoStart();
            }
        });

        /* With the use of an Action Listener to know if the user has clicked on the button, this part of the method will perform the validation. */
        submit_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                chrono.chronoPause();
                //loads data into model
                gameController.loadInputInModel(false); //No clearing inputs
                gameController.solveBoard();
                gameController.view.printSolveBoard();
            }
        });

        /* With the use of an Action Listener to know if the user has clicked on the button, this part of the method will save the current state of
         * the game.
         *  */
        save_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // Call save method
            }
        });
        
        restart_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                chrono.resetTimer();
                chrono.chronoStart();
                gameController.loadInputInModel(true); //Clear inputs
            }
        });
    }
}
