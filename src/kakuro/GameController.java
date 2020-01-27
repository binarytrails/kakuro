// @author Vsevolod Ivanov
// @author ...
// @brief Game controller class which handles the Kakuro game.

package kakuro;

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
        return(model.checkBoard());
    }
}
