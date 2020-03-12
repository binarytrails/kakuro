// @author Vsevolod Ivanov
// @author ...

package kakuro.core;

public class BoardCell
{
    public enum CellType
    {
        EMPTY,    /* |   | */
        INPUT,    /* |   | */
        FILLED01, /* | \n| */
        FILLED10, /* |n\ | */
        FILLED11  /* |n\n| */
    }
    
    private CellType type;
    private int value1 = -1;
    private int value2 = -1;

    public BoardCell(CellType type)
    {
        this.type = type;
    };

    public BoardCell(CellType type, int value1)
    {
        this.type = type;
        this.value1 = value1;
    }

    public BoardCell(CellType type, int value1, int value2)
    {
        this.type = type;
        this.value1 = value1;
        this.value2 = value2;
    }

    public int getFirstValue()
    {
        return this.value1;
    }

    public int getSecondValue()
    {
        return this.value2;
    }

    public void setFirstValue(int value)
    {
        this.value1 = value;
    }

    public CellType getType()
    {
        return this.type;
    }
}
