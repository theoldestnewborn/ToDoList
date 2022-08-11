package com.todolist.dto.assembler;

import com.todolist.dto.UserLoginDto;
import com.todolist.dto.UserRegisterDto;
import jakarta.servlet.http.HttpServletRequest;

public class DtoAssembler {

    private final UserLoginDtoAssembler userLoginDtoAssembler = new UserLoginDtoAssembler();
    private final UserRegisterDtoAssembler userRegisterDtoAssembler = new UserRegisterDtoAssembler();

    public void assemble(HttpServletRequest request){
        String path = request.getServletPath();
        if(path.equals(Paths.LOGIN_PATH)){
            UserLoginDto user = userLoginDtoAssembler.assemble(request);
            request.setAttribute("user", user);
        } else if(path.equals(Paths.REGISTER_PATH)){
            UserRegisterDto user = userRegisterDtoAssembler.assemble(request);
            request.setAttribute("user", user);
        }
    }
}
