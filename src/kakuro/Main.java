package kakuro;

import kakuro.controllers.GameController;

/**
 * Main is the main class, where we instantiate the Kakuro game controller.
 * We pass in a 10x10 rows and columns to the controller
 *
 * @author Vsevolod Ivanov
 * @author Hoang Thuan Pham
 * Date written: January 15th, 2020
 */
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
