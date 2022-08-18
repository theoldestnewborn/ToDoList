package com.todolist.service;

import com.todolist.dao.ListDao;
import com.todolist.dto.ListDto;
import com.todolist.entities.Lists;
import com.todolist.entities.User;
import jakarta.servlet.http.HttpServletRequest;


public class ListService {
    ListDao listDao = new ListDao();
    UserService userService = new UserService();

    public boolean isListNameProper(String listName, User user) {
        boolean isListNameProper = true;
        ListDao listDao = new ListDao();
        if (!listDao.getAllListsNames(user).contains(listName.trim())
                && !listName.trim().isEmpty()) {
            isListNameProper = false;
        }
        return isListNameProper;
    }

    public HttpServletRequest createListRequest (HttpServletRequest req) {
        User user = userService.getUserFromSession(req);
        String listName = req.getParameter("listName");
        boolean listNameProper = isListNameProper(listName, user);
        req.setAttribute("isListNameProper", isListNameProper(listName, user));
        if (!listNameProper) {
            listDao.addList(listName, user);
            Lists list = listDao.getListByName(listName, user);
            req.setAttribute("list", list);
        } else {
            Lists errorList = new Lists();
            errorList.setListName(listName);
            req.setAttribute("list", errorList);
        }
        return req;
    }

    public Lists getListFromDb(ListDto listDto) {
        int idList = listDto.getIdList();
        return listDao.getListById(idList);
    }

}

