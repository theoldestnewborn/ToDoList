package com.todolist.dto.assembler;

import com.todolist.dto.UserLoginDto;
import com.todolist.entities.User;
import jakarta.servlet.http.HttpServletRequest;

public class UserLoginDtoAssembler {

    public UserLoginDto assemble (HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        return new UserLoginDto(userName, password);
    }
}
