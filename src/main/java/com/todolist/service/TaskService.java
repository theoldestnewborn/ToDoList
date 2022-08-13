package com.todolist.service;

import com.todolist.dao.TaskDao;
import com.todolist.entities.Lists;
import com.todolist.entities.Task;

public class TaskService {
    public boolean hasProperName (Lists list, Task task) {
        boolean hasProperName = false;
        String taskBody = task.getTaskBody();
        TaskDao taskDao = new TaskDao();
        if (!taskDao.taskExists(list, task) && !taskBody.trim().isEmpty()) {
            hasProperName = true;
        }
        return hasProperName;
}
}
