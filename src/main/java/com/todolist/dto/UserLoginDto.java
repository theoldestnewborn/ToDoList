package com.todolist.dto;

public class UserLoginDto {
    private String userName, password;

    public UserLoginDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserLoginDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
