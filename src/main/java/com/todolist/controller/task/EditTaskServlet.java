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

@WebServlet(name = "editTask", value = "/editTask")
public class EditTaskServlet extends HttpServlet {
    @Override
    public void init() {
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] listAndTasks = req.getParameter("listAndTask").
                split(",");
        int idList = Integer.parseInt(listAndTasks[0]);
        String taskBody = listAndTasks[1];
        Lists list = new ListDao().getListById(idList);
        Task oldTask = new TaskDao().getTaskByBody(list, new Task(list, taskBody));
        Task newTask = new Task(list, req.getParameter("newTaskBody"));
        boolean hasProperName = new TaskService().hasProperName(list, newTask);
        req.setAttribute("hasProperName", hasProperName);
        req.setAttribute("update", true);
        if (hasProperName) {
            new TaskDao().editTask(oldTask, newTask);
            req.setAttribute("task", newTask);
        } else {
            req.setAttribute("task", oldTask);
            req.setAttribute("taskWithWrongName", newTask);
        }
        req.setAttribute("list", list);
        req.getRequestDispatcher("/edit-task.jsp").forward(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] listAndTasks = req.getParameter("listAndTask").
                split(",");
        int idList = Integer.parseInt(listAndTasks[0]);
        String taskBody = listAndTasks[1];
        Lists list = new ListDao().getListById(idList);
        Task task = new TaskDao().getTaskByBody(list, new Task(list, taskBody));
        req.setAttribute("task", task);
        req.setAttribute("list", list);
        req.setAttribute("allTasks", new TaskDao().getAllTasksOfList(list));
        req.getRequestDispatcher("/edit-task.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
    }
}