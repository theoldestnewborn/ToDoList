package com.todolist.entities;

public class Lists {
    private int idList;
    private String listName;
    private int tasksQuantity;
    private int fk_id_user;

    public Lists() {
    }

    public int getIdList() {
        return idList;
    }

    public void setIdList(int idList) {
        this.idList = idList;
    }

    public int getFk_id_user() {
        return fk_id_user;
    }
    public void setFk_id_user(int fk_id_user) {
        this.fk_id_user = fk_id_user;
    }

    public int getTasksQuantity() {
        return tasksQuantity;
    }
    public void setTasksQuantity(int tasksQuantity) {
        this.tasksQuantity = tasksQuantity;
    }
    public String getListName() {
        return listName;
    }
    public void setListName(String listName) {
        this.listName = listName;
    }

}
