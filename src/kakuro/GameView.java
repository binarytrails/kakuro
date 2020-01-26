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

    public void printBoard(Boolean withAnswers)
    {
        System.out.println("board:");
        for(int column = 0; column < controller.model.columns; column++)
        {
            for(int row = 0; row < controller.model.rows; row++)
            {
                BoardCell cell = controller.model.board[column][row];
                switch (cell.getType())
                {
                    case EMPTY:
                        System.out.print(" x ");
                        break;
                    case INPUT:
                        int answer = cell.getValue();
                        System.out.print(" " + (withAnswers ? answer : "_") + " ");
                        break;
                    case FILLED11:
                    case FILLED10:
                    case FILLED01:
                        int value = cell.getValue();
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
