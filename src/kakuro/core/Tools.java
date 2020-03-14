package kakuro.core;

import java.util.Enumeration;
import java.util.Random;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * Utility class
 *
 * @author Vsevolod Ivanov
 * Date written: January 26th, 2020
 */
public class Tools
{
    public static int randomInt(int min, int max)
    {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static void arrayToNodes(DefaultMutableTreeNode parentNode, int[] numbers)
    {
        for(int number: numbers)
        {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(number);
            parentNode.add(node);
        }
    }
    
    public static int[] childrenToArray(TreeNode node)
    {
        int index = 0;
        int[] array = new int[node.getChildCount()];
        for(Enumeration<? extends TreeNode> tree = node.children(); tree.hasMoreElements();)
        {
            int number = Integer.parseInt(tree.nextElement().toString());
            array[index] = number;
            index++;
        }
        return array;
    }
}
