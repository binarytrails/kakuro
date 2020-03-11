// @author Brian Gamboc-Javiniar
// @author Nalveer Moocheet
// @brief Test for the database connection

package kakuro;

import static org.junit.Assert.assertEquals;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import kakuro.utils.DatabaseConnection;

public class TestDBConnection {
    @Test
	public void testConnect(){
	    //Arrange
        DatabaseConnection db = new DatabaseConnection();
        db.connect();
        //Act
        Connection conn = db.getConnection(); 
        assertEquals(conn!=null, true);
        //Assert
        db.disconnect();
	}
     
	@Test
	public void testDisconnect(){
	    //Arrange
        DatabaseConnection db = new DatabaseConnection();
        db.connect();
        db.disconnect();
        //Act
        Connection conn = db.getConnection();
        //Assert
        try {
        assertEquals(conn.isClosed(), true);
          } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }
	}	
}
