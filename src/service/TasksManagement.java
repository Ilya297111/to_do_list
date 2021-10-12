package service;

import entity.Task;
import repository.Connect;
import repository.SetStatementUpdate;
import entity.Variables;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TasksManagement implements TasksService {
    private Scanner scanner = new Scanner(System.in);
    private static List<Task> tasks = new ArrayList<>();

    @Override
    public void create() {
        System.out.println("Введите название задачи");
        String taskName = scanner.nextLine();
        System.out.println("Опишите задачу");
        String taskText = scanner.nextLine();
        String sql = "INSERT INTO " + Variables.tableName3 + " (" + Variables.table1_table3_Id + ", " + Variables.table3_Name +
                ", " + Variables.table3_Text + ", " + Variables.table3_Status + ", " + Variables.table2_table3_Category +
                ") VALUE (" + UserFind.personalNumber + ",'"+ taskName +"', '"+ taskText +"' , false, "+ settingCategories() +" )";
        SetStatementUpdate.sendSt(sql);
    }

    private void toList() {
        String sql = "SELECT " + Variables.table3_Name + ", " + Variables.table3_Text + ", " + Variables.table2_table3_Category +
                ", " + Variables.table3_Status + " FROM " + Variables.tableName3 + " WHERE " + Variables.table1_table3_Id +
                " =" + UserFind.personalNumber;
        try (Connection connection = Connect.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String s1 = resultSet.getString(1);
                String s2 = resultSet.getString(2);
                String s3 = resultSet.getString(3);
                Boolean b1 = resultSet.getBoolean(4);
                tasks.add(new Task(s3, s1, s2, b1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void show() {
        int count = 0;
        tasks.clear();
        toList();
        for (Task arr : tasks) {
            System.out.println("№ " + (count++));
            System.out.println(arr);
            System.out.println("******************");
        }
        tasks.clear();
        count = 0;
    }

    @Override
    public void update(String i) {
        if (i.equals("1")) {
            changeName();
        }
        if (i.equals("2")) {
            changeTextOfTask();
        }
        if (i.equals("3")) {
            changeStatus();
        } else {
            System.out.println("Выбрана несущестующа категория");
        }
    }

    private void changeName() {
        show();
        toList();
        System.out.println("Введи номер записи имя которой нужно изменить");
        int num = scanner.nextInt();
        scanner.nextLine();
        if (num <= tasks.size()) {
            System.out.println("Введите новое имя");
            String newName = scanner.nextLine();
            Task taskForChange = tasks.get(num);
            String sql = "UPDATE " + Variables.tableName3 + " SET " + Variables.table3_Name + " = '" + newName +
                    "' WHERE " + Variables.table1_table3_Id + " = " + UserFind.personalNumber + " AND " +
                    Variables.table3_Name + " = '" + taskForChange.getTaskName() + "' AND " + Variables.table3_Text +
                    " = '" + taskForChange.getTaskText() + "'";
            SetStatementUpdate.sendSt(sql);
        }
    }

    private void changeTextOfTask() {
        show();
        toList();
        System.out.println("Введи номер записи имя которой нужно изменить");
        int num = scanner.nextInt();
        scanner.nextLine();
        if (num <= tasks.size()) {
            System.out.println("Введите новый текст задачи");
            String newText = scanner.nextLine();
            Task taskForChange = tasks.get(num);
            String sql = "UPDATE " + Variables.tableName3 + " SET " + Variables.table3_Text + " = '" + newText + "' WHERE " +
                    Variables.table1_table3_Id + " = " + UserFind.personalNumber + " AND " +
                    Variables.table3_Name + " = '" + taskForChange.getTaskName() + "' AND " + Variables.table3_Text + " = '" +
                    taskForChange.getTaskText() + "'";
            SetStatementUpdate.sendSt(sql);
        }
    }

    private void changeStatus() {
        show();
        toList();
        System.out.println("Введи номер записи имя которой нужно изменить");
        int num = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите новый статус задания\n1 - Выполнена\n2 - Не выполнена");
        boolean status = false;
        int x = scanner.nextInt();
        if (x == 1 || x == 2) {
            if (x == 1) {
                status = true;
            }
            if (x == 2) {
                status = false;
            }
        } else {
            System.out.println("Введена неверная команда");
        }
        Task taskForChange = tasks.get(num);
        String sql = "UPDATE " + Variables.tableName3 + " SET " + Variables.table3_Status + " = " + status + " WHERE " +
                Variables.table1_table3_Id + " = " + UserFind.personalNumber + " AND " + Variables.table3_Name + " = '" +
                taskForChange.getTaskName() + "' AND " + Variables.table3_Text + " = '" + taskForChange.getTaskText() + "'";
        SetStatementUpdate.sendSt(sql);
    }

    @Override
    public void delete() {
        show();
        toList();
        System.out.println("Введи номер записи который нужно удалить");
        int num = scanner.nextInt();
        if (num <= tasks.size()) {
            Task taskForDelete = tasks.get(num);
            String sql = "DELETE FROM " + Variables.tableName3 + " WHERE " + Variables.table3_Name + " = '" +
                    taskForDelete.getTaskName() + "' AND " + Variables.table3_Text + " = '" + taskForDelete.getTaskText() +
                    "' AND " + Variables.table1_table3_Id + " = " + UserFind.personalNumber;
            SetStatementUpdate.sendSt(sql);
        } else {
            System.out.println("Запись не найдена");
        }
    }

    private String settingCategories() {
        System.out.println("Выберите номер категории");
        System.out.println("1 - баг, 2 - разработка, 3 - изучние");
        String num = scanner.nextLine();
        if (num.equals("1")) {
            return
                    Variables.category1;
        }
        if (num.equals("2")) {
            return Variables.category2;
        }
        if (num.equals("3")) {
            return Variables.category3;
        } else return null;
    }
}