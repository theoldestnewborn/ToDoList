package com.todolist.controller.task;

import com.todolist.dao.ListDao;
import com.todolist.dao.TaskDao;
import com.todolist.entities.Lists;
import com.todolist.entities.Task;
import com.todolist.service.ListService;
import com.todolist.service.TaskService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "addTask", value = "/addTask")
public class AddTaskServlet extends HttpServlet {
    @Override
    public void init() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String listName = req.getParameter("listName");
        String task_body = req.getParameter("task_body");
        Task task = new Task(listName, task_body);
        req.setAttribute("ifExistsOrWrongName", new TaskService().ifExistsOrWrongName(task));
        req.setAttribute("listName", listName);
        req.setAttribute("task", task);
        req.setAttribute("allTasks", new TaskDao().getAllTasksOfList(listName));
        req.getRequestDispatcher("/view-all-tasks-create.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    public void destroy() {
    }
}