package com.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/UpdateTaskServlet")
public class UpdateTaskServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        int totalDays = Integer.parseInt(request.getParameter("totalDays"));
        int daysPassed = Integer.parseInt(request.getParameter("daysPassed"));

        // ✅ VALIDATION
        if (daysPassed > totalDays) {
            daysPassed = totalDays;
        }

        try {
            Connection con = DatabaseConnection.getConnection();

            int progress = (daysPassed * 100) / totalDays;

            String sql = "UPDATE tasks SET totaldays=?, dayspassed=?, progress=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, totalDays);
            ps.setInt(2, daysPassed);
            ps.setInt(3, progress);
            ps.setInt(4, id);

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("ViewTasksServlet");
    }
}