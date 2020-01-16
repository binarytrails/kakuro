// @author Vsevolod Ivanov
// @author ...
// @brief Game controller class which handles the Kakuro game.

package kakuro;

public class GameController
{
	private GameView view;
	private GameModel model;

	public GameController(final int columns, final int rows)
	{
		this.model = new GameModel(columns, rows);
		this.view = new GameView();
	}

}
