package com.todolist.service;

import com.todolist.dao.ListDao;
import com.todolist.entities.Lists;

public class ListService {

    public boolean ifExistsOrWrongName(Lists list) {
        boolean ifExistsOrWrongName = true;
        ListDao listDao = new ListDao();
        if (!listDao.getAllListsNames().contains(list.getListName().trim())
                && !list.getListName().trim().isEmpty()) {
            listDao.addList(list.getListName());
            ifExistsOrWrongName = false;
        }
        return ifExistsOrWrongName;
    }
}

