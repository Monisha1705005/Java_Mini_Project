package com.project;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            System.out.println("REGISTER HIT: " + username);

            User u = new User();
            u.setUsername(username);
            u.setPassword(password);

            boolean status = UserService.register(u);

            System.out.println("REGISTER STATUS: " + status);

            if (status) {
                response.sendRedirect("login.html");
            } else {
                response.sendRedirect("register.html");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Registration Error: " + e.getMessage());
        }
    }
}