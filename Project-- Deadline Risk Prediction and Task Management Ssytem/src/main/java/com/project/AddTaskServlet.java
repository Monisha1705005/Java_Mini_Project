package com.project;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Task t = new Task();

        int totalDays = Integer.parseInt(request.getParameter("totalDays"));
        int daysPassed = Integer.parseInt(request.getParameter("daysPassed"));

        // ✅ VALIDATION
        if (daysPassed > totalDays) {
            daysPassed = totalDays;
        }

        t.setName(request.getParameter("name"));
        t.setTotalDays(totalDays);
        t.setDaysPassed(daysPassed);

        TaskService.addTask(t);

        response.sendRedirect("dashboard.html");
    }
}