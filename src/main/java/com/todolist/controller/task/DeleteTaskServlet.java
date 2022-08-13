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

@WebServlet(name = "deleteTask", value = "/deleteTask")
public class DeleteTaskServlet extends HttpServlet {
    @Override
    public void init() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] taskAndList = req.getParameter("listAndTask").
                split(",");
        int idList = Integer.parseInt(taskAndList[0]);
        String taskBody = taskAndList[1];
        Lists list = new ListDao().getListById(idList);
        Task taskToDelete = new Task(list, taskBody);
        Task task = new TaskDao().getTaskByBody(list, taskToDelete);
        new TaskDao().deleteTask(task);
        req.setAttribute("task", task);
        req.setAttribute("list", list);
        req.setAttribute("allTasks", new TaskDao().getAllTasksOfList(list));
        req.setAttribute("delete", true);
        req.getRequestDispatcher("/view-all-tasks.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
    }
}