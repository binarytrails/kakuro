// @author Vsevolod Ivanov
// @author ...
// @brief Game model class which handles the Kakuro game storage.

package kakuro;

import java.util.Enumeration;
import java.util.Random;

import javax.swing.tree.TreeNode;

public class GameModel
{

    public final int columns;
    public final int rows;
    public BoardCell[][] board;
    private static UniquePartitions partitions;

    public GameModel(final int columns, final int rows)
    {
        this.columns = columns;
        this.rows = rows;
        partitions = new UniquePartitions();
    }

    public void initBoard()
    {
        board = new BoardCell[this.columns][this.rows];

        for(int row = 0; row < this.rows; row++)
        {
            for(int column = 0; column < this.columns; column++)
            {
                board[row][column] = new BoardCell(BoardCell.CellType.EMPTY);
            }
        }
    }

    public void generateBoard()
    {
        // most upper line
        TreeNode eightBlocksTree = partitions.root.getChildAt(6 /* start at two blocks tree */);
        // chose a random sum in children
        TreeNode randEightBlockTree = eightBlocksTree.getChildAt(randomInt(0,7));
        int randEightBlockSum = Integer.parseInt(randEightBlockTree.toString());
        board[1][0] = new BoardCell(BoardCell.CellType.FILLED01, randEightBlockSum);
        // mark & fill the input cells
        int[] array = childrenToArray(randEightBlockTree);
        for(int column = 1; column <= 8; column++)
        {
            board[1][column] = new BoardCell(BoardCell.CellType.INPUT, -1, array[column-1]);
        }
        // most lower line
        eightBlocksTree = partitions.root.getChildAt(6 /* start at two blocks tree */);
        // chose a random sum in children
        randEightBlockTree = eightBlocksTree.getChildAt(randomInt(0,7));
        randEightBlockSum = Integer.parseInt(randEightBlockTree.toString());
        board[8][9] = new BoardCell(BoardCell.CellType.FILLED10, randEightBlockSum);
        // mark & fill the input cells
        array = childrenToArray(randEightBlockTree);
        for(int column = 8; column >=1; column--)
        {
            board[8][column] = new BoardCell(BoardCell.CellType.INPUT, -1, array[8-column]);
        }
        // most 1st column rows
        eightBlocksTree = partitions.root.getChildAt(6 /* start at two blocks tree */);
        // chose a random sum in children
        randEightBlockTree = eightBlocksTree.getChildAt(randomInt(0,7));
        randEightBlockSum = Integer.parseInt(randEightBlockTree.toString());
        // find sum that starts with 1 and ends with 8
        for(Enumeration<? extends TreeNode> tree = eightBlocksTree.children(); tree.hasMoreElements();)
        {
            TreeNode candidateTree = tree.nextElement();
            int[] candidatesArray = childrenToArray(candidateTree);
            if (candidatesArray[0] == board[1][1].getAnswerValue() && candidatesArray[7] == board[1][7].getAnswerValue())
            {
                // put sum number
                int sum = Integer.parseInt(candidateTree.toString());
                board[0][1] = new BoardCell(BoardCell.CellType.FILLED10, sum);
                // mark & fill the input cells
                for(int row = 1; row < 8; row++)
                {
                    board[row][1] = new BoardCell(BoardCell.CellType.INPUT, -1, candidatesArray[row]);
                }
                break;
            }
            else if (candidatesArray[0] == board[8][8].getAnswerValue() && candidatesArray[7] == board[1][8].getAnswerValue())
            {
                // put sum number
                int sum = Integer.parseInt(candidateTree.toString());
                board[0][1] = new BoardCell(BoardCell.CellType.FILLED10, sum);
                // mark & fill the input cells
                for(int row = 1; row < 8; row++)
                {
                    board[row][8] = new BoardCell(BoardCell.CellType.INPUT, -1, candidatesArray[row]);
                }
                break;
            }
        }
    }

    private int randomInt(int min, int max)
    {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    private int[] childrenToArray(TreeNode node)
    {
        int index = 0;
        int[] array = new int[node.getChildCount()];
        for(Enumeration<? extends TreeNode> tree = node.children(); tree.hasMoreElements();)
        {
            int number = Integer.parseInt(tree.nextElement().toString());
            //System.out.print(" " + number + " ");
            array[index] = number;
            index++;
        }
        return array;
    }
}
