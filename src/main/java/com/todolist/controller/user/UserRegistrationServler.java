package com.todolist.controller.user;

import com.todolist.dao.UserDao;
import com.todolist.dto.UserRegisterDto;
import com.todolist.dto.mapper.UserMapper;
import com.todolist.entities.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "register", value = "/register")
public class UserRegistrationServler extends HttpServlet {

    private UserDao userDao;
    private UserMapper mapper;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
        mapper = new UserMapper();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = mapper.toUser((UserRegisterDto) request.getAttribute("user"));
        userDao.registerUser(user);
        request.setAttribute("color", "green");
        request.setAttribute("message", "User is registered!");
        request.getRequestDispatcher("viewAll").forward(request, response);
    }


    @Override
    public void destroy() {
        super.destroy();
    }
}
