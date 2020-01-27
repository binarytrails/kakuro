// @author Vsevolod Ivanov
// @author ...
// @brief Game controller class which handles the Kakuro game.

package kakuro;

import java.util.Scanner;

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
        model.generateBoard();
        this.view = new GameView(this);
        view.printStartup();
        view.printBoard(false/*show answer values*/);
        view.board_ui();
        System.out.print("check input? 1 or 0: ");
        Scanner inputReader = new Scanner(System.in);
            int answer = inputReader.nextInt();
        if(answer==1) {
            view.check();
        }
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
