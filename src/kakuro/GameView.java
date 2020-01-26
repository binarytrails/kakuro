// @author Vsevolod Ivanov
// @author ...
// @brief Game view class which handles the Kakuro game interface.

package kakuro;

public class GameView
{
    private final GameController controller;

    public GameView(final GameController controller)
    {
        this.controller = controller;
    }

    public void printStartup()
    {
        System.out.println("welcome to kakuro game!");
        System.out.println("=> use numbers between 1-9 to fill the cells;");
    }

    public void printBoard(Boolean showAnswerValues)
    {
        System.out.println("board:");
        for(int row = 0; row < controller.model.columns; row++)
        {
            for(int column = 0; column < controller.model.rows; column++)
            {
                BoardCell cell = controller.model.board[row][column];
                switch (cell.getType())
                {
                    case EMPTY:
                        System.out.print(" x ");
                        break;
                    case INPUT:
                        System.out.print(" " +
                                (showAnswerValues ? (cell.getAnswerValue() != -1 ? cell.getAnswerValue() : "_") :
                                                    (cell.getInputValue() != -1 ? cell.getInputValue() : "_")) + " ");
                        break;
                    case FILLED11:
                    case FILLED10:
                    case FILLED01:
                        int value = cell.getInputValue();
                        if (value > 9)
                            System.out.print(" ");
                        System.out.print(value);
                        break;
                    default:
                        break;
                }
            }
            System.out.println();
        }
    }
}
