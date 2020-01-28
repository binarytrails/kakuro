// @author Vsevolod Ivanov
// @author Isabelle Charette
// @brief Game view class which handles the Kakuro game interface.

package kakuro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

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
 
 * Examples on Frames, panels, labels and grids
 * https://www.guru99.com/java-swing-gui.html#8
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/spring.html
 * 
 */
public class GameView
{
    private final GameController controller;
    Scanner inputReader = new Scanner(System.in);

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

    //takes player input of game board and outputs to console
    public void check() {

        for(int row = 0; row < controller.model.columns; row++)
        {
            for(int column = 0; column < controller.model.rows; column++)
            {
                BoardCell cell = controller.model.board[row][column];
                if(!input[row][column].getText().isEmpty()) {
                    switch (cell.getType())
                    {
                        case INPUT:
                            System.out.println(input[row][column].getText());
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

        //identifies type of each cell and populates it
        //input or non-playable
        for(int row = 0; row < controller.model.columns; row++)
        {
            for(int column = 0; column < controller.model.rows; column++)
            {
                //object to be placed in each panel (=cell) of the board
                JTextField textField = null;
                //tracking the type of each cell
                BoardCell cell = controller.model.board[row][column];
                //adding extra panel that will overlay the cells that are non-playable with game level numbers and diagonal line
                JPanel diagonalPanel = null;
                
                //according to type of cell, populate
                switch (cell.getType())
                {
                    case EMPTY:
                        textField = new JTextField();
                        settingTextField(textField);
                        textField.setBorder(new LineBorder(Color.GRAY,1));
                        panel.add(textField);
                        break;
                        
                    case INPUT:
                        textField = new JTextField();
                        textField.setHorizontalAlignment(JTextField.CENTER);
                        textField.setBorder(new LineBorder(Color.GRAY,1));
                        panel.add(textField);
                        break;
                        
                    case FILLED01:
                        textField = new JTextField(cell.getFirstValue()+"");
                        settingTextField(textField);
                        //adding diagonal line in board cell
                        diagonalPanel = new line_panel(new BorderLayout(), textField, true);
                        diagonalPanel.setBorder(new LineBorder(Color.GRAY,1));
                        panel.add(diagonalPanel);
                        break;
                        
                    case FILLED10:
                        textField = new JTextField(cell.getFirstValue()+"");
                        settingTextField(textField);
                        //adding diagonal line in board cell
                        diagonalPanel = new line_panel(new BorderLayout(), textField, false);
                        diagonalPanel.setBorder(new LineBorder(Color.GRAY,1));
                        panel.add(diagonalPanel);
                        break;
                        
                    case FILLED11:
                        textField = new JTextField(cell.getFirstValue() +"");
                        JTextField textFieldRIGHT = new JTextField(cell.getSecondValue()+"");
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

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameSize*gridSizeX, frameSize*gridSizeY);
        frame.setResizable(false);
        frame.getContentPane().add(panel);
        frame.setVisible(true);

    }
    
    public void settingTextField(JTextField txt) {
        txt.setBackground(Color.black);
        txt.setForeground(Color.white);
        txt.setEditable(false);
    }
    public void printBoard(Boolean showAnswerValues)
    {
        System.out.println("board:");
        for(int row = 0; row < controller.model.columns; row++)
        {
            for(int column = 0; column < controller.model.rows; column++)
            {
                BoardCell cell = controller.model.board[row][column];
                switch (cell.getType())
                {
                    case EMPTY:
                        System.out.print(" x ");
                        break;
                    case INPUT:
                        System.out.print(" " +
                                (showAnswerValues ? (cell.getSecondValue() != -1 ? cell.getSecondValue() : "_") :
                                    (cell.getFirstValue() != -1 ? cell.getFirstValue() : "_")) + " ");
                        break;
                    case FILLED11:
                    case FILLED10:
                    case FILLED01:
                        int value = cell.getFirstValue();
                        if (value > 9)
                            System.out.print(" ");
                        System.out.print(value);
                        break;
                    default:
                        break;
                }
            }
            System.out.println();
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
}
