// @author Vsevolod Ivanov
// @author ...

package kakuro;

public class BoardCell
{
    enum CellType
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

    BoardCell(CellType type)
    {
        this.type = type;
    };

    BoardCell(CellType type, int value1)
    {
        this.type = type;
        this.value1 = value1;
    }

    BoardCell(CellType type, int value1, int value2)
    {
        this.type = type;
        this.value1 = value1;
        this.value2 = value2;
    }

    int getFirstValue()
    {
        return this.value1;
    }

    int getSecondValue()
    {
        return this.value2;
    }

    void setFirstValue(int value)
    {
        this.value1 = value;
    }

    CellType getType()
    {
        return this.type;
    }
}
