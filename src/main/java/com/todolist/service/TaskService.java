package com.todolist.service;

import com.todolist.dao.TaskDao;
import com.todolist.entities.Task;

import java.util.List;

public class TaskService {
    public boolean ifExistsOrWrongName(Task task) {
        boolean ifExistsOrWrongName = true;
        String task_body = task.getTask_body();
        TaskDao taskDao = new TaskDao();
        if (!taskDao.ifTaskExists(task) && !task_body.trim().isEmpty()) {
            taskDao.addTask(task);
            ifExistsOrWrongName = false;
        }
        return ifExistsOrWrongName;
}
}
