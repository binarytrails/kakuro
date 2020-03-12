// @author Vsevolod Ivanov
// @author ...
// @brief Game model class which handles the Kakuro game storage.

package kakuro.models;

import java.util.Enumeration;

import javax.swing.JTextField;
import javax.swing.tree.TreeNode;

import kakuro.core.*;

public class GameModel
{

    public final int columns;
    public final int rows;
    public BoardCell[][] board;
    private static UniquePartitions partitions;

    public GameModel(final int columns, final int rows)
    {
        this.columns = columns;
        this.rows = rows;
        partitions = new UniquePartitions();
    }

    public void initBoard()
    {
        board = new BoardCell[this.rows][this.columns];

        for(int row = 0; row < this.rows; row++)
        {
            for(int column = 0; column < this.columns; column++)
            {
                board[row][column] = new BoardCell(BoardCell.CellType.EMPTY);
            }
        }
    }
}
