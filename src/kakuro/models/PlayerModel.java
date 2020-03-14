package kakuro.models;

/**
 * Player model class, which acts as our representation data for the object
 * TODO: continue in iteration 3
 *
 * @author Brian Gamboc-Javiniar
 * Date written: March 1st, 2020
 */
public class PlayerModel {
    private String username; // username of the player
    private String password; // password of the player
    
    /**
     * PlayerModel constructor, which sets the username and password
     * 
     * @param username
     *  - a username of the player
     * @param password
     *  - a password of the player
     */
    public PlayerModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    /**
     * Accesses the username of the player
     * 
     * @return A string
     */
    public String getPlayerUsername() {
        return this.username;
    }
    
    /**
     * Accesses the password of the player
     * 
     * @return A string
     */
    public String getPlayerPassword() {
        return this.password;
    }
    
    /**
     * Sets the player's username to the object
     * 
     * @param username
     *  - username of the player
     */
    public void setPlayerUsername(String username) {
        this.username = username;
    }
    
    /**
     * Sets the player's password to the object
     * 
     * @param password
     *  - password of the player
     */
    public void setPlayerPassword(String password) {
        this.password = password;
    }
}
