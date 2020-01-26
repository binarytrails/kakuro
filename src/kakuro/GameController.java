// @author Vsevolod Ivanov
// @author ...
// @brief Game controller class which handles the Kakuro game.

package kakuro;

public class GameController
{
    public GameView view;
    public GameModel model;

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
            view.printGetInputNumber();
            view.printBoard(false);
        }
    }
}
