package controllers;

import java.sql.*;

public class Controller {

    public boolean checkAccept(String login, String password)
    {
        return !login.isEmpty() && !password.isEmpty();
    }

    public int getUserRole(String login, String password) {
        String role = "";
        int roleIndex = 0;

        try (Connection con = ConnectDB.ConnectDB()) {
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

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
