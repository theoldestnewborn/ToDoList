package com.todolist.controller.list;

import com.todolist.dao.ListDao;
import com.todolist.dto.assembler.ListAssembler;
import com.todolist.entities.Lists;
import com.todolist.entities.User;
import com.todolist.service.ListService;
import com.todolist.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "deleteList", value = "/deleteList")
public class DeleteListServlet extends HttpServlet {
    public UserService userService;
    public ListDao listDao;
    public ListAssembler listAssembler;

    @Override
    public void init() {
        userService = new UserService();
        listDao = new ListDao();
        listAssembler = new ListAssembler();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getUserFromSession(req);
        Lists list = listAssembler.assembleListFromRequest(req);
        listDao.deleteList(list, user);
        req.setAttribute("deletedList", list);
        req.setAttribute("allLists", listDao.getAllLists(user));
        req.setAttribute("delete", true);
        req.getRequestDispatcher("/view-all-lists.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
    }
}