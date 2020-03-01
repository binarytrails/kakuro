package testst;

import kakuro.utils.*;

public class Testing {

    public static void main(String[] args) {
       DatabaseConnection x = new DatabaseConnection();
       
       x.connect();
       
       x.disconnect();
    }

}
