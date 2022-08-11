package com.todolist.dao;

import com.todolist.entities.Lists;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListDao {

    public ListDao() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addList(String listName) {
        try (Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into lists (list_name) " +
                    "values ('" + listName.trim() + "');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteList(String listName) {
        try (Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("delete from lists " +
                    "where list_name = ('" + listName + "');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        try (Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("truncate table lists, tasks");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Lists getOneList(String listName) {
        Lists list = null;
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from lists " +
                    "where list_name = '" + listName + "'");
            rs.next();
                list = new Lists();
                list.setListName(rs.getString("list_name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Lists> getAllLists() {
        List<Lists> allLists = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from lists");
            while (rs.next()) {
                Lists lists = new Lists();
                String listName = rs.getString("list_name");
                lists.setListName(listName);
                Statement stmt1 = connection.createStatement();
                ResultSet rs1 = stmt1.executeQuery("SELECT COUNT(fk_list_name) as quantity " +
                        "FROM tasks " +
                        "WHERE fk_list_name = ('" + listName + "');");
                rs1.next();
                lists.setTasksQuantity(rs1.getInt("quantity"));
                allLists.add(lists);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allLists;
    }

    public List<String> getAllListsNames() {
        List<String> allLists = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from lists");
            while (rs.next()) {
                allLists.add(rs.getString("list_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allLists;
    }

}
