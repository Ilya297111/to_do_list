package service;

public interface AdminService {

    boolean adminVerification(String log, String pas);

    void createDatabase();

    void deleteDatabase();

    void showAllUsers();

    void deleteUser();

}
