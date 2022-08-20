package com.todolist.controller;

import java.io.*;

import com.todolist.dao.ListDao;
import com.todolist.entities.User;
import com.todolist.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "start", value = "/start")
public class StartServlet extends HttpServlet {
    public ListDao listDao;
    public UserService userService;
    @Override
    public void init() {
        listDao = new ListDao();
        userService = new UserService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getAttribute("message");
            User user = userService.getUserFromSession(req);
            req.setAttribute("allLists", listDao.getAllLists(user));
            req.getRequestDispatcher("/view-all-lists.jsp").forward(req, resp);

    }
    @Override
    public void destroy() {
    }
}