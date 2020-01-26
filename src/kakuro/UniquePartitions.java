// @author Vsevolod Ivanov
// @author ...

package kakuro;

import javax.swing.tree.DefaultMutableTreeNode;

public class UniquePartitions
{
    DefaultMutableTreeNode root;
    
    UniquePartitions()
    {
        this.root = new DefaultMutableTreeNode(0);
        fillCombinations(this.root);
    }
    
    public DefaultMutableTreeNode getTreeRoot()
    {
        return this.root;
    }

    private void arrayToNodes(DefaultMutableTreeNode parentNode, int[] numbers)
    {
        for(int number: numbers)
        {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(number);
            parentNode.add(node);
        }
    }
    
    private void fillCombinations(DefaultMutableTreeNode tree)
    {
        DefaultMutableTreeNode part;
        DefaultMutableTreeNode number;
        
        part = new DefaultMutableTreeNode(2);
            number = new DefaultMutableTreeNode(3);
                arrayToNodes(number, new int[]{1,2});
            part.add(number);
            number = new DefaultMutableTreeNode(4);
                arrayToNodes(number, new int[]{1,3});
            part.add(number);
            number = new DefaultMutableTreeNode(16);
                arrayToNodes(number, new int[]{7,9});
            part.add(number);
            number = new DefaultMutableTreeNode(17);
                arrayToNodes(number, new int[]{8,9});
            part.add(number);
        this.root.add(part);
        
        part = new DefaultMutableTreeNode(3);
            number = new DefaultMutableTreeNode(6);
                arrayToNodes(number, new int[]{1,2,3});
            part.add(number);
            number = new DefaultMutableTreeNode(7);
                arrayToNodes(number, new int[]{1,2,4});
            part.add(number);
            number = new DefaultMutableTreeNode(23);
                arrayToNodes(number, new int[]{6,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(24);
                arrayToNodes(number, new int[]{7, 8, 9});
            part.add(number);
        this.root.add(part);
        
        part = new DefaultMutableTreeNode(4);
            number = new DefaultMutableTreeNode(10);
                arrayToNodes(number, new int[]{1,2,3,4});
            part.add(number);
            number = new DefaultMutableTreeNode(11);
                arrayToNodes(number, new int[]{1,2,3,5});
            part.add(number);
            number = new DefaultMutableTreeNode(29);
                arrayToNodes(number, new int[]{5,7,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(30);
                arrayToNodes(number, new int[]{6,7,8,9});
            part.add(number);
        this.root.add(part);
             
        part = new DefaultMutableTreeNode(5);
            number = new DefaultMutableTreeNode(21);
                arrayToNodes(number, new int[]{1,2,3,4,5,6});
            part.add(number);
            number = new DefaultMutableTreeNode(22);
                arrayToNodes(number, new int[]{1,2,3,4,5,7});
            part.add(number);
            number = new DefaultMutableTreeNode(38);
                arrayToNodes(number, new int[]{3,5,6,7,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(39);
                arrayToNodes(number, new int[]{4,5,6,7,8,9});
            part.add(number);
        this.root.add(part);
        
        part = new DefaultMutableTreeNode(6);
            number = new DefaultMutableTreeNode(28);
                arrayToNodes(number, new int[]{1,2,3,4,5,6,7});
            part.add(number);
            number = new DefaultMutableTreeNode(29);
                arrayToNodes(number, new int[]{1,2,3,4,5,6,8});
            part.add(number);
            number = new DefaultMutableTreeNode(41);
                arrayToNodes(number, new int[]{2,4,5,6,7,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(42);
                arrayToNodes(number, new int[]{3,4,5,6,7,8,9});
            part.add(number);
        this.root.add(part);
        
        part = new DefaultMutableTreeNode(7);
            number = new DefaultMutableTreeNode(28);
                arrayToNodes(number, new int[]{1,2,3,4,5,6,7});
            part.add(number);
            number = new DefaultMutableTreeNode(29);
                arrayToNodes(number, new int[]{1,2,3,4,5,6,8});
            part.add(number);
            number = new DefaultMutableTreeNode(41);
                arrayToNodes(number, new int[]{2,4,5,6,7,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(42);
                arrayToNodes(number, new int[]{3,4,5,6,7,8,9});
            part.add(number);
        this.root.add(part);
        
        part = new DefaultMutableTreeNode(8);
            number = new DefaultMutableTreeNode(36);
                arrayToNodes(number, new int[]{1,2,3,4,5,6,7,8});
            part.add(number);
            number = new DefaultMutableTreeNode(37);
                arrayToNodes(number, new int[]{1,2,3,4,5,6,7,9});
            part.add(number);
            number = new DefaultMutableTreeNode(38);
                arrayToNodes(number, new int[]{1,2,3,4,5,6,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(39);
                arrayToNodes(number, new int[]{1,2,3,4,5,7,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(40);
                arrayToNodes(number, new int[]{1,2,3,4,6,7,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(41);
                arrayToNodes(number, new int[]{1,2,3,5,6,7,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(42);
                arrayToNodes(number, new int[]{1,2,4,5,6,7,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(43);
                arrayToNodes(number, new int[]{1,3,4,5,6,7,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(44);
            arrayToNodes(number, new int[]{2,3,4,5,6,7,8,9});
            part.add(number);
        this.root.add(part);
        
        part = new DefaultMutableTreeNode(9);
            number = new DefaultMutableTreeNode(45);
                arrayToNodes(number, new int[]{1,2,3,4,5,6,7,8,9});
            part.add(number);
        this.root.add(part);
        
    }
}
