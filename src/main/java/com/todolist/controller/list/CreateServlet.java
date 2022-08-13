package com.todolist.controller.list;

import com.todolist.dao.ListDao;
import com.todolist.dao.UserDao;
import com.todolist.entities.Lists;
import com.todolist.entities.User;
import com.todolist.service.ListService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet(name = "create", value = "/create")
public class CreateServlet extends HttpServlet {
    @Override
    public void init() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void destroy() {
    }
}