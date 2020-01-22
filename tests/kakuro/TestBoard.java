package kakuro;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBoard
{
    @Test
    public void testSize()
    {
        // Arrange
        int columns = 10;
        int rows = 10;
        // Act
        GameModel model = new GameModel(columns, rows);
        // Assert
        assertEquals(model.columns, columns);
        assertEquals(model.rows, rows);
    }

}
