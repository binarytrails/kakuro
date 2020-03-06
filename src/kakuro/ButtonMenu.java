package kakuro;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ButtonMenu {
    JButton pause_button;
    JButton play_button;
    JButton submit_button;
    JButton newGame_button;
    JButton choose_game_button;
    JButton save_button;
    JButton restart_button;
    JButton load_button;
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
        choose_game_button = new JButton("Choose a game");
        save_button = new JButton("Save");
        restart_button = new JButton("Restart");
        load_button = new JButton("Load Game");
        mainPanel = new JPanel();
        chrono = new Chrono();

        // Set up
        mainPanel.add(play_button).setVisible(false);
        mainPanel.add(pause_button).setVisible(false);
        mainPanel.add(restart_button);
        mainPanel.add(submit_button).setVisible(false);
        mainPanel.add(choose_game_button);
        mainPanel.add(save_button).setVisible(false);
        mainPanel.add(load_button);

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
    
    private void toggleMenu() {
        play_button.setVisible(true);
        pause_button.setVisible(true);
        submit_button.setVisible(true);
        save_button.setVisible(true);
        
        choose_game_button.setVisible(false);
        load_button.setVisible(false);
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
                chrono.chronoPause();
                gameController.view.loadInputInModel();
               
                gameController.saveGame();
            }
        });
        
        /* 
         * With the use of an Action Listener to know if the user has clicked on the button, this part of the method will load a saved game of a user
         *  
         **/
        load_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(gameController.loadGame() != null) {
                   JOptionPane.showMessageDialog(null, "Successfully loaded saved game!");
                   toggleMenu();
                } else {
                    JOptionPane.showMessageDialog(null, "You do not have any saved game!", "Not Found", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        /* 
         * With the use of an Action Listener to know if the user has clicked on the button, this part of the method will pop a dialog with preconfigured games
         *  
         **/
        choose_game_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                
                //TODO: hardcoded for now and need other boards solution boards -iteration 2 UI
                Object[] levels = {"1", "2", "3"};
                String chooseGame = (String) JOptionPane.showInputDialog(null, "Choose a level (1 - 3), 1 being easiest to 3 being the hardest", "Difficulty level", JOptionPane.PLAIN_MESSAGE, null, levels, levels[0]);
                
                if(chooseGame != null) {
                    int gameLevel = Integer.parseInt(chooseGame);
                    toggleMenu();
                    
                    gameController.loadPreconfiguredGame(gameLevel);
                }
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
