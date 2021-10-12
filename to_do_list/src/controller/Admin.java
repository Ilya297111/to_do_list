package controller;

import entity.Variables;
import service.AdminDatabaseManagement;
import java.util.Scanner;

public class Admin {

    public static void admin() {
        AdminDatabaseManagement admin = new AdminDatabaseManagement();
        System.out.println("Введите логин администратора");
        Scanner scanner = new Scanner(System.in);
        String log = scanner.nextLine();
        System.out.println("Введите пароль администратора");
        String pas = scanner.nextLine();
        if (admin.adminVerification(log, pas)) {
            System.out.println("Доступ разрешен\n");
            work();
        } else {
            System.out.println("В доступе отказано\n");
            Verification.verification();
        }
    }

    private static void work() {
        AdminDatabaseManagement admin = new AdminDatabaseManagement();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите действие:");
        System.out.println("1 - Создать БД; 2 - Удалить БД;");
        System.out.println("3 - Показать всех пользователей; 4 - Удалить пользователь;");
        System.out.println("5 - Выход из учетной записи; 6 - Выход из программы;");
        String i = scanner.nextLine();
        if (i.equals("1")) {
            admin.createDatabase();
            System.out.println("База данных " + Variables.databaseName +" созданна\n");
        }
        if (i.equals("2")) {
            admin.deleteDatabase();
            System.out.println("База данных " + Variables.databaseName +" удаленна\n");
        }
        if (i.equals("3")) {
            admin.showAllUsers();
        }
        if (i.equals("4")) {
            admin.deleteUser();
        }
        if (i.equals("5")) {
            System.out.println("Выход с пользователя");
            Verification.verification();
        }
        if (i.equals("6")) {
            System.out.println("Выход с программы");
            System.exit(0);
        } else {
            work();
        }
    }
}