package dao;

import model.Warehouse;
import controllers.ConnectDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDAO {


    public List<Warehouse> getAllWarehouses() {
        List<Warehouse> warehouses = new ArrayList<>();
        String sql = "SELECT id, name, address FROM warehouses WHERE is_active = true";

        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Warehouse w = new Warehouse();
                w.setId(rs.getLong("id"));
                w.setName(rs.getString("name"));
                w.setAddress(rs.getString("address"));
                warehouses.add(w);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return warehouses;
    }


    public Warehouse getWarehouseById(Long id) {
        String sql = "SELECT id, name, address FROM warehouses WHERE id = ? AND is_active = true";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Warehouse w = new Warehouse();
                w.setId(rs.getLong("id"));
                w.setName(rs.getString("name"));
                w.setAddress(rs.getString("address"));
                return w;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean addWarehouse(Warehouse warehouse) {
        String sql = "INSERT INTO warehouses (name, address, is_active) VALUES (?, ?, true)";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, warehouse.getName());
            pstmt.setString(2, warehouse.getAddress());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateWarehouse(Warehouse warehouse) {
        String sql = "UPDATE warehouses SET name = ?, address = ? WHERE id = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, warehouse.getName());
            pstmt.setString(2, warehouse.getAddress());
            pstmt.setLong(3, warehouse.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteWarehouse(Long id) {
        String sql = "UPDATE warehouses SET is_active = false WHERE id = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ============================================================
    // 6. ПРОВЕРКА СУЩЕСТВОВАНИЯ СКЛАДА ПО НАЗВАНИЮ
    // ============================================================
    public boolean warehouseExists(String name) {
        String sql = "SELECT COUNT(*) FROM warehouses WHERE name = ? AND is_active = true";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ============================================================
    // 7. ПОЛУЧИТЬ ВСЕ СКЛАДЫ (ВКЛЮЧАЯ НЕАКТИВНЫЕ)
    // ============================================================
    public List<Warehouse> getAllWarehousesIncludingInactive() {
        List<Warehouse> warehouses = new ArrayList<>();
        String sql = "SELECT id, name, address FROM warehouses";

        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Warehouse w = new Warehouse();
                w.setId(rs.getLong("id"));
                w.setName(rs.getString("name"));
                w.setAddress(rs.getString("address"));
                warehouses.add(w);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return warehouses;
    }
}