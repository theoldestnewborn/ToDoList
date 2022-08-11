package com.todolist.entities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Lists {
    private String listName;
    private int tasksQuantity;

    public Lists() {
    }

    public Lists(String listName) {
        this.listName = listName;
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
