package kakuro;

//TODO: iteration 3 work
public class PlayerModel {
    private String username;
    private String password;
    
    public PlayerModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getPlayerUsername() {
        return this.username;
    }
    
    //TODO: remove later when user is implemented properly in iteration 3
    public String getPlayerPassword() {
        return this.password;
    }
    
    public void setPlayerUsername(String username) {
        this.username = username;
    }
    
    public void setPlayerPassword(String password) {
        this.password = password;
    }
}
