package dao;

import model.Product;
import controllers.ConnectDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class ProductDAO {

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, sku, name, category, purchase_price, selling_price, min_stock, is_active FROM products";

        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getLong("id"));
                p.setArticle(rs.getString("sku"));
                p.setName(rs.getString("name"));
                p.setCategory(rs.getString("category"));
                p.setPurchasePrice(rs.getBigDecimal("purchase_price"));
                p.setSellingPrice(rs.getBigDecimal("selling_price"));
                p.setMinStock(rs.getBigDecimal("min_stock"));
                p.setIsActive(rs.getBoolean("is_active"));
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }


    public Product getProductById(Long id) {
        String sql = "SELECT id, sku, name, category, purchase_price, selling_price, min_stock FROM products WHERE id = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Product p = new Product();
                p.setId(rs.getLong("id"));
                p.setArticle(rs.getString("sku"));
                p.setName(rs.getString("name"));
                p.setCategory(rs.getString("category"));
                p.setPurchasePrice(rs.getBigDecimal("purchase_price"));
                p.setSellingPrice(rs.getBigDecimal("selling_price"));
                p.setMinStock(rs.getBigDecimal("min_stock"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Object[]> getLowStockProducts(Long warehouseId) {
        List<Object[]> items = new ArrayList<>();
        String sql = """
            SELECT p.sku, p.name, 
                   COALESCE(SUM(CASE WHEN m.type = 'IN' THEN m.quantity ELSE 0 END), 0) -
                   COALESCE(SUM(CASE WHEN m.type = 'OUT' THEN m.quantity ELSE 0 END), 0) as stock,
                   p.min_stock
            FROM products p
            LEFT JOIN movements m ON m.product_id = p.id AND m.warehouse_id = ?
            WHERE p.is_active = true
            GROUP BY p.id, p.sku, p.name, p.min_stock
            HAVING COALESCE(SUM(CASE WHEN m.type = 'IN' THEN m.quantity ELSE 0 END), 0) -
                   COALESCE(SUM(CASE WHEN m.type = 'OUT' THEN m.quantity ELSE 0 END), 0) < p.min_stock
        """;

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, warehouseId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                items.add(new Object[]{
                        rs.getString("sku"),
                        rs.getString("name"),
                        rs.getBigDecimal("stock"),
                        rs.getBigDecimal("min_stock")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public boolean addProduct(Product product) {
        String sql = "INSERT INTO products (sku, name, category, purchase_price, selling_price, min_stock, is_active) VALUES (?, ?, ?, ?, ?, ?, true)";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getArticle());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getCategory());
            pstmt.setBigDecimal(4, product.getPurchasePrice());
            pstmt.setBigDecimal(5, product.getSellingPrice());
            pstmt.setBigDecimal(6, product.getMinStock());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProduct(Product product) {
        String sql = "UPDATE products SET sku = ?, name = ?, category = ?, purchase_price = ?, selling_price = ?, min_stock = ? WHERE id = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getArticle());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getCategory());
            pstmt.setBigDecimal(4, product.getPurchasePrice());
            pstmt.setBigDecimal(5, product.getSellingPrice());
            pstmt.setBigDecimal(6, product.getMinStock());
            pstmt.setLong(7, product.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteProduct(Long id) {
        String sql = "UPDATE products SET is_active = false WHERE id = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}