package com.todolist.controller.task;

import com.todolist.dao.ListDao;
import com.todolist.dao.TaskDao;
import com.todolist.entities.Task;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "editTask", value = "/editTask")
public class EditTaskServlet extends HttpServlet {
    @Override
    public void init() {
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] listAndTasks = req.getParameter("listAndTask").
                split(",");
        String listName = listAndTasks[0];
        String task_body = listAndTasks[1];
        TaskDao taskDao = new TaskDao();
        Task oldTask = taskDao.getOneTask(listName, task_body);
        Task newTask = new Task(listName, req.getParameter("newTaskBody"));
        taskDao.editTask(oldTask, newTask);
        req.setAttribute("task", newTask);
        req.getRequestDispatcher("/editTask.jsp").forward(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] listAndTasks = req.getParameter("listAndTask").
                split(",");
        String listName = listAndTasks[0];
        String task_body = listAndTasks[1];
        TaskDao taskDao = new TaskDao();
        Task task = taskDao.getOneTask(listName, task_body);
        req.setAttribute("task", task);
        req.setAttribute("allTasks", taskDao.getAllTasks());
        req.getRequestDispatcher("/editTask.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
    }
}