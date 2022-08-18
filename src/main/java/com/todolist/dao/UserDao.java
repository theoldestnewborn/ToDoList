package com.todolist.dao;

import com.todolist.entities.User;
import com.todolist.service.UserService;

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
            String hashedPassword = new UserService().passwordHasher(user);
            stmt.execute("insert into users (login, email, password) " +
                    "values ('" + user.getLogin() + "' , '" + user.getEmail() + "' , '" + hashedPassword + "');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public User getUserByLogin(User userForm) {
        User user = null;
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users " +
                    "where login = '" + userForm.getLogin() + "'");
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id_user"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public boolean findByLoginOrEmail(User userForm) {
        boolean userExists = false;
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users " +
                    "where login = '" + userForm.getLogin() + "'");
            if (rs.next()) {
                userExists = true;
            }
            Statement stmt1 = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery("select * from users " +
                    "where email = '" + userForm.getEmail() + "'");
            if (rs1.next()) {
                userExists = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userExists;
    }

    public Optional<User> findByLoginAndPassword(User userForm) {
        List<User> filteredUsers = new ArrayList<>();
        String hashedPasswordToCheck = new UserService().passwordHasher(userForm);
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users " +
                    "where login = '" + userForm.getLogin() + "' " +
                    "and password = '" + hashedPasswordToCheck + "'");
            while (rs.next()) {
                User userDb = new User();
                userDb.setLogin(rs.getString("login"));
                filteredUsers = new ArrayList<>();
                filteredUsers.add(userDb);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filteredUsers.size() > 0 ? Optional.of(filteredUsers.get(0)) : Optional.empty();
    }

}
