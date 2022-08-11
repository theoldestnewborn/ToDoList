package com.todolist.dao;

import com.todolist.entities.Lists;
import com.todolist.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {

    public UserDao() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerUser(User user) {
        try (Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            stmt.execute("insert into users (user_name, email, password) " +
                    "values ('" + user.getUserName() + "' , '" + user.getEmail() + "' , '" + user.getPassword() + "');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAll () {
        List <User> allUsers = null;
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            while (rs.next()) {
                allUsers = new ArrayList<>();
                User user = new User();
                user.setUserName(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allUsers;
    }

    public Optional <User> findByEmailAndPassword (User userForm) {
        List <User> filteredUsers;
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users " +
                    "where user_name = '" + userForm.getUserName() + "' " +
                    "and password = '" + userForm.getPassword() + "'");
            rs.next();
            User userDb = new User();
            userDb.setUserName(rs.getString("user_name"));
            userDb.setPassword(rs.getString("password"));
            filteredUsers = new ArrayList<>();
                    filteredUsers.add(userDb);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filteredUsers.size() > 0 ? Optional.of(filteredUsers.get(0)): Optional.empty();
    }

}
