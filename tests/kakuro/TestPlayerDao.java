// @author Vsevolod Ivanov
// @author Nalveer Moocheet
// @brief Test for the PlayerDao class

package kakuro;

import kakuro.player.dao.*;

import kakuro.utils.DatabaseConnection;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

//TestPlayerDao will verify if either the user is registered or not and if the user is login or not. It will also test if the username and password match the data available in the Database 

public class TestPlayerDao {
	@Test
	public void testRegisterAlreadyRegistered(){
		//arrange
		DatabaseConnection db = new DatabaseConnection(); //has hard coded player "TestPlayer" password: "123" already in db 
		db.connect();
		String name = "TestPlayer";
		String password = "123";
		PlayerDaoImpl playerDoa = new PlayerDaoImpl();
		boolean registered = true;
		try {
		//Act	
		registered = playerDoa.register(db.getConnection(),name,password);		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Assert
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
	public void testLoginRegistered(){
		//Arrange
		DatabaseConnection db = new DatabaseConnection(); 
		db.connect();		
		String name = "TestPlayer";                //hard coded in table
		String password = "123";		
		PlayerDaoImpl playerDoa = new PlayerDaoImpl();		
		boolean login  = false;		
		try {
		//Act	
		login = playerDoa.login(db.getConnection(),name,password);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Assert
		assertEquals(login,true);		
		db.disconnect();
	}
	
	@Test
	public void testLoginNotRegistered(){
		//Arrange
		DatabaseConnection db = new DatabaseConnection(); 
		db.connect();		
		String name = "NotInDB";                //hard coded in table
		String password = "NOPass";		
		PlayerDaoImpl playerDoa = new PlayerDaoImpl();		
		boolean login  = true;		
		try {
		//Act	
		login = playerDoa.login(db.getConnection(),name,password);		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Assert
		assertEquals(login,false);
		
		db.disconnect();
	}	
}
