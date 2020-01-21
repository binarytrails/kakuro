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
        model.generateBoard();
        this.view = new GameView(this);
        view.printStartup();
        view.printBoard();
    }
}
