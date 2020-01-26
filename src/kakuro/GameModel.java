// @author Vsevolod Ivanov
// @author ...
// @brief Game model class which handles the Kakuro game storage.

package kakuro;


public class GameModel
{

    public final int columns;
    public final int rows;
    public BoardCell[][] board;

    public GameModel(final int columns, final int rows)
    {
        this.columns = columns;
        this.rows = rows;
    }

    public void generateBoard()
    {
        board = new BoardCell[this.columns][this.rows];

        for(int column = 0; column < this.columns; column++)
        {
            for(int row = 0; row < this.rows; row++)
            {
                board[column][row] = new BoardCell(BoardCell.CellType.EMPTY);
            }
        }
    }

    public boolean checkBoard() {
    	
    	for(int i=0;i<columns;i++) {
    		
    		for(int j=0;j<rows;j++) {
    			
    			switch(board[i][j].getType()) {
    				
    			case FILLED01:	{    
    			    
    				int row = j+1;
    				int sum = 0;
    				//continues to add horizontally until next cell is not an INPUT type
    				while(row <= rows && board[i][row].getType()==BoardCell.CellType.INPUT) {       //horizontal sum check
    					
                        sum += board[i][row].getValue();   					
    					row++;
    				}
    			    
    			 if(sum!=board[i][j]."?????????????")
    				 return false;
    			 }
    			 break;
    			 
    			case FILLED10 : {                                               //vertical sum check
    				
    				int colum = i+1;
    				int sum = 0;
    				while(colum <= columns && board[colum][j].getType()==BoardCell.CellType.INPUT) {     
    					
                        sum += board[colum][j].getValue();   					
    					colum++;
    				}
    			    
    			 if(sum!=board[i][j]."??????????")
    				 return false;
    			 }
    			 break;
    			 
    			case FILLED11 : {
    				
    				
    			int colum = i+1;
    			int row = j+1;
    			int sumRows = 0;
    			int sumColums = 0;
    			
    			//checking row sum
    			while(row <= rows && board[i][row].getType()==BoardCell.CellType.INPUT) {       //horizontal sum check
					
                    sumRows += board[i][row].getValue();   					
					row++;
				}
    			
    			//checking column sum
    			while(colum <= columns && board[colum][j].getType()==BoardCell.CellType.INPUT) {     
    					
                    sumColums += board[colum][j].getValue();   					
    				colum++;
    			 }
    			    
    			 if(sumColums!=board[i][j]."???????????" || sumRows != board[i][j]."??????????")
    				 return false;
    			
    			} 
    			break;
    			default :{};
    			
    		}	
    				
    			}
    			
    			
    			
    		}
    		
    	  return true;
    		
    	}








}
