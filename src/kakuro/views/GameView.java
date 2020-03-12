// @author Vsevolod Ivanov
// @author Isabelle Charette
// @brief Game view class which handles the Kakuro game interface.

package kakuro.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.Scanner;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.NumberFormatter;

import kakuro.controllers.AppController;
import kakuro.controllers.AppController.UserActions;
import kakuro.controllers.BoardController;
import kakuro.controllers.ButtonMenuController;
import kakuro.controllers.ChronoController;
import kakuro.core.BoardCell;
import kakuro.core.LinePanel;

public class GameView
{
    private AppController controller;
    
    //Subviews
    ChronoView chronoView;
    BoardView boardView;
    ButtonMenuView buttonMenuView;
    
    //UI components
    JPanel currentBoard;
    
    //Main application frame and properties
    JFrame frame;
    int gridSizeX;
    int gridSizeY;
   
    //UI support
    
    private ButtonMenuView buttonMenu;
    
    private JLabel startUI;
     
    public GameView(final AppController controller, Boolean X11, ChronoView chronoView, BoardView boardView, ButtonMenuView buttonMenuView)
    {
        if (controller != null)
        {
            this.controller = controller;
            gridSizeX = controller.model.rows;
            gridSizeY = controller.model.columns;
        }
        
        if (X11) {
            frame = new JFrame("KAKURO");
            //buttonMenu = new ButtonMenuView(frame, gridSizeX, gridSizeY, controller);
            
            //Initialize the application frame
            int frameSize = 60;
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            int x = frameSize*gridSizeX;
            int y = frameSize*gridSizeY;
            frame.setSize(x, y);
            frame.setResizable(false);
            
            this.chronoView = chronoView;
            this.boardView = boardView;
            this.buttonMenuView = buttonMenuView;
            
            //Create a timer at the top
            frame.getContentPane().add(chronoView.timerLabel, BorderLayout.BEFORE_FIRST_LINE);
            //Main game UI stays at the center
            currentBoard = boardView.boardPanel;
            frame.getContentPane().add(currentBoard);
            //Button menu stays at the bottom 
            frame.getContentPane().add(buttonMenuView.mainPanel, BorderLayout.AFTER_LAST_LINE);
            
            frame.pack();
            frame.setSize(x,y);
            frame.setVisible(true);
        }
    }
    
    public void updateView(JPanel newBoardPanel) {
      //If a panel is already attached to the frame, remove it
        if(currentBoard != null)
            frame.getContentPane().remove(currentBoard);
        
        //Save a reference to the new panel
        frame.getContentPane().add(newBoardPanel);
        currentBoard = newBoardPanel;;
    }
    
    public void showBoard() {
        currentBoard.setVisible(true);
    }
    
    public void hideBoard() {
        currentBoard.setVisible(false);
    }

    //Methods for the menuButtons
    public ButtonMenuView getButtonMenu() {
        return buttonMenu;
    }

    public void setButtonMenu(ButtonMenuView buttonMenu) {
        this.buttonMenu = buttonMenu;
    }
}
