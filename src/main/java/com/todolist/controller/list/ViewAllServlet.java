package com.todolist.controller.list;

import com.todolist.dao.ListDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "viewAll", value = "/viewAll")
public class ViewAllServlet extends HttpServlet {
    @Override
    public void init() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new ListDao().deleteList(req.getParameter("listName"));
        req.setAttribute("allLists", new ListDao().getAllLists());
        req.getRequestDispatcher("/view-all-lists.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("allLists", new ListDao().getAllLists());
        req.getRequestDispatcher("/view-all-lists.jsp").forward(req,resp);
    }

    @Override
    public void destroy() {
    }
}