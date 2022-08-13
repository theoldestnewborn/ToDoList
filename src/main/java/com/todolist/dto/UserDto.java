package com.todolist.dto;

public class UserDto {
    public String login;

    public UserDto(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public UserDto() {
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
