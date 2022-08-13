package com.todolist.dto.mapper;

import com.todolist.dto.UserDto;
import com.todolist.dto.UserLoginDto;
import com.todolist.dto.UserRegisterDto;
import com.todolist.entities.User;

public class UserMapper {
    public User toUser(UserLoginDto dto){
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        return user;
    }

    public User toUser(UserRegisterDto dto){
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public UserDto toUserDto(User user){
        return new UserDto(user.getLogin());
    }

}
