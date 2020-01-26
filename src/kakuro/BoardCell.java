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
    private int input = -1;
    private int answer = -1;

    BoardCell(CellType type)
    {
        this.type = type;
    };

    BoardCell(CellType type, int input)
    {
        this.type = type;
        this.input = input;
    }

    BoardCell(CellType type, int number, int answer)
    {
        this.type = type;
        this.input = number;
        this.answer = answer;
    }

    int getInputValue()
    {
        return this.input;
    }

    int getAnswerValue()
    {
        return this.answer;
    }

    CellType getType()
    {
        return this.type;
    }
}
