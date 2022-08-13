package com.todolist.dto.assembler;

import com.todolist.dto.UserLoginDto;
import jakarta.servlet.http.HttpServletRequest;

public class UserLoginDtoAssembler {

    public UserLoginDto assemble (HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        return new UserLoginDto(login, password);
    }
}
