package com.todolist.controller.list;

import com.todolist.dao.ListDao;
import com.todolist.dao.UserDao;
import com.todolist.entities.Lists;
import com.todolist.entities.User;
import com.todolist.service.ListService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "viewAll", value = "/viewAll")
public class ViewAllServlet extends HttpServlet {
    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("allLists", new ListDao().getAllLists(user));
        req.getRequestDispatcher("/view-all-lists.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String listName = req.getParameter("listName");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Lists testList = new Lists(listName);
        boolean listExistsOrWrongName = new ListService().ifExistsOrWrongName(testList, user);
        req.setAttribute("ifExistsOrWrongName", listExistsOrWrongName);
        if (!listExistsOrWrongName) {
            new ListDao().addList(listName, user);
            Lists list = new ListDao().getListByName(listName, user);
            req.setAttribute("list", list);
        }
        req.setAttribute("allLists", new ListDao().getAllLists(user));
        req.setAttribute("create", true);
        req.getRequestDispatcher("/view-all-lists.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
    }
}