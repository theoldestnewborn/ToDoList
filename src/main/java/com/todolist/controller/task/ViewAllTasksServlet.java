package com.todolist.controller.task;

import com.todolist.dao.ListDao;
import com.todolist.dao.TaskDao;
import com.todolist.dto.assembler.ListAssembler;
import com.todolist.dto.assembler.TaskAssembler;
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

@WebServlet(name = "viewAllTasks", value = "/viewAllTasks")
public class ViewAllTasksServlet extends HttpServlet {
    public TaskDao taskDao;
    public TaskService taskService;
    public ListAssembler listAssembler;


    @Override
    public void init() {
        taskDao = new TaskDao();
        taskService = new TaskService();
        listAssembler = new ListAssembler();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lists list = listAssembler.assembleListFromRequest(req);
        req.setAttribute("list", list);
        req.setAttribute("allTasks", taskDao.getAllTasksOfList(list));
        req.getRequestDispatcher("/view-all-tasks.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lists list = listAssembler.assembleListFromRequest(req);
        HttpServletRequest newRequest = taskService.createTaskRequest(req);
        req.setAttribute("create", true);
        req.setAttribute("list", list);
        req.setAttribute("allTasks", taskDao.getAllTasksOfList(list));
        req.getRequestDispatcher("/view-all-tasks.jsp").forward(newRequest, resp);
    }

    @Override
    public void destroy() {
    }
}