package com.todolist.dto.assembler;

import com.todolist.dto.UserRegisterDto;
import jakarta.servlet.http.HttpServletRequest;

public class UserRegisterDtoAssembler {

    public UserRegisterDto assemble(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        return new UserRegisterDto(userName, email, password);
    }
}
