package kakuro;

public class GameDifficultyListItem {
    private String description;
    private GameDifficulty difficulty; 

    public GameDifficultyListItem(String description, GameDifficulty difficulty) {
        this.description = description;
        this.difficulty = difficulty;
    }

    public GameDifficulty getDifficulty() {
        return difficulty;
    }

    public String toString() {
        return description;
    }
}
