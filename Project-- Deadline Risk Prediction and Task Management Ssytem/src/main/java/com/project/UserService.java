package com.project;

import java.sql.*;

public class UserService {

    public static boolean register(User u) {
        try {
            Connection con = DatabaseConnection.getConnection();

            if (con == null) {
                System.out.println("DB CONNECTION FAILED");
                return false;
            }

            String sql = "INSERT INTO users(username,password) VALUES(?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, u.getUsername().trim());
            ps.setString(2, u.getPassword().trim());

            int rows = ps.executeUpdate();

            System.out.println("REGISTER ROWS INSERTED: " + rows);

            con.close();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean login(String username, String password) {
        try {
            Connection con = DatabaseConnection.getConnection();

            if (con == null) {
                System.out.println("DB CONNECTION FAILED");
                return false;
            }

            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username.trim());
            ps.setString(2, password.trim());

            ResultSet rs = ps.executeQuery();

            boolean result = rs.next();

            System.out.println("LOGIN CHECK RESULT: " + result);

            con.close();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}