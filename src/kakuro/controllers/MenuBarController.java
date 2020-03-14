package kakuro.controllers;

import javax.swing.JComponent;

import kakuro.core.Cell;
import kakuro.core.GameDifficulty;
import kakuro.views.MenuBarView;

public class MenuBarController {
    GameController appController;
    
    private MenuBarView buttonMenuView;
    private boolean isPaused;
    
    public MenuBarController(GameController appController){
        this.appController = appController;
        buttonMenuView = new MenuBarView(this);
    }
    
    public MenuBarView getButtonMenuView() {
        return buttonMenuView;
    }
    
    public boolean isPaused() {
        return appController.isPaused;
    }
    
    public void pause() {
       appController.pause();
    }
    
    public void resume() {
       appController.resume();
    }
    
    public JComponent getView() {
        return buttonMenuView.getMainPanel();
    }
    
    public void submit() {
        appController.submit();
    }
    
    public void save() {
        appController.saveGame();
    }
    
    public Cell[][] load() {
        return appController.loadGame();
    }
    
    public void loadPreconfiguredGame(GameDifficulty gameLevel) {
        appController.loadPreconfiguredGame(GameDifficulty.GameDifficultyToInt(gameLevel));
    }
    
    public void restart() {
        appController.restart();
    }
}
