package kakuro.models;

import java.util.Enumeration;

import javax.swing.JTextField;
import javax.swing.tree.TreeNode;

import kakuro.core.*;

/**
 * Game model class, which acts as our representation data for the object
 * 
 * @author Vsevolod Ivanov
 * @author Isabelle Charette
 * Date written: January 29th, 2020
 */
public class GameModel
{

    private final int columns; // number of columns in our game
    private final int rows; // number of rows in our game
    public Cell[][] board; // multidimensional Cell array object reference
    private static UniquePartitions partitions; // UniquePartitions object reference

    /**
     * GameModel constructor, which sets the rows and columns of the game
     * @param columns
     *  - number of columns 
     * @param rows
     *  - number of rows
     */
    public GameModel(final int columns, final int rows)
    {
        this.columns = columns;
        this.rows = rows;
        partitions = new UniquePartitions();
    }
    
    /**
     * initBoard method that initializes our board to empty cells
     */
    public void initBoard()
    {
        board = new Cell[this.rows][this.columns];

        for(int row = 0; row < this.rows; row++)
        {
            for(int column = 0; column < this.columns; column++)
            {
                board[row][column] = new Cell(Cell.CellType.EMPTY);
            }
        }
    }
    
    /**
     * Accesses the columns of the Game
     * 
     * @return An integer
     */
    public int getColumns() {
        return columns;
    }
    
    /**
     * Accesses the rows of the Game
     * 
     * @return An integer
     */
    public int getRows() {
        return rows;
    }
}
