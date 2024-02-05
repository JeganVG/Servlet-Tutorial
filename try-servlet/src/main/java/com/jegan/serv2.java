package com.jegan;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class serv2 extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    	String given_username = req.getParameter("username");
    	String given_password = req.getParameter("password");
    	int given_age = Integer.parseInt(req.getParameter("age"));
    	String given_nationality = req.getParameter("nationality");
    	String given_gender = req.getParameter("gender");
    	
    	PrintWriter out = res.getWriter();
    	String url = "jdbc:postgresql://localhost:5432/appranix";
        String username = "postgres";
        String password = "postgres";

        try {
            java.sql.Connection connection = DriverManager.getConnection(url, username, password);
            java.sql.Statement statement = connection.createStatement();

            String sql = "INSERT INTO public.\"user\" VALUES ('"+given_username+"','"+given_password+"',"+given_age+",'"+given_nationality+"','"+given_gender+"')";
            statement.executeUpdate(sql);
            out.println("<html>");
            out.println("<head><title>Response</title></head>");
            out.println("<body>");
            out.println("<h1>LOGIN TO CONTINUE</h1>");
            

            statement.close();
            connection.close();
            res.sendRedirect("/try-servlet/index.html");
    }
        catch (SQLException e) {
            e.printStackTrace();

        }
    }
}

