// @author Vsevolod Ivanov
// @author ...
// @brief Game model class which handles the Kakuro game storage.

package kakuro;

public class GameModel
{
    public enum CellType
    {
        EMPTY,    /* |   |*/
        INPUT,    /* |   |*/
        FILLED01, /* | \n| */
        FILLED10, /* |n\ | */
        FILLED11  /* |n\n| */
    }

    public final int columns;
    public final int rows;
    public CellType[][] board;

    public GameModel(final int columns, final int rows)
    {
        this.columns = columns;
        this.rows = rows;
    }

    public void generateBoard()
    {
        board = new CellType[this.columns][this.rows];

        for(int column = 0; column < this.columns; column++)
        {
            for(int row = 0; row < this.rows; row++)
            {
                board[column][row] = CellType.EMPTY;
            }
        }
    }
}
