package com.todolist.controller.list;

import com.todolist.dao.ListDao;
import com.todolist.entities.User;
import com.todolist.service.ListService;
import com.todolist.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "viewAll", value = "/viewAll")
public class ViewAllServlet extends HttpServlet {

    public UserService userService;
    private ListService listService;
    public ListDao listDao;

    private static final Logger logger = LoggerFactory.getLogger(ViewAllServlet.class);

    @Override
    public void init() {
        userService = new UserService();
        listService = new ListService();
        listDao = new ListDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = userService.getUserFromSession(req);
        req.setAttribute("allLists", listDao.getAllLists(user));
        req.getRequestDispatcher("/view-all-lists.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getUserFromSession(req);
        HttpServletRequest newRequest = listService.createListRequest(req);
        req.setAttribute("allLists", listDao.getAllLists(user));
        req.setAttribute("create", true);
        req.getRequestDispatcher("/view-all-lists.jsp").forward(newRequest, resp);
    }

    @Override
    public void destroy() {
    }
}