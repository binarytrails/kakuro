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
    private int inputNumber = -1;

    BoardCell(CellType type)
    {
        this.type = type;
    };
    BoardCell(CellType type, int number)
    {
        this.type = type;
        this.inputNumber = number;
    }
    
    int getValue()
    {
        return this.inputNumber;
    }
    CellType getType()
    {
        return this.type;
    }
}
