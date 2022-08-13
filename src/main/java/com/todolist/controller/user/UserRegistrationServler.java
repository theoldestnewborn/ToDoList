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
        if (!userDao.findByLogin(user)) {
            userDao.registerUser(user);
            request.setAttribute("message", "User " + user.getLogin() +  " is registered!");
            request.setAttribute("register", true);
            request.getRequestDispatcher("/start.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Such user already exists");
            request.setAttribute("register", false);
            request.getRequestDispatcher("/start.jsp").forward(request, response);
        }
    }


    @Override
    public void destroy() {
        super.destroy();
    }
}
