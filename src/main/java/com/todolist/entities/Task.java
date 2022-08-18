package com.todolist.entities;

public class Task {
    private String taskBody;
    private boolean isComplete;
    private boolean isActive;
    private int idList, id;

    public Task() {
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
