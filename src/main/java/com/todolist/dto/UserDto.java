package com.todolist.dto;

public class UserDto {
    public String userName;

    public UserDto(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public UserDto() {
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
