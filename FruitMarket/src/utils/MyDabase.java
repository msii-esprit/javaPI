package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDabase {
    final String URL = "jdbc:mysql://localhost:3306/projet_pi";
    final String USER="root";
    final String PASS="";
   private Connection connection;
    private static MyDabase instance;

  private MyDabase(){
        try {
            connection = DriverManager.getConnection(URL,USER,PASS);
        System.out.println("Connected");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public static MyDabase getInstance() {
        if (instance == null)
            instance = new MyDabase();
            return instance;

    }
      public Connection getConnection(){
          return connection;
        }


}
