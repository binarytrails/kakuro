package kakuro.views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kakuro.controllers.ChronoController;
import kakuro.controllers.GameController;
import kakuro.controllers.MenuBarController;
import kakuro.core.GameDifficulty;
import kakuro.core.GameDifficultyListItem;

public class MenuBarView {
    //Controller
    MenuBarController menuBarController;
    
    //UI components
    JButton pause_button;
    JButton submit_button;
    JButton choose_game_button;
    JButton save_button;
    JButton restart_button;
    JButton load_button;
    public JPanel mainPanel;

    public MenuBarView(MenuBarController menuBarController) {
        this.menuBarController = menuBarController;
        
        //Initialize the buttons
        pause_button = new JButton("Pause");
        submit_button = new JButton("Submit");
        choose_game_button = new JButton("New Game");
        save_button = new JButton("Save");
        restart_button = new JButton("Restart");
        load_button = new JButton("Load Game");
        mainPanel = new JPanel();
        
        //Set up the event listeners
        buttonsSetUp();
        
        //Set up visibility and add the buttons to a panel
        mainPanel.add(pause_button).setVisible(false);
        mainPanel.add(restart_button).setVisible(false);;
        mainPanel.add(submit_button).setVisible(false);
        mainPanel.add(choose_game_button);
        mainPanel.add(save_button).setVisible(false);
        mainPanel.add(load_button);
    }
    
    private void toggleMenu() {
        pause_button.setVisible(true);
        submit_button.setVisible(true);
        save_button.setVisible(true);
        restart_button.setVisible(true);
        
        choose_game_button.setVisible(false);
        load_button.setVisible(false);
    }

    public void buttonsSetUp() {

        /* With the use of an Action Listener to know if the user has clicked on the button, this part of the method will stop the timer. */    
        pause_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(!menuBarController.isPaused()) {
                    pause_button.setText("Resume");
                    menuBarController.pause();
                }
                else {
                    pause_button.setText("Pause");
                    menuBarController.resume();
                }
            }
        });

        /* With the use of an Action Listener to know if the user has clicked on the button, this part of the method will perform the validation. */
        submit_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                menuBarController.submit();
            }
        });

        /* With the use of an Action Listener to know if the user has clicked on the button, this part of the method will save the current state of
         * the game.
         *  */
        save_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                menuBarController.save();
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
                if(menuBarController.load() != null) {
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
                GameDifficultyListItem[] levels = {new GameDifficultyListItem("Easy", GameDifficulty.EASY),
                        new GameDifficultyListItem("Medium", GameDifficulty.MEDIUM),
                        new GameDifficultyListItem("Difficult", GameDifficulty.DIFFICULT)};

                GameDifficultyListItem difficultyItem = (GameDifficultyListItem) JOptionPane.showInputDialog(null, "Choose a difficulty level", "Difficulty level", JOptionPane.PLAIN_MESSAGE, null, levels, levels[0]);
                
                if(difficultyItem != null) {
                    menuBarController.loadPreconfiguredGame(difficultyItem.getDifficulty());
                    toggleMenu();
                }
            }
        });
        
        restart_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                menuBarController.restart();
            }
        });
    }
}
