package com.todolist.controller.user;

import com.todolist.dao.UserDao;
import com.todolist.dto.UserLoginDto;
import com.todolist.dto.mapper.UserMapper;
import com.todolist.entities.User;
import com.todolist.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
            String path = request.getContextPath() + "/viewAll";
            response.sendRedirect(path);
        } else {
            ServletContext context = request.getServletContext();
            context.setAttribute("message", "No user with such login or wrong password. Please, try again");
            context.setAttribute("register", false);
            String path = request.getContextPath() + "/start.jsp";
            response.sendRedirect(path);
        }
    }

    @Override
    public void destroy() {
    }
}
