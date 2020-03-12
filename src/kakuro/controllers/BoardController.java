package kakuro.controllers;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kakuro.core.BoardCell;
import kakuro.views.BoardView;

public class BoardController {
    GameController appController;
    
    int gridSizeX;
    int gridSizeY;
    
    BoardView boardView;
    JPanel currentPanel;
    
    public BoardController(int gridSizeX, int gridSizeY, GameController appController) {
        this.gridSizeX = gridSizeX;
        this.gridSizeY = gridSizeY;
        this.appController = appController; 
        
        boardView = new BoardView(appController.model.columns, appController.model.rows, gridSizeX, gridSizeY);
        boardView.getBoardUI(appController.model.board);
        //boardView.
    }
    
    public JPanel loadGame() {
        return boardView.getBoardUI(appController.model.board);
    }
    
    public BoardView getView() {
        return boardView;
    }
    
    public JTextField[][] getSavedInput() {
        return boardView.cells;
    }
    
    //Number formatter methods
    public int getMinNumberValid() {
        return boardView.getMinNumberValid();
    }
    
    public Object getNumberFormatterClassType() {
        return boardView.getNumberFormatterClassType();
    }
    
    public int getMaxNumberValid() {
        return boardView.getMaxNumberValid();
    }
}
