package com.todolist.controller.task;

import com.todolist.dao.TaskDao;
import com.todolist.entities.Task;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "deleteTask", value = "/deleteTask")
public class DeleteTaskServlet extends HttpServlet {
    @Override
    public void init() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] taskAndList = req.getParameter("listAndTask").
                split(",");
        String listName = taskAndList[0];
        String task_body = taskAndList[1];
        Task task = new Task(listName, task_body);
        new TaskDao().deleteTask(task);
        req.setAttribute("task", task);
        req.setAttribute("listName",listName);
        req.setAttribute("allTasks", new TaskDao().getAllTasksOfList(listName));
        req.getRequestDispatcher("/view-all-tasks-delete.jsp").forward(req,resp);
    }

    @Override
    public void destroy() {
    }
}