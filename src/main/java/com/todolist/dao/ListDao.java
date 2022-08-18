package com.todolist.dao;

import com.todolist.entities.Lists;
import com.todolist.entities.User;

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

    public void addList(String listName, User user) {
        try (Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into lists (list_name, fk_id_user) " +
                    "values ('" + listName.trim() + "'," + user.getId() + ");");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteList(Lists list, User user) {
        try (Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("delete from lists " +
                    "where list_name = ('" + list.getListName() + "') " +
                    "and fk_id_user = " + user.getId() + ";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Lists getListById(int idList) {
        Lists list = null;
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from lists " +
                    "where id_list = ('" + idList + "');");
            rs.next();
            list = new Lists();
            list.setIdList(rs.getInt("id_list"));
            list.setListName(rs.getString("list_name"));
            list.setFk_id_user(rs.getInt("fk_id_user"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Lists getListByName(String listName, User user) {
        Lists list = null;
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from lists " +
                    "where list_name = ('" + listName + "') " +
                    "and fk_id_user = ('" + user.getId() + "');");
            rs.next();
            list = new Lists();
            list.setIdList(rs.getInt("id_list"));
            list.setListName(rs.getString("list_name"));
            list.setFk_id_user(rs.getInt("fk_id_user"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Lists> getAllLists(User user) {
        List<Lists> allLists = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from lists " +
                    "where fk_id_user = " + user.getId() + ";");
            while (rs.next()) {
                Lists list = new Lists();
                int idList = rs.getInt("id_list");
                String listName = rs.getString("list_name");
                list.setIdList(idList);
                list.setListName(listName);
                Statement stmt1 = connection.createStatement();
                ResultSet rs1 = stmt1.executeQuery("SELECT COUNT(fk_id_list) as quantity " +
                        "FROM tasks " +
                        "WHERE fk_id_list = ('" + idList + "');");
                while (rs1.next()) {
                    list.setTasksQuantity(rs1.getInt("quantity"));
                }
                allLists.add(list);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allLists;
    }

    public List<String> getAllListsNames(User user) {
        List<String> allLists = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from lists " +
                    "where fk_id_user = " + user.getId() + ";");
            while (rs.next()) {
                allLists.add(rs.getString("list_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allLists;
    }

}
