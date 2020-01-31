// @author Vsevolod Ivanov
// @author Nalveer Moocheet
// @brief Game controller class which handles the Kakuro game.

package kakuro;

import java.util.ArrayList;
import java.util.HashMap;

public class GameController
{
    public GameView view;
    public GameModel model;

    public enum UserActions
    {
        UNKNOWN,
        INPUT,
        SOLVE,
        ANSWERS
    }

    public GameController(final int columns, final int rows)
    {
        this.model = new GameModel(columns, rows);
        initGame(model);
    }

    private void initGame(GameModel model)
    {
        model.initBoard();
        if (model.columns == 10 && model.rows == 10)
            model.generateBoard10x10();
        this.view = new GameView(this);
        view.printStartup();
        view.printBoard(false/*show answer values*/);
        view.boardUI();
        view.timerButtonControl();
    }

    public void loopGame()
    {
        while (true)
        {
            switch (view.printGetUserAction())
            {
                case INPUT:
                    view.printGetInputNumber();
                    view.printBoard(false/*show answer values*/);
                    break;
                case SOLVE:
                    view.printSolveBoard();
                    break;
                case ANSWERS:
                    view.printBoard(true/*show answer values*/);
                    break;
                case UNKNOWN:
                default:
                    break;
            }
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
    				int row = j+1;
    				int sum = 0;
    				map = new HashMap<Integer,Integer>();
    				//continues to add horizontally until next cell is not an INPUT type
    				while(row <= model.columns && model.board[i][row].getType()==BoardCell.CellType.INPUT) {       //horizontal sum check
                        
    					
    					int cell = model.board[i][row].getFirstValue();   //getting cell value
    					
    					if(map.containsKey(cell)) {        //if already has number as input return false
    						return false;
    					}
    					else {
    						map.put(cell, cell);
    					}
    					sum += cell;   					  //adding the cell value
    					row++;
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
    				int colum = i+1;
    				int sum = 0;
    				map = new HashMap<Integer,Integer>();
    				
    				while(colum <= model.rows && model.board[colum][j].getType()==BoardCell.CellType.INPUT) {     
    					
                        int cell =  model.board[colum][j].getFirstValue();   					
                    	
                        if(map.containsKey(cell)) {                      //if already has number as input return false
    						return false;
    					}
    					else {
    						map.put(cell, cell);
    					}
                        sum += cell;
                        colum++;
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
    			int colum = i+1;
    			int row = j+1;
    			int sumRows = 0;
    			int sumColums = 0;
    			map = new HashMap<Integer,Integer>();
    			//checking row sum
    			while(row <= model.columns && model.board[i][row].getType()==BoardCell.CellType.INPUT) {       //horizontal sum check
					
    				int cell = model.board[i][row].getFirstValue();
                    
    			    if(map.containsKey(cell)) {      //if already has number as input return false
						return false;
					}
					else {
						map.put(cell, cell);         //insert in hashmap if not already present in the map
					}
    				
    				sumRows += cell;   					
					row++;
				}
    			map.clear();
    			
    			//checking column sum
    			while(colum <= model.rows && model.board[colum][j].getType()==BoardCell.CellType.INPUT) {     //vertical sum check
    					
    				int cell = model.board[colum][j].getFirstValue();
    			    
    				if(map.containsKey(cell)) {                      //if already has number as input return false
						return false;
					}
					else {
						map.put(cell, cell);
					}
    				
    				sumColums += cell;   					
    				colum++;
    			 }
    			 map.clear(); 
    
    			 if(sumColums==model.board[i][j].getFirstValue() && sumRows == model.board[i][j].getSecondValue())
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
}
