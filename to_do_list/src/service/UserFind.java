package service;

import entity.User;
import repository.Connect;
import entity.Variables;
import java.sql.*;
import java.util.Scanner;

public class UserFind {
    private Scanner scanner = new Scanner(System.in);
    private  User userByDB;
    private static int id;
    public static int personalNumber;

    public boolean find() {
        System.out.println("Введите логин");
        String log = scanner.nextLine();
        System.out.println("Введите пароль");
        String pass = Encryption.cipher(scanner.nextLine());
        String sql = "SELECT * FROM "+ Variables.tableName1 +" WHERE BINARY " + Variables.table1_Login + " LIKE '" + log +
                "' AND BINARY " + Variables.table1_Password + " LIKE '" + pass + "'";

        try (Connection con = Connect.connect();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                String DB_Login = resultSet.getString(2);
                String DB_Pass = resultSet.getString(3);
                userByDB = new User(DB_Login, DB_Pass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (userByDB != null) {
            personalNumber = id;
            return true;
        } else {
            return false;
        }
    }
}