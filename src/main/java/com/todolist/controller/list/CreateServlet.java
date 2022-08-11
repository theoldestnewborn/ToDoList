package com.todolist.controller.list;

import com.todolist.dao.ListDao;
import com.todolist.entities.Lists;
import com.todolist.service.ListService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "create", value = "/create")
public class CreateServlet extends HttpServlet {
    @Override
    public void init() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String listName = req.getParameter("listName");
        Lists list = new Lists(listName);
        req.setAttribute("listName", list.getListName());
        req.setAttribute("ifExistsOrWrongName", new ListService().ifExistsOrWrongName(list));
        req.setAttribute("allLists", new ListDao().getAllLists());
        req.getRequestDispatcher("/view-all-lists-create.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
    }
}