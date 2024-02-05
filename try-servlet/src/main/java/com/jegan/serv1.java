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

public class serv1 extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        
    	PrintWriter out = res.getWriter();
        
        String given_username = req.getParameter("username");
        String given_password = req.getParameter("password");
        String url = "jdbc:postgresql://localhost:5432/appranix";
        String username = "postgres";
        String password = "postgres";

        try {
            java.sql.Connection connection = DriverManager.getConnection(url, username, password);
            java.sql.Statement statement = connection.createStatement();

            String sql = "SELECT * FROM public.\"user\" where username='"+given_username+"'";
            java.sql.ResultSet resultSet = statement.executeQuery(sql);

            String ret_username = null;
            String ret_password = null;
            int ret_age = 0;
            String ret_nationality = null;
            String ret_gender = null;
            
            while(resultSet.next()){
            ret_username = resultSet.getString(1);
            ret_password = resultSet.getString(2);
            ret_age = resultSet.getInt(3);
            ret_nationality = resultSet.getString(4);
            ret_gender = resultSet.getString(5);
            
            }

            if(ret_password==null) {
            	res.sendRedirect("/try-servlet/404.html");
            }
            else if(ret_password.equals(given_password)) {
            	
            	 out.println("<html>");
                 out.println("<head><title>Response</title></head>");
                 
                 out.println("<body>");
                 out.println("<center>");
                 
                 out.println("<h2>USER DETAILS</h2>");
                 out.println("<table border='|'>");
                 out.println("<tr><td><p>Username: " + ret_username + "</p></td></tr>");
                 out.println("<tr><td><p>Age: " + ret_age + "</p></td></tr>");
                 out.println("<tr><td><p>Nationaltiy: " + ret_nationality + "</p></td></tr>");
                 out.println("<tr><td><p>Gender: " + ret_gender + "</p></td></tr>");
                 
                 out.println("</center>");
                 out.println("</body>");
                 out.println("</html>");
            }
            else {
            	System.out.println("LOGIN FAILED");
            	res.sendRedirect("/try-servlet/index.html");
            }
            
            resultSet.close();
            statement.close();
            connection.close();
        } 
        
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

