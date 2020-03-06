package kakuro;

import kakuro.player.dao.*;

import kakuro.utils.DatabaseConnection;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

public class TestPlayerDao {

	
	@Test
	public void testRegisterAlreadyRegistered() {
		
		DatabaseConnection db = new DatabaseConnection(); //has hard coded player "TestPlayer" password: "123" already in db 
		db.connect();
		
		String name = "TestPlayer";
		String password = "123";
		
		PlayerDaoImpl playerDoa = new PlayerDaoImpl();
		
		boolean registered = true;
		
		try {
			
		registered = playerDoa.register(db.getConnection(),name,password);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(registered,false);
		
		db.disconnect();
	}
	
	
	// TODO change once can unregister a user
//	@Test
//	public void testRegisterFirstTime() {
//		
//		DatabaseConnection db = new DatabaseConnection(); 
//		db.connect();
//		
//		String name = "Player1";                //new user
//		String password = "password1";
//		
//		PlayerDaoImpl playerDoa = new PlayerDaoImpl();
//		
//		boolean registered = false;
//		
//		try {
//			
//		registered = playerDoa.register(db.getConnection(),name,password);
//		
//		} catch (SQLException e) {
//		    e.printStackTrace();
//		}
//		
//		assertEquals(registered,true);
//		
//		db.disconnect();
//	}
	
	@Test
	public void testLoginRegistered() {
		
		DatabaseConnection db = new DatabaseConnection(); 
		db.connect();
		
		String name = "TestPlayer";                //hard coded in table
		String password = "123";
		
		PlayerDaoImpl playerDoa = new PlayerDaoImpl();
		
		boolean login  = false;
		
		try {
			
		login = playerDoa.login(db.getConnection(),name,password);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(login,true);
		
		db.disconnect();
	}
	
	@Test
	public void testLoginNotRegistered() {
		
		DatabaseConnection db = new DatabaseConnection(); 
		db.connect();
		
		String name = "NotInDB";                //hard coded in table
		String password = "NOPass";
		
		PlayerDaoImpl playerDoa = new PlayerDaoImpl();
		
		boolean login  = true;
		
		try {
			
		login = playerDoa.login(db.getConnection(),name,password);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(login,false);
		
		db.disconnect();
	}
	
	
	
	
}
