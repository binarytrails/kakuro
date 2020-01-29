// @author Vsevolod Ivanov
// @author Isabelle Charette
// @brief Game view class which handles the Kakuro game interface.

package kakuro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.NumberFormatter;

/*
 * SOURCES 
 * Examples on extending JPanel class and using paintComponent method:
 * http://programmedlessons.org/java5/Notes/chap36/ch36_10.html
 * https://stackoverflow.com/questions/31519857/draw-triangle-on-top-of-rectangle-in-java
 * https://stackoverflow.com/questions/10767265/drawing-a-line-on-a-jframe
 * 
 * Examples for textfield formatting
 * https://stackoverflow.com/questions/7582238/changing-border-color-of-awt-textfield
 * https://www.programcreek.com/java-api-examples/?class=javax.swing.JTextField&method=setBorder
 * https://examples.javacodegeeks.com/desktop-java/swing/java-swing-layout-example/
 * https://gist.github.com/gysel/4074617
 * https://coderanch.com/t/391046/java/textfield-accepts-numbers
 * https://docs.oracle.com/javase/tutorial/uiswing/components/formattedtextfield.html
 * https://stackoverflow.com/questions/11093326/restricting-jtextfield-input-to-integers
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/JFormattedTextField.html
*/

import kakuro.GameController.UserActions;

public class GameView
{
    private final GameController controller;
    private Scanner inputReader = new Scanner(System.in);

    //stores player input of board
    public JTextField[][] input;

    public GameView(final GameController controller)
    {
        this.controller = controller;
        input = new JTextField[controller.model.rows][controller.model.columns];
    }

    public void printStartup()
    {
        System.out.println("welcome to kakuro game!");
        System.out.println("=> use numbers between 1-9 to fill the cells;");
    }

    public void loadInputInModel()
    {
        for(int row = 0; row < controller.model.columns; row++)
        {
            for(int column = 0; column < controller.model.rows; column++)
            {
                BoardCell cell = controller.model.board[row][column];
                String value = input[row][column].getText();
                if(!value.isEmpty()) {
                    switch (cell.getType())
                    {
                        case INPUT:
                            controller.model.board[row][column].setFirstValue(Integer.parseInt(value));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    //creates user interface of the game board 
    public void board_ui() {
        int gridSizeX = controller.model.rows;
        int gridSizeY = controller.model.columns;
        int frameSize = 60;
        //creating grid of cells
        JPanel panel = new JPanel(new GridLayout(gridSizeX,gridSizeY));
        //creating window of the game
        JFrame frame = new JFrame("KAKURO");

        //Number formatting: 
        //https://docs.oracle.com/javase/tutorial/uiswing/components/formattedtextfield.html
        //https://stackoverflow.com/questions/11093326/restricting-jtextfield-input-to-integers
        //https://docs.oracle.com/javase/8/docs/api/javax/swing/JFormattedTextField.html
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(1);
        formatter.setMaximum(9);
        //this allows temporary invalid input, particularly to be able to delete and try again
        //if invalid input, when clicking onto another cell, the input will be deleted
        formatter.setAllowsInvalid(true);

        //identifies type of each cell and populates it
        //input or non-playable
        for(int row = 0; row < controller.model.columns; row++)
        {
            for(int column = 0; column < controller.model.rows; column++)
            {
                JFormattedTextField textField = null;

                //tracking the type of each cell
                BoardCell cell = controller.model.board[row][column];
                //adding extra panel that will overlay the cells that are non-playable with game level numbers and diagonal line
                JPanel diagonalPanel = null;

                //according to type of cell, populate
                switch (cell.getType())
                {
                    case EMPTY:
                        textField = new JFormattedTextField(formatter);
                        settingTextField(textField);
                        textField.setBorder(new LineBorder(Color.GRAY,1));
                        panel.add(textField);
                        break;

                    case INPUT:
                        textField = new JFormattedTextField(formatter);
                        textField.setHorizontalAlignment(JTextField.CENTER);
                        textField.setBorder(new LineBorder(Color.GRAY,1));
                        panel.add(textField);
                        break;

                    case FILLED01:
                        textField = new JFormattedTextField(cell.getFirstValue());
                        settingTextField(textField);
                        //adding diagonal line in board cell
                        diagonalPanel = new line_panel(new BorderLayout(), textField, true);
                        diagonalPanel.setBorder(new LineBorder(Color.GRAY,1));
                        panel.add(diagonalPanel);
                        break;

                    case FILLED10:
                        textField = new JFormattedTextField(cell.getFirstValue());
                        settingTextField(textField);
                        //adding diagonal line in board cell
                        diagonalPanel = new line_panel(new BorderLayout(), textField, false);
                        diagonalPanel.setBorder(new LineBorder(Color.GRAY,1));
                        panel.add(diagonalPanel);
                        break;

                    case FILLED11:
                        textField = new JFormattedTextField(cell.getFirstValue());
                        JFormattedTextField textFieldRIGHT = new JFormattedTextField(cell.getSecondValue());
                        settingTextField(textField);
                        settingTextField(textFieldRIGHT); 
                        //using constructor that expects two text values to place in board cell
                        diagonalPanel = new line_panel(new BorderLayout(), textField, textFieldRIGHT);
                        diagonalPanel.setBorder(new LineBorder(Color.GRAY,1));
                        panel.add(diagonalPanel);
                        break;

                    default:
                        break;
                }

                //placing textfield value in input array to track user input
                input[row][column] = textField;
            }
        }
        Chrono timer = new Chrono(frame);
        timer.timing();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameSize*gridSizeX, frameSize*gridSizeY);
        frame.setResizable(false);
        frame.getContentPane().add(panel);
        frame.setVisible(true);

    }

    public void settingTextField(JTextField txt) {
        txt.setBackground(Color.black);
        txt.setForeground(Color.white);
        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txt.setEditable(false);
    }
    public void printBoard(Boolean showAnswerValues)
    {
        System.out.println("board:");
        for(int row = 0; row < controller.model.columns; row++)
        {
            for(int column = 0; column < controller.model.rows; column++)
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

    public void printGetInputNumber()
    {
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
