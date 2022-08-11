package com.todolist.controller.task;

import com.todolist.dao.TaskDao;
import com.todolist.entities.Task;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "viewAllTasks", value = "/viewAllTasks")
public class ViewAllTasksServlet extends HttpServlet {
    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("listName", req.getParameter("listName"));
        req.setAttribute("allTasks", new TaskDao().getAllTasksOfList(req.getParameter("listName")));
        req.getRequestDispatcher("/view-all-tasks.jsp").forward(req,resp);
    }

    @Override
    public void destroy() {
    }
}