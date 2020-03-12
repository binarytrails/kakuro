package kakuro.controllers;

import kakuro.core.BoardCell;
import kakuro.core.GameDifficulty;
import kakuro.views.ButtonMenuView;

public class ButtonMenuController {
    GameController appController;
    
    private ButtonMenuView buttonMenuView;
    private boolean isPaused;
    
    public ButtonMenuController(GameController appController){
        this.appController = appController;
        buttonMenuView = new ButtonMenuView(this);
    }
    
    public ButtonMenuView getButtonMenuView() {
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
    
    public ButtonMenuView getView() {
        return buttonMenuView;
    }
    
    public void submit() {
        appController.submit();
    }
    
    public void save() {
        appController.saveGame();
    }
    
    public BoardCell[][] load() {
        return appController.loadGame();
    }
    
    public void loadPreconfiguredGame(GameDifficulty gameLevel) {
        appController.loadPreconfiguredGame(GameDifficulty.GameDifficultyToInt(gameLevel));
    }
    
    public void restart() {
        appController.restart();
    }
}
