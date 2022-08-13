package com.todolist.entities;

import com.todolist.dao.TaskDao;

public class Task {
    private String taskBody;
    private boolean isComplete;
    private boolean isActive;
    private int idList, id;

    public Task() {
    }

    public Task(Lists list, String task) {
        this.idList = list.getIdList();
        this.taskBody = task;
        this.isComplete = false;
        this.isActive = false;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getIdList() {
        return idList;
    }

    public void setIdList(int idList) {
        this.idList = idList;
    }

    public void setTaskBody(String taskBody) {
        this.taskBody = taskBody;
    }

    public String getTaskBody() {
        return taskBody;
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
