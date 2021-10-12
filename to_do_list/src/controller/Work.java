package controller;

import service.TasksManagement;

import java.util.Scanner;

public class Work {

    public static void task(){
        Scanner scanner = new Scanner(System.in);
        TasksManagement tasksManagement = new TasksManagement();
        System.out.println("Выберите действия:");
        System.out.println("1 - Создание записи; 2 - Удаление записи; 3 - Показать все записи;");
        System.out.println("4 - Изменить запись; 5 - Выход из учетной записи; 6 - Выход из программы;");
        String i = scanner.nextLine();
        if(i.equals("1")){
            tasksManagement.create();
            func();
        }
        if(i.equals("2")){
            tasksManagement.delete();
            func();
        }
        if(i.equals("3")){
            tasksManagement.show();
            func();
        }
        if(i.equals("4")){
            System.out.println("Укажите номер категории");
            System.out.println("1 - Изменить имя; 2 - Изменить текст задачи; 3 - Изменить статус;");
            String num = scanner.nextLine();
            tasksManagement.update(num);
        }
        if(i.equals("5")){
            Verification.verification();
        }
        if(i.equals("6")){
            System.exit(0);
        }
        else {
            System.out.println("Выбрана неверная команда");
            System.out.println();
            task();
        }
    }

    private static void func () {
        System.out.println();
        task();
    }
}
