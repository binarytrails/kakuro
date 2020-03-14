package kakuro.core;

/**
 * Cell class that is defines each cell in our board
 *
 * @author Vsevolod Ivanov
 * Date written: January 24th, 2020
 */
public class Cell
{
    /**
     * types of Cells that we can have
     */
    public enum CellType
    {
        EMPTY,    /* |   | */
        INPUT,    /* |   | */
        FILLED01, /* | \n| */
        FILLED10, /* |n\ | */
        FILLED11  /* |n\n| */
    }
    
    private CellType type; // CellType object reference
    private int value1 = -1; // the left value of the filled type or the value of the input
    private int value2 = -1; // the right value of the filled type

    /**
     * Cell constructor for the type of cell being generated
     * 
     * @param type
     *  - the type of cell being created
     */
    public Cell(CellType type)
    {
        this.type = type;
    };

    /**
     * Cell Constructor for the type of cell being generated and a value associated to it
     * 
     * @param type
     *  - the type of cell being created
     * @param value1
     *  - the value being inserted to the cell
     */
    public Cell(CellType type, int value1)
    {
        this.type = type;
        this.value1 = value1;
    }
    
    /**
     * Cell Constructor for the type of cell being generated and values associated to it
     * 
     * @param type
     *  - the type of cell being created
     * @param value1
     *  - the value being inserted to the cell
     * @param value2
     *  - the value being inserted to the cell
     */
    public Cell(CellType type, int value1, int value2)
    {
        this.type = type;
        this.value1 = value1;
        this.value2 = value2;
    }
    
    /**
     * Accesses the first value of the cell
     * @return
     *  - an integer
     */
    public int getFirstValue()
    {
        return this.value1;
    }
    
    /**
     * Accesses the second value of the cell
     * @return
     *  - an integer
     */
    public int getSecondValue()
    {
        return this.value2;
    }
    
    /**
     * sets the first value of the cell
     */
    public void setFirstValue(int value)
    {
        this.value1 = value;
    }
    
    /**
     * Accesses the type for the cell
     * @return
     *  - a CellType object
     */
    public CellType getType()
    {
        return this.type;
    }
}
