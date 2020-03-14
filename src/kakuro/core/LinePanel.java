/*
 * SOURCES 
 * Examples on extending JPanel class and using paintComponent method // Examples for textfield formatting
 * http://programmedlessons.org/java5/Notes/chap36/ch36_10.html
 * https://docs.oracle.com/javase/tutorial/uiswing/components/formattedtextfield.html
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/JFormattedTextField.html
 */
package kakuro.core;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import javax.swing.JTextField;


/** 
 * Line panel class used to populate non-playable board cells: adds numbers and diagonal line 
 * It is also extending JPanel to use the graphics with paintComponent method overriding
 * 
 * @author Isabelle Charette
 * Date created: January 27th, 2020
*/
public class LinePanel extends JPanel {
    /**
     * LinePanel constructor to create the layout, textfield, and to align them when one number in cell
     * @param layout
     *  - the layout manager of our game
     * @param textField
     *  - the textfield view of our game
     * @param align
     *  - verifies if our textfield is aligned
     */
    public LinePanel(LayoutManager layout, JTextField textField, Boolean align) {
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

    /**
     * LinePanel constructor to create the layout and textfield when two numbers in cell
     * @param layout
     *  - the layout manager of our game
     * @param textField
     *  - the textfield view of our game
     * @param align
     *  - verifies if our textfield is aligned
     */
    public LinePanel(LayoutManager layout, JTextField textFieldLEFT, JTextField textFieldRIGHT) {
        super(layout);
        this.setBackground(Color.black);
 
        settingTxt(textFieldLEFT);
        settingTxt(textFieldRIGHT);
        
        textFieldLEFT.setHorizontalAlignment(JTextField.LEFT);
        textFieldRIGHT.setHorizontalAlignment(JTextField.RIGHT);
     
        this.add(textFieldLEFT, BorderLayout.SOUTH);
        this.add(textFieldRIGHT, BorderLayout.NORTH);
   
    }
    
    /**
     * Creates the background black and foregroudn white
     * 
     * @param txt
     *  - the JTextField reference
     */
    public void settingTxt(JTextField txt) {
        txt.setBackground(new Color(0,0,0,0));
        txt.setForeground(Color.white);
    }
    
    /**
     * paintComponent method draws the diagonal line in the black cells
     */
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
