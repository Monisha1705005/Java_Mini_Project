package com.project;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TaskService {

    public static void addTask(Task t) {

        try {
            Connection con = DatabaseConnection.getConnection();

            int progress = (t.getDaysPassed() * 100) / t.getTotalDays();

            String sql = "INSERT INTO tasks(name, totaldays, dayspassed, progress) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, t.getName());
            ps.setInt(2, t.getTotalDays());
            ps.setInt(3, t.getDaysPassed());
            ps.setInt(4, progress);

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}