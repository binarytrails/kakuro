package kakuro;

//@author Nalveer Moocheet
//@brief Test for the GameDifficultyListItem


 import static org.junit.Assert.assertEquals;
 import org.junit.Test;
 import kakuro.core.GameDifficulty;
 import kakuro.core.GameDifficultyListItem;

  
  public class TestGameDifficultyListItem {
    
	  
	//this function tests the constructor of GameDifficultyListItem
	@Test
	public void testConstructorOfTestGameDifficultyListItem() {
		
		//Arrange
		String description = "Difficult Game";
		GameDifficulty gameDiff = GameDifficulty.DIFFICULT;
	    
		//Act
		GameDifficultyListItem obj = new GameDifficultyListItem(description,gameDiff);
		
		//Assert
		assertEquals(obj.getDifficulty(),gameDiff);
		assertEquals(obj.toString(),description);
		
		
		
	}
	
	//this function test the toString method
	@Test
	public void testToString() {
		
		//Arrange
		String description = "Easy Game";
		GameDifficulty gameDiff = GameDifficulty.EASY;
		GameDifficultyListItem obj = new GameDifficultyListItem(description,gameDiff);
	
		//Act
		String toString = obj.toString();
		
		//Assert
		assertEquals(toString, description);
		
	
	}
	
	//this function tests the getDifficulty method
	@Test
	public void testGetDifficulty() {
		
		//Arrange
		String description = "Medium Game";
		GameDifficulty gameDiff = GameDifficulty.MEDIUM;
		GameDifficultyListItem obj = new GameDifficultyListItem(description,gameDiff);
	
		//Act
		GameDifficulty difficulty= obj.getDifficulty();
		
		//Assert
		assertEquals(difficulty, gameDiff);
		
	
	}
	
	
	
	
	
	
	
	
  }
