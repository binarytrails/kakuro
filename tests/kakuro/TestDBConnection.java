package tests;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import kakuro.utils.DatabaseConnection;

public class TestDBConnection {

	
	@Test
	public void testConnect() {
	  
	  String expectedOutput = "Success! Connected to SQLite database";
		 
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	  System.setOut(new PrintStream(outContent));
		  	
	  DatabaseConnection db = new DatabaseConnection();
	  
	  assertEquals(expectedOutput, outContent.toString());
	  
	  db.disconnect();
	
	}
     
	@Test
	public void testDisconnect() {
	
	  DatabaseConnection db = new DatabaseConnection();
      db.connect();
	  
      String expectedOutput = "Success! Disconnected from SQLite database";
	 
	  ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      System.setOut(new PrintStream(outContent));
	  
	  db.disconnect();
	  
	  assertEquals(expectedOutput, outContent.toString());
	   
	
	}
	
	
	
}
