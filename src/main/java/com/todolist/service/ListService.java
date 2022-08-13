package com.todolist.service;

import com.todolist.dao.ListDao;
import com.todolist.entities.Lists;
import com.todolist.entities.User;

public class ListService {

    public boolean ifExistsOrWrongName(Lists list, User user) {
        boolean ifExistsOrWrongName = true;
        ListDao listDao = new ListDao();
        if (!listDao.getAllListsNames(user).contains(list.getListName().trim())
                && !list.getListName().trim().isEmpty()) {
            ifExistsOrWrongName = false;
        }
        return ifExistsOrWrongName;
    }
}

