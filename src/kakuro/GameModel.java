// @author Vsevolod Ivanov
// @author ...
// @brief Game model class which handles the Kakuro game storage.

package kakuro;

import java.util.Enumeration;

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
        board = new BoardCell[this.rows][this.columns];

        for(int row = 0; row < this.rows; row++)
        {
            for(int column = 0; column < this.columns; column++)
            {
                board[row][column] = new BoardCell(BoardCell.CellType.EMPTY);
            }
        }
    }

    public void generateBoard10x10()
    {
        // second row
        TreeNode eightBlocksTree = partitions.root.getChildAt(6 /* start at two blocks tree */);
        // chose a random sum in children
        TreeNode randEightBlockTree = eightBlocksTree.getChildAt(Tools.randomInt(0,7));
        int randEightBlockSum = Integer.parseInt(randEightBlockTree.toString());
        board[1][0] = new BoardCell(BoardCell.CellType.FILLED01, -1, randEightBlockSum);
        // mark & fill the input cells
        int[] array = Tools.childrenToArray(randEightBlockTree);
        for(int column = 1; column <= 8; column++)
        {
            board[1][column] = new BoardCell(BoardCell.CellType.INPUT, -1, array[column-1]);
        }
        // row - 1
        eightBlocksTree = partitions.root.getChildAt(6 /* start at two blocks tree */);
        // chose a random sum in children
        randEightBlockTree = eightBlocksTree.getChildAt(Tools.randomInt(1,7));
        randEightBlockSum = Integer.parseInt(randEightBlockTree.toString());
        board[8][0] = new BoardCell(BoardCell.CellType.FILLED01, -1, randEightBlockSum);
        // mark & fill the input cells
        array = Tools.childrenToArray(randEightBlockTree);
        for(int column = 8; column >=1; column--)
        {
            board[8][column] = new BoardCell(BoardCell.CellType.INPUT, -1, array[8-column]);
        }
        // second row
        eightBlocksTree = partitions.root.getChildAt(6 /* start at two blocks tree */);
        // chose a random sum in children
        randEightBlockTree = eightBlocksTree.getChildAt(Tools.randomInt(0,7));
        randEightBlockSum = Integer.parseInt(randEightBlockTree.toString());
        // find sum for column that connect first row to row - 1
        for(Enumeration<? extends TreeNode> tree = eightBlocksTree.children(); tree.hasMoreElements();)
        {
            TreeNode candidateTree = tree.nextElement();
            int[] candidatesArray = Tools.childrenToArray(candidateTree);
            // second column
            if (candidatesArray[0] == board[1][1].getSecondValue() && candidatesArray[7] == board[1][8].getSecondValue())
            {
                // put sum number
                int sum = Integer.parseInt(candidateTree.toString());
                board[0][1] = new BoardCell(BoardCell.CellType.FILLED10, sum);
                // mark & fill the input cells
                for(int row = 1; row < 8; row++)
                {
                    board[row+1][1] = new BoardCell(BoardCell.CellType.INPUT, -1, candidatesArray[row]);
                }
                break;
            }
        }
    }
}
