package dao;

import model.Movement;
import controllers.ConnectDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovementDAO {


    public List<Object[]> getRecentMovements(int limit) {
        List<Object[]> movements = new ArrayList<>();
        String sql = """
            SELECT 
                TO_CHAR(m.doc_date, 'DD.MM HH24:MI') as date_time,
                m.type,
                p.name as product_name,
                CASE WHEN m.type = 'IN' THEN '+' || m.quantity 
                     ELSE '-' || m.quantity END as quantity,
                u.full_name as user_name
            FROM movements m
            JOIN products p ON p.id = m.product_id
            JOIN users u ON u.id = m.user_id
            ORDER BY m.doc_date DESC, m.created_at DESC
            LIMIT ?
        """;

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, limit);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                movements.add(new Object[]{
                        rs.getString("date_time"),
                        rs.getString("type"),
                        rs.getString("product_name"),
                        rs.getString("quantity"),
                        rs.getString("user_name")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movements;
    }

    
    public boolean saveMovement(Movement movement) {
        String sql = """
            INSERT INTO movements 
            (type, doc_number, doc_date, product_id, warehouse_id, user_id, 
             quantity, price, from_cell, to_cell, counterparty, comment)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, movement.getType().name());
            pstmt.setString(2, movement.getDocNumber());
            pstmt.setDate(3, Date.valueOf(movement.getDocDate()));
            pstmt.setLong(4, movement.getProductId());
            pstmt.setLong(5, movement.getWarehouseId());
            pstmt.setLong(6, movement.getUserId());
            pstmt.setBigDecimal(7, movement.getQuantity());
            pstmt.setBigDecimal(8, movement.getPrice());
            pstmt.setString(9, movement.getFromCell());
            pstmt.setString(10, movement.getToCell());
            pstmt.setString(11, movement.getCounterparty());
            pstmt.setString(12, movement.getComment());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}