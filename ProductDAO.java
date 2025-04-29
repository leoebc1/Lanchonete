package com.example.lancheTDS.dao;

import com.example.lancheTDS.database.ConnectionDB;
import com.example.lancheTDS.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {

    public boolean addProduct(Product product) {
        String sql = "INSERT INTO produtos (nome, preco, descricao) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionDB.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getDescription());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Product getProdById(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        Product product = null;

        try (Connection conn = ConnectionDB.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                product = new Product(
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getString("descricao")
                );
                product.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    public boolean updateProduct(Product product) {
        String sql = "UPDATE produtos SET nome = ?, preco = ?, descricao = ? WHERE id = ?";

        try (Connection conn = ConnectionDB.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getDescription());
            stmt.setInt(4, product.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (Connection conn = ConnectionDB.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
 
