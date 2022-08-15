package com.todolist.controller.user;

import com.todolist.dao.ListDao;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userToLogin = mapper.toUser((UserLoginDto) request.getAttribute("user"));
        boolean isRegistered = userService.isRegistered(userToLogin);
        HttpSession session = request.getSession();
        session.setAttribute("isAuth", isRegistered);
        if (isRegistered) {
            User user = new UserDao().getUserByLogin(userToLogin);
            session.setAttribute("user", user);
            request.setAttribute("allLists", new ListDao().getAllLists(user));
            request.getRequestDispatcher("/viewAll").forward(request, response);
        } else {
            request.setAttribute("message", "No user with such login or wrong password. Please, try again");
            request.setAttribute("register", false);
            request.getRequestDispatcher("/start.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
