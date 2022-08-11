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

@WebServlet(name = "setTaskComplete", value = "/setTaskComplete")
public class SetTaskCompleteServlet extends HttpServlet {
    @Override
    public void init() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] taskAndList = req.getParameter("listAndTask").
                split(",");
        String listName = taskAndList[0];
        String task_body = taskAndList[1];
        boolean isComplete = Boolean.parseBoolean(taskAndList[2]);
        TaskDao taskDao = new TaskDao();
        Task task = new Task(listName, task_body);
        taskDao.setTaskComplete(task, isComplete);
        req.setAttribute("listName",listName);
        req.setAttribute("task", taskDao.getOneTask(listName, task_body));
        req.getRequestDispatcher("/editTask.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getParameter("isComplete");
        req.getParameter("task_body");
        req.setAttribute("allTasks", new TaskDao().getAllTasksOfList(req.getParameter("listName")));
        req.getRequestDispatcher("/view-all-tasks.jsp").forward(req,resp);
    }

    @Override
    public void destroy() {
    }
}