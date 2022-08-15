package com.todolist.service;

import com.todolist.dao.UserDao;
import com.todolist.entities.User;
import org.apache.commons.codec.digest.DigestUtils;

public class UserService {
    private UserDao userDao = null;

    public UserService(){
        userDao = new UserDao();
    }

    public boolean isRegistered (User user) {
        return userDao.findByLoginAndPassword(user).isPresent();
    }

    public String passwordHasher (User user) {
        String unhashedPassword = user.getPassword();
        String hashedPassword = DigestUtils.md5Hex(unhashedPassword);
        return hashedPassword;
    }

}
