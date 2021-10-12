package entity;

import java.util.Objects;

public class User {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User (String login, String password){
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean equals (Object obj){
        if (obj instanceof User){
            User user = (User) obj;
            return user.login.equals(this.login) && user.password.equals(this.password);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(login, password);
    }

    @Override
    public String toString(){
        return "login - " + login + "; password - " + password;
    }
}
