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

    public List<Task> getAllTasksOfList(Lists list) {
        List<Task> allTasks = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tasks " +
                    "where fk_id_list = ('" + list.getIdList() + "');");
            while (rs.next()) {
                Task task = new Task();
                task.setTaskBody(rs.getString("task_body"));
                task.setActive(rs.getBoolean("active"));
                task.setComplete(rs.getBoolean("status"));
                allTasks.add(task);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allTasks;
    }

    public boolean taskExists(Lists list, Task task) {
        int idList = list.getIdList();
        String taskBody = task.getTaskBody();
        boolean taskExists = false;
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tasks " +
                    "where fk_id_list = '" + idList + "' " +
                    "and task_body = '" + taskBody + "';");
            if (rs.next()) {
                taskExists = true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return taskExists;
    }

    public Task getTaskByBody(Lists list, Task taskByBody) {
        Task task = null;
        int idList = list.getIdList();
        String taskBody = taskByBody.getTaskBody();
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tasks " +
                    "where fk_id_list = '" + idList + "' " +
                    "and task_body = '" + taskBody + "';");
            while (rs.next()) {
                task = new Task();
                task.setId(rs.getInt("id_task"));
                task.setIdList(rs.getInt("fk_id_list"));
                task.setTaskBody(rs.getString("task_body"));
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

    public void setTaskActive(Task task, boolean isActive) {
        try (Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            int idList = task.getIdList();
            String taskBody = task.getTaskBody();
            if (isActive) {
                stmt.executeUpdate("update tasks set active = false; " +
                        "update tasks set active = true " +
                        "where fk_id_list = '" + idList + "' and task_body = '" + taskBody + "'; " +
                        "update tasks set status = false " +
                        "where fk_id_list = '" + idList + "' and task_body = '" + taskBody + "';");
            } else {
                stmt.executeUpdate("update tasks set active = false; ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editTask(Task oldTask, Task newTask) {
        try (Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            int idList = oldTask.getIdList();
            String taskBodyNew = newTask.getTaskBody();
            String taskBodyOld = oldTask.getTaskBody();
            stmt.executeUpdate("update tasks set task_body = '" + taskBodyNew + "', status = false, active = false " +
                    "where fk_id_list = '" + idList + "' and task_body = '" + taskBodyOld + "';");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTaskComplete(Task task, boolean isComplete) {
        try (Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            int idList = task.getIdList();
            String taskBody = task.getTaskBody();
            if (isComplete == true) {
                stmt.executeUpdate("update tasks set status = " + isComplete + " " +
                        "where fk_id_list = '" + idList + "' and task_body = '" + taskBody + "'; " +
                        "update tasks set active = false " +
                        "where fk_id_list = '" + idList + "' and task_body = '" + taskBody + "';");
            } else {
                stmt.executeUpdate("update tasks set status = " + isComplete + " " +
                        "where fk_id_list = '" + idList + "' and task_body = '" + taskBody + "';");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTask(Lists list, Task task) {
        try (Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            String taskBody = task.getTaskBody().trim();
            int idList = list.getIdList();
            stmt.executeUpdate("insert into tasks (fk_id_list, task_body) " +
                    "values ('" + idList + "','" + taskBody + "' );");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTask(Task task) {
        int idList = task.getIdList();
        String taskBody = task.getTaskBody();
        try (Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("delete from tasks " +
                    "where fk_id_list = ('" + idList + "') " +
                    "and task_body = ('" + taskBody + "');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
