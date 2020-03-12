/* @author Hoang Thuan Pham
 * Date created: March 9th, 2020
 * Enumerator: GameDifficulty 
 * Description: An enumerator for different levels of game difficulties
*/


package kakuro.core;

public enum GameDifficulty {
    EASY,       //Easy game difficulty
    MEDIUM,     //Medium game difficulty
    DIFFICULT;  //Difficult game difficulty

    /*Convert the enumerator to an integer. The method is to support database storage where enumerators are not supported.
     * @param: an enumerator type
     * @return: the corresponding integer to an enumerator value
     * */
    public static int GameDifficultyToInt(GameDifficulty difficulty) {
        switch(difficulty) {
            case EASY: return 1;
            case MEDIUM: return 2;
            default: return 3;
        }
    }
}
