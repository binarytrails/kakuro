package kakuro.controllers;

import kakuro.core.Cell;
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

/**
 * Game controller class which handles the Kakuro game,
 * which basically orchestrates the model and view of the game
 *
 * @author Vsevolod Ivanov
 * @author Nalveer Moocheet
 * @author Audrey-Laure St-Louis
 * @author Brian Gamboc-Javiniar
 * @author Hoang Thuan Pham
 * Date written: January 15th, 2020
 */
public class GameController
{
    public DatabaseConnection database; // DatabaseConnection object reference
    public GameProgressDao gameProgress; // GameProgressDao object reference
    public GameDao game; // GameDao object reference
    
    public GameView view; // GameView object reference
    public GameModel model; // GameModel object reference
    private Boolean gui = true; // shows the graphical user interface of our game application
    
    //Sub-views controller
    private ChronoController chronoController; // ChronoController object reference
    private MenuBarController buttonMenuController; // MenuBarController object reference
    private GameConsole console; // GameConsole object reference
    
    boolean isPaused = false; // to pause our game

    /**
     * User actions for our view
     */
    public enum UserActions
    {
        UNKNOWN,
        INPUT,
        SOLVE,
        ANSWERS
    }
    
    /**
     * GameController constructors
     *
     * @param columns
     *  - number of columns for our grid
     * @param rows
     *  - number of rows for our grid
     * @param gui
     *  - display our graphical user interface
     */
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

    /**
     * pause method that pauses the timer and hides the view of the current game
     */
    public void pause() {
        isPaused = true;
        chronoController.chronoPause();
        view.hideBoard();
    }
    
    /**
     * resume method that starts the timer again and shows the view of the current game
     */
    public void resume() {
        isPaused = false;
        chronoController.chronoStart();
        view.showBoard();
    }
    
    /**
     * restart method that wipes the game's timer and data in the model
     */
    public void restart() {
        chronoController.resetTimer();
        chronoController.chronoStart();
        loadInputInModel(true); //Clear inputs
    }
    
    /**
     * submit method that pauses the timer and verifies if the game is solved
     */
    public void submit() {
        chronoController.chronoPause();
        loadInputInModel(false); //No clearing inputs
        solveBoard();
        console.printSolveBoard();
    }
    
    /**
     * saveGame method that saves an instance of a current game to the database
     */
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
    
    /**
     * Accesses the saved game from the database
     * 
     * @return a multidimensional array Cell object
     */
    public Cell[][] loadGame() {
        try {            
            //TODO: fixed player and to fix in iteration 3
            Cell[][] boardCell = gameProgress.load(getDatabaseConnection(), "TestPlayer");
            
            if(boardCell != null) {
                model.board = boardCell;         
                System.out.println("Successfully loaded game progress");
                
                console.printStartup();
                console.printBoard(false);
                
                if (gui){
                    view.updateView();
                }
                
                return model.board;
            }
            
            
        } catch(SQLException e) {
            System.err.println("Failed to load game");
        }
        
        return null;
    }
    
    /**
     * solveBoard method that verifies if the give solved Kakuro game is the right answer or not
     * 
     * @return
     *  - returns true if the game is correctly solves, otherwise returns false
     */
    public Boolean solveBoard()
    {
        
        int filledCells=0;   //to keep track of number of filled cells
        ArrayList<Boolean> check = new ArrayList<Boolean>();
        HashMap<Integer,Integer> map;
        
        //nested loop to go through the whole board
        for(int i=0;i<model.getRows();i++) {
            
            for(int j=0;j<model.getColumns();j++) {
                
                switch(model.board[i][j].getType()) {
                
                //check sum if its a filled boards
                case FILLED01:  {    
                    
                    filledCells++;          
                    int column = j+1;
                    int sum = 0;
                    map = new HashMap<Integer,Integer>();
                    //continues to add horizontally until next cell is not an INPUT type
                    while(column <= model.getColumns() && model.board[i][column].getType()==Cell.CellType.INPUT) {       //horizontal sum check
                        
                        
                        int cell = model.board[i][column].getFirstValue();   //getting cell value
                        
                        if(map.containsKey(cell)) {        //if already has number as input return false
                            return false;
                        }
                        else {
                            map.put(cell, cell);
                        }
                        sum += cell;                      //adding the cell value
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
                    
                    while(row <= model.getRows() && model.board[row][j].getType()==Cell.CellType.INPUT) {     
                        
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
                while(column <= model.getColumns() && model.board[i][column].getType()==Cell.CellType.INPUT) {       //horizontal sum check
                    
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
                while(row <= model.getRows() && model.board[row][j].getType()==Cell.CellType.INPUT) {     //vertical sum check
                        
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

    /**
     * loopGame method that is constantly aware of our User Actions while the game application is on
     * This one is for the console, it might not be in the interface
     */
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
    
    /**
     * Number formatter
     * Accesses the minimum number that is valid in our board
     * 
     * @return
     *  - an integer
     */
    public int getMinNumberValid() {
        return view.getMinNumberValid();
    }
    
    /**
     * Number formatter
     * Accesses type of the number formatter
     * 
     * @return
     *  - an object
     */
    public Object getNumberFormatterClassType() {
        return view.getNumberFormatterClassType();
    }
    
    /**
     * Number formatter
     * Accesses the maximum number that is valid in our board
     * 
     * @return
     *  - an integer
     */
    public int getMaxNumberValid() {
        return view.getMaxNumberValid();
    }
    
    /**
     * initGame method which initializes our board
     * 
     * @param model
     *  - A GameModel object to help pass our current data in the model
     */
    private void initGame(GameModel model)
    {
        
        model.initBoard();        
        
        //Currently the view must be loaded the first to populate the data
        chronoController = new ChronoController();
        //boardController = new BoardController(model.rows, model.columns, this);
        buttonMenuController = new MenuBarController(this);
        
        this.view = new GameView(this, gui, chronoController.getView(), buttonMenuController.getView());;
        this.console = new GameConsole(this);
        
        console.printStartup();
        console.printBoard(false/*show answer values*/);
    }

    /**
     * Accesses the database connection
     * 
     * @return a Connection of the database
     */
    private Connection getDatabaseConnection() {
        return database.getConnection();
    }
    
    /**
     * connectDatabase method that helps connect to our database
     */
    private void connectDatabase() {
        database.connect();
    }
    
    /**
     * disconnectDatabase method that helps disconnect our database
     */
    private void disconnectDatabase() {
        database.disconnect();
    }
    
    /**
     * loadPreconfiguredGame method that loads a specific preconfigured game from the database
     * 
     * @param gameLevel
     *  - the difficulty of the game
     */
    void loadPreconfiguredGame(int gameLevel) {
        try {
            ArrayList<Cell[][]> boardCells = game.loadAllPreconfiguredGames(getDatabaseConnection());

            //Load the new game model
            model.board = boardCells.get(gameLevel-1);
            
            console.printStartup();
            console.printBoard(false);
            
            if (gui){
                //Update the new view
                view.updateView();
                
                //Start the timer
                chronoController.show();
                chronoController.chronoStart();
            }
            
        } catch(SQLException e) {
            System.err.println("Failed to load preconfigred game");
        }
    }
    
    /**
     * Updates our GameModel data
     * 
     * @param clearInput
     *  - to wipe our current data in the model
     */
    private void loadInputInModel(boolean clearInput) {
        JTextField[][] saveInput = view.getSavedInput();
        String value;
        
        for(int row = 0; row < model.getColumns(); row++)
        {
            for(int column = 0; column < model.getRows(); column++)
            {
                Cell cell = model.board[row][column];
               
                if (cell.getType() == Cell.CellType.INPUT)
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
}
