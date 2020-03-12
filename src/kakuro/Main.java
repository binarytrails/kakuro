// @author Vsevolod Ivanov
// @author ...
// @brief Main instantiating the Kakuro game controller.

package kakuro;

import kakuro.controllers.AppController;

public class Main
{
    public static void main(String[] args)
    {
        int columns = 10;
        int rows = 10;

        AppController game = new AppController(columns, rows, true/*GUI*/);
        game.loopGame();
    }
}
