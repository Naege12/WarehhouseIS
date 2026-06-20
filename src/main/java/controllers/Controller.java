package controllers;

import model.User;

import java.sql.*;

public class Controller {

    public boolean checkAccept(String login, String password)
    {
        return !login.isEmpty() && !password.isEmpty();
    }

    public int getUserRole(String login, String password) {
        String role = "";
        int roleIndex = 0;

        try (Connection con = ConnectDB.getConnection()) {
            String sql = "SELECT role FROM users WHERE login = ? AND password_hash = ?";
            PreparedStatement prpQuery = con.prepareStatement(sql);
            prpQuery.setString(1, login);
            prpQuery.setString(2, password);
            ResultSet result = prpQuery.executeQuery();

            if (result.next()) {
                role = result.getString("role");
                System.out.println("Найденная роль: " + role);

                if (role.equals("admin")) {
                    roleIndex = 1;
                } else if (role.equals("user")) {
                    roleIndex = 2;
                } else {
                    roleIndex = 0;
                }
            } else {
                System.out.println("Пользователь не найден: " + login);
                return 0;
            }

            return roleIndex;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public User getUserInDb(String login, String password)
    {
        String sql = "SELECT * FROM users Where login = ? AND password_hash = ?";
        User user = null;
        try(Connection con = ConnectDB.getConnection())
        {
            PreparedStatement prpQuery = con.prepareStatement(sql);
            prpQuery.setString(1, login);
            prpQuery.setString(2, password);
            ResultSet resultSet = prpQuery.executeQuery();

            if (resultSet.next())
            {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password_hash"));
                user.setFullName(resultSet.getString("full_name"));
                user.setRole(resultSet.getString("role"));
                user.setIsBlocked(resultSet.getBoolean("is_blocked"));
                user.setMustChangePassword(resultSet.getBoolean("must_change_password"));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return user;
    }
}
