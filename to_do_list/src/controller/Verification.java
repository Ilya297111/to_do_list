package controller;

import service.UserFind;
import service.UserRegistration;
import java.util.Scanner;

public class Verification {

    public static void verification (){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в управление списком дел.");
        System.out.println("Авторизируйтесь или зарегистрируйтесь что бы продолжить:");
        System.out.println("1 - Авторизация; 2 - Регистрация; 3 - Настройка; 4 - Выход;");
        String i = scanner.nextLine();
        if (i.equals("1")) {
            UserFind userFind = new UserFind();
            if(userFind.find()) {
                System.out.println("Авторизация прошла успешно\n");
                Work.task();
            } else {
                System.out.println("Пользователь не найден\n");
                verification();
            }
        }
        if (i.equals("2")) {
            UserRegistration userRegistration = new UserRegistration();
            userRegistration.registration();
        }
        if(i.equals("3")) {
            Admin.admin();
        }
        if (i.equals("4")) {
            System.exit(0);
        }
        else {
            System.out.println("Выбрана несуществующая команда\n");
            verification();
        }
    }
}