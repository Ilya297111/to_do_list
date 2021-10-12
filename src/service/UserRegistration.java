package service;

import controller.Verification;
import entity.User;
import repository.Connect;
import repository.SetStatementUpdate;
import entity.Variables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class UserRegistration {
    private Scanner scanner = new Scanner(System.in);
    private User user;
    private String login;

    public void registration() {
        System.out.println("Введите логин");
        login = scanner.nextLine();
        checkLogin();
        System.out.println("Введите пароль");
        String password1 = Encryption.cipher(scanner.nextLine());
        System.out.println("Повторите пароль");
        String password2 = Encryption.cipher(scanner.nextLine());
        if (!password1.equals(password2)) {
            System.out.println("Пароль введен неверно\n");
            registration();
        } else {
            user = new User(login, password2);
            writingToDB();
            System.out.println("Пользователь создан\n");
            Verification.verification();
        }
    }

    private void writingToDB() {
        String sql = "INSERT INTO " + Variables.tableName1 + " (" + Variables.table1_Login + " , " +
                Variables.table1_Password + ") VALUES ( '" + user.getLogin() + "' , '" + user.getPassword() + "')";
        SetStatementUpdate.sendSt(sql);
    }

    private void checkLogin() {
        String sql = "SELECT * FROM " + Variables.tableName1 + " WHERE BINARY " + Variables.table1_Login + " LIKE ?";
        String checkLogin = null;
        try (Connection connection = Connect.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                checkLogin = resultSet.getString(Variables.table1_Login);
            }
        } catch (Exception e) {
        }
        if (checkLogin != null) {
            System.out.println("Логин занят\n");
            registration();
        } else {
            System.out.println("Логин доступен\n");
        }
    }
}