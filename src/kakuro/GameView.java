// @author Vsevolod Ivanov
// @author Isabelle Charette
// @brief Game view class which handles the Kakuro game interface.

package kakuro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
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

    //ui
    int gridSizeX;
    int gridSizeY;
    int frameSize = 60;
    
    //creating grid of cells
    JPanel panel;
    
    //creating window of the game
    JFrame frame = new JFrame("KAKURO");

    JTextField input[][];
    
    //CHRONO
    private  int hours=0;
    private int minutes=0;
    private int seconds=0;
    private int delay=1000;
    JLabel timerLabel;
    JButton pauseButton;
    JButton playButton;
    JButton submitButton;
    JButton newGameButton;
    JButton saveButton;
    JPanel mainPanel;
    ActionListener timer_listener;

    //    int row;
    //    int column;
    public GameView(final GameController controller)
    {
        this.controller = controller;
        //        row = controller.model.rows;
        //        column = controller.model.columns;
        gridSizeX =  controller.model.rows;
        gridSizeY = controller.model.columns;
        panel = new JPanel(new GridLayout(gridSizeX,gridSizeY));
        chronoSetUp();
        input = new JTextField[controller.model.rows][controller.model.columns];
    }

    public void printStartup()
    {
        System.out.println("welcome to kakuro game!");
        System.out.println("=> use numbers between 1-9 to fill the cells;");
    }

    public void chronoSetUp() {
        timerLabel = new JLabel("0:"+hours+":"+minutes+":"+seconds); 
        pauseButton = new JButton("Pause");
        playButton = new JButton("Play");
        submitButton = new JButton("Submit");
        newGameButton = new JButton("New Game");
        saveButton = new JButton("Save");
        mainPanel = new JPanel();

        mainPanel.add(playButton);
        mainPanel.add(pauseButton);
        mainPanel.add(submitButton);
        frame.getContentPane().add(timerLabel, BorderLayout.BEFORE_FIRST_LINE);
        frame.getContentPane().add(mainPanel, BorderLayout.AFTER_LAST_LINE);
    }

    public void timerButtonControl() {

        timer_listener= new ActionListener()
        {
            public void actionPerformed(ActionEvent e1)
            {   
                seconds++;
                if(seconds==60)
                {
                    seconds=0;
                    minutes++;
                }
                if(minutes==60)
                {
                    minutes=0;
                    hours++;
                }
                timerLabel.setText("0:"+hours+":"+minutes+":"+seconds);
            }
        };

        Timer time = new Timer(delay,timer_listener);
        time.start();

        /* With the use of an Action Listener to know if the user has clicked on the button, this part of the method will stop the timer. */    
        pauseButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                time.stop();
            }
        });
        /* With the use of an Action Listener to know if the user has clicked on the button, this part of the method will start the timer. */
        playButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                time.start();
            }
        });

        /* With the use of an Action Listener to know if the user has clicked on the button, this part of the loop method will restart the timer. */
        newGameButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                hours=0;
                minutes=0;
                seconds=0;
                timerLabel.setText("0:"+hours+":"+minutes+":"+seconds);
                time.start();
            }
        });

        /* With the use of an Action Listener to know if the user has clicked on the button, this part of the method will perform the validation. */
        submitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                time.stop();
                
                //tried adding action listening to each textfield cell but no success so added it here.***
                for(int i = 0; i < controller.model.rows; i++)
                {
                    for(int j = 0; j < controller.model.rows; j++)
                    {
                        //tracking the type of each cell
                        BoardCell cell = controller.model.board[i][j];
                        switch(cell.getType()) {
                            case INPUT:
                                cell.setFirstValue(Integer.parseInt(input[i][j].getText()));
                                int temp = cell.getFirstValue();
                                System.out.println(temp);
                                break;
                            default :
                                break;
                        }
                    }
                }
                controller.solveBoard();
                printSolveBoard();
            }
        });

        /* With the use of an Action Listener to know if the user has clicked on the button, this part of the method will save the current state of
         * the game.
         *  */
        saveButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // Call save method
            }
        });
    }

    //creates user interface of the game board 
    public void boardUI() {
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
        for(int i = 0; i < controller.model.rows; i++)
        {
            for(int j = 0; j < controller.model.rows; j++)
            {
                //                
                //                int row = i;
                //                int colum = j;
                JFormattedTextField textField = null;

                //tracking the type of each cell
                BoardCell cell = controller.model.board[i][j];
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
                        input[i][j] = textField;
                        break;

                    case FILLED01:
                        textField = new JFormattedTextField(cell.getSecondValue());
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
            }
        }
        int x = frameSize*gridSizeX;
        int y = frameSize*gridSizeY;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(x, y);
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
