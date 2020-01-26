// @author Vsevolod Ivanov
// @author ...
// @brief Game model class which handles the Kakuro game storage.

package kakuro;

import java.util.Enumeration;
import java.util.Random;

import javax.swing.tree.TreeNode;

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
        board = new BoardCell[this.columns][this.rows];

        for(int row = 0; row < this.rows; row++)
        {
            for(int column = 0; column < this.columns; column++)
            {
                board[column][row] = new BoardCell(BoardCell.CellType.EMPTY);
            }
        }
    }

    public void generateBoard()
    {
        // basis longest (1-9) in center
        int basisRow = randomInt(1,9);
        board[basisRow][0] = new BoardCell(BoardCell.CellType.FILLED01, 45);
        for(int column = 1; column <= 9; column++)
        {
            board[basisRow][column] = new BoardCell(BoardCell.CellType.INPUT, column);
        }
    }

    private int randomInt(int min, int max)
    {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
