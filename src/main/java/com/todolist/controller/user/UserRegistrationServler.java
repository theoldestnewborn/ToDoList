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
        if (userDao.findByLoginOrEmail(user)||request.getParameter("login").trim().isEmpty()) {
            ServletContext context = request.getServletContext();
            context.setAttribute("message", "Such user already exists or wrong login");
            context.setAttribute("register", false);
            String path = request.getContextPath() + "/start.jsp";
            response.sendRedirect(path);

        } else if (!request.getParameter("password").equals(request.getParameter("repeatPassword"))) {
            ServletContext context = request.getServletContext();
            context.setAttribute("message", "Entered passwords don't match each other");
            context.setAttribute("register", false);
            String path = request.getContextPath() + "/start.jsp";
            response.sendRedirect(path);

        } else {
            userDao.registerUser(user);
            ServletContext context = request.getServletContext();
            context.setAttribute("message", "User " + user.getLogin() +  " is registered!");
            context.setAttribute("register", true);
            String path = request.getContextPath() + "/start.jsp";
            response.sendRedirect(path);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
