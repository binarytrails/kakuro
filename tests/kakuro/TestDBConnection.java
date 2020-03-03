package tests;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import kakuro.utils.DatabaseConnection;

public class TestDBConnection {

	
	@Test
	public void testConnect() {
	  
		  	
	  DatabaseConnection db = new DatabaseConnection();
	  
	  assertEquals(db!=null, true);
	  
	  db.disconnect();
	
	}
     
	@Test
	public void testDisconnect() {
	
	  DatabaseConnection db = new DatabaseConnection();
      db.connect();
	 	  
	  db.disconnect();
	  
	  assertEquals(db==null, true);
	   
	
	}
	
	
	
}
