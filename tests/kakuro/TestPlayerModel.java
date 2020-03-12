package kakuro;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import kakuro.models.PlayerModel;

//iteration 3

public class TestPlayerModel {

	
	@Test
	public void testConstructor() {
		
		String trueUsername = "name1";
		String truePassword = "password1";
		
		PlayerModel plaMod = new PlayerModel(trueUsername, truePassword);
		
		assertEquals(plaMod.getPlayerUsername(),trueUsername);
		assertEquals(plaMod.getPlayerPassword(),truePassword);
		
	}
	
	@Test
	public void testGetterMethod() {
		
		PlayerModel plaMod = new PlayerModel("name1", "password1");
		
		assertEquals(plaMod.getPlayerUsername(),"name1");
		assertEquals(plaMod.getPlayerPassword(),"password1");
		
	}
	
	@Test
	public void testSetterMethod() {
		
		PlayerModel plaMod = new PlayerModel("dummyName", "dummyPassword");
		
		String newName = "name1";
		String newPassword = "password1";
		
		plaMod.setPlayerUsername(newName);
		plaMod.setPlayerPassword(newPassword);
		
		assertEquals(plaMod.getPlayerUsername(),newName);
		assertEquals(plaMod.getPlayerPassword(),newPassword);
		
	}
	
	
	
}
