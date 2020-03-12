package kakuro.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.NumberFormatter;

import kakuro.core.BoardCell;
import kakuro.core.LinePanel;

public class BoardView {
    int gridSizeX;
    int gridSizeY;
    int rows;
    int columns;
    
    public JTextField[][] cells; // TODO remove and use listeners to interact directly with model.board
    public JPanel boardPanel; //The reference to the current displaying pane (board UI)
    
    //Number formatter for displaying
    NumberFormat numberFormat = NumberFormat.getInstance();
    NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
    
    public BoardView(int rows, int columns, int gridSizeX, int gridSizeY) {
        cells = new JTextField[rows][columns];
        this.gridSizeX = gridSizeX;
        this.gridSizeY = gridSizeY;
        this.rows = rows;
        this.columns = columns;
        
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setMinimum(1);
        numberFormatter.setMaximum(9);
    }
    
    public JPanel getBoardUI(BoardCell[][] board) {
        //creating grid of cells
        JPanel panel = new JPanel(new GridLayout(gridSizeX,gridSizeY));
        //creating window of the game
     
        //this allows temporary invalid input, particularly to be able to delete and try again
        //if invalid input, when clicking onto another cell, the input will be deleted
        numberFormatter.setAllowsInvalid(true);

        //identifies type of each cell and populates it
        //input or non-playable
        for(int row = 0; row < rows; row++)
        {
            for(int column = 0; column < columns; column++)
            {
                JFormattedTextField textField = null;

                //tracking the type of each cell
                BoardCell cell = board[row][column];
                //adding extra panel that will overlay the cells that are non-playable with game level numbers and diagonal line
                JPanel diagonalPanel = null;

                //according to type of cell, populate
                switch (cell.getType())
                {
                    case EMPTY:
                        textField = new JFormattedTextField(numberFormatter);
                        settingTextField(textField);
                        textField.setBorder(new LineBorder(Color.GRAY,1));
                        panel.add(textField);
                        break;

                    case INPUT:
                        textField = new JFormattedTextField(numberFormatter);
                        textField.setHorizontalAlignment(JTextField.CENTER);
                        textField.setBorder(new LineBorder(Color.GRAY,1));
                        //When you load a game, there is some data exists. We have to check to make sure we are displaying the saved input
                        if(cell.getFirstValue()!=-1)
                            textField.setValue(cell.getFirstValue());
                        panel.add(textField);
                        break;

                    case FILLED01:
                        textField = new JFormattedTextField(cell.getSecondValue());
                        settingTextField(textField);
                        //adding diagonal line in board cell
                        diagonalPanel = new LinePanel(new BorderLayout(), textField, true);
                        diagonalPanel.setBorder(new LineBorder(Color.GRAY,1));
                        panel.add(diagonalPanel);
                        break;

                    case FILLED10:
                        textField = new JFormattedTextField(cell.getFirstValue());
                        settingTextField(textField);
                        //adding diagonal line in board cell
                        diagonalPanel = new LinePanel(new BorderLayout(), textField, false);
                        diagonalPanel.setBorder(new LineBorder(Color.GRAY,1));
                        panel.add(diagonalPanel);
                        break;

                    case FILLED11:
                        textField = new JFormattedTextField(cell.getFirstValue());
                        JFormattedTextField textFieldRIGHT = new JFormattedTextField(cell.getSecondValue());
                        settingTextField(textField);
                        settingTextField(textFieldRIGHT); 
                        //using constructor that expects two text values to place in board cell
                        diagonalPanel = new LinePanel(new BorderLayout(), textField, textFieldRIGHT);
                        diagonalPanel.setBorder(new LineBorder(Color.GRAY,1));
                        panel.add(diagonalPanel);
                        break;

                    default:
                        break;
                }
                //placing textfield value in input array to track user input
                this.cells[row][column] = textField;
            }
        }
        
        boardPanel = panel;
        
        return panel;
    }
    
    public void settingTextField(JTextField txt) {
        txt.setBackground(Color.black);
        txt.setForeground(Color.white);
        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txt.setEditable(false);
    }
    
    //Number formatter methods
    public int getMinNumberValid() {
        return (int) numberFormatter.getMinimum();
    }
    
    public Object getNumberFormatterClassType() {
        return numberFormatter.getClass();
    }
    
    public int getMaxNumberValid() {
        return (int) numberFormatter.getMaximum();
    }
}
