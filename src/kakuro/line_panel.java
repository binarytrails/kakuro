// @author Isabelle Charette
// @author ...
// @brief class used to populate non-playable board cells: adds numbers and diagonal line
// class extending JPanel to use the graphics with paintComponent method overriding

package kakuro;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

public class line_panel extends JPanel {
    
    //constructor used if only one number in the cell
    public line_panel(LayoutManager layout, JTextField textField, Boolean align) {
        super(layout);
        this.setBackground(Color.black);
        settingTxt(textField);

        if(align) {
            textField.setHorizontalAlignment(JTextField.RIGHT);
            this.add(textField,BorderLayout.NORTH);
        }else {
            textField.setHorizontalAlignment(JTextField.LEFT);
            this.add(textField,BorderLayout.SOUTH);
        }
    }

    //constructor used if two numbers in the cell
    public line_panel(LayoutManager layout, JTextField textFieldLEFT, JTextField textFieldRIGHT) {
        super(layout);
        this.setBackground(Color.black);
 
        settingTxt(textFieldLEFT);
        settingTxt(textFieldRIGHT);
        
        textFieldLEFT.setHorizontalAlignment(JTextField.LEFT);
        textFieldRIGHT.setHorizontalAlignment(JTextField.RIGHT);
     
        this.add(textFieldLEFT, BorderLayout.SOUTH);
        this.add(textFieldRIGHT, BorderLayout.NORTH);
   
    }

    public void settingTxt(JTextField txt) {
        txt.setBackground(new Color(0,0,0,0));
        txt.setForeground(Color.white);
    }
    
    //method that draws the diagonal line in the black cells
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        //object 2 added to the panel of the cell on the board
        Graphics2D g2D = (Graphics2D) g; //casting to 2D graphics object to use stroke method
        g2D.setColor(Color.GRAY);
        int offset = 1;
        Line2D diagonal = new Line2D.Float(offset, offset, this.getWidth()-offset, this.getHeight()-offset);
        g2D.setStroke(new BasicStroke(2));
        g2D.draw(diagonal);
    }
}
