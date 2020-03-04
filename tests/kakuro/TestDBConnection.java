package tests;

import static org.junit.Assert.assertEquals;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import kakuro.utils.DatabaseConnection;

public class TestDBConnection {

	
	@Test
	public void testConnect() {
	  
		  	
	  DatabaseConnection db = new DatabaseConnection();
	  
	  db.connect();
	  
	  Connection conn = db.getConnection(); 
	  
	  assertEquals(conn!=null, true);
	  
	  db.disconnect();
	
	}
     
	@Test
	public void testDisconnect() {
	
	  DatabaseConnection db = new DatabaseConnection();
      
	  db.connect();
	  
	  db.disconnect();
	  
	  Connection conn = db.getConnection();
	  
	  try {
		assertEquals(conn.isClosed(), true);
	  } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	   
	
	}
	
	
	
}
