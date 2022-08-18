package com.todolist.controller.task;

import com.todolist.dao.ListDao;
import com.todolist.dao.TaskDao;
import com.todolist.dto.assembler.TaskAssembler;
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

    public TaskDao taskDao;
    public TaskService taskService;
    public TaskAssembler taskAssembler;


    @Override
    public void init() {
        taskDao = new TaskDao();
        taskService = new TaskService();
        taskAssembler = new TaskAssembler();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Task task = taskAssembler.assembleTaskFromRequest(req);
        Lists list = taskAssembler.assembleListFromRequest(req);
        req.setAttribute("task", task);
        req.setAttribute("list", list);
        req.setAttribute("allTasks", taskDao.getAllTasksOfList(list));
        req.getRequestDispatcher("/edit-task.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lists list = taskAssembler.assembleListFromRequest(req);
        HttpServletRequest newRequest = taskService.updateTaskRequest(req);
        req.setAttribute("list", list);
        req.getRequestDispatcher("/edit-task.jsp").forward(newRequest, resp);
    }

    @Override
    public void destroy() {
    }
}