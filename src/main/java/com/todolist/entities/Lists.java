package com.todolist.entities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Lists {
    private int idList;
    private String listName;
    private int tasksQuantity;
    private int fk_id_user;

    public Lists() {
    }

    public Lists(String listName) {
        this.listName = listName;
    }

    public int getFk_id_user() {
        return fk_id_user;
    }

    public Lists(String listName, int tasksQuantity, int fk_id_user) {
        this.listName = listName;
        this.tasksQuantity = tasksQuantity;
        this.fk_id_user = fk_id_user;
    }

    public Lists(int idList, String listName, int tasksQuantity, int fk_id_user) {
        this.idList = idList;
        this.listName = listName;
        this.tasksQuantity = tasksQuantity;
        this.fk_id_user = fk_id_user;
    }

    public int getIdList() {
        return idList;
    }

    public void setIdList(int idList) {
        this.idList = idList;
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

    public Map<String, List<Task>> createList (String listName) {
        Map<String, List<Task>> list = new LinkedHashMap<>();
        List<Task> tasks = new ArrayList<>();
        list.put(listName, tasks);
        return list;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

}
