/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProjetoMVC.DAO;


import ProjetoMVC.Database.ConnectionDB;
import ProjetoMVC.Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author LEONARDOESLABAOBARBO
 */
public class UserDAO {

    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (nickname, password) VALUES (?, ?)";
        String passwordHash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        try (Connection conn = ConnectionDB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getEmail());
            stmt.setString(2, passwordHash);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean validateLogin(User user) {
        String sql = "SELECT password FROM users WHERE nickname = ?";

        try (Connection conn = ConnectionDB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getEmail());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                return BCrypt.checkpw(user.getPassword(), storedHash);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePassword(String email, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE nickname = ?";
        String passwordHash = BCrypt.hashpw(newPassword, BCrypt.gensalt());

        try (Connection conn = ConnectionDB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, passwordHash);
            stmt.setString(2, email);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

