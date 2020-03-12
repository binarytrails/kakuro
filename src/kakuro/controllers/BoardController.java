package kakuro.controllers;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kakuro.core.BoardCell;
import kakuro.views.BoardView;

public class BoardController {
    AppController appController;
    
    int gridSizeX;
    int gridSizeY;
    
    BoardView boardView;
    JPanel currentPanel;
    
    public BoardController(int gridSizeX, int gridSizeY, AppController appController) {
        this.gridSizeX = gridSizeX;
        this.gridSizeY = gridSizeY;
        this.appController = appController; 
        
        boardView = new BoardView(10, 10, gridSizeX, gridSizeY);
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
        return boardView.getMaxNumberValid();
    }
    
    public Object getNumberFormatterClassType() {
        return boardView.getNumberFormatterClassType();
    }
    
    public int getMaxNumberValid() {
        return boardView.getMaxNumberValid();
    }
}
