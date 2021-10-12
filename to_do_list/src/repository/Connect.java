package repository;

import entity.Variables;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    public static Connection connect (){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(Variables.URL, Variables.username, Variables.password);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
