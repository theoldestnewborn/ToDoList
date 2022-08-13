package com.todolist.controller;

import java.io.*;

import com.todolist.dao.ListDao;
import com.todolist.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "start", value = "/start")
public class StartServlet extends HttpServlet {
    @Override
    public void init() {
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (!(boolean)session.getAttribute("isAuth")) {
            req.getRequestDispatcher("/start.jsp").forward(req, resp);
        } else {
            User user = (User) session.getAttribute("user");
            req.setAttribute("allLists", new ListDao().getAllLists(user));
            req.getRequestDispatcher("/view-all-lists.jsp").forward(req, resp);
        }
    }
    @Override
    public void destroy() {
    }
}