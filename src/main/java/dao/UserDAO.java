package dao;

import controllers.ConnectDB;
import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public User getUserInDb(String login) {
        String sql = "SELECT * FROM users Where login = ?";
        User user = null;
        try (Connection con = ConnectDB.getConnection()) {
            PreparedStatement prpQuery = con.prepareStatement(sql);
            prpQuery.setString(1, login);
            ResultSet resultSet = prpQuery.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password_hash"));
                user.setFullName(resultSet.getString("full_name"));
                user.setRole(resultSet.getString("role"));
                user.setFailedAttempts(resultSet.getInt("failed_attempts"));
                user.setIsBlocked(resultSet.getBoolean("is_blocked"));
                user.setMustChangePassword(resultSet.getBoolean("must_change_password"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public boolean addUser(User user)
    {
        String sql = "INSERT INTO users (login, password_hash, full_name, role, is_blocked, failed_attempts, last_login, must_change_password, created_at) VALUES (?, ?, ?, ?, false, 0, null, true, ?)";
        try(Connection connection = ConnectDB.getConnection())
        {
            Timestamp timestamp;
            PreparedStatement prpQuery = connection.prepareStatement(sql);
            prpQuery.setString(1, user.getLogin());
            prpQuery.setString(2, user.getPassword());
            prpQuery.setString(3, user.getFullName());
            prpQuery.setString(4, user.getRole());
            timestamp = Timestamp.valueOf(user.getCreatedAt());
            prpQuery.setTimestamp(5, timestamp);

            return prpQuery.executeUpdate() > 0;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }


    public boolean updateUser(User user) {
        String sql = "UPDATE users SET login = ?, password_hash = ?, full_name = ?, role = ?, " +
                "is_blocked = ?, failed_attempts = ?, must_change_password = ?, last_login = ? WHERE id = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getFullName());
            pstmt.setString(4, user.getRole());
            pstmt.setBoolean(5, user.getIsBlocked());
            pstmt.setInt(6, user.getFailedAttempts());
            pstmt.setBoolean(7, user.isMustChangePassword());

            // ✅ Безопасное преобразование (проверка на null)
            pstmt.setTimestamp(8, user.getLastLogin() != null ?
                    Timestamp.valueOf(user.getLastLogin()) : null);

            pstmt.setLong(9, user.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean blockedUser(User user) {
        String sql = "UPDATE user SET is_blocked = ? where id = ?";
        try (Connection con = ConnectDB.getConnection()) {
            PreparedStatement prpQuery = con.prepareStatement(sql);

            prpQuery.setBoolean(1, user.getIsBlocked());
            prpQuery.setLong(2, user.getId());
            return prpQuery.executeUpdate() > 0;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection con = ConnectDB.getConnection())
        {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                User u = new User();
                Timestamp timestamp;

                u.setId(resultSet.getLong("id"));
                u.setLogin(resultSet.getString("login"));
                u.setPassword(resultSet.getString("password_hash"));
                u.setFullName(resultSet.getString("full_name"));
                u.setRole(resultSet.getString("role"));
                u.setIsBlocked(resultSet.getBoolean("is_blocked"));
                timestamp = resultSet.getTimestamp("last_login");
                if(timestamp != null)
                {
                    u.setLastLogin(timestamp.toLocalDateTime());
                }
                else
                {
                    u.setLastLogin(null);
                }
                u.setMustChangePassword(resultSet.getBoolean("must_change_password"));
                timestamp = resultSet.getTimestamp("created_at");
                if(timestamp != null)
                {
                    u.setCreatedAt(timestamp.toLocalDateTime());
                }
                else
                {
                    u.setCreatedAt(null);
                }
                users.add(u);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return users;
    }

    public User getUserById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getLong("id"));
                u.setLogin(rs.getString("login"));
                u.setPassword(rs.getString("password_hash"));
                u.setFullName(rs.getString("full_name"));
                u.setRole(rs.getString("role"));
                u.setIsBlocked(rs.getBoolean("is_blocked"));
                u.setFailedAttempts(rs.getInt("failed_attempts"));
                u.setMustChangePassword(rs.getBoolean("must_change_password"));
                return u;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
