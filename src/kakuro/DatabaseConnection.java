package kakuro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//Testing git add
public class DatabaseConnection {
    Connection connection = null;
    
    protected Connection connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Success! Connected to SQLite database");
        } catch (Exception e) {
            System.err.println("Failed to connect to SQLite database");
        }
        
        return connection;
    }
    
    protected void disconnect() {
        try {
            if (connection != null) {
                connection.close();

                System.out.println("Success! Disconnected from SQLite database");
            }
        } catch (Exception e) {
            System.err.println("Failed to disconnect from SQLite database");
        }
       
    }
    
    protected Boolean login(String uid, String pwd) {
           String query = "SELECT * FROM player where username=? AND password=? ";
           
           try {
               connection = this.connect();
               PreparedStatement psmt = connection.prepareStatement(query);
   
               psmt.setString(1, uid);
               psmt.setString(2, pwd);
               
               ResultSet rs = psmt.executeQuery();
               
               int count = 0;
               
               while(rs.next()) {
                   count++;
               }
               
               if (count == 1) {
                   return true;
               }
               
           } catch (SQLException e ) {
               System.err.println("Failed to login");
           }
           
           
           return false;
        
    }
    
    protected Boolean register(String uid, String pwd) {
        String checkIfUserExistQuery = "SELECT * FROM player where username=? ";
        String registerQuery = "INSERT INTO player (username, password) VALUES (?, ?)";
          
        try {
            connection = this.connect();
            
            PreparedStatement checkUserPsmt = connection.prepareStatement(checkIfUserExistQuery);
            checkUserPsmt.setString(1, uid);
            
            ResultSet rs = checkUserPsmt.executeQuery();
            
            int count = 0;
            
            while(rs.next()) {
                count++;
            }
            
            if(count > 0) {
                System.err.println("User already exist");
                return false;
            } else {
                PreparedStatement registerPsmt = connection.prepareStatement(registerQuery);
                registerPsmt.setString(1, uid);
                registerPsmt.setString(2, pwd);
                
                registerPsmt.executeUpdate();
                System.out.println("Succesfully registered");
                return true;
            }
            
            
        } catch (SQLException e) {
            System.err.println("Failed to register");
        }
        
        return false;
    }
    
    // save 
    // take in object / array 
    // insert to db (serializing)
    //talk :d
    // load 
    // select the username
    // de-serialize back to an object
    // return data (which format)
}
