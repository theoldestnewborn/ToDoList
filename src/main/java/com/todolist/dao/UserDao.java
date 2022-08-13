package com.todolist.dao;

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
            stmt.execute("insert into users (login, email, password) " +
                    "values ('" + user.getLogin() + "' , '" + user.getEmail() + "' , '" + user.getPassword() + "');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAll() {
        List<User> allUsers = null;
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            while (rs.next()) {
                allUsers = new ArrayList<>();
                User user = new User();
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allUsers;
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

    public boolean findByLogin(User userForm) {
        boolean userExists = false;
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users " +
                    "where login = '" + userForm.getLogin() + "'");
            if (rs.next()) {
                userExists = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userExists;
    }

    public Optional<User> findByLoginAndPassword(User userForm) {
        List<User> filteredUsers = null;
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users " +
                    "where login = '" + userForm.getLogin() + "' " +
                    "and password = '" + userForm.getPassword() + "'");
            while (rs.next()) {
                User userDb = new User();
                userDb.setLogin(rs.getString("login"));
                userDb.setPassword(rs.getString("password"));
                filteredUsers = new ArrayList<>();
                filteredUsers.add(userDb);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filteredUsers.size() > 0 ? Optional.of(filteredUsers.get(0)) : Optional.empty();
    }

    public Integer getIdUser(User user) {
        Integer idUser = null;
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users " +
                    "where login = '" + user.getLogin() + "');");
            rs.next();
            idUser = rs.getInt("id_user");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idUser;
    }

    public User getUserById(int idUser) {
        List<User> filteredUsers;
        User userDb = null;
        try (Connection connection = DriverManager.getConnection
                (DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD)) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users " +
                    "where id_user = " + idUser + ";");
            rs.next();
            userDb = new User();
            userDb.setLogin(rs.getString("login"));
            userDb.setEmail(rs.getString("email"));
            userDb.setId(rs.getInt("id_user"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userDb;
    }
}
