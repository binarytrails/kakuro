// @author Vsevolod Ivanov
// @author ...
// @brief Game model class which handles the Kakuro game storage.

package kakuro;

public class GameModel
{
    public enum Cell
    {
        EMPTY,    /* |   |*/
        INPUT,    /* |   |*/
        FILLED01, /* | \n| */
        FILLED10, /* |n\ | */
        FILLED11  /* |n\n| */
    }

    public final int columns;
    public final int rows;

    public GameModel(final int columns, final int rows)
    {
        this.columns = columns;
        this.rows = rows;
    }
}
