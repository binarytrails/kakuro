package kakuro.controllers;

import javax.swing.JComponent;

import kakuro.core.Cell;
import kakuro.core.GameDifficulty;
import kakuro.views.MenuBarView;

/**
 * Menu bar controller class which handles the buttons in our game application,
 * which basically orchestrates the model and view of the menu bar
 *
 * @author Vsevolod Ivanov
 * @author Isabelle Charette
 * @author Audrey-Laure St-Louis
 * @author Brian Gamboc-Javiniar
 * @author Hoang Thuan Pham
 * Date written: February 7th, 2020
 */
public class MenuBarController {
    GameController appController; // GameController object reference
    
    private MenuBarView buttonMenuView; // MenuBarView object reference
    private boolean isPaused; // check the game status
    
    /**
     * MenuBarController constructor
     * 
     * @param appController
     *  - an appController reference
     */
    public MenuBarController(GameController appController){
        this.appController = appController;
        buttonMenuView = new MenuBarView(this);
    }
    
    /**
     * Accesses the menu bar view 
     * 
     * @return
     *  - MenuBarView object
     */
    public MenuBarView getButtonMenuView() {
        return buttonMenuView;
    }
    
    /**
     * Accesses if the game is currently being paused or not from the appController
     * 
     * @return
     *  - a boolean value
     */
    public boolean isPaused() {
        return appController.isPaused;
    }
    
    /**
     * pause method that pauses the game from the appController
     */
    public void pause() {
       appController.pause();
    }
    
    /**
     * resume method that resumes the game from the appController
     */
    public void resume() {
       appController.resume();
    }
    
    /**
     * Accesses the JComponent in our game application from the appController
     * 
     * @return
     *  - JComponent object
     */
    public JComponent getView() {
        return buttonMenuView.mainPanel;
    }
    
    /**
     * submit method that submits the game from the appController
     */
    public void submit() {
        appController.submit();
    }
    
    /**
     * save method that submits the game from the appController
     */
    public void save() {
        appController.saveGame();
    }
    
    /**
     * Accesses the saved game of the database from the appController
     * 
     * @return
     *  - a multidimensional Cell array
     */
    public Cell[][] load() {
        return appController.loadGame();
    }
    
    /**
     * loadPreconfigredGame of the database from the appController
     * 
     * @param gameLevel
     *  - the difficulty of the game
     */
    public void loadPreconfiguredGame(GameDifficulty gameLevel) {
        appController.loadPreconfiguredGame(GameDifficulty.GameDifficultyToInt(gameLevel));
    }
    
    /**
     * restart method that wipes the data from the view and model from the appController
     */
    public void restart() {
        appController.restart();
    }
}
