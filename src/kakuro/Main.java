// @author Vsevolod Ivanov
// @author ...
// @brief Main instantiating the Kakuro game controller.

package kakuro;

import kakuro.controllers.GameController;

public class Main
{
    public static void main(String[] args)
    {
        int columns = 10;
        int rows = 10;

        GameController game = new GameController(columns, rows, true/*GUI*/);
        game.loopGame();
    }
}
