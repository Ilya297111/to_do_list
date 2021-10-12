package service;

import entity.User;
import repository.Connect;
import repository.SetStatementUpdate;
import entity.Variables;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminDatabaseManagement implements AdminService {
    private static final String login = "admin";
    private static final String password = "admin";
    private List<User> users = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public boolean adminVerification(String log, String pas) {
        return (login.equals(log) && password.equals(pas));
    }

    @Override
    public void createDatabase() {
        String sql1 = "CREATE DATABASE " + Variables.databaseName;
        String createCategory = "INSERT INTO " + Variables.tableName2 + " (" + Variables.table2_table3_Category + ") VALUE" +
                " (" + Variables.category1 + "), (" + Variables.category2 + "), (" + Variables.category3 + ")";
        conn(sql1);

        String table1 = new StringBuilder("CREATE TABLE ").append(Variables.tableName1).append(" (").append(Variables.table1_table3_Id)
                .append(" INTEGER AUTO_INCREMENT PRIMARY KEY, ").append(Variables.table1_Login).append(" VARCHAR(100) UNIQUE, ")
                .append(Variables.table1_Password).append(" VARCHAR(100))").toString();

        String table2 = new StringBuilder("CREATE TABLE ").append(Variables.tableName2).append(" ( ")
                .append(Variables.table2_table3_Category).append("  VARCHAR(100) PRIMARY KEY)").toString();

        String table3 = new StringBuilder("CREATE TABLE ").append(Variables.tableName3).append(" (").append(Variables.table1_table3_Id)
                .append(" INTEGER , ").append(Variables.table3_Text).append(" VARCHAR(300) NOT NULL, ").append(Variables.table3_Name)
                .append(" VARCHAR(100) NOT NULL, ").append(Variables.table3_Status).append(" BOOLEAN NOT NULL, ")
                .append(Variables.table2_table3_Category).append(" VARCHAR(100), FOREIGN KEY (").append(Variables.table1_table3_Id)
                .append(") REFERENCES ").append(Variables.tableName1).append("(").append(Variables.table1_table3_Id)
                .append("), FOREIGN KEY (").append(Variables.table2_table3_Category).append(") REFERENCES ")
                .append(Variables.tableName2).append(" (").append(Variables.table2_table3_Category).append("))").toString();

        SetStatementUpdate.sendSt(table1);
        SetStatementUpdate.sendSt(table2);
        SetStatementUpdate.sendSt(table3);
        SetStatementUpdate.sendSt(createCategory);
    }

    @Override
    public void deleteDatabase() {
        String sql2 = "DROP DATABASE " + Variables.databaseName;
        conn(sql2);
    }

    @Override
    public void showAllUsers() {
        int count = 0;
        String showAllUsers = "SELECT * FROM " + Variables.tableName1;
        try (Connection connection = Connect.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(showAllUsers)) {
            while (resultSet.next()) {
                String s1 = resultSet.getString(Variables.table1_Login);
                String s2 = resultSet.getString(Variables.table1_Password);
                users.add(new User(s1, s2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (User arr : users) {
            System.out.println("№ " + (count++));
            System.out.println(arr);
            System.out.println("******************");
        }
    }

    @Override
    public void deleteUser() {
        showAllUsers();
        System.out.println("Введи номер пользователя которого нужно удалить");
        int num = scanner.nextInt();
        if (num <= users.size()) {
            User userForDelete = users.get(num);
            String sql = "DELETE FROM " + Variables.tableName1 + " WHERE " + Variables.table1_Login + " = '" +
                    userForDelete.getLogin() + "' AND " + Variables.table1_Password + " = '" + userForDelete.getPassword() + "'";
            SetStatementUpdate.sendSt(sql);
        } else {
            System.out.println("Запись не найдена");
        }
    }

    private void conn(String sql) {
        String URL = "jdbc:mysql://localhost/?useUnicode=true&serverTimezone=UTC";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection connection = DriverManager.getConnection(URL, Variables.username, Variables.password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    public static String transformation(StringBuilder stringBuilder) {
        return stringBuilder.toString();
    }
}
