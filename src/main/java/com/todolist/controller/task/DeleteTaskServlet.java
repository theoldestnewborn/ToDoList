package com.todolist.controller.task;

import com.todolist.dao.TaskDao;
import com.todolist.dto.assembler.TaskAssembler;
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
    private TaskDao taskDao;
    public TaskAssembler taskAssembler;


    @Override
    public void init() {
        taskDao = new TaskDao();
        taskAssembler = new TaskAssembler();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Task task = taskAssembler.assembleTaskFromRequest(req);
        Lists list = taskAssembler.assembleListFromRequest(req);
        taskDao.deleteTask(task);
        req.setAttribute("task", task);
        req.setAttribute("list", list);
        req.setAttribute("allTasks", taskDao.getAllTasksOfList(list));
        req.setAttribute("delete", true);
        req.getRequestDispatcher("/view-all-tasks.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
    }
}