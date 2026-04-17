package com.project;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ViewTasksServlet")
public class ViewTasksServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        ArrayList<Task> list = new ArrayList<>();

        try {
            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM tasks");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Task t = new Task();

                t.setId(rs.getInt("id")); // IMPORTANT
                t.setName(rs.getString("name"));
                t.setTotalDays(rs.getInt("totaldays"));
                t.setDaysPassed(rs.getInt("dayspassed"));
                t.setProgress(rs.getInt("progress"));

                list.add(t);
            }

            request.setAttribute("data", list);
            request.getRequestDispatcher("viewTasks.jsp").forward(request, response);

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}