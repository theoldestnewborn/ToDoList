package com.todolist.controller.task;

import com.todolist.dao.ListDao;
import com.todolist.dao.TaskDao;
import com.todolist.entities.Lists;
import com.todolist.entities.Task;
import com.todolist.service.TaskService;
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
        Lists list = new ListDao().getListById(Integer.parseInt(req.getParameter("idList")));
        req.setAttribute("list", list);
        req.setAttribute("allTasks", new TaskDao().getAllTasksOfList(list));
        req.getRequestDispatcher("/view-all-tasks.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lists list = new ListDao().getListById(Integer.parseInt(req.getParameter("idList")));
        String taskBody = req.getParameter("taskBody");
        Task testTask = new Task(list, taskBody);
        boolean hasProperName = new TaskService().hasProperName(list, testTask);
        req.setAttribute("hasProperName", hasProperName);
        req.setAttribute("create", true);
        if (hasProperName) {
            new TaskDao().addTask(list, testTask);
            Task task = new TaskDao().getTaskByBody(list, testTask);
            req.setAttribute("task", task);
        } else {
            req.setAttribute("task", testTask);
        }
        req.setAttribute("list", list);
        req.setAttribute("allTasks", new TaskDao().getAllTasksOfList(list));
        req.getRequestDispatcher("/view-all-tasks.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
    }
}