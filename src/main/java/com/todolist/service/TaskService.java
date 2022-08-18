package com.todolist.service;

import com.todolist.dao.TaskDao;
import com.todolist.dto.TaskDto;
import com.todolist.dto.assembler.ListAssembler;
import com.todolist.dto.assembler.TaskAssembler;
import com.todolist.entities.Lists;
import com.todolist.entities.Task;
import jakarta.servlet.http.HttpServletRequest;

public class TaskService {
    TaskDao taskDao = new TaskDao();
    TaskAssembler taskAssembler = new TaskAssembler();
    ListAssembler listAssembler = new ListAssembler();

    public boolean isTaskNameProper(Lists list, String taskBody) {
        boolean isTaskNameProper = false;
        if (!taskDao.taskExists(list, taskBody) && !taskBody.trim().isEmpty()) {
            isTaskNameProper = true;
        }
        return isTaskNameProper;
    }

    public HttpServletRequest updateTaskRequest(HttpServletRequest req) {
        Task oldTask = taskAssembler.assembleTaskFromRequest(req);
        String updatedTaskBody = req.getParameter("updatedTaskBody");
        Lists list = taskAssembler.assembleListFromRequest(req);
        Task newTask = new Task();
        newTask.setIdList(list.getIdList());
        newTask.setTaskBody(updatedTaskBody);
        boolean isTaskNameProper = isTaskNameProper(list,updatedTaskBody);
        req.setAttribute("hasProperName", isTaskNameProper);
        req.setAttribute("update", true);
        if (isTaskNameProper) {
            taskDao.editTask(oldTask, newTask);
            req.setAttribute("task", newTask);
        } else {
            req.setAttribute("task", oldTask);
            req.setAttribute("taskWithWrongName", newTask);
        }
        return req;
    }

    public HttpServletRequest createTaskRequest (HttpServletRequest req) {
        Lists list = listAssembler.assembleListFromRequest(req);
        Task task = taskAssembler.assembleNewTaskFromRequest(req);
        String taskBody = task.getTaskBody();
        boolean hasProperName = isTaskNameProper(list,taskBody);
        req.setAttribute("hasProperName", hasProperName);
        if (hasProperName) {
            taskDao.addTask(list, task);
            task = taskDao.getTaskByBody(list, taskBody);
            req.setAttribute("task", task);
        } else {
            req.setAttribute("task", task);
        }
        return req;
    }

    public Task getTaskFromDb(Lists list, TaskDto taskDto) {
        String taskBody = taskDto.getTaskBody();
        return taskDao.getTaskByBody(list, taskBody);
    }

    public void setTaskComplete(Task task, boolean isComplete) {
        taskDao.setTaskComplete(task, isComplete);
    }

    public void setTaskActive(Task task, boolean isComplete) {
        taskDao.setTaskActive(task, isComplete);
    }
}
