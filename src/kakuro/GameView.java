// @author Vsevolod Ivanov
// @author ...
// @brief Game view class which handles the Kakuro game interface.

package kakuro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class GameView
{
    private final GameController controller;

    public GameView(final GameController controller)
    {
        this.controller = controller;

    }

    public void printStartup()
    {
        System.out.println("welcome to kakuro game!");
        System.out.println("=> use numbers between 1-9 to fill the cells;");
    }

    public void board_ui() {
        JPanel panel = new JPanel(new GridLayout(10,10));
        JFrame frame = new JFrame("KAKURO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        //frame.pack();
        String st1;
        String st2;

        for(int column = 0; column < controller.model.columns; column++)
        {
            for(int row = 0; row < controller.model.rows; row++)
            {
                JTextField textField = null;

                switch (controller.model.board[column][row])
                {

                    case EMPTY:
                        textField = new JTextField();


                        break;
                    case FILLED01:
                        //                        textField = new JTextField("0/1");
                        st2 = "2";
                        textField = new JTextField("/" + st2);
                        textField.setBackground(Color.black);
                        textField.setForeground(Color.white);
                        textField.setEditable(false);

                        break;
                    case FILLED10:
                        st1 = "1";
                        //                        textField = new JTextField("1/0");
                        textField = new JTextField(st1 + "/");
                        textField.setBackground(Color.black);
                        textField.setForeground(Color.white);
                        textField.setEditable(false);
                        break;

                    case FILLED11:
                        st1 = "1";
                        st2 = "2";
                        textField = new JTextField(st1 + "/" + st2);
                        //                        textField = new JTextField("1/1");
                        textField.setBackground(Color.black);
                        textField.setForeground(Color.white);
                        textField.setEditable(false);
                        break;
                    default:
                        break;
                }
                panel.add(textField);
                //                textField.setBorder(new LineBorder(Color.black,1));
            }
        }
        frame.setResizable(false);
        frame.getContentPane().add(panel);
        frame.setVisible(true);

    }
    public void printBoard()
    {
        System.out.println("board:");
        for(int column = 0; column < controller.model.columns; column++)
        {
            for(int row = 0; row < controller.model.rows; row++)
            {
                switch (controller.model.board[column][row])
                {
                    case EMPTY:
                        System.out.print("x");
                        break;
                    default:
                        break;
                }
            }
            System.out.println();
        }
    }
}
