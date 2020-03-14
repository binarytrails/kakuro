package kakuro.core;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 
 * Generating unique partitions for rows and columns in our game
 * Lists all possible answers in a Tree ADT: 
 * Level 1 : partitions (number of cells/boxes to be filled consecutively)
 * Level 2 : possible sums for that number of cells
 * Level 3 : an array of its values representing consecutively cells values from smallest to largest
 *
 * @author Vsevolod Ivanov
 * Date written: January 26th, 2020
 */
public class UniquePartitions
{
    public DefaultMutableTreeNode root;
    
    public UniquePartitions()
    {
        this.root = new DefaultMutableTreeNode(0);
        fillCombinations(this.root);
    }
    
    public DefaultMutableTreeNode getTreeRoot()
    {
        return this.root;
    }
    
    private void fillCombinations(DefaultMutableTreeNode tree)
    {
        DefaultMutableTreeNode part;
        DefaultMutableTreeNode number;
        
        part = new DefaultMutableTreeNode(2);
            number = new DefaultMutableTreeNode(3);
                Tools.arrayToNodes(number, new int[]{1,2});
            part.add(number);
            number = new DefaultMutableTreeNode(4);
                Tools.arrayToNodes(number, new int[]{1,3});
            part.add(number);
            number = new DefaultMutableTreeNode(16);
                Tools.arrayToNodes(number, new int[]{7,9});
            part.add(number);
            number = new DefaultMutableTreeNode(17);
                Tools.arrayToNodes(number, new int[]{8,9});
            part.add(number);
        this.root.add(part);
        
        part = new DefaultMutableTreeNode(3);
            number = new DefaultMutableTreeNode(6);
                Tools.arrayToNodes(number, new int[]{1,2,3});
            part.add(number);
            number = new DefaultMutableTreeNode(7);
                Tools.arrayToNodes(number, new int[]{1,2,4});
            part.add(number);
            number = new DefaultMutableTreeNode(23);
                Tools.arrayToNodes(number, new int[]{6,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(24);
                Tools.arrayToNodes(number, new int[]{7, 8, 9});
            part.add(number);
        this.root.add(part);
        
        part = new DefaultMutableTreeNode(4);
            number = new DefaultMutableTreeNode(10);
                Tools.arrayToNodes(number, new int[]{1,2,3,4});
            part.add(number);
            number = new DefaultMutableTreeNode(11);
                Tools.arrayToNodes(number, new int[]{1,2,3,5});
            part.add(number);
            number = new DefaultMutableTreeNode(29);
                Tools.arrayToNodes(number, new int[]{5,7,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(30);
                Tools.arrayToNodes(number, new int[]{6,7,8,9});
            part.add(number);
        this.root.add(part);
             
        part = new DefaultMutableTreeNode(5);
            number = new DefaultMutableTreeNode(21);
                Tools.arrayToNodes(number, new int[]{1,2,3,4,5,6});
            part.add(number);
            number = new DefaultMutableTreeNode(22);
                Tools.arrayToNodes(number, new int[]{1,2,3,4,5,7});
            part.add(number);
            number = new DefaultMutableTreeNode(38);
                Tools.arrayToNodes(number, new int[]{3,5,6,7,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(39);
                Tools.arrayToNodes(number, new int[]{4,5,6,7,8,9});
            part.add(number);
        this.root.add(part);
        
        part = new DefaultMutableTreeNode(6);
            number = new DefaultMutableTreeNode(28);
                Tools.arrayToNodes(number, new int[]{1,2,3,4,5,6,7});
            part.add(number);
            number = new DefaultMutableTreeNode(29);
                Tools.arrayToNodes(number, new int[]{1,2,3,4,5,6,8});
            part.add(number);
            number = new DefaultMutableTreeNode(41);
                Tools.arrayToNodes(number, new int[]{2,4,5,6,7,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(42);
                Tools.arrayToNodes(number, new int[]{3,4,5,6,7,8,9});
            part.add(number);
        this.root.add(part);
        
        part = new DefaultMutableTreeNode(7);
            number = new DefaultMutableTreeNode(28);
                Tools.arrayToNodes(number, new int[]{1,2,3,4,5,6,7});
            part.add(number);
            number = new DefaultMutableTreeNode(29);
                Tools.arrayToNodes(number, new int[]{1,2,3,4,5,6,8});
            part.add(number);
            number = new DefaultMutableTreeNode(41);
                Tools.arrayToNodes(number, new int[]{2,4,5,6,7,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(42);
                Tools.arrayToNodes(number, new int[]{3,4,5,6,7,8,9});
            part.add(number);
        this.root.add(part);
        
        part = new DefaultMutableTreeNode(8);
            number = new DefaultMutableTreeNode(36);
                Tools.arrayToNodes(number, new int[]{1,2,3,4,5,6,7,8});
            part.add(number);
            number = new DefaultMutableTreeNode(37);
                Tools.arrayToNodes(number, new int[]{1,2,3,4,5,6,7,9});
            part.add(number);
            number = new DefaultMutableTreeNode(38);
                Tools.arrayToNodes(number, new int[]{1,2,3,4,5,6,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(39);
                Tools.arrayToNodes(number, new int[]{1,2,3,4,5,7,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(40);
                Tools.arrayToNodes(number, new int[]{1,2,3,4,6,7,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(41);
                Tools.arrayToNodes(number, new int[]{1,2,3,5,6,7,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(42);
                Tools.arrayToNodes(number, new int[]{1,2,4,5,6,7,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(43);
                Tools.arrayToNodes(number, new int[]{1,3,4,5,6,7,8,9});
            part.add(number);
            number = new DefaultMutableTreeNode(44);
            Tools.arrayToNodes(number, new int[]{2,3,4,5,6,7,8,9});
            part.add(number);
        this.root.add(part);
        
        part = new DefaultMutableTreeNode(9);
            number = new DefaultMutableTreeNode(45);
                Tools.arrayToNodes(number, new int[]{1,2,3,4,5,6,7,8,9});
            part.add(number);
        this.root.add(part);
        
    }
}
