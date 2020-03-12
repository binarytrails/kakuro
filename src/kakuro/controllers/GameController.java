// @author Vsevolod Ivanov
// @author Nalveer Moocheet
// @brief Game controller class which handles the Kakuro game.

package kakuro.controllers;

import kakuro.core.BoardCell;
import kakuro.core.DatabaseConnection;
import kakuro.game.dao.GameDao;
import kakuro.game.dao.GameDaoImpl;
import kakuro.gameprogress.dao.GameProgressDao;
import kakuro.gameprogress.dao.GameProgressDaoImpl;
import kakuro.models.GameModel;
import kakuro.views.GameConsole;
import kakuro.views.GameView;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTextField;

public class GameController
{
    public DatabaseConnection database;
    public GameProgressDao gameProgress;
    public GameDao game;
    
    public GameView view;
    public GameModel model;
    private Boolean gui = true;
    
    //Sub-views controller
    private ChronoController chronoController;
    public BoardController boardController;
    public ButtonMenuController buttonMenuController;
    
    public GameConsole console;
    
    public boolean isPaused = false;

    public enum UserActions
    {
        UNKNOWN,
        INPUT,
        SOLVE,
        ANSWERS
    }

    public GameController(final int columns, final int rows, final Boolean gui)
    {
        this.model = new GameModel(columns, rows);
        this.gui = gui;
        
        database = new DatabaseConnection();
        connectDatabase();
        
        gameProgress = new GameProgressDaoImpl();
        game = new GameDaoImpl();
        
        initGame(model);
    }

    private void initGame(GameModel model)
    {
        
        model.initBoard();        
        
        //Currently the view must be loaded the first to populate the data
        chronoController = new ChronoController();
        boardController = new BoardController(model.rows, model.columns, this);
        buttonMenuController = new ButtonMenuController(this);
        
        this.view = new GameView(this, gui, chronoController.getView(), boardController.getView(), buttonMenuController.getView());;
        this.console = new GameConsole(this);
        
        console.printStartup();
        console.printBoard(false/*show answer values*/);
    }

    public void loopGame()
    {
        while (true)
        {
            switch (console.printGetUserAction())
            {
                case INPUT:
                    console.printGetInputNumber();
                    console.printBoard(false/*show answer values*/);
                    break;
                case SOLVE:
                    if (gui)
                        loadInputInModel(false);
                    console.printSolveBoard();
                    break;
                case ANSWERS:
                    console.printBoard(true/*show answer values*/);
                    break;
                case UNKNOWN:
                default:
                    break;
            }
        }
    }
    
    public Connection getDatabaseConnection() {
        return database.getConnection();
    }
    
    public void connectDatabase() {
        database.connect();
    }
    
    public void disconnectDatabase() {
        database.disconnect();
    }
    
    public void saveGame() {
        
        try {            
            chronoController.chronoPause();
            loadInputInModel(false);
            //TODO: fixed player and to fix in iteration 3
            gameProgress.save(getDatabaseConnection(), "TestPlayer", model.board);
            
            System.out.println("Successfully saved game progress");
        } catch(SQLException e) {
            System.err.println("Failed to save game");
        }
    }
    
    public BoardCell[][] loadGame() {
        try {            
            //TODO: fixed player and to fix in iteration 3
            BoardCell[][] boardCell = gameProgress.load(getDatabaseConnection(), "TestPlayer");
            
            if(boardCell != null) {
                model.board = boardCell;         
                System.out.println("Successfully loaded game progress");
                
                console.printStartup();
                console.printBoard(false);
                
                if (gui){
                    view.updateView(boardController.loadGame());
                }
                
                return model.board;
            }
            
            
        } catch(SQLException e) {
            System.err.println("Failed to load game");
        }
        
        return null;
    }
    
    public void loadPreconfiguredGame(int gameLevel) {
        try {
            ArrayList<BoardCell[][]> boardCells = game.loadAllPreconfiguredGames(getDatabaseConnection());

            //Load the new game model
            model.board = boardCells.get(gameLevel-1);
            
            console.printStartup();
            console.printBoard(false);
            
            if (gui){
                //Update the new view
                view.updateView(boardController.loadGame());
                
                //Start the timer
                chronoController.show();
                chronoController.chronoStart();
            }
            
        } catch(SQLException e) {
            System.err.println("Failed to load preconfigred game");
        }
    }

    public Boolean solveBoard()
    {
        
    	int filledCells=0;   //to keep track of number of filled cells
    	ArrayList<Boolean> check = new ArrayList<Boolean>();
        HashMap<Integer,Integer> map;
    	
        //nested loop to go through the whole board
    	for(int i=0;i<model.rows;i++) {
    		
    		for(int j=0;j<model.columns;j++) {
    			
    			switch(model.board[i][j].getType()) {
    			
    			//check sum if its a filled boards
    			case FILLED01:	{    
    			    
    				filledCells++;          
    				int column = j+1;
    				int sum = 0;
    				map = new HashMap<Integer,Integer>();
    				//continues to add horizontally until next cell is not an INPUT type
    				while(column <= model.columns && model.board[i][column].getType()==BoardCell.CellType.INPUT) {       //horizontal sum check
                        
    					
    					int cell = model.board[i][column].getFirstValue();   //getting cell value
    					
    					if(map.containsKey(cell)) {        //if already has number as input return false
    						return false;
    					}
    					else {
    						map.put(cell, cell);
    					}
    					sum += cell;   					  //adding the cell value
    					column++;
    				}
    			 
    			 map.clear();                             //clearing hashmap after use
    			
    			 if(sum==model.board[i][j].getSecondValue())
    				 check.add(true);
    			 else
    				 return false;
    			}
    			
    			 break;
    			 
    			case FILLED10 : {                                               //vertical sum check
    				filledCells++;
    				int row = i+1;
    				int sum = 0;
    				map = new HashMap<Integer,Integer>();
    				
    				while(row <= model.rows && model.board[row][j].getType()==BoardCell.CellType.INPUT) {     
    					
                        int cell =  model.board[row][j].getFirstValue();   					
                    	
                        if(map.containsKey(cell)) {                      //if already has number as input return false
    						return false;
    					}
    					else {
    						map.put(cell, cell);
    					}
                        sum += cell;
                        row++;
    				}
    			 map.clear();
    			 
    			 if(sum==model.board[i][j].getFirstValue())
    				check.add(true);
    			 else 
    			    return false;
    			 }
    			 break;
    			 
    			case FILLED11 : {
    				
    			filledCells++;	
    			int row = i+1;
    			int column = j+1;
    			int sumRows = 0;
    			int sumColumns = 0;
    			map = new HashMap<Integer,Integer>();
    			//checking row sum
    			while(column <= model.columns && model.board[i][column].getType()==BoardCell.CellType.INPUT) {       //horizontal sum check
					
    				int cell = model.board[i][column].getFirstValue();
                    
    			    if(map.containsKey(cell)) {      //if already has number as input return false
						return false;
					}
					else {
						map.put(cell, cell);         //insert in hashmap if not already present in the map
					}
    				
    				sumColumns += cell;   					
					column++;
				}
    			map.clear();
    			
    			//checking column sum
    			while(row <= model.rows && model.board[row][j].getType()==BoardCell.CellType.INPUT) {     //vertical sum check
    					
    				int cell = model.board[row][j].getFirstValue();
    			    
    				if(map.containsKey(cell)) {                      //if already has number as input return false
						return false;
					}
					else {
						map.put(cell, cell);
					}
    				
    				sumRows += cell;   					
    				row++;
    			 }
    			 map.clear(); 
    
    			 if(sumRows==model.board[i][j].getFirstValue() && sumColumns == model.board[i][j].getSecondValue())
    				 check.add(true);
    			 else
    				 return false;
    			
    			} 
    			break;
    			default :{};
    			
    		}	
    				
    			}
    			
    		}
    	
    	boolean correct=true;
    	
    	for(int i=0;i<filledCells;i++) {
    		if(check.get(i)!=true)
    			correct=false;
    	}
    	
          if(correct)    	
    	  return true;
          else
          return false;
    }
    
    public void loadInputInModel(boolean clearInput) {
        JTextField[][] saveInput = boardController.getSavedInput();
        String value;
        
        for(int row = 0; row < model.columns; row++)
        {
            for(int column = 0; column < model.rows; column++)
            {
                BoardCell cell = model.board[row][column];
               
                if (cell.getType() == BoardCell.CellType.INPUT)
                {
                    if(clearInput) {
                        saveInput[row][column].setText("");
                        model.board[row][column].setFirstValue(-1);
                    }
                    else {
                        value = saveInput[row][column].getText();
                        if(!value.isEmpty())
                            model.board[row][column].setFirstValue(Integer.parseInt(value));
                    }
                }
            }
        }
    }

    public void pause() {
        isPaused = true;
        chronoController.chronoPause();
        view.hideBoard();
    }
    
    public void resume() {
        isPaused = false;
        chronoController.chronoStart();
        view.showBoard();
    }
    
    public void restart() {
        chronoController.resetTimer();
        chronoController.chronoStart();
        loadInputInModel(true); //Clear inputs
    }
    
    public void submit() {
        chronoController.chronoPause();
        loadInputInModel(false); //No clearing inputs
        solveBoard();
        console.printSolveBoard();
    }
}