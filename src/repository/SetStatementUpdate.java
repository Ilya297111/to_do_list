package repository;

import java.sql.Connection;
import java.sql.Statement;

public class SetStatementUpdate {
    public static void sendSt(String sql) {
        try (Connection connection = Connect.connect();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
