package com.todolist.dao;

import com.todolist.entities.Lists;
import com.todolist.entities.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDao {

    public TaskDao() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Task> getAllTasks() {
        List<Task> allTasks = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tasks;");
            while (rs.next()) {
                Task task = new Task();
                task.setTask(rs.getString("task_body"));
                task.setActive(rs.getBoolean("active"));
                task.setComplete(rs.getBoolean("status"));
                task.setListName(rs.getString("fk_list_name"));
                allTasks.add(task);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allTasks;
    }

    public List<Task> getAllTasksOfList(String listName) {
        List<Task> allTasks = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tasks " +
                    "where fk_list_name = ('" + listName + "');");
            while (rs.next()) {
                Task task = new Task();
                task.setTask(rs.getString("task_body"));
                task.setActive(rs.getBoolean("active"));
                task.setComplete(rs.getBoolean("status"));
                allTasks.add(task);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allTasks;
    }

    public boolean ifTaskExists(Task task) {
        String listName = task.getListName();
        String task_body = task.getTask_body();
        boolean ifTaskExists = false;
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tasks " +
                    "where fk_list_name = '" + listName + "' " +
                    "and task_body = '" + task_body + "';");
            if (rs.next()) {
                ifTaskExists = true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ifTaskExists;
    }

    public Task getOneTask(String listName, String task_body) {
        Task task = null;
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tasks " +
                    "where fk_list_name = '" + listName + "' " +
                    "and task_body = '" + task_body + "';");
            while (rs.next()) {
                task = new Task();
                task.setListName(rs.getString("fk_list_name"));
                task.setTask(rs.getString("task_body"));
                task.setActive(rs.getBoolean("active"));
                task.setComplete(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return task;
    }

    public boolean hasActiveTask() {
        boolean hasActive = false;
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tasks " +
                    "where active = true");
            if (rs.next()) {
                hasActive = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hasActive;
    }

    public void setActiveTask(Task task, boolean isActive) {
        try (Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            String listName = task.getListName();
            String task_body = task.getTask_body();
            if (isActive) {
                stmt.executeUpdate("update tasks set active = false; " +
                        "update tasks set active = true " +
                        "where fk_list_name = '" + listName + "' and task_body = '" + task_body + "'; " +
                        "update tasks set status = false " +
                        "where fk_list_name = '" + listName + "' and task_body = '" + task_body + "';");
            } else {
                stmt.executeUpdate("update tasks set active = false; ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Task getActiveTask() {
        Task task = null;
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tasks " +
                    "where active = true");
            while (rs.next()) {
                task = new Task();
                task.setListName(rs.getString("fk_list_name"));
                task.setTask(rs.getString("task_body"));
                task.setActive(rs.getBoolean("active"));
                task.setComplete(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return task;
    }

    public void editTask(Task oldTask, Task newTask) {
        try (Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            String listName = oldTask.getListName();
            String task_body_new = newTask.getTask_body();
            String task_body_old = oldTask.getTask_body();
            stmt.executeUpdate("update tasks set task_body = '" + task_body_new + "', status = false, active = false " +
                    "where fk_list_name = '" + listName + "' and task_body = '" + task_body_old + "';");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTaskComplete(Task task, boolean isComplete) {
        try (Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            String listName = task.getListName();
            String task_body = task.getTask_body();
            if (isComplete == true) {
                stmt.executeUpdate("update tasks set status = " + isComplete + " " +
                        "where fk_list_name = '" + listName + "' and task_body = '" + task_body + "'; " +
                        "update tasks set active = false " +
                        "where fk_list_name = '" + listName + "' and task_body = '" + task_body + "';");
            } else {
                stmt.executeUpdate("update tasks set status = " + isComplete + " " +
                        "where fk_list_name = '" + listName + "' and task_body = '" + task_body + "';");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTask(Task task) {
        try (Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            String listName = task.getListName();
            String task_body = task.getTask_body();
            stmt.executeUpdate("insert into tasks (fk_list_name, task_body) " +
                    "values ('" + listName + "','" + task_body.trim() + "' );");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTask(Task task) {
        String listName = task.getListName();
        String task_body = task.getTask_body();
        try (Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("delete from tasks " +
                    "where fk_list_name = ('" + listName + "') " +
                    "and task_body = ('" + task_body + "');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
