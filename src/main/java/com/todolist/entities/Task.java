package com.todolist.entities;

import com.todolist.dao.TaskDao;

public class Task {
    private String task_body, listName;
    private boolean isComplete;
    private boolean isActive;

    public Task() {
    }

    public Task(String listName, String task) {
        this.listName = listName;
        this.task_body = task;
        this.isComplete = false;
        this.isActive = false;
    }

    public void setTask_body(String task_body) {
        this.task_body = task_body;
    }

    public String getTask_body() {
        return task_body;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public void setTask(String task) {
        this.task_body = task;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
}
