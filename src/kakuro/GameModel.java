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
}
