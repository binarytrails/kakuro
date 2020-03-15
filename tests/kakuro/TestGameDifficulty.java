
// @author Nalveer Moocheet
// @brief Test for the Game Difficulty

package kakuro;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import kakuro.core.GameDifficulty;

public class TestGameDifficulty {

	 // This method tests if the corresponding integer of a difficulty level is correct
	 @Test
	 public void testGameDifficultyToInt() {
		 
		 //Arrange
		 GameDifficulty easyGame = GameDifficulty.EASY;
		 GameDifficulty mediumGame = GameDifficulty.MEDIUM;
		 GameDifficulty hardGame = GameDifficulty.DIFFICULT;
		 
		 //Act
		 int one = GameDifficulty.GameDifficultyToInt(easyGame);
		 int two = GameDifficulty.GameDifficultyToInt(easyGame);
		 int three = GameDifficulty.GameDifficultyToInt(easyGame);
		 
		 //Assert
		 assertEquals(one, 1);
		 assertEquals(two, 2);
		 assertEquals(three, 3);
		 
	 }
	
	
	
}
