package kakuro.views;

import java.util.Scanner;

import kakuro.controllers.GameController;
import kakuro.controllers.GameController.UserActions;
import kakuro.core.*;

public class GameConsole {
    private GameController controller;
    private Scanner inputReader = new Scanner(System.in);
    
    public GameConsole(final GameController controller) {
        this.controller = controller;
    }
    
    public void printStartup()
    {
        System.out.println("welcome to kakuro game!");
        System.out.println("=> use numbers between 1-9 to fill the cells;");
    }
    
    public void printBoard(Boolean showAnswerValues)
    {
        System.out.println("board:");
        for(int row = 0; row < controller.model.rows; row++)
        {
            for(int column = 0; column < controller.model.columns; column++)
            {
                int value = 0;
                int value2 = 0;
                BoardCell cell = controller.model.board[row][column];
                switch (cell.getType())
                {
                    case EMPTY:
                        System.out.print("  x  ");
                        break;
                    case INPUT:
                        System.out.print("  " +
                                (showAnswerValues ? (cell.getSecondValue() != -1 ? cell.getSecondValue() : "_") :
                                    (cell.getFirstValue() != -1 ? cell.getFirstValue() : "_")) +
                                "  ");
                        break;
                    case FILLED11:
                        value = cell.getFirstValue();
                        value2 = cell.getSecondValue();
                        System.out.print(" " + value + "\\" + value2);
                        break;
                    case FILLED10:
                        value = cell.getFirstValue();
                        System.out.print(" " + value + "\\");
                        break;
                    case FILLED01:
                        value2 = cell.getSecondValue();
                        System.out.print(" \\" + value2);
                        break;
                    default:
                        break;
                }
                if (value > 9 || value2 > 9)
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    public UserActions printGetUserAction()
    {
        System.out.print("\nList of actions i=input s=solve a=answers\nChoose an action: ");
        switch (inputReader.next())
        {
            case "i":
                return UserActions.INPUT;

            case "s":
                return UserActions.SOLVE;
            case "a":
                return UserActions.ANSWERS;
            default:
                return UserActions.UNKNOWN;
        }
    }

    public void printGetInputNumber(){
        int row = -1;
        int column = -1;
        int number = -1;
        while (true)
        {
            while (row < 1 || row > 10)
            {
                System.out.print("row: ");
                try {
                    row = inputReader.nextInt();
                    if (row < 1 || row > 10)
                        System.out.println("error: out of bounds");
                }
                catch (java.util.InputMismatchException e)
                {
                    System.out.println("error: invalid digit");
                    inputReader.nextLine();
                }
            }
            while (column < 1 || column > 10)
            {
                System.out.print("column: ");
                try {
                    column = inputReader.nextInt();
                    if (column < 1 || column > 10)
                        System.out.println("error: out of bounds");
                }
                catch (java.util.InputMismatchException e)
                {
                    System.out.println("error: invalid digit");
                    inputReader.nextLine();
                }
            }
            if (controller.model.board[row-1][column-1].getType() == BoardCell.CellType.INPUT)
                break;
            else {
                System.out.println("error: not an input cell");
                row = -1;
                column = -1;
            }
        }
        while (number < 1 || number > 9)
        {
            try {
                System.out.print("number: ");
                number = inputReader.nextInt();
                if (number < 1 || number > 9)
                    System.out.println("error: out of bounds");
            }
            catch (java.util.InputMismatchException e)
            {
                System.out.println("error: invalid digit");
                inputReader.nextLine();
            }
        }
        controller.model.board[row-1][column-1].setFirstValue(number);
    }

    public void printSolveBoard()
    {
        Boolean success = controller.solveBoard();
        if (success)
            System.out.println("The board is solved!");
        else
            System.out.println("The solution is incorrect.");
    }
}
