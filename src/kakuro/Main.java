// @author Vsevolod Ivanov
// @author ...
// @brief Main instantiating the Kakuro game controller.

package kakuro;

public class Main
{
    public static void main(String[] args)
    {
        int columns = 10;
        int rows = 10;

        new GameController(columns, rows);
    }
}
