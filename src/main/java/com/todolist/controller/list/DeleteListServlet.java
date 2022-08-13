package com.todolist.controller.list;

import com.todolist.dao.ListDao;
import com.todolist.entities.Lists;
import com.todolist.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "deleteList", value = "/deleteList")
public class DeleteListServlet extends HttpServlet {
    @Override
    public void init() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int idList = Integer.parseInt(req.getParameter("idList"));
        Lists list = new ListDao().getListById(idList);
        req.setAttribute("deletedList",list);
        new ListDao().deleteList(list, user);
        req.setAttribute("allLists", new ListDao().getAllLists(user));
        req.setAttribute("delete", true);
        req.getRequestDispatcher("/view-all-lists.jsp").forward(req,resp);
    }

    @Override
    public void destroy() {
    }
}