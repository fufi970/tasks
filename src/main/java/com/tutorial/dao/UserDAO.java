package com.tutorial.dao;

import com.tutorial.model.User;
import com.tutorial.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

public User authenticate(String username, String password) {
    String sql = "SELECT id, username, password FROM users WHERE username = ? AND password = ?";
    try (Connection conn = DBUtil.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setString(1, username);
        ps.setString(2, password);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password")
                );
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
    }
}