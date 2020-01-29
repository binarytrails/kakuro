// @author Vsevolod Ivanov
// @author Nalveer Moocheet
// @brief Game controller class which handles the Kakuro game.

package kakuro;

import java.util.ArrayList;

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
        view.board_ui();
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
        
    	int filledCells=0;
    	ArrayList<Boolean> check = new ArrayList<Boolean>();
        
    	for(int i=0;i<model.rows;i++) {
    		
    		for(int j=0;j<model.columns;j++) {
    			
    			switch(model.board[i][j].getType()) {
    				
    			case FILLED01:	{    
    			    filledCells++;
    				int row = j+1;
    				int sum = 0;
    				//continues to add horizontally until next cell is not an INPUT type
    				while(row <= model.columns && model.board[i][row].getType()==BoardCell.CellType.INPUT) {       //horizontal sum check
                        
    					sum += model.board[i][row].getFirstValue();   					
    					row++;
    				}
    			    
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
    				while(colum <= model.rows && model.board[colum][j].getType()==BoardCell.CellType.INPUT) {     
    					
                        sum += model.board[colum][j].getFirstValue();   					
    					colum++;
    				}
    			    
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
    			
    			//checking row sum
    			while(row <= model.columns && model.board[i][row].getType()==BoardCell.CellType.INPUT) {       //horizontal sum check
					
                    sumRows += model.board[i][row].getFirstValue();   					
					row++;
				}
    			
    			//checking column sum
    			while(colum <= model.rows && model.board[colum][j].getType()==BoardCell.CellType.INPUT) {     //vertical sum check
    					
                    sumColums += model.board[colum][j].getFirstValue();   					
    				colum++;
    			 }
    			    
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
