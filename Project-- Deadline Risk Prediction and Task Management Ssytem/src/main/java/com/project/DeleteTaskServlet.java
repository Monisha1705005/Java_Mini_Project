package com.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/DeleteTaskServlet")
public class DeleteTaskServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Connection con = DatabaseConnection.getConnection();

            String sql = "DELETE FROM tasks WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            System.out.println("TASK DELETED ROWS: " + rows);

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("ViewTasksServlet");
    }
}