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

@WebServlet(name = "setTaskComplete", value = "/setTaskComplete")
public class SetTaskCompleteServlet extends HttpServlet {
    public TaskDao taskDao;
    public TaskAssembler taskAssembler;

    @Override
    public void init() {
        taskDao = new TaskDao();
        taskAssembler = new TaskAssembler();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Task task = taskAssembler.assembleTaskCompleteFromRequest(req);
        Lists list = taskAssembler.assembleListFromRequest(req);
        req.setAttribute("task", task);
        req.setAttribute("list", list);
        req.setAttribute("allTasks", taskDao.getAllTasksOfList(list));
        req.getRequestDispatcher("/edit-task.jsp").forward(req,resp);
    }

    @Override
    public void destroy() {
    }
}