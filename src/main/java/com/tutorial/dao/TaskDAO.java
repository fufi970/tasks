
package com.tutorial.dao;

import com.tutorial.model.Task;
import com.tutorial.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

public List<Task> findByUserId(int userId) {
    List<Task> list = new ArrayList<>();
    String sql = "SELECT id, title, completed, user_id FROM tasks WHERE user_id = ? ORDER BY id DESC";

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, userId);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Task(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getBoolean("completed"),
                    rs.getInt("user_id")
                ));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

public void insert(String title, int userId, boolean completed) {
    String sql = "INSERT INTO tasks (title, completed, user_id) VALUES (?, ?, ?)";
    try (Connection conn = DBUtil.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setString(1, title);
        ps.setBoolean(2, completed);
        ps.setInt(3, userId);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}