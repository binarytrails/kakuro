package kakuro.core;

/** 
 * The game difficulty found in the dropdown menu when preloading a game
 * 
 * @author Hoang Thuan Pham
 * Date created: March 9th, 2020
*/
public class GameDifficultyListItem {
    private String description; // the description of the game difficulty
    private GameDifficulty difficulty; // GameDifficulty object reference

    /**
     * GameDifficultyListItem constructor that sets the description and difficulty of the game
     * 
     * @param description
     *  - the description of the difficulty
     * @param difficulty
     *  - the difficulty level
     */
    public GameDifficultyListItem(String description, GameDifficulty difficulty) {
        this.description = description;
        this.difficulty = difficulty;
    }
    
    /**
     * Accesses the difficulty of the game
     * 
     * @return
     *  - A GameDifficulty object
     */
    public GameDifficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Accesses the description of the difficulty
     */
    public String toString() {
        return description;
    }
}
