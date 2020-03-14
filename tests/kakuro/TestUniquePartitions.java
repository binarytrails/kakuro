// @author Vsevolod Ivanov
// @brief Test for the UniquePartitions class

package kakuro;

import static org.junit.Assert.*;

import java.util.Enumeration;

import javax.swing.tree.TreeNode;

import org.junit.BeforeClass;
import org.junit.Test;

//The TestUniquePartitions class will check that the layout and the division of all parts are consistent
public class TestUniquePartitions
{
    private static UniquePartitions partitions;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{
        partitions = new UniquePartitions();
    }

    @Test
    public void testHasAllParts(){
        // Arrange
        int part;
        int index = 0;
        int[] parts = {2,3,4,5,6,7,8,9};
        // Act
        for(Enumeration<TreeNode> partNode = partitions.root.children(); partNode.hasMoreElements();)
        {
            part = Integer.parseInt(partNode.nextElement().toString());
            // Assert
            assertEquals(part, parts[index]);
            index++;
        }
    }

    @Test
    public void testNumberEqualsPartitionSum(){
        // Act
        for(Enumeration<TreeNode> partNode = partitions.root.children(); partNode.hasMoreElements();)
        {
            TreeNode partTree = partNode.nextElement();
            System.out.println(partTree);
            for(Enumeration<? extends TreeNode> numberNode = partTree.children(); numberNode.hasMoreElements();)
            {
                TreeNode numberTree = numberNode.nextElement();
                int number = Integer.parseInt(numberTree.toString());
                System.out.println(" " + number);
                System.out.print("  ");
                int partitionSum = 0;
                for(Enumeration<? extends TreeNode> partitionNode = numberTree.children(); partitionNode.hasMoreElements();)
                {
                    TreeNode partitionTree = partitionNode.nextElement();
                    int partition = Integer.parseInt(partitionTree.toString());
                    partitionSum += partition;
                    System.out.print(partition + (partitionNode.hasMoreElements() ? "+" : ""));
                }
                System.out.println();
                System.out.println("  sum=" + partitionSum);
                // Assert
                assertEquals(number, partitionSum);
            }
        }
    }
}
