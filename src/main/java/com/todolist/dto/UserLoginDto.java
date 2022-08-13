package com.todolist.dto;

public class UserLoginDto {
    private String login, password;

    public UserLoginDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserLoginDto() {
    }

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
}
