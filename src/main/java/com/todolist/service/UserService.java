package com.todolist.service;

import com.todolist.dao.UserDao;
import com.todolist.entities.User;

public class UserService {
    private UserDao userDao = null;

    public UserService(){
        userDao = new UserDao();
    }

    public boolean isRegistered (User user) {
        return userDao.findByLoginAndPassword(user).isPresent();
    }
}
