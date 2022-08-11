package com.todolist.controller.user;

import com.todolist.dao.UserDao;
import com.todolist.dto.UserLoginDto;
import com.todolist.dto.mapper.UserMapper;
import com.todolist.entities.User;
import com.todolist.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class UserLoginServlet extends HttpServlet {
    UserMapper mapper;
    UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        mapper = new UserMapper();
        userService = new UserService();
     }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = mapper.toUser((UserLoginDto) request.getAttribute("user"));
        HttpSession session = request.getSession();
        boolean isRegistered = userService.isRegistered(user);
        session.setAttribute("isAuth", isRegistered);
        request.getRequestDispatcher("viewAll").forward(request,response);
    }

    @Override
    public void destroy() {
    }
}
