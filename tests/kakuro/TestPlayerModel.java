// @author Brian Gamboc-Javiniar
// @author Nalveer Moocheet
// @brief Test for the PlayerModel class

package kakuro;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import kakuro.PlayerModel;

	//iteration 3
	// TestPlayerModel class will test with dummy values of username and password. It will also test the set and get methods.
	public class TestPlayerModel {
	@Test
	public void testConstructor(){
		//Arrange
		String trueUsername = "name1";
		String truePassword = "password1";
		//Act
		PlayerModel plaMod = new PlayerModel(trueUsername, truePassword);
		//Assert
		assertEquals(plaMod.getPlayerUsername(),trueUsername);
		assertEquals(plaMod.getPlayerPassword(),truePassword);
	}
	
	@Test
	public void testGetterMethod(){
		//Act
		PlayerModel plaMod = new PlayerModel("name1", "password1");
		//Assert
		assertEquals(plaMod.getPlayerUsername(),"name1");
		assertEquals(plaMod.getPlayerPassword(),"password1");
		
	}
	
	@Test
	public void testSetterMethod(){
		//Arrange
		PlayerModel plaMod = new PlayerModel("dummyName", "dummyPassword");
		String newName = "name1";
		String newPassword = "password1";
		//Act
		plaMod.setPlayerUsername(newName);
		plaMod.setPlayerPassword(newPassword);
		//Assert
		assertEquals(plaMod.getPlayerUsername(),newName);
		assertEquals(plaMod.getPlayerPassword(),newPassword);
	}
}
