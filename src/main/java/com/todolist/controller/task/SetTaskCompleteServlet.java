package com.todolist.controller.task;

import com.todolist.dao.ListDao;
import com.todolist.dao.TaskDao;
import com.todolist.entities.Lists;
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
        String[] listAndTasks = req.getParameter("listAndTask").
                split(",");
        int idList = Integer.parseInt(listAndTasks[0]);
        String taskBody = listAndTasks[1];
        boolean isComplete = Boolean.parseBoolean(listAndTasks[2]);
        Lists list = new ListDao().getListById(idList);
        Task task = new TaskDao().getTaskByBody(list, new Task(list, taskBody));
        new TaskDao().setTaskComplete(task, isComplete);
        Task taskUpdated = new TaskDao().getTaskByBody(list, task);
        req.setAttribute("task", taskUpdated);
        req.setAttribute("list", list);
        req.setAttribute("allTasks", new TaskDao().getAllTasksOfList(list));
        req.getRequestDispatcher("/edit-task.jsp").forward(req,resp);
    }

    @Override
    public void destroy() {
    }
}