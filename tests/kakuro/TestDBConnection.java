// @author Brian Gamboc-Javiniar
// @author Nalveer Moocheet
// @brief Test for the database connection

package kakuro;

import static org.junit.Assert.assertEquals;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import kakuro.utils.DatabaseConnection;

//The TestDBConnection method will check if the connection and the disconnection to the SQL Lite Database is successful or not
public class TestDBConnection {
	
    //testConnect method will check if it is possible to connect to the database or not by simulating it
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
    
  //testDisconnect method will check if there is no issues while disconnecting to the database
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
