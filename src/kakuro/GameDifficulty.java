package kakuro;

public enum GameDifficulty {
    EASY,
    MEDIUM,
    DIFFICULT;

    public static int GameDifficultyToInt(GameDifficulty difficulty) {
        switch(difficulty) {
            case EASY: return 1;
            case MEDIUM: return 2;
            default: return 3;
        }
    }
}
