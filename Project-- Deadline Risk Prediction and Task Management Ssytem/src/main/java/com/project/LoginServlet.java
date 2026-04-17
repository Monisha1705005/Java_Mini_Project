package com.project;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Debug input values
        System.out.println("INPUT USER: [" + username + "]");
        System.out.println("INPUT PASS: [" + password + "]");

        // Check login
        boolean status = UserService.login(username, password);

        // THIS IS YOUR REQUESTED LINE (DEBUG OUTPUT)
        System.out.println("LOGIN RESULT: " + status);

        // Redirect logic
        if (status) {

            HttpSession session = request.getSession();
            session.setAttribute("user", username);

            System.out.println("LOGIN SUCCESS → DASHBOARD");

            response.sendRedirect("dashboard.html");

        } else {

            System.out.println("LOGIN FAILED → BACK TO LOGIN");

            response.sendRedirect("login.html");
        }
    }
}